function OTP(Bus,Load,Generator,Line,Y,P)
   global  Bus Load Generator Line Y P;
[nl,ml]=size(Line);
[ng,mg]=size(Generator);
   B=Y; 
   [nbb,mbb]=size(Bus);
 [nb,mb]=size(B);
 [nd,md]=size(Load);
 [ng,mg]=size(Generator);
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
    fprintf('不符合要求需引入松弛变量')
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
         fprintf('    已找到最优解!\n');        
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
         %输出结果
          fprintf('基向量为:'); X
          fprintf('目标函数值为:') ;  f
     else
         if(B\A(:,k)<=0)          % 如果B\A(;,k)中的每一个分量都小于零。。
               flag=0;
               fprintf('     \n 此问题不存在最优解!\n');  %若B\A(:,k)的第k列均不大于0，则该问题不存在最优解。。
         else
               b1=B\b';
               temp=inf;
               for i=1:m
                   if ((A(i,k)>0) && (b1(i)/(A(i,k)+eps))<temp )
                       temp=b1(i)/A(i,k);   %找退基变量
                       r=i;
                   end                
               end
               fprintf('x(%d)进基，x(%d)退基\n',k,D(r,2));  %显示进基变量和退基变量
               B(:,r)=A(:,k);
               cB(r)=c(k); %确定进基退基变量后，相应的基矩阵及新基对应的目标值的c也相应改变
               D(r,2)=k;   %改变D中的映射关系
              
         end
     end
   end
end