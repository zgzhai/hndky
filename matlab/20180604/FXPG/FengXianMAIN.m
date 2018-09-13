clear;clc;
%%变压器风险预测模型
% 设置输入：
A=xlsread('BYQstate');              %变压器状态信息。
x=A(1,1);                           %变压器整体健康状态评分值
t=A(2,1);                           %变压器运行年限
% 故障诊断结果：
A1(1)=input('风险来源分析：');           %故障诊断结果
A1(2)=input('风险来源分析：');           %状态评价结果
%
% 概率等级设置：
a1=0.03;                            %超过该值，变压器正常故障极少发生
a2=0.07;                            %超过该值，变压器正常故障偶尔发生
a3=0.12;                            %超过该值，变压器正常故障可能发生
a4=0.18;                            %超过该值，变压器正常故障很可能发生
% 风险后果等级：
b1=2.2;                             %超过该值，风险后果适中
b2=2.8;                             %超过该值，风险后果稍微严重
b3=3.0;                             %超过该值，风险后果严重
% 风险后果权重：
w1=0.1313;
w2=0.1863;
w3=0.0888;
w4=0.0888;
w5=0.2813;
w6=0.2234;
% 调用函数：
Pro=P_YY(x)+P_OR(t)                 %变压器总站概率  %变压器当前概率为诱因性故障概率与偶然故障概率值之和。当前只考虑变压器整体状态。
F_R=F_ZY(w1,w2)+F_HG(w3,w4,w5,w6)                   %变压器风险后果为重要性后果与损失性后果的加权。
% 判断等级：
if Pro>a4&F_R>b1                   %其中a0,a1,a2,a3,a4和b0,b1,b2,b3分别为故障概率等级和风险后果等级
    Res='A';
   elseif Pro>a4
       Res='B';
       elseif Pro>a3&F_R>b2
           Res='A';
           elseif Pro>a3&F_R>b1
               Res='B';
               elseif Pro>a3
                   Res='C';
                   elseif Pro>a2&F_R>b3
                       Res='A';
                       elseif Pro>a2&F_R>b2 
                           Res='B';
                           elseif Pro>a2&F_R>b1
                               Res='C';
                               elseif Pro>a2
                                   Res='D';
                                   elseif Pro>a1&F_R>b2 
                                       Res='B';
                                       elseif Pro>a1&F_R>b1 
                                           Res='C'; 
                                           elseif Pro>a1
                                               Res='D'; 
                                               elseif F_R>b3 
                                                   Res='B';
                                                   elseif F_R>b2 
                                                       Res='C';
                                                       else 
                                                          Res='D'; 
end
% 输出结果：
if Res=='A'
    fprintf('风险等级：A\n');
    fprintf('含义：高风险，应立刻采取行动\n');
    fprintf('风险来源分析：%s\n',A1);
    elseif Res=='B'
        fprintf('风险等级：B\n');
        fprintf('含义：风险适中，应采取办法予以消除\n') ;   
        fprintf('风险来源分析：%s\n',A1);
        elseif Res=='C'
            fprintf('风险等级：C\n')
            fprintf('含义：低风险，应采取办法予以减轻\n')    
            fprintf('风险来源分析：%s\n',A1);
            else Res=='D'
                fprintf('风险等级：D\n')
                fprintf('含义：小风险，可接受\n')    
                fprintf('风险来源分析：%s\n',A1);
end