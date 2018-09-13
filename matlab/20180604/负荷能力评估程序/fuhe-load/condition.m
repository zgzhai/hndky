%%%%%%%将变压器负荷状态分为过负荷，重载，正常，轻载四个状态。
%%%%%过负荷条件%%%
%%热点温度超过98℃或110℃，高压、中压、低压超过额定负荷电流。


Tlimit_top=75;              %顶层油温限值，热改性绝缘纸采用85，非热改性绝缘纸采用75.
Tlimit_hs=98;               %热点温度限值,热改性绝缘纸采用110，非热改性绝缘纸采用98.

MINGPAI=xlsread('nameplate');      %读取excel（'nameplate'）中的运行信息.
C_H_r=MINGPAI(1,1);                 %读取变压器高压侧额定容量
C_M_r=MINGPAI(2,1);                 %读取变压器中压侧额定容量                
C_L_r=MINGPAI(3,1);                 %读取变压器低压侧额定容量
V_H_r=MINGPAI(4,1);                 %读取变压器当前高压侧电压值
V_M_r=MINGPAI(5,1);                 %读取变压器当前中压侧电压值
V_L_r=MINGPAI(6,1);                 %读取变压器当前低压侧电压值
I_H_r=MINGPAI(7,1);                 %读取变压器当前高压侧电流值
I_M_r=MINGPAI(8,1);                 %读取变压器当前中压侧电流值
I_L_r=MINGPAI(9,1);                 %读取变压器当前低压侧电流值

XINXI=xlsread('operation');      %读取excel（'operation'）中的运行信息.
TH_hs=XINXI(1,1);                 %读取变压器当前热点温度值
TH_top=XINXI(2,1);                %读取变压器当前顶层油温值                
V_H_C=XINXI(3,1);                 %读取变压器当前高压侧电压值
V_M_C=XINXI(4,1);                 %读取变压器当前中压侧电压值
V_L_C=XINXI(5,1);                 %读取变压器当前低压侧电压值
I_H_C=XINXI(6,1);                 %读取变压器当前高压侧电流值
I_M_C=XINXI(7,1);                 %读取变压器当前中压侧电流值
I_L_C=XINXI(8,1);                 %读取变压器当前低压侧电流值

if TH_hs>=Tlimit_hs|TH_top>=Tlimit_top
   disp('变压器过热引起的过负荷');
else if I_H_C>=I_H_r
        disp('变压器高压侧过负荷');
    else if I_M_C>=I_M_r
        disp('变压器中压侧过负荷');
        else if I_L_C>=I_L_r
             disp('变压器低压侧过负荷');
            else if I_H_C>=0.8*I_H_r
                    disp('变压器重载');
                else if I_H_C>=0.5*I_H_r
                        disp('变压器正常负荷运行');
                    else
                        disp('变压器轻载');
                    end
                end
            end
        end
    end
end


