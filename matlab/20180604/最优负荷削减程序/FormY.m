function Y=FormY(Bus,Line)
%����ڵ㵼�ɾ���
global Bus Line;

[nb,mb]=size(Bus);
[nl,ml]=size(Line);
%ȥ��ƽ��ڵ�
Y=zeros(nb-1,nb-1);

for i=1:nl  
    I=Line(i,1);          
    J=Line(i,2);    
    %�ж��Ƿ���ƽ��ڵ�
    if (Bus(I,3)<3)&(Bus(J,3)<3)  
       X=Line(i,4);
       B=1/X;
       Y(I,I)=Y(I,I)+B;
       Y(J,J)=Y(J,J)+B;
       Y(I,J)=Y(I,J)-B;
       Y(J,I)=Y(I,J); 
    elseif (Bus(I,3)==3)&(Bus(J,3)<3)
       X=Line(i,4);
       B=1/X;
       Y(J,J)=Y(J,J)+B;  
       %����ƽ��ڵ㣬������ƽ��ڵ������ڵ���Ե�����Ҫ����
    elseif  (Bus(I,3)<3)&(Bus(J,3)==3) 
       X=Line(i,4);
       B=1/X;
       Y(I,I)=Y(I,I)+B; 
    end
end



