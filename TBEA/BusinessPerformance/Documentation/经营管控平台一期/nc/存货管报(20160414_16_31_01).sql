-----------------------------存货账面表-----------------------------------(账面原值为净额+减值）
select unit_code,
       unit_name,
       inputdate,
       imda.m10083, --账面净额
       imdo.m10000 --减值
  from iufo_measure_data_aabf9rn7 imda
  left join iufo_measure_data_osrehdc8 imdo
    on imda.alone_id = imdo.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imda.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0


-----------------------------能源存货-----------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd8.m10006 yclnc, --原材料（年初）
       imd8.m10010 kcspnc, --库存商品（年初）
       imd8.m10002 dpbtfnc, --生产成本-待配比土方（年初）
       imd8.m10008 fcspnc, --发出商品（年初）
       imd8.m10000 dhnc, --低耗（年初）
       imd8.m10004 hjnc, --合计（年初）
       imd8.m10007 ycl, --原材料（期末）
       imd8.m10011 kcsp, --库存商品（期末）
       imd8.m10003 dpbtf, --生产成本-待配比土方（期末）
       imd8.m10009 fcsp, --发出商品（期末）
       imd8.m10001 dh, --低耗（期末）
       imd8.m10005 hj --合计（期末）
  from iufo_measure_data_844a2dr9 imd8
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd8.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
