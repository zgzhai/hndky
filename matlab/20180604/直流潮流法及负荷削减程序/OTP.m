function OTP(Bus,Load,Generator,Line,Y,P)
   global  Bus Load Generator Line Y P delta PIJ; %此处多定义了delta和PIJ
[nl,ml]=size(Line);
[ng,mg]=size(Generator);
[nbb,mbb]=size(Bus);      
%% 此部分与output.m中相角输出程序相同,只是修改了nbb，目的是得到fValue矩阵
for i = 1:nbb-1
    fValue(i)=delta(i);
    while(fValue(i)*180/pi<=-180)
        fValue(i)=fValue(i)+2*pi;
    end
    while(fValue(i)*180/pi>=180)
        fValue(i)=fValue(i)-2*pi;
    end
    fValue(i)=fValue(i)*180/pi;
end
%% 电网风险输出作为负荷削减程序的启动判断的判断条件，此部分程序与Output.m中电网风险输出部分相同
xiangjiaocha=zeros(nl,1);  %计算相角差余弦值
for i=1:nl
    I=Line(i,1);
    J=Line(i,2);
       if Bus(I,3)==3
       fValue(I)=0;
    elseif Bus(J,3)==3
        fValue(J)=0;
    end
       xiangjiaocha(i,1)=cos(fValue(I)-fValue(J));
end
zhilushiji=zeros(nl,1);
rongliang=zeros(nl,1);
zhilubiaoshi=zeros(nl,1);
for i=1:nl
    rongliang(i,1)=Line(i,5);
    zhilubiaoshi(i,1)=Line(i,3);
end
panduantiaojian=0;  %判断是否进行负荷削减计算
for m=1:nl
    I=Line(m,1);
    J=Line(m,2);
    if zhilubiaoshi(m,1)==1     %线路
        zhilushiji(m,1)=PIJ(m)/(3^0.5*xiangjiaocha(m,1)); %线路
    else
           zhilushiji(m,1)=PIJ(m);    %变压器支路
    end
    zhilushiji(m,1)=abs(zhilushiji(m,1));
    rongliang(m,1)=zhilushiji(m,1)/rongliang(m,1);
    rongliang(m,1)=100*rongliang(m,1);
    if rongliang(m,1)>100            %   修改了判定条件
    panduantiaojian=1;
    end
end
%% 判断是否进行负荷削减的条件
if panduantiaojian==1
   %%   以下是原本OPT中程序
 B=Y; 
 [nb,mb]=size(B);
 [nd,md]=size(Load);
 B=inv(B);
 B1=zeros(nb,1);
 B2=zeros(1,mb+1);
 B=[B B1];
 B=[B;B2];
 T=zeros(nl,mb+1);%(1 2 )1条线路，2各节点
  for i=1:nl
     for j=1:mb+1
         T(i,j)=(B(Line(i,1),j)-B(Line(i,2),j))/Line(i,4);
     end
  end
  p=zeros(nbb,1);
for i=1:nbb
    ID=Bus(i,1);
        for j=1:nd
           if Load(j,1)==ID
              p(ID)=p(ID)-Load(j,2);
           end
        end
           for k=1:ng
           if Generator(k,1)==ID
              p(ID)=p(ID)+Generator(k,2);
           end
       end
end
 pl=zeros(nbb,1);
 for i=1:nbb
     pl(i)=Load(i,2);
 end
   padd=-sum(sum(p));
   p=T*p;
[nt,mt]=size(T);
pmax=zeros(nl,1);
pmin=zeros(nl,1);
for i=1:nl
    pmax(i,1)=Line(i,5)-p(i);
    pmin(i,1)=-Line(i,5)-p(i);
end
c=zeros(nbb+2*nt+nbb,1);%等式约束加入
A=zeros(2*nt+nbb,nbb+2*nt+nbb);
b=zeros(2*nt+1+nbb,1);
for i=1:nbb
    c(i,1)=1;
end
for i=1:nt
    for j=1:mt
    A(i,j)=-T(i,j);
    end
 A(i,mt+i)=1;
end
for i=nt+1:nt+nt
    for j=1:mt
    A(i,j)=T(i-nt,j);
    end
 A(i,mt+i)=1;
end
j=1;
for i=2*nt+1:2*nt+nbb
    A(i,j)=1;
    j=j+1;
    A(i,mt+i)=1;
end
%等式约束或不等式约束修改点
for i=1:nbb
     A(2*nt+1+nbb,i)=-1;
end  
b=[-pmin;pmax;pl;-padd];
b=b';
c=c';

%单纯形法 开始计算
[m,n]=size(A);
E=1:m;E=E';              
F=n-m+1:n;F=F';
D=[E,F];             %创建一个一一映射，为了结果能够标准输出
X=zeros(1,n);        %初始化X
if(n<m)              %判断是否为标准型    
%fprintf('不符合要求需引入松弛变量')

    flag=0;
else
    flag=1;
    B=A(:,n-m+1:n);   %找基矩阵
    cB=c(n-m+1:n);    %基矩阵对应目标值的c
   while flag 
     w=cB/B;         %计算单纯形乘子，cB/B=cB*inv(B),用cB/B的目的是，为了提高运行速度。。
     panbieshu=w*A-c;  %计算判别数。
     [z,k]=max(panbieshu);  % k作为进基变量下标  。。 
  
     if(z<0.000000001)     
         flag=0;             %所有判别数都小于0时达到最优解。。
         
        %% fprintf('    已找到最优解!\n');    此处删除    
         
         xB=(B\b')';
         f=cB*xB';   
         for i=1:n
           mark=0;
             for j=1:m
                if (D(j,2)==i)
                 mark=1;
                 X(i)=xB(D(j,1));     %利用D找出xB与X之间的关系。。
                end
             end
             if mark==0
               X(i)=0;         %如果D中没有X(i),则X(i)为非基变量，所以X(i)＝0。。
             end
         end
         %% 输出结果       
          %fprintf('基向量为:'); X
         if f<=0
             fprintf('此种状态下不需要削减负荷，请加强重载线路及重载变压器的运行监控 \n') ; 
         elseif f>0
             fprintf('此种状态下可以进行负荷转移以优化电网静态稳定性，重载线路受端及重载变压器最少需要转移的负荷量总量约为:%d  p.u.，请根据主变负荷能力评估结果进行优化 \n',f) ;  
         end
          
     else
         if(B\A(:,k)<=0)          % 如果B\A(;,k)中的每一个分量都小于零。。
               flag=0;
               fprintf('     \n 此状态下不存在优化最优解!\n');  %若B\A(:,k)的第k列均不大于0，则该问题不存在最优解。。
               fprintf('     \n 请加强重载线路及重载变压器的运行监控，过载设备请根据变压器负荷能力评估结果进行负荷转移！\n');  
         else
               b1=B\b';
               temp=inf;
               for i=1:m
                   if ((A(i,k)>0) && (b1(i)/(A(i,k)+eps))<temp )
                       temp=b1(i)/A(i,k);   %找退基变量
                       r=i;
                   end                
               end
%                fprintf('x(%d)进基，x(%d)退基\n',k,D(r,2));  %显示进基变量和退基变量
               B(:,r)=A(:,k);
               cB(r)=c(k); %确定进基退基变量后，相应的基矩阵及新基对应的目标值的c也相应改变
               D(r,2)=k;   %改变D中的映射关系
              
         end
     end
   end
end
%% 以下是if panduantiaojian==1的else
else
    fprintf('此状态下不需要负荷削减，请加强监控  \n');
end