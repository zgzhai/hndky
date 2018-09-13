function DCPF()
%直流潮流程序
clc;
clear;
global Y  P  delta  PIJ   myf  Bus  Line Load Generator;
myf=fopen('C:\Users\xyy\Desktop\海南程序 - xin-不存在电压等级之分全部是标幺值\负荷削减程序\最优负荷削减程序\output.dat','wt')

openfile();
P=FormP();
Y=FormY();
delta=CalDelta();
PIJ=CalP();
OTP();
Output();
fclose(myf)
