function DCPF()
%ֱ����������
clc;
clear;
global Y  P  delta  PIJ   myf  Bus  Line Load Generator;
myf=fopen('C:\Users\Administrator\Desktop\ֱ������\output.dat','wt')

openfile();
P=FormP();
Y=FormY();
delta=CalDelta();
PIJ=CalP();
Output();

fclose(myf)
