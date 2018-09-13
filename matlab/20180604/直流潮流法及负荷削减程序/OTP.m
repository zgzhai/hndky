function OTP(Bus,Load,Generator,Line,Y,P)
   global  Bus Load Generator Line Y P delta PIJ; %�˴��ඨ����delta��PIJ
[nl,ml]=size(Line);
[ng,mg]=size(Generator);
[nbb,mbb]=size(Bus);      
%% �˲�����output.m��������������ͬ,ֻ���޸���nbb��Ŀ���ǵõ�fValue����
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
%% �������������Ϊ������������������жϵ��ж��������˲��ֳ�����Output.m�е����������������ͬ
xiangjiaocha=zeros(nl,1);  %������ǲ�����ֵ
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
panduantiaojian=0;  %�ж��Ƿ���и�����������
for m=1:nl
    I=Line(m,1);
    J=Line(m,2);
    if zhilubiaoshi(m,1)==1     %��·
        zhilushiji(m,1)=PIJ(m)/(3^0.5*xiangjiaocha(m,1)); %��·
    else
           zhilushiji(m,1)=PIJ(m);    %��ѹ��֧·
    end
    zhilushiji(m,1)=abs(zhilushiji(m,1));
    rongliang(m,1)=zhilushiji(m,1)/rongliang(m,1);
    rongliang(m,1)=100*rongliang(m,1);
    if rongliang(m,1)>100            %   �޸����ж�����
    panduantiaojian=1;
    end
end
%% �ж��Ƿ���и�������������
if panduantiaojian==1
   %%   ������ԭ��OPT�г���
 B=Y; 
 [nb,mb]=size(B);
 [nd,md]=size(Load);
 B=inv(B);
 B1=zeros(nb,1);
 B2=zeros(1,mb+1);
 B=[B B1];
 B=[B;B2];
 T=zeros(nl,mb+1);%(1 2 )1����·��2���ڵ�
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
c=zeros(nbb+2*nt+nbb,1);%��ʽԼ������
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
%��ʽԼ���򲻵�ʽԼ���޸ĵ�
for i=1:nbb
     A(2*nt+1+nbb,i)=-1;
end  
b=[-pmin;pmax;pl;-padd];
b=b';
c=c';

%�����η� ��ʼ����
[m,n]=size(A);
E=1:m;E=E';              
F=n-m+1:n;F=F';
D=[E,F];             %����һ��һһӳ�䣬Ϊ�˽���ܹ���׼���
X=zeros(1,n);        %��ʼ��X
if(n<m)              %�ж��Ƿ�Ϊ��׼��    
%fprintf('������Ҫ���������ɳڱ���')

    flag=0;
else
    flag=1;
    B=A(:,n-m+1:n);   %�һ�����
    cB=c(n-m+1:n);    %�������ӦĿ��ֵ��c
   while flag 
     w=cB/B;         %���㵥���γ��ӣ�cB/B=cB*inv(B),��cB/B��Ŀ���ǣ�Ϊ����������ٶȡ���
     panbieshu=w*A-c;  %�����б�����
     [z,k]=max(panbieshu);  % k��Ϊ���������±�  ���� 
  
     if(z<0.000000001)     
         flag=0;             %�����б�����С��0ʱ�ﵽ���Ž⡣��
         
        %% fprintf('    ���ҵ����Ž�!\n');    �˴�ɾ��    
         
         xB=(B\b')';
         f=cB*xB';   
         for i=1:n
           mark=0;
             for j=1:m
                if (D(j,2)==i)
                 mark=1;
                 X(i)=xB(D(j,1));     %����D�ҳ�xB��X֮��Ĺ�ϵ����
                end
             end
             if mark==0
               X(i)=0;         %���D��û��X(i),��X(i)Ϊ�ǻ�����������X(i)��0����
             end
         end
         %% ������       
          %fprintf('������Ϊ:'); X
         if f<=0
             fprintf('����״̬�²���Ҫ�������ɣ����ǿ������·�����ر�ѹ�������м�� \n') ; 
         elseif f>0
             fprintf('����״̬�¿��Խ��и���ת�����Ż�������̬�ȶ��ԣ�������·�ܶ˼����ر�ѹ��������Ҫת�Ƶĸ���������ԼΪ:%d  p.u.����������为������������������Ż� \n',f) ;  
         end
          
     else
         if(B\A(:,k)<=0)          % ���B\A(;,k)�е�ÿһ��������С���㡣��
               flag=0;
               fprintf('     \n ��״̬�²������Ż����Ž�!\n');  %��B\A(:,k)�ĵ�k�о�������0��������ⲻ�������Ž⡣��
               fprintf('     \n ���ǿ������·�����ر�ѹ�������м�أ������豸����ݱ�ѹ��������������������и���ת�ƣ�\n');  
         else
               b1=B\b';
               temp=inf;
               for i=1:m
                   if ((A(i,k)>0) && (b1(i)/(A(i,k)+eps))<temp )
                       temp=b1(i)/A(i,k);   %���˻�����
                       r=i;
                   end                
               end
%                fprintf('x(%d)������x(%d)�˻�\n',k,D(r,2));  %��ʾ�����������˻�����
               B(:,r)=A(:,k);
               cB(r)=c(k); %ȷ�������˻���������Ӧ�Ļ������»���Ӧ��Ŀ��ֵ��cҲ��Ӧ�ı�
               D(r,2)=k;   %�ı�D�е�ӳ���ϵ
              
         end
     end
   end
end
%% ������if panduantiaojian==1��else
else
    fprintf('��״̬�²���Ҫ�������������ǿ���  \n');
end