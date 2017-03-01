CREATE VIEW user_usage_category AS
select usageid, url, category=(
case
	when(
	url like '%hzb_zbhz.do'or
	url like '%hzb_companys.do'or
	url like '%gcy_zbhz.do'or
	url like '%gdw_zbhz.do'or
	url like '%acyhzzb.do')
	then '��Ӫָ��������'

	when(
	url like '%hzb_zbhz_prediction.do'or
	url like '%hzb_companys_prediction.do'or
	url like '%financial_zbhz_prediction.do'or
	url like '%gdw_zbhz_prediction.do')
	then '��Ӫָ��Ԥ�����'

	when(
	url like '%AllCompanysNC_overview.do'or
	url like '%CompanysNC.do'or
	url like '%dbxq.do'or
	url like '%jjzjz.do'or
	url like '%cwyjsf/show.do'or
	url like '%cwcpdlml/show.do'or
	url like '%cwgbjyxxjl/show.do')
	then '�������'

	when(
	url like '%yszkrb/yszk.do'or
	url like '%ydzb/xjlrb.do'or
	url like '%companys_ranking.do'or
	url like '%xtnyrb.do')
	then '��Ӫָ�����'


	when(
	url like '%ztdbfx.do'or
	url like '%fldbfx.do')
	then '���й�˾�Ա�����'


	when(
	url like '%bidCollectionWrapper.do'or
	url like '%WinBidModelCollection.do'or
	url like '%gdwddfxhCollection.do'or
	url like '%gdwddCollection.do'or
	url like '%ddfjdCollection.do'or
	url like '%codeCollection.do'or
	url like '%modelCollection.do'or
	url like '%ddDetailJsp.do')
	then '����ȫ���̹ܿ�'

	when(
	url like '%scqytjqk.do'or
	url like '%scqy.do'or
	url like '%scjb.do')
	then '�г�ǩԼ��Ϣ'

	when(
	url like '%zhzl.do'or
	url like '%zhgsljzbhz.do'or
	url like '%zhgsydzbhz.do'or
	url like '%zhgsfcyUnion.do')
	then '�ں�ָ��'


	when(
	url like '%/jcycljg/show.do'or
	url like '%/import/show.do')
	then '�۸�����ݻ���'

	when(
	url like '%yszkgb/show.do'or
	url like '%chgb/show.do')
	then 'ͨ�þ�Ӫ�ܱ�'

	when(
	url like '%dzwzgb/show.do'or
	url like '%wlydd/show.do'or
	url like '%sbdczclwcqk/show.do'or
	url like '%sbdscqyqk/show.do'or
	url like '%wgcpqk/show.do')
	then '�����ҵ��Ӫ�ܱ�'

	when(
	url like '%cbfx/show.do'or
	url like '%nyzbscqk/show.do')
	then '��Դ��ҵ��Ӫ����'

	when(
	url like '%xnychFrame/show.do'or
	url like '%report/gcyzb.do'or
	url like '%report/yszkhkzb.do'or
	url like '%report/xnyzb.do'or
	url like '%report/xnyqyzb.do')
	then '����Դ��ҵ��Ӫ����'
	else
	'δ����'
end)
from 
	user_usage_ex