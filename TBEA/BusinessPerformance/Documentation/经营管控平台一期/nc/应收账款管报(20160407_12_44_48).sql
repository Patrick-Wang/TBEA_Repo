---------------��1 Ӧ���ʿ������-----------------
select 
iui.unit_code,
iui.unit_name,--��λ����
imdx.m10063 yz,--Ӧ���˿�ԭֵ
imdx.m10151 hzzb,--����׼��
imdx.m10132 jz,--��ֵ
inputdate--����
from
iufo_measure_data_xyy6hd5t imdx
left join (select alone_id,code,inputdate,keyword2,keyword3,time_code,ts,ver from iufo_measure_pubdata ) imp on imdx.alone_id = imp.alone_id
left join (select unit_id,unit_code,unit_name from iufo_unit_info) iui on imp.code = iui.unit_id
where imp.ver = 0
--and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
order by unit_code,inputdate desc

--------------��2 Ӧ���˿�����仯------------------
select 
iui.unit_code,
iui.unit_name,--��λ����
imdx.m10080 wu,--��������
imdx.m10020 siwu,--��������
imdx.m10111 sansi,--��������
imdx.m10097 ersan,--��������
imdx.m10144 yier,--һ������
imdx.m10016 yi,--һ������
imdx.m10033 hj,--�ϼ�
inputdate--����
from
iufo_measure_data_xyy6hd5t imdx
left join (select alone_id,code,inputdate,keyword2,keyword3,time_code,ts,ver from iufo_measure_pubdata ) imp on imdx.alone_id = imp.alone_id
left join (select unit_id,unit_code,unit_name from iufo_unit_info) iui on imp.code = iui.unit_id
where imp.ver = 0
--and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
order by unit_code,inputdate desc
