function y=GZZT(q)         %����ģʽӰ��
DS=xlsread('state_data');  %��ȡ��ѹ������ģʽ
d1=DS(1,1);
d2=DS(2,1);
d3=DS(3,1);
d4=DS(4,1);
d5=DS(5,1);
d6=DS(6,1);
d7=DS(7,1);
d8=DS(8,1);
%%%%%%�������״̬Ӱ������%%%
 K1=1.73*0.000001;
 C1=0.193;
 k1=1.7300e-06;
 k2=K1*exp(-100*C1)*exp(C1*(100-q)-1);
 y1=(k1-k2)/k1;
 y2=d1+d2+d3+d4+d5+d6+d7+d8;
 if y2>=1
     y=y1+y2;
 else
     y=y1;
 end
end
 