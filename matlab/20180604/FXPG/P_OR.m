%%%%2、偶然性故障概率
function y=P_OR(t)           %此处t为变压器运行年限
k=4.5777;                    %函数参数，隐藏可调
c=28.287;                    
if t<=15
    y=0.0169;
else
    y=(k/c)*(t/c)^(k-1);
end
end