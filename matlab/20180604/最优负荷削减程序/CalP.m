function PIJ=CalP(Bus,Line,delta)
%������·����PIJ=Delta(i)-Delta(j)/x
global Bus Line delta;

[nl,ml]=size(Line);
PIJ=zeros(nl,1);
for i=1:nl
    I=Line(i,1);
    J=Line(i,2);
    X=Line(i,4);
    %ƽ��ڵ�ǶȵĴ���
    if Bus(I,3)==3
       delta(I)=0;
    elseif Bus(J,3)==3
       delta(J)=0;
    end
    
    PIJ(i,1)=(delta(I)-delta(J))/X;
end

        