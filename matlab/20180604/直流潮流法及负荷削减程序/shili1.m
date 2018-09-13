% data for hainan beibu
disp('hainan beibu')
%(bus#)( ang  )(bus type)
Bus=[
   1      0.0000    1;
   2      0.0000    2;
   3      0.0000    3];

%Load ID    PL
Load=[
     1    230;
     2    580;
     3    296;
           ];
 
%Generator  ID    PG
 Generator=[
     1   420;
     2   0;
     3   560;
    ]; 

%      b#1 b#2 (name)    (x)    (Capacity) (longth)
Line = [
       1   2	1	0.00497 300;
       1   3	1	0.00551 289;
       2   3	1	0.00504 251;
       ];