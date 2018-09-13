function Output(Bus,Line,delta,myf,PIJ,Y)
%������ɾ��󡢽ڵ���ǡ���·����
global myf Bus Line delta PIJ  Y;

[nb,mb]=size(Bus);
[nl,ml]=size(Line);
%���ɾ���
fprintf(myf,'-------------���ɾ���----------\n',1);
for i=1:nb-1
    for j=1:nb-1
        fprintf(myf,'%.2f    ',Y(i,j));
    end
    fprintf(myf,'\n');
end


%�ڵ����
fprintf(myf,'-------------�ڵ����----------\n',1);
for i = 1:nb-1
    fValue(i)=delta(i);
    while(fValue(i)*180/pi<=-180)
        fValue(i)=fValue(i)+2*pi;
    end
    while(fValue(i)*180/pi>=180)
        fValue(i)=fValue(i)-2*pi;
    end
    fprintf(myf,'�ڵ�%d�ĽǶ�   %f\n',i,fValue(i)*180/pi);
end

%��·����
for j = 1:nl
    I=Line(j,1);
    J=Line(j,2);
    fprintf(myf,'��·P��%d--%d��    %f\n',I,J,PIJ(j));
end
fprintf(myf, '\n');