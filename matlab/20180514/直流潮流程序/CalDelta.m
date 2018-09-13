function delta=CalDelta(Bus,Y,P)
%º∆À„delta   Y*delta=P
global Bus Y P;

[nb,mb]=size(Bus);
delta=zeros(nb-1,1);
delta=delta+inv(Y)*P;



