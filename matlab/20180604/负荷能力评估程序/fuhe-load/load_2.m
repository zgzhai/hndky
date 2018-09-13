 function y=load_2       %正常周期负荷能力评估

n_iters=96;                  %迭代次数，一天24小时，每小时采4个点,
IC1=xlsread('state_data');    %读取变压器状态评分值
IC=IC1(9,1);

Klimit_fu=1.2;               %辅助设备容量等级约束
Tlimit_top=105;              %顶层油温约束
Tlimit_hs=120;               %热点温度约束
Llimit_L=10*86400;              %相对寿命损失约束
Flimit_IC=GZZT(IC);          %故障模式约束


type=3;                %变压器类型：中大型变压器 ONAN type=1; 中大型变压器 ONAF type=2;中大型变压器 OF type=3;中大型变压器 OD type=4
kind=2;                %变压器分类：自耦变压器时 kind=1；双绕组变压器时 kind=2； 三绕组变压器时 kind=3；
Y_text=1;              %当变压器材质及相关参数存在时，Y_text=1; 否则，Y_text=0 
Y_temdata=1;           %是否存在变压器额定负荷（100%）的温升数据和60-80%负荷的温升数据；若有，则Y_temdata=2，否则Y_temdata=1；
Y_cool=1;              %冷却器投入0%，Y_cool=0；冷却器投入50%，Y_cool=0.5；冷却器投入100%，Y_cool=1；
Y_resis=1;             %变压器绕组直流电阻及电压变比数据存在，Y_resis=1；否则，Y_resis=0.
Y_paper=0;             %变压器采用热改性绝缘纸，Y_paper=1；采用非热改性绝缘纸，Y_paper=0.
Tap=9;                 %输入高压侧实际分接位置.
n=0.33;                 %输入计算指数（0.8~1.0） 一般情况下，对于AN类型的变压器n取值接近0.8 对于AF类型的变压器n取值接近1.0
n1=0.5;                  %输入计算指数（0.8~2.0） 一般情况下，对于ON类型的变压器m取值接近0.8 对于OD类型的变压器m取值接近2.0
                     

%箱体尺寸%%%%%%%%
A=xlsread('size');      %读取excel（'size'）中的尺寸信息.
global l w h                 %长宽高，设置全局变量
l=A(1,1);                    %输入变压器箱体长度（m）.
w=A(2,1);                    %输入变压器箱体宽度（m）.
h=A(3,1);                    %输入变压器箱体高度（m）.

%%%%变压器绕组直流电阻及电压变比%%%%%%%%%%%%
switch (kind)
    case 1
        Tap_r=3;                                %输入高压侧额定分接位置.
        Tap=3;                  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%用excel读取数据                %输入高压侧实际分接位置.
        U_fjt=3.32;                             %输入中压侧每级分接头额定电压差值（kV）
        T_C=28.4;                                 %绕组测量时的油温.

        D_H=xlsread('resistanceH');               %读取excel（'resistanceH'）中的高压侧不同分接下的绕组直流电阻信息.
        D_M=xlsread('resistanceM');               %读取excel（'resistanceM'）中的中压侧的绕组直流电阻信息.
        D_L=xlsread('resistanceL');               %读取excel（'resistanceL'）中的低压侧的绕组直流电阻信息(一般为线电阻).
        R_H=D_H(Tap,:);                           %选取当前分接下的高压侧绕组直流电阻.
        R_M=D_M;                                  %选取当前分接下的中压侧绕组直流电阻.

        R_Ld=D_L;                                 %选取当前分接下的低压侧绕组直流线电阻电阻.
        R_p=sum(R_Ld)/2;                          %将输入低压侧高压、中压和低压三相的线电阻化为相电阻
        R_L=[(R_Ld(1,3)- R_p)-R_Ld(1,1)*R_Ld(1,2)/(R_Ld(1,3)- R_p) (R_Ld(1,1)- R_p)-R_Ld(1,3)*R_Ld(1,2)/(R_Ld(1,1)- R_p) (R_Ld(1,2)- R_p)-R_Ld(1,1)*R_Ld(1,3)/(R_Ld(1,2)- R_p)];  %低压侧相电阻.
        
        K_V=xlsread('ratio1');              %读取excel（'ratio1'）中的变比信息.
        
        k_HM=K_V(Tap,1);                    %输入当前分接位置变压器高压侧和中压侧的变比.
        k_ML=K_V(Tap,2);                    %输入当前分接位置变压器高压侧和中压侧的变比.
        k_HL=K_V(1,3);                      %输入变压器高压侧和低压侧的变比.

    case 2
        Tap_r=9;                                %输入高压侧额定分接位置.
        Tap=17;                  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%用excel读取数据                 %输入高压侧实际分接位置.
        U_fjt=1.25;                             %输入高压侧每级分接头额定电压差值（kV）
        T_C=26;                                 %绕组测量时的油温.

        D_H=xlsread('resistanceH');               %读取excel（'resistanceH'）中的高压侧不同分接下的绕组直流电阻信息.
        D_L=xlsread('resistanceL');               %读取excel（'resistanceL'）中的低压侧的绕组直流电阻信息(一般为线电阻).
        
        R_H=D_H(Tap,:);                           %选取当前分接下的高压侧绕组直流电阻.
      
        R_Ld=D_L;                                 %选取低压侧绕组直流线电阻电阻.
        R_p=sum(R_Ld)/2;                          %将输入低压侧高压、中压和低压三相的线电阻化为相电阻
        R_L=[(R_Ld(1,3)- R_p)-R_Ld(1,1)*R_Ld(1,2)/(R_Ld(1,3)- R_p) (R_Ld(1,1)- R_p)-R_Ld(1,3)*R_Ld(1,2)/(R_Ld(1,1)- R_p) (R_Ld(1,2)- R_p)-R_Ld(1,1)*R_Ld(1,3)/(R_Ld(1,2)- R_p)];  %低压侧相电阻.
        
        K_V=xlsread('ratio2');                %读取excel（'ratio1'）中的变比信息.
        
        k_HL=K_V(Tap,1);                      %输入当前分接下变压器高压侧和低压侧的变比.

    case 3
        Tap_r=9;                                %输入高压侧额定分接位置.
        Tap=9;                                  %输入高压侧实际分接位置.
        U_fjt=2.25;                             %输入高压侧每级分接头额定电压差值（kV）
        T_C=26;                                 %绕组测量时的油温.

        D_H=xlsread('resistanceH');               %读取excel（'resistanceH'）中的高压侧不同分接下的绕组直流电阻信息.
        D_M=xlsread('resistanceM');               %读取excel（'resistanceM'）中的中压侧的绕组直流电阻信息.
        D_L=xlsread('resistanceL');               %读取excel（'resistanceL'）中的低压侧的绕组直流电阻信息(一般为线电阻).
        R_H=D_H(Tap,:);                           %选取当前分接下的高压侧绕组直流电阻.
        R_M=D_M;                                  %读取中压侧绕组直流电阻.

        R_Ld=D_L;                                 %读取低压侧绕组直流线电阻电阻.
        R_p=sum(R_Ld)/2;                          %将输入低压侧高压、中压和低压三相的线电阻化为相电阻
        R_L=[(R_Ld(1,3)- R_p)-R_Ld(1,1)*R_Ld(1,2)/(R_Ld(1,3)- R_p) (R_Ld(1,1)- R_p)-R_Ld(1,3)*R_Ld(1,2)/(R_Ld(1,1)- R_p) (R_Ld(1,2)- R_p)-R_Ld(1,1)*R_Ld(1,3)/(R_Ld(1,2)- R_p)];  %低压侧相电阻.
       
        K_V=xlsread('ratio3');               %读取excel（'ratio3'）中的变比信息.
        
        k_HM=K_V(Tap,1);                      %输入当前分接位置变压器高压侧和中压侧的变比.
        k_ML=K_V(1,2);                        %输入当前分接位置变压器高压侧和中压侧的变比.
        k_HL=K_V(Tap,3);                      %输入变压器高压侧和低压侧的变比.

end

%%%%%%%%%%%温升试验数据%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
switch (kind)
    case 1
         E=xlsread('temperaturerise1');               %读取excel（'temperaturerise'）中的温升试验信息.

        T_top_r=E(1,1);                               %读取温升试验下的顶层油温升（K）.
        T_oil_r=E(2,1);                               %读取温升试验下的平均油温升（K）.
        T_wnd_r=E(3,1);                               %读取温升试验下的绕组平均温升（K）.
        T_amb_r=E(11,1);                              %读取温升试验下的环境温度（℃）.
        
        H=E(6,1);                                     %读取热点温度系数。
        
        T_boil_r=2*T_oil_r-T_top_r;                   %计算温升试验下的底层油温升（K）.
        T_hs_r=H*(T_wnd_r-T_oil_r)+T_top_r;           %计算热点温度温升（K）.
        
        P_fe_r=E(4,1);                                %读取温升试验下变压器的空载损耗（W）.
        P_cu_r=E(5,1);                                %读取温升试验下变压器的负载损耗（W）.
        I_H_DC=E(8,1);                                %读取温升试验下变压器高压侧负载电流（A）.
        I_M_DC=E(9,1);                                %读取温升试验下变压器中压侧负载电流（A）.
        I_L_DC=E(10,1);                                %读取温升试验下变压器低压侧负载电流（A）.
        %%%%%高压侧和低压侧的电流转换%%%%%%%%%%%%%%%%%%%
%       I_H_DC=I_L_DC/k_HL;
        I_L_DC=I_H_DC*k_HL;
        %%%%%%附加损耗计算%%%%%%%%%%%%%%%%%%%
        if Y_resis==1
        K_T=(235+T_wnd_r)/(235+T_C);
        P_dc_r=K_T*sum(R_H)*I_H_DC^2+K_T*sum(R_L)*I_L_DC^2;             %额定下的直流电阻损耗.
        else
            P_dc_r=P_cu_r;
        end
        P_fj_r=P_cu_r-P_dc_r;                                           %额定下的附加损耗.
        R=P_cu_r/P_fe_r;                                                %负载损耗比.

    case 2
        E=xlsread('temperaturerise2');                %读取excel（'temperaturerise'）中的温升试验信息.

        T_top_r=E(1,1);                               %读取温升试验下的顶层油温升（K）.
        T_oil_r=E(2,1);                               %读取温升试验下的平均油温升（K）.
        T_wnd_r=E(3,1);                               %读取温升试验下的绕组平均温升（K）.
        T_amb_r=E(10,1);                              %读取温升试验下的环境温度（℃）.
        
        H=E(6,1);                                     %读取热点温度系数。
        
        T_boil_r=2*T_oil_r-T_top_r;                   %计算温升试验下的底层油温升（K）.
        T_hs_r=H*(T_wnd_r-T_oil_r)+T_top_r;           %计算热点温度温升（K）.
        
        P_fe_r=E(4,1);                                %读取温升试验下变压器的空载损耗（W）.
        P_cu_r=E(5,1);                                %读取温升试验下变压器的负载损耗（W）.
        I_H_DC=E(8,1);                                %读取温升试验下变压器高压侧负载电流（A）.
%       I_L_DC=E(9,1);                                %读取温升试验下变压器低压侧负载电流（A）.
        %%%%%高压侧和低压侧的电流转换%%%%%%%%%%%%%%%%%%%
%       I_H_DC=I_L_DC/k_HL;
        I_L_DC=I_H_DC*k_HL;
        %%%%%%附加损耗计算%%%%%%%%%%%%%%%%%%%
        if Y_resis==1
        K_T=(235+T_wnd_r)/(235+T_C);
        P_dc_r=K_T*sum(R_H)*I_H_DC^2+K_T*sum(R_L)*I_L_DC^2;             %额定下的直流电阻损耗.
        else
            P_dc_r=P_cu_r;
        end
        P_fj_r=P_cu_r-P_dc_r;                                           %额定下的附加损耗.
        R=P_cu_r/P_fe_r;                                                %负载损耗比.

    case 3
        if Y_temdata==1
%%%%额定负荷（100%）时的温升数据
        E=xlsread('temperaturerise31');                %读取excel（'temperaturerise31'）中额定负荷下温升试验信息.
        
        T_top_r=E(1,1);                               %读取温升试验下的顶层油温升（K）.
        T_oil_r=E(2,1);                               %读取温升试验下的平均油温升（K）.
        T_wnd_r=E(3,1);                               %读取温升试验下的绕组平均温升（K）.
        T_amb_r=E(11,1);                              %读取温升试验下的环境温度（℃）.
        
        H=E(6,1);                                     %读取热点温度系数。
        
        T_boil_r=2*T_oil_r-T_top_r;                   %计算温升试验下的底层油温升（K）.
        T_hs_r=H*(T_wnd_r-T_oil_r)+T_top_r;           %计算热点温度温升（K）.
        
        P_fe_r=E(4,1);                                %读取温升试验下变压器的空载损耗（W）.
        P_cu_r=E(5,1);                                %读取温升试验下变压器的负载损耗（W）.指变压器高压-中压负载损耗与高压-中压负载损耗之和。
        I_H_DC=E(8,1);                                %读取温升试验下变压器高压侧负载电流（A）.
        I_M_DC=E(9,1);                                %读取温升试验下变压器中压侧负载电流（A）.
        I_L_DC=E(10,1);                               %读取温升试验下变压器低压侧负载电流（A）.
        
        else if Y_temdata==0&Y_cool==0
%%%%冷却器投入100%，负荷（100%）时的温升数据
        E=xlsread('temperaturerise32');                %读取excel（'temperaturerise32'）中负荷（100%和70%）时的温升试验信息.
            
        T_top_r=E(1,1);                               %读取温升试验下的顶层油温升（K）.
        T_oil_r=E(2,1);                               %读取温升试验下的平均油温升（K）.
        T_wnd_r=E(3,1);                               %读取温升试验下的绕组平均温升（K）.
        T_amb_r=E(11,1);                              %读取温升试验下的环境温度（℃）.
        
        H=E(6,1);                                     %读取热点温度系数。
        
        T_hs_r=H*(T_wnd_r-T_oil_r)+T_top_r;           %计算热点温度温升（K）.
        
        P_fe_r=E(4,1);                                %读取温升试验下变压器的空载损耗（W）.
        P_cu_r=E(5,1);                                %读取温升试验下变压器的负载损耗（W）.指变压器高压-中压负载损耗与高压-中压负载损耗之和。
        I_H_DC=E(8,1);                                %读取温升试验下变压器高压侧负载电流（A）.
        I_M_DC=E(9,1);                                %读取温升试验下变压器中压侧负载电流（A）.
        I_L_DC=E(10,1);                               %读取温升试验下变压器低压侧负载电流（A）.
        
        else if Y_temdata==0&Y_cool==1
%%%%冷却器投入70%，负荷（70%）时的温升数据          
        E=xlsread('temperaturerise32');                %读取excel（'temperaturerise32'）中负荷（100%和70%）时的温升试验信息.
         
        T_top_r=E(1,2);                               %读取温升试验下的顶层油温升（K）.
        T_oil_r=E(2,2);                               %读取温升试验下的平均油温升（K）.
        T_wnd_r=E(3,2);                               %读取温升试验下的绕组平均温升（K）.
        T_amb_r=E(11,2);                              %读取温升试验下的环境温度（℃）.        
        
       
        H=E(6,2);                                     %读取热点温度系数。
        
        T_boil_r=2*T_oil_r-T_top_r;                   %计算温升试验下的底层油温升（K）.
        T_hs_r=H*(T_wnd_r-T_oil_r)+T_top_r;           %计算热点温度温升（K）.
        
        P_fe_r=E(4,2);                                %读取温升试验下变压器的空载损耗（W）.
        P_cu_r=E(5,2);                                %读取温升试验下变压器的负载损耗（W）.指变压器高压-中压负载损耗与高压-中压负载损耗之和。
        I_H_DC=E(8,2);                                %读取温升试验下变压器高压侧负载电流（A）.
        I_M_DC=E(9,2);                                %读取温升试验下变压器中压侧负载电流（A）.
        I_L_DC=E(10,2);                               %读取温升试验下变压器低压侧负载电流（A）.
        
        else
%%%%冷却器投入50%%%%   
        E=xlsread('temperaturerise32');                %读取excel（'temperaturerise32'）中负荷（100%和70%）时的温升试验信息.            
        T_top_r=0.5*(E(1,1)+E(1,2));                               %读取温升试验下的顶层油温升（K）.
        T_oil_r=0.5*(E(2,1)+E(2,2));                               %读取温升试验下的平均油温升（K）.
        T_wnd_r=0.5*(E(3,1)+E(3,2));                               %读取温升试验下的绕组平均温升（K）.
        T_amb_r=0.5*(E(11,1)+E(11,2));                               %读取温升试验下的环境温度（℃）.        
               
        H=E(6,1);                                     %读取热点温度系数。
        
        T_boil_r=2*T_oil_r-T_top_r;                   %计算温升试验下的底层油温升（K）.
        T_hs_r=H*(T_wnd_r-T_oil_r)+T_top_r;           %计算热点温度温升（K）.
        
        P_fe_r=0.5*(E(4,1)+E(4,2));                                %读取温升试验下变压器的空载损耗（W）.
        P_cu_r=0.5*(E(5,1)+E(5,2));                                %读取温升试验下变压器的负载损耗（W）.指变压器高压-中压负载损耗与高压-中压负载损耗之和。
        I_H_DC=0.5*(E(8,1)+E(8,2));                                 %读取温升试验下变压器高压侧负载电流（A）.
        I_M_DC=0.5*(E(9,1)+E(9,2));                                %读取温升试验下变压器中压侧负载电流（A）.
        I_L_DC=0.5*(E(10,1)+E(10,2));                              %读取温升试验下变压器低压侧负载电流（A）.

        end 
        end            
        end
       
%%%%高压侧和中压、低压侧的电流转换%%%%%%%%%%%%%%%%%%%
%         I_H_DC=I_M_DC*k_HM+I_L_DC/k_HL;

%%%%%%附加损耗计算%%%%%%%%%%%%%%%%%%%
        if Y_resis==1 
        K_T=(235+T_wnd_r)/(235+T_C);
        P_dc_r=K_T*sum(R_H)*I_H_DC^2+K_T*sum(R_M)*I_M_DC^2+K_T*sum(R_L)*I_L_DC^2;             %额定下的直流电阻损耗.
        else
        P_dc_r=P_cu_r;    
        end
        P_fj_r=P_cu_r-P_dc_r;                                           %额定下的附加损耗.
        R=P_cu_r/P_fe_r;                                                %负载损耗比. 
end


%%%%%%%%时间常数计算%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

if Y_text==1
    B=xlsread('texture');           %读取excel（'texture'）中的材质信息.
    m_tank=B(1,1);                  %输入变压器壳体质量（kg）.
    m_oil=B(2,1);                   %输入变压器油质量（kg）.
    m_wnd=B(3,1);                   %输入变压器绕组质量（kg）.
    m_fe=B(4,1);                    %输入变压器铁芯质量（kg）.

    c_tank=B(1,2);                  %输入变压器壳体的比热容（J/(kg·℃）.
    c_oil=B(2,2);                   %输入变压器油的比热容（J/(kg·℃）.
    c_wnd=B(3,2);                   %输入变压器绕组的比热容（J/(kg·℃）.
    c_fe=B(4,2);                    %输入变压器铁芯的比热容（J/(kg·℃）.
%%%%%%%%传热热容计算%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    C_th_1=m_tank*c_tank+m_oil*c_oil+m_wnd*c_wnd+m_fe*c_fe;  %环境温度至顶层油温模型的热容.     
    C_th_2=m_wnd*c_wnd+m_fe*c_fe;                            %环境温度至平均油温模型的热容.    
    C_th_3=m_wnd*c_wnd;                                      %平均油温至绕组平均温度模型的热容.
%%%%%%%%试验下传热热阻计算%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
    R_th_top_air=(T_top_r-T_oil_r)/(P_fe_r+P_cu_r);                    %顶层油温至环境温度的传热热阻.
    R_th_oil_air=T_oil_r/(P_fe_r+P_cu_r);                              %平均油温至环境温度的传热热阻.
    R_th_wnd_oil=(T_wnd_r-T_oil_r)/P_cu_r;                             %绕组平均温度至平均油温的传热热阻.
%%%%%%%%时间常数计算%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    t_top=R_th_top_air*C_th_1;                               %环境温度至顶层油温模型的时间常数.
    t_oil=R_th_oil_air*C_th_2;                               %环境温度至平均油温模型的时间常数.
    t_wnd=R_th_wnd_oil*C_th_3;                               %平均油温至绕组平均温度模型的时间常数.
else
switch (type)
    case 1
    t_top=210;                                %环境温度至顶层油温模型的时间常数.
    t_oil=210;                               %环境温度至平均油温模型的时间常数.
    t_wnd=10;                                 %平均油温至绕组平均温度模型的时间常数. 
    case 2
    t_top=150;                                %环境温度至顶层油温模型的时间常数.
    t_oil=150;                               %环境温度至平均油温模型的时间常数.
    t_wnd=7;                                 %平均油温至绕组平均温度模型的时间常数. 
    case 3
    t_top=90;                                %环境温度至顶层油温模型的时间常数.
    t_oil=90;                               %环境温度至平均油温模型的时间常数.
    t_wnd=7;                                 %平均油温至绕组平均温度模型的时间常数. 
    case 4
    t_top=90;                                %环境温度至顶层油温模型的时间常数.
    t_oil=90;                               %环境温度至平均油温模型的时间常数.
    t_wnd=7;                                 %平均油温至绕组平均温度模型的时间常数. 
end
end

%%%%%%%%第一次计算时模型温度初值%%%%%%%%%%%%%%%%%%%%%%%%
        F=xlsread('initial1');               %读取excel（'initial'）中的温度初值. 
        T_top_0=F(1,1);                                       %读取变压器的顶层油温初值（℃）.
        T_oil_0=F(2,1);                                       %读取变压器的平均油温初值（℃）.
        T_wnd_0=F(3,1);                                       %读取变压器的绕组平均温度初值（℃）.
        T_hs_0=H*(T_wnd_0-T_oil_0)+T_top_0;             %读取变压器当前的热点温度（℃）.
    if Y_paper==0
        V_0=2^((T_hs_0-98)/6);                                   %相对老化速率
    else
        V_0=exp(15000/(110+273)-15000/(T_hs_0+273));            %相对老化速率
    end
%%%%%%%%太阳辐射功率%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        S=2*(l*w+w*h+l*h);                                       %变压器表面积.
        a=0.5;                                                   %辐射功率吸收系数.
        b=0.5; 
%%%%%%变压器负荷条件%%%%%%%%%   
Con=xlsread('con_load1');           %读取变压器负荷条件
T_amb=Con(1,1);                     %最高环境温度或一天的平均温度,或者一天的温度曲线。
I_H_current=Con(1,2);                 %初始负荷电流
p_sun=0;                            %忽略日照辐射影响 
K1=I_H_DC;


  T_amb_1=zeros(1,n_iters);                          %环境温度间隔调整数组    
  I_H_current_1=zeros(1,n_iters);                    %负载电流间隔调整数组
  p_sun_1=zeros(1,n_iters);                          %日照辐射量间隔调整数组

                                               
for i=1:n_iters
         T_amb_1(i)=T_amb+10;
         I_H_current_1(i)=I_H_current;
         p_sun_1(i)=p_sun; 
end
%%%%行矩阵转变成列矩阵%%%%%%%
            T_amb=T_amb_1';
            I_H_current=I_H_current_1';
            p_sun=p_sun_1';
            P_sun=a*b*S*p_sun;                                   %变压器吸收的热辐射功率.        
       %%%%%%%%模型计算过程%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%必要参数设置%%%%%%%%%%%%%%
          interval=15;                                             %对计算时间间隔（min） 

        num=size(T_amb,1);                                   %统计本次计算提取的数据点个数.

 while(0<1)
        T_top_G=zeros(1,(num-1));                                %用于记录顶层油温度数据的数组.      %num为给定数据的长度
        T_oil_G=zeros(1,(num-1));                                %用于记录变压器油平均温度数据的数组.
        T_wnd_G=zeros(1,(num-1));                                %用于记录绕组平均温度数据的数组.
        T_hs_G=zeros(1,(num-1));                                 %用于记录热点温度数据的数组.
        V_G=zeros(1,(num-1));                                    %用于记录变压器相对老化速率的数组.
%%%%%%%%%变压器当前各温度值%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        T_top_now=T_top_0;                                     %读取变压器当前的顶层油温（℃）.
        T_oil_now=T_oil_0;                                     %读取变压器当前的平均油温（℃）. 
        T_wnd_now=T_wnd_0;                                     %读取变压器当前的绕组顶层油温（℃）.
        T_hs_now=T_hs_0;                                       %读取变压器当前的热点温度（℃）.
        T_oil_rate=T_amb_r+T_oil_r;                            %额定时的平均油温（℃）.  
        T_top_rate=T_amb_r+T_top_r;                            %额定时的顶层油温（℃）.
        T_wnd_rate=T_amb_r+T_wnd_r;                            %额定时的平均绕组温度（℃）. 
        L=0;                                                   %初始相对寿命损失
        L1=0; 
%%%%%%%采用龙格—库塔算法求解模型%%%%%%%%%%%%%%%%%%%%%%%%%%%
for i=2:num                                                    %num为给定数据的长度
    
   T_amb_now=T_amb(i-1);                  %前一次环境温度的实时测量值（℃）.
   I_current_now=I_H_current(i-1);          %前一次负载的电流测量值（A）.
   P_sun_now=P_sun(i-1);                  %前一时刻日照辐射功率（W）.

% aT=T_top_now-T_top_C(i-1);
% T_top_now=T_top_now-aT;
% T_oil_now=T_oil_now-aT;
% T_wnd_now=T_wnd_now-aT;
%每30min进行一次校正一次计算得到的变压器温度.
 
for k=1:(6*interval)                           %每次的时间间隔
    
    
    K=I_current_now/I_H_DC;
    P_cu_now=(P_dc_r/P_cu_r)*((T_wnd_now+235)/(T_wnd_rate+235))+(P_fj_r/P_cu_r)*((T_wnd_rate+235)/(T_wnd_now+235));
    
    
%基于环境温度计算平均油温
    u_p=exp(2797.3/(T_oil_now+273))/exp(2797.3/(T_oil_rate+273));

    t=1:1:10;                                      %以10s为步长进行计算
    T_oil =@(t,T_oil_now) (((1+(R*K^2)*P_cu_now+P_sun_now/P_fe_r)/(1+R))*T_oil_r-((T_oil_now-T_amb_now)^(n+1)/((u_p*T_oil_r)^n)))/t_oil;  %平均油温计算程序（微分方程，T_oil_now相当于y，t相当于x）
    [x,y]=ode45(T_oil,t,T_oil_now);                %（求解微分方程，T_oil_now相当于y，t相当于x）
    T_oil_now=y(10);
 %基于平均油温计算顶层油温
%     u_p=exp(2797.3/(T_top_now+273))/exp(2797.3/(T_top_rate+273));
 
    t=1:1:10;
    T_top=@(t,T_top_now) ((1+R*K^2*P_cu_now)/(1+R)*(T_top_r-T_oil_r)-((T_top_now-T_oil_now)^(n1+1)/((u_p*(T_top_r-T_oil_r))^n1)))/t_top;  %顶油温度计算程序（微分方程，T_top_now相当于y，t相当于x）
    [x1,y1]=ode45(T_top,t,T_top_now);             %（求解微分方程，T_oil_now相当于y，t相当于x）
    T_top_now=y1(10);                              %10s后的顶层油温

 %基于平均油温计算绕组平均温度
%     u_p=exp(2797.3/(T_wnd_now+273))/exp(2797.3/(T_wnd_rate+273));
 
    t=1:1:10;
    T_wnd=@(t,T_wnd_now) (K^2*P_cu_now*(T_wnd_r-T_oil_r)-((T_wnd_now-T_oil_now)^(n1+1)/((u_p*(T_wnd_r-T_oil_r))^n1)))/t_wnd;               %绕组平均油温. %（微分方程，T_wnd_now相当于y，t相当于x）
    [x2,y2]=ode23(T_wnd,t,T_wnd_now);                %（求解微分方程，T_wnd_now相当于y，t相当于x）
    T_wnd_now=y2(10);                               %10s后的平均绕组温度
    
    T_hs_now=H*(T_wnd_now-T_oil_now)+T_top_now;                      %热点温度估算值.
    if Y_paper==0
        V_now=2^((T_hs_now-98)/6);                                   %相对老化速率
    else
        V_now=exp(15000/(110+273)-15000/(T_hs_now+273));            %相对老化速率
    end
    L2=10*V_now;
    L1=L1+L2;        
end

%每十五分钟记录一次数据
T_oil_G(i-1)=T_oil_now;
T_top_G(i-1)=T_top_now;
T_wnd_G(i-1)=T_wnd_now;
T_hs_G(i-1)=T_hs_now;  
V_G(i-1)=V_now;
L=L+L1;                                                              %相对寿命损失
L1=0;

if (T_hs_now>=Tlimit_hs|T_top_now>=Tlimit_top|K>=Klimit_fu|L>=Llimit_L)              %判断负荷是否超出范围 
    break;
end
end

if (T_hs_now>=Tlimit_hs)                                                   %输出越限条件
    disp('热点温度越限'); 
else if (T_top_now>=Tlimit_top)
    disp('顶层油温越限'); 
else if (K>=Klimit_fu)
    disp('负荷率越限'); 
else if (L>=Llimit_L)
    disp('相对老化速率越限'); 
    end
    end
    end
end

if (T_hs_now>=Tlimit_hs|T_top_now>=Tlimit_top|K>=Klimit_fu|L>=Llimit_L)   %判断负荷是否超出约束范围 
     break; 
end

   I_H_current=zeros(1,num);         %电流
   K1=K*I_H_DC+0.01*K1;
   for j=1:num
    I_H_current(j)=K1;
   end

T_hs_G1=[T_hs_0(1),T_hs_G];
T_top_G1=[T_top_0(1),T_top_G];
V_G1=[V_0(1),V_G]; 

 end

 if Flimit_IC>1
    disp('存在潜伏性故障，变压器过负荷运行有风险，建议尽快检修。');
    K=(Flimit_IC-1)*(K-0.01);
else
    K=Flimit_IC*(K-0.01);
end

x3=1:interval:interval*num;

%  plot(x3,T_hs_measured,'m-.^');
hold on
grid on
plot(x3,T_hs_G1,'r-.*');
plot(x3,T_top_G1,'k--*');
plot(x3,V_G1,'b--^');


legend ('热点温度计算值','顶层油温计算值','顶层油温测量值')            %,,'热点温度测量值')
%title('变压器热路模型热点温度计算')
ylabel('温度/摄氏度')
xlabel('时间/分钟')

T_hs_G1
T_top_G1
V_G1
K
 end