%%%%1.1、资产价值
function y=F1(x)     %x为变压器容量，单位：MVA
if x>180
    F11=10;
elseif x>150
    F11=9;
elseif x>120
    F11=7;
elseif x>90
    F11=5; 
else
    F11=3;
end
y=F11;
end