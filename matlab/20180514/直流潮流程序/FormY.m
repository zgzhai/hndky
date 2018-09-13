function Y=FormY(Bus,Line)
%计算节点导纳矩阵
global Bus Line;

[nb,mb]=size(Bus);
[nl,ml]=size(Line);
%去掉平衡节点
Y=zeros(nb-1,nb-1);

for i=1:nl  
    I=Line(i,1);          
    J=Line(i,2);    
    %判断是否是平衡节点
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
       %不计平衡节点，但是与平衡节点相连节点的自导纳需要计算
    elseif  (Bus(I,3)<3)&(Bus(J,3)==3) 
       X=Line(i,4);
       B=1/X;
       Y(I,I)=Y(I,I)+B; 
    end
end



