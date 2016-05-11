
----------------应交税金表（本年期初数）------------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10064   zzs_c, --增值税期初
       imd5.m10040   xfs_c, --消费税期初
       imd5.m10016   yys_c, --营业税期初
       imd5.m10120   cjs_c, --城建税期初
       imd5.m10096   jyffj_c, --教育费附加(包括地方教育费附加)期初
       imd5.m10073   qysds_c, --企业所得税期初
       imd5.m10048   tdsys_c, --土地使用税期初
       imd5.m10024   tdzzs_c, --土地增值税期初
       imd5.m10000   ccsys_c, --车船使用税期初
       imd5.m10104   fcs_c, --房产税期初
       imd5.m10080   yhs_c, --印花税期初
       imd5.m10055   grsds_c, --个人所得税期初
       imd5.m10031   zys_c, --资源税期初
       imd5.m10007   gs_c, --关税期初
       imd5.m10087   hj_c --合计期初（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
   and to_number(substr(inputdate, 6, 2)) = 1
  -- and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
 order by unit_code, inputdate desc



---------------应交税费表（本月应交数）----------------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10062   zzs_yj, --增值税应交
       imd5.m10038   xfs_yj, --消费税应交
       imd5.m10014   yys_yj, --营业税应交
       imd5.m10118   cjs_yj, --城建税应交
       imd5.m10094   jyffj_yj, --教育费附加(包括地方教育费附加)应交
       imd5.m10071   qysds_yj, --企业所得税应交
       imd5.m10046   tdsys_yj, --土地使用税应交
       imd5.m10022   tdzzs_yj, --土地增值税应交
       imd5.m10127   ccsys_yj, --车船使用税应交
       imd5.m10102   fcs_yj, --房产税应交
       imd5.m10078   yhs_yj, --印花税应交
       imd5.m10054   grsds_yj, --个人所得税应交
       imd5.m10030   zys_yj, --资源税应交
       imd5.m10006   gs_yj, --关税应交
       imd5.m10086   hj_yj --合计应交（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
--and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
 order by unit_code, inputdate desc
 
 --------------应交税费表（本月已交数）-----------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10060   zzs_yij, --增值税已交
       imd5.m10036   xfs_yij, --消费税已交
       imd5.m10012   yys_yij, --营业税已交
       imd5.m10116   cjs_yij, --城建税已交
       imd5.m10092   jyffj_yij, --教育费附加(包括地方教育费附加)已交
       imd5.m10068   qysds_yij, --企业所得税已交
       imd5.m10044   tdsys_yij, --土地使用税已交
       imd5.m10020   tdzzs_yij, --土地增值税已交
       imd5.m10124   ccsys_yij, --车船使用税已交
       imd5.m10100   fcs_yij, --房产税已交
       imd5.m10076   yhs_yij, --印花税已交
       imd5.m10052   grsds_yij, --个人所得税已交
       imd5.m10028   zys_yij, --资源税已交
       imd5.m10004   gs_yij, --关税已交
       imd5.m10084   hj_yij --合计已交（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 order by unit_code, inputdate desc
 
  --------------应交税费表（本月未交数）-----------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10058   zzs_wj, --增值税未交
       imd5.m10034   xfs_wj, --消费税未交
       imd5.m10010   yys_wj, --营业税未交
       imd5.m10114   cjs_wj, --城建税未交
       imd5.m10090   jyffj_wj, --教育费附加(包括地方教育费附加)未交
       imd5.m10066   qysds_wj, --企业所得税未交
       imd5.m10042   tdsys_wj, --土地使用税未交
       imd5.m10018   tdzzs_wj, --土地增值税未交
       imd5.m10122   ccsys_wj, --车船使用税未交
       imd5.m10098   fcs_wj, --房产税未交
       imd5.m10074   yhs_wj, --印花税未交
       imd5.m10050   grsds_wj, --个人所得税未交
       imd5.m10026   zys_wj, --资源税未交
       imd5.m10002   gs_wj, --关税未交
       imd5.m10082   hj_wj --合计未交（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 order by unit_code, inputdate desc
 
 ----------------应交税金表（本月期末数）------------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
imd5.m10056 zzs_m,--增值税期末数
imd5.m10032 xfs_m,--消费税期末数
imd5.m10008 yys_m,--营业税期末数
imd5.m10112 cjs_m,--城建税期末数
imd5.m10088 jyffj_m,--教育费附加(包括地方教育费附加)期末数
imd5.m10063 qysds_m,--企业所得税期末数
imd5.m10039 tdsys_m,--土地使用税期末数
imd5.m10015 tdzzs_m,--土地增值税期末数
imd5.m10119 ccsys_m,--车船使用税期末数
imd5.m10095 fcs_m,--房产税期末数
imd5.m10070 yhs_m,--印花税期末数
imd5.m10047 grsds_m,--个人所得税期末数
imd5.m10023 zys_m,--资源税期末数
imd5.m10126 gs_m,--关税期末数
imd5.m10079 hj_m--合计期末数（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 order by unit_code, inputdate desc
 
 ---------------应交税费表（累计应交数）----------------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10061   zzs_ljyj, --增值税累计应交
       imd5.m10037   xfs_ljyj, --消费税累计应交
       imd5.m10013   yys_ljyj, --营业税累计应交
       imd5.m10117   cjs_ljyj, --城建税累计应交
       imd5.m10093   jyffj_ljyj, --教育费附加(包括地方教育费附加)累计应交
       imd5.m10069   qysds_ljyj, --企业所得税累计应交
       imd5.m10045   tdsys_ljyj, --土地使用税累计应交
       imd5.m10021   tdzzs_ljyj, --土地增值税累计应交
       imd5.m10125   ccsys_ljyj, --车船使用税累计应交
       imd5.m10101   fcs_ljyj, --房产税累计应交
       imd5.m10077   yhs_ljyj, --印花税累计应交
       imd5.m10053   grsds_ljyj, --个人所得税累计应交
       imd5.m10029   zys_ljyj, --资源税累计应交
       imd5.m10005   gs_ljyj, --关税累计应交
       imd5.m10085   hj_ljyj --合计累计应交（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
--and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
 order by unit_code, inputdate desc
 
 
  ---------------应交税费表（累计已交数）----------------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10059   zzs_ljyij, --增值税累计已交
       imd5.m10035   xfs_ljyij, --消费税累计已交
       imd5.m10011   yys_ljyij, --营业税累计已交
       imd5.m10115   cjs_ljyij, --城建税累计已交
       imd5.m10091   jyffj_ljyij, --教育费附加(包括地方教育费附加)累计已交
       imd5.m10067   qysds_ljyij, --企业所得税累计已交
       imd5.m10043   tdsys_ljyij, --土地使用税累计已交
       imd5.m10019   tdzzs_ljyij, --土地增值税累计已交
       imd5.m10123   ccsys_ljyij, --车船使用税累计已交
       imd5.m10099   fcs_ljyij, --房产税累计已交
       imd5.m10075   yhs_ljyij, --印花税累计已交
       imd5.m10051   grsds_ljyij, --个人所得税累计已交
       imd5.m10027   zys_ljyij, --资源税累计已交
       imd5.m10003   gs_ljyij, --关税累计已交
       imd5.m10083   hj_ljyij --合计累计已交（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
--and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
 order by unit_code, inputdate desc

 ---------------应交税费表（累计未交数）----------------------
select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imd5.m10057   zzs_ljwj, --增值税累计未交
       imd5.m10033   xfs_ljwj, --消费税累计未交
       imd5.m10009   yys_ljwj, --营业税累计未交
       imd5.m10113   cjs_ljwj, --城建税累计未交
       imd5.m10089   jyffj_ljwj, --教育费附加(包括地方教育费附加)累计未交
       imd5.m10065   qysds_ljwj, --企业所得税累计未交
       imd5.m10041   tdsys_ljwj, --土地使用税累计未交
       imd5.m10017   tdzzs_ljwj, --土地增值税累计未交
       imd5.m10121   ccsys_ljwj, --车船使用税累计未交
       imd5.m10097   fcs_ljwj, --房产税累计未交
       imd5.m10072   yhs_ljwj, --印花税累计未交
       imd5.m10049   grsds_ljwj, --个人所得税累计未交
       imd5.m10025   zys_ljwj, --资源税累计未交
       imd5.m10001   gs_ljwj, --关税累计未交
       imd5.m10081   hj_ljwj --合计累计未交（其它税费没有值，直接用合计减去以上所列税费）
  from iufo_measure_data_55pe8cph imd5
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd5.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
--and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
 order by unit_code, inputdate desc
