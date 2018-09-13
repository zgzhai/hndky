function y=qujian(T1,T2,a,b)   %y是计算后的温度，T1、T2分别为本次上一次和本次温度输入值，K1和K2分别为波动值上下限，
if T2>(T1+a)&T2<(T1-b)
    T2=T1;
end
y=T2;


