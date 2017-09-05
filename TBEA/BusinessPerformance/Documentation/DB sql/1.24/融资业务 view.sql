create view financing_bank_currency_credit_v as 
select 
	case
		when bankName = '���ҿ�������' and currency='RMB' then '������'
		when bankName = '�й�����������' and currency='RMB'  then '����������'
		when bankName = '�й���������' and currency='RMB'  then '��������'
		when bankName = '�й�ũҵ����' and currency='RMB'  then 'ũҵ����'
		when bankName = '�й�����' and currency='RMB'  then '�й�����'
		when bankName = '�й���������' and currency='RMB'  then '��������'
		when bankName = '��ͨ����' and currency='RMB'  then '��ͨ����'
		when bankName = '�Ϻ��ֶ���չ����' and currency='RMB'  then '�ַ�����'
		when bankName = '��ҵ����' and currency='RMB'  then '��ҵ����'
		when bankName = '��������' and currency='RMB'  then '��������'
		when bankName in ('�й��������',
			'�㷢����',
			'��������',
			'��������',
			'�й���������',
			'ƽ������',
			'�ൺ����',
			'�й�������������',
			'��³ľ������',
			'��������') and currency='RMB'  then '������С����'
		else '--' end  	bankNo,
	*
from financing_bank_currency_credit;

create view financing_bank_currency_credit_total_v as 
select 
case 					
	 when bankName = '���ҿ�������' then '���ҿ�������'
	 when bankName = '����������' then '����������'
	 when bankName = '��������' then '��������'
	 when bankName = 'ũҵ����' then 'ũҵ����'
	 when bankName = '�й�����' then '�й�����'
	 when bankName = '��������' then '��������'
	 when bankName = '��ͨ����' then '��ͨ����'
	 when bankName = '�ַ�����' then '�ַ�����'
	 when bankName = '��ҵ����' then '��ҵ����'
	 when bankName = '��������' then '��������'
	 when bankName = '�������' then '�������'
	 when bankName = '�㷢����' then '�㷢����'
	 when bankName = '��������' then '��������'
	 when bankName = '��������' then '��������'
	 when bankName = '��������' then '��������'
	 when bankName = '��������' then '��������'
	 when bankName = 'ƽ������' then 'ƽ������'
	 when bankName = '�ൺ����' then '�ൺ����'
	 when bankName = '�ʴ���' then '�ʴ���'
	 when bankName = '��ᣨ�й���' then '��ᣨ�й���'
	 when bankName = '�������У��й���' then '�������У��й���'
	 when bankName = '�������' then '�������'
	 when bankName = 'HDFC����' then 'HDFC����'
	 when bankName = 'ӡ��SBI����' then 'ӡ��SBI����'
	 when bankName = 'ӡ��ICBC����' then 'ӡ��ICBC����'
	 when bankName = 'Deuetsche����־����' then 'Deuetsche����־����'
	 when currency='RMB' then '������ҵ����'
	 else bankName end	bankNo,
	*
from financing_bank_currency_credit 

