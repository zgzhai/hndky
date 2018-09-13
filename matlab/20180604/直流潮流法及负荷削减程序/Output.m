function Output(Bus,Line,delta,myf,PIJ,Y)
%����ڵ���ǡ���·��������������
global myf Bus Line delta PIJ  Y;

[nb,mb]=size(Bus);
[nl,ml]=size(Line);
% %���ɾ���
% fprintf(myf,'-------------���ɾ���----------\n',1);
% for i=1:nb-1
%     for j=1:nb-1
%         fprintf(myf,'%.2f    ',Y(i,j));
%     end
%     fprintf(myf,'\n');
% end


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
    fValue(i)=fValue(i)*180/pi;
    fprintf(myf,'�ڵ�%d�ĽǶ�   %f\n',i,fValue(i));
end
%%
for i=1:nb
    if Bus(i,3)==3
        fprintf(myf,'ƽ��ڵ�%d�ĽǶ�   0\n',i);
    end
end
%%

%��·����
for j = 1:nl
    I=Line(j,1);
    J=Line(j,2);
    fprintf(myf,'��·P��%d--%d��    %f\n',I,J,PIJ(j));
end
fprintf(myf, '\n');
%% �����������

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
    if rongliang(m,1)>60
    fprintf(myf,'�ڵ�%d���ڵ�%d֧·�����ʽϴ���Խ�޷��գ�������ԼΪ:  %2.2f%%  \n',I,J,rongliang(m,1));
    else
    fprintf(myf,'��״̬�½ڵ�%d���ڵ�%d֧·��������Խ�޷���  \n',I,J);
    end
end

    