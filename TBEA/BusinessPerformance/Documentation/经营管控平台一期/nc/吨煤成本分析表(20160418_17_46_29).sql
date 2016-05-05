select iui.unit_code,
       iui.unit_name, --单位名称
       inputdate,
       imdc.m10028   tfblbpcbsj, --土方剥离爆破成本实际
       imdc.m10004   ymbpcbsj, --原煤爆破成本实际
       imdc.m10040   ymcycbsj, --原煤采运成本实际
       imdc.m10016   hsdycbsj, --回筛倒运成本实际
       imdc.m10052   zccbsj, --装车成本实际
       imdc.m10034   zjcbxjsj, --直接成本小计实际
       imdc.m10010   fkkcbsj, --非可控成本实际
       imdc.m10046   kkcbsj, --可控成本实际
       imdc.m10022   zzfyxjsj, --制造费用小计实际
       imdc.m10058   sccbhjsj, --生产成本合计实际
       imdc.m6gtjtk  dyrklsj --当月入库量实际
  from iufo_measure_data_c5lltnso imdc
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdc.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 order by unit_code, inputdate desc
