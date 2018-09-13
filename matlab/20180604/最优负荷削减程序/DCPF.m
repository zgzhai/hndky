function DCPF()
%直流潮流程序
clc;
clear;
global Y  P  delta  PIJ   myf  Bus  Line Load Generator;
myf=fopen('C:\Users\31580\Desktop\程序编写\测序测试\最优负荷削减程序\output.dat','wt')

openfile();
P=FormP();
Y=FormY();
delta=CalDelta();
PIJ=CalP();
OTP();
Output();

fclose(myf)
