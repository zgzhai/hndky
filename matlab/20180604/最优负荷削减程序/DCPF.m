function DCPF()
%ֱ����������
clc;
clear;
global Y  P  delta  PIJ   myf  Bus  Line Load Generator;
myf=fopen('C:\Users\31580\Desktop\�����д\�������\���Ÿ�����������\output.dat','wt')

openfile();
P=FormP();
Y=FormY();
delta=CalDelta();
PIJ=CalP();
OTP();
Output();

fclose(myf)
