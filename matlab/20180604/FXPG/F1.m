%%%%1.1���ʲ���ֵ
function y=F1(x)     %xΪ��ѹ����������λ��MVA
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