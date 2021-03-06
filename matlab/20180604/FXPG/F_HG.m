function y=F_HG(w3,w4,w5,w6)  %w1,w2,w3,w4,w5,w6为不同风险后果的重要程度，默认值分别为0.1313，0.1863，0.0888，0.0888，0.2813，0.2234
%%%%2.1、资产损失
F3=0.125*3+0.083*6+0.00001*9;      % 变压器成本损失按照一般程度，中度程度，重度程度分别为12．5％，8．3％，0．O％。
%%%%2.2、环境损失
F4=0.083*3+0.028*6+0.00001*9;      % 变压器环境损失按照一般程度，中度程度，重度程度分别为8．3％，2．8％，O．O％。
%%%%2.3、人身安全
F5=0.001*1+0.0001*3+0.00001*6+0.000001*9; % 变压器人身安全损失按照重轻伤事故，一般人身事故，重大人身事故和特大人身事故分别为0.1％，0.01％，O．O01％和O．O001％。
%%%%2.4、电网安全
F6=0.028*4+0.001*6+0.0001*7+0.00001*10; % 变压器人身安全损失按照一级电网事故，二级电网事故，三级电网事故和四级电网事故分别为2.8％，0.1％，O．01％和O．O01％。
y=w3*F3+w4*F4+w5*F5+w6*F6;
end