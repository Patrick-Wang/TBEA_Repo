create view financing_bank_RMB_credit_v as select 
	case bankName 
		when '������' then '������'
		when '����������' then '����������'
		when '��������' then '��������'
		when 'ũҵ����' then 'ũҵ����'
		when '�й�����' then '�й�����'
		when '��������' then '��������'
		when '��ͨ����' then '��ͨ����'
		when '�ַ�����' then '�ַ�����'
		when '��ҵ����' then '��ҵ����'
		when '��������' then '��������'
		else '������С����' end 	bankNo,
	*
from financing_bank_RMB_credit;
