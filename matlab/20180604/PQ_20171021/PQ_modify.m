[x]=xlsread('pqinput.xls','A2:A2');
[y]=xlsread('pqinput.xls','B2:B2');
e=xlsread('pqinput.xls','B4:B4');
[point]=xlsread('pqinput.xls','D3:H100');
[zhilu]=xlsread('pqinput.xls','J3:R100');
TYPE=zeros(x,1);
U=zeros(x,1);
a=zeros(x,1);
P=zeros(x,1);
Q=zeros(x,1);
I=zeros(y,1);
J=zeros(y,1);

Rij=zeros(y,1);
Xij=zeros(y,1);
Zij=Rij+j*Xij; 
Y=zeros(x); 
G=zeros(x); 
B=zeros(x); 
B0=zeros(y,1);
RT=zeros(y,1);
XT=zeros(y,1);
ZT=RT+j*XT;
KT=zeros(y,1); 
%------------------------------���󸳳�ֵ��
TYPE=point(:,1);
U=point(:,2);
a=point(:,3);
P=point(:,4);
Q=point(:,5);
I=zhilu(:,1);
J=zhilu(:,2);
Rij=zhilu(:,3);
Xij=zhilu(:,4);
Zij=Rij+j*Xij;
B0=zhilu(:,5);
RT=zhilu(:,6);
XT=zhilu(:,7);
ZT=RT+j*XT;
KT=zhilu(:,8);
W=zhilu(:,9);
%------------------------------��ڵ㵼�ɾ���Y
for m=1:y 
    if KT(m)==0
        Y(I(m),J(m))=-1/Zij(m);
        Y(J(m),I(m))=-1/Zij(m);
    else 
          Y(I(m),J(m))=-1/(KT(m)*ZT(m));
          Y(J(m),I(m))=-1/(KT(m)*ZT(m));
    end
end

for m=1:x  
    for n=1:y
       if KT(n)==0
           if(I(n)==m|J(n)==m)
               Y(m,m)=Y(m,m)-Y(I(n),J(n))+j*B0(n)/2;
           end
       elseif W(n)==0
           if I(n)==m
           		Y(m,m)=Y(m,m)-Y(I(n),J(n))+(KT(n)-1)/KT(n)*(1/ZT(n));
       		 elseif J(n)==m  
           		Y(m,m)=Y(m,m)-Y(I(n),J(n))+(1-KT(n))/(KT(n)^2)*(1/ZT(n)); 
           end
       else
           if I(n)==m
               Y(m,m)=Y(m,m)-Y(I(n),J(n))+(1-KT(n))/(KT(n)^2)*(1/ZT(n)); 
           elseif J(n)==m
               Y(m,m)=Y(m,m)-Y(I(n),J(n))+(KT(n)-1)/KT(n)*(1/ZT(n));
           else  
               Y(m,m)=Y(m,m);
           end
       end
   end
end

G=real(Y);

%-----------------------��B'�����������B1
 B=imag(Y);
 
 ph=find(TYPE(:,1)==3);

 BB=B;

 BB(:,ph)=[];

 BB(ph,:)=[];

 B1=BB;

 B1=inv(B1);
 %-----------------------%��B''���������B2
 phpv=find(TYPE(:,1)>1);

 BB=B; 

 BB(:,phpv)=[];

 BB(phpv,:)=[];

 B2=BB; 
 
 disp(B2);
 
 B2=inv(B2);
 %-------------������ڵ��й����ʲ�ƽ����deltaPi
 k=0; 
 kp=1;
 kq=1;
 
 while(((kp~=0)||(kq~=0))&&k<=20)
 		kp=1;
 		kq=1;
 		notph=find(TYPE(:,1)<3);
 		deltaPi=zeros(x-1,1);
 		pq=find(TYPE(:,1)==1);
 		pqnum=size(B2);
 		pqnum=pqnum(1);
 		deltaQi=zeros(pqnum,1);
 		
 		for m=1:(x-1)
    	sum1=0;
      
      for n=1:x
 				sum1=sum1+U(notph(m))*U(n)*(G(notph(m),n)*cos(a(notph(m))-a(n))+B(notph(m),n)*sin(a(notph(m))-a(n)));
      end
      
      deltaPi(m)=P(notph(m))-sum1;
 		end 
    
    max1=max(abs(deltaPi));
    
    if max1<=e  
       kp=0;
       if kq==0 break  %��������
       else   %��������ѹ������       
       		for m=1:pqnum
         		sum2=0;
         		
         		for n=1:x
							sum2=sum2+U(pq(m))*U(n)*(G(pq(m),n)*sin(a(pq(m))-a(n))-B(pq(m),n)*cos(a(pq(m))-a(n)));  
         		end
         		
         		deltaQi(m)=Q(pq(m))-sum2;
       		end
          max2=max(abs(deltaQi));
      		
      		if max2<=e       		
      			kq=0;         	
         		if kp==0 break
         		else k=k+1;
         		end         	
      		else %��������ѹ������
      			Uq=U;
      			Uq(phpv)=[];
      			Upq=Uq;
     				deltaU=-B2*(deltaQi./Upq);
     				
     				for m=1:pqnum  
         			U(pq(m))=U(pq(m))+deltaU(m);
     				end
     				
     				kp=1;
     				k=k+1;
      		end
       end   
    else  %���й���ƽ���������㾫��Ҫ��
     	 Up=U; 
     	 Up(ph)=[];
     	 Unotph=Up;
     	 deltaa=((-B1*(deltaPi./Unotph))./Unotph);   %�������������
     	 
     	 for m=1:(x-1)
     			a(notph(m))=a(notph(m))+deltaa(m);  %�������
     	 end
     	 
     	 kq=1;
       
       for m=1:pqnum    %�޹���ƽ��������
         sum2=0;
         
         for n=1:x
						sum2=sum2+U(pq(m))*U(n)*(G(pq(m),n)*sin(a(pq(m))-a(n))-B(pq(m),n)*cos(a(pq(m))-a(n)));  
         end
         
         deltaQi(m)=Q(pq(m))-sum2;  %�����޹���ƽ����
       end  
      
       Uq=U;
       Uq(phpv)=[];
       Upq=Uq;
       deltaU=-B2*(deltaQi./Upq);
    
       for m=1:pqnum  
       		U(pq(m))=U(pq(m))+deltaU(m);  %��ѹ����
    	 end
       
       kp=1;
    	 k=k+1;
    end
 end //while
 
 
 sum3=0+j*0; 
 for m=1:x
 		sum3=sum3+conj(Y(ph,m))*(U(m)*cos(a(m))-i*U(m)*sin(a(m)))
 end
 
 Sph=(U(ph)*cos(a(ph))+j*U(ph)*sin(a(ph)))*sum3;
 %-----------------����·����Sij��Sji
 Sij=zeros(y,1);
 Sji=zeros(y,1);
 
 for m=1:y
 		if KT(m)==0   
 		   1:Sij(m)=(U(I(m))*cos(a(I(m)))+i*U(I(m))*sin(a(I(m))))*((U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m))))*(  -i*B0(m)/2                         )+(U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m)))-U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m))))*conj(-Y(I(m),J(m)))); 
           1:Sji(m)=(U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m))))*((U(J(m))*cos(a(J(m)))-i*U(J(m))*sin(a(J(m))))*(  -i*B0(m)/2                         )+(U(J(m))*cos(a(J(m)))-i*U(J(m))*sin(a(J(m)))-U(I(m))*cos(a(I(m)))+i*U(I(m))*sin(a(I(m))))*conj(-Y(I(m),J(m))));
    	   
    	   2:Sij(m)=(U(I(m))*cos(a(I(m)))+i*U(I(m))*sin(a(I(m))))*((U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m))))*(  (1-KT(m))/(KT(m))^2*conj(1/ZT(m))  )+(U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m)))-U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m))))*conj(-Y(I(m),J(m))));
           2:Sji(m)=(U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m))))*((U(J(m))*cos(a(J(m)))-i*U(J(m))*sin(a(J(m))))*(  (KT(m)-1)/KT(m)*conj(1/ZT(m))      )+(U(J(m))*cos(a(J(m)))-i*U(J(m))*sin(a(J(m)))-U(I(m))*cos(a(I(m)))+i*U(I(m))*sin(a(I(m))))*conj(-Y(I(m),J(m))));
           
           3:Sij(m)=(U(I(m))*cos(a(I(m)))+i*U(I(m))*sin(a(I(m))))*((U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m))))*(  (KT(m)-1)/KT(m)*conj(1/ZT(m))      )+(U(I(m))*cos(a(I(m)))-i*U(I(m))*sin(a(I(m)))-U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m))))*conj(-Y(I(m),J(m))));
           3:Sji(m)=(U(J(m))*cos(a(J(m)))+i*U(J(m))*sin(a(J(m))))*((U(J(m))*cos(a(J(m)))-i*U(J(m))*sin(a(J(m))))*(  (1-KT(m))/(KT(m))^2*conj(1/ZT(m))  )+(U(J(m))*cos(a(J(m)))-i*U(J(m))*sin(a(J(m)))-U(I(m))*cos(a(I(m)))+i*U(I(m))*sin(a(I(m))))*conj(-Y(I(m),J(m))));
       end
    end
 end //for
 
 deltaSij=Sij+Sji;    %��·�������
 S=zeros(x,1)+i*zeros(x,1);
 
 for b=1:x 
   for m=1:y
   		if I(m)==b 
      		S(b)=S(b)+Sij(m);
      else
        if J(m)==b
           S(b)=S(b)+Sji(m);
        else  
           S(b)=S(b);
        end
  	  end
	 end //for m
 end //for b
 
 P=real(S);  
 Q=imag(S);  

 sumdeltaS=sum(S);



fid=fopen('shiyanjieguo.txt','wt');
fprintf(fid,'             ******************************PQ�ֽⷨ������********************************\n');
fprintf(fid,'                                       *****************����������*************\n');
fprintf(fid,'                                       ��������kΪ: %d \n',k);
fprintf(fid,'                                       ================================================\n');
fprintf(fid,'                                       ƽ��ڵ�%d�ĸ�����SphΪ: %f+j*(%f) \n',ph,real(Sph),imag(Sph));  
fprintf(fid,'                                       ================================================\n');
fprintf(fid,'                                       �ڵ��ѹUΪ:  \n');
for m=1:x
    fprintf(fid,'                                   ��%d���ڵ��ѹ: %f\n',m,U(m));
end
fprintf(fid,'                                       ================================================\n');
fprintf(fid,'                                       �ڵ����aΪ:  \n');
for m=1:x
    fprintf(fid,'                                   ��%d���ڵ����: %f\n',m,a(m));
end
fprintf(fid,'                                       ================================================\n');
fprintf(fid,'                                       �ڵ㸴����SΪ:  \n');
for m=1:x
    fprintf(fid,'                                   ��%d���ڵ㸴����: %f+i*(%f)\n',m,real(S(m)),imag(S(m)));
end
fprintf(fid,'                                       ================================================\n');
fprintf(fid,'                                       �ڵ��й�����PΪ:  \n');
for m=1:x
    fprintf(fid,'                                   ��%d���ڵ��й�����: %f\n',m,P(m));
end
fprintf(fid,'                                       ================================================\n');
fprintf(fid,'                                      �ڵ��޹�����QΪ:  \n');
for m=1:x
    fprintf(fid,'                                  ��%d���ڵ��޹�����Q: %f\n',m,Q(m));
end
fprintf(fid,'                                      ================================================\n');
fprintf(fid,'                                      ��·����Sij��SjiΪ:  \n');
for m=1:y
    fprintf(fid,'                                  �ڵ�%d���ڵ�%d�Ĺ���Ϊ:  %f+i*(%f) \n',I(m),J(m),real(Sij(m)),imag(Sij(m)));
    fprintf(fid,'                                   �ڵ�%d���ڵ�%d�Ĺ���Ϊ:  %f+i*(%f) \n',J(m),I(m),real(Sji(m)),imag(Sji(m)));
end
fprintf(fid,'                                      ================================================\n');
fprintf(fid,'                                      ���������sumdeltaSΪ: %f+i*(%f) \n',real(sumdeltaS),imag(sumdeltaS));
fprintf(fid,'                                      ================================================\n');
fprintf(fid,'                                      ��·�������deltaSijΪ:  \n');
for m=1:y
    fprintf(fid,'                                  %d--%d��·�Ĺ������Ϊ:  %f+i*(%f) \n',I(m),J(m),real(deltaSij(m)),imag(deltaSij(m)));
end
fclose(fid);         %�ر��ļ�,�������


