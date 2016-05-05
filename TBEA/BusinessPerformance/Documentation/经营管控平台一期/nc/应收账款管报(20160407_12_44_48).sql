---------------表1 应收帐款账面表-----------------
select 
iui.unit_code,
iui.unit_name,--单位名称
imdx.m10063 yz,--应收账款原值
imdx.m10151 hzzb,--坏账准备
imdx.m10132 jz,--净值
inputdate--日期
from
iufo_measure_data_xyy6hd5t imdx
left join (select alone_id,code,inputdate,keyword2,keyword3,time_code,ts,ver from iufo_measure_pubdata ) imp on imdx.alone_id = imp.alone_id
left join (select unit_id,unit_code,unit_name from iufo_unit_info) iui on imp.code = iui.unit_id
where imp.ver = 0
--and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
order by unit_code,inputdate desc

--------------表2 应收账款账龄变化------------------
select 
iui.unit_code,
iui.unit_name,--单位名称
imdx.m10080 wu,--五年以上
imdx.m10020 siwu,--四至五年
imdx.m10111 sansi,--三至四年
imdx.m10097 ersan,--二至三年
imdx.m10144 yier,--一至二年
imdx.m10016 yi,--一年以内
imdx.m10033 hj,--合计
inputdate--日期
from
iufo_measure_data_xyy6hd5t imdx
left join (select alone_id,code,inputdate,keyword2,keyword3,time_code,ts,ver from iufo_measure_pubdata ) imp on imdx.alone_id = imp.alone_id
left join (select unit_id,unit_code,unit_name from iufo_unit_info) iui on imp.code = iui.unit_id
where imp.ver = 0
--and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
order by unit_code,inputdate desc
