-----------------------工程类收入20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdo.m10123 gnsr, --今年累计国内工程销售收入
       imd0.m10304 gnsbdsr, --今年累计其中：输变电工程销售收入
       imdo.m10102 gnsbdepcsr, --今年累计输变电-EPC模式销售收入
       imdo.m10076 gnsbdbtsr, --今年累计输变电-BT模式销售收入
       imdo.m10050 gnsbdqtsr, --今年累计输变电-其他模式销售收入
       imd0.m10278 gngfsr, --今今年累计光伏工程销售收入
       imdo.m10024 gngfepcsr, --今年累计光伏-EPC模式销售收入
       imdz.m10353 gngfbtsr, --今年累计光伏-BT模式销售收入
       imdz.m10327 gngfqtsr, --今年累计光伏-其他模式销售收入
       imd0.m10252 gnfdsr, --今年累计风电工程销售收入
       imdz.m10301 gnfdepcsr, --今年累计风电-EPC模式销售收入
       imdz.m10275 gnfdbtsr, --今年累计风电-BT模式销售收入
       imdz.m10249 gnfdqtsr, --今年累计风电-其他模式销售收入
       imdz.m10235 gjsr, --今年累计国际工程销售收入
       imd0.m10206 gjsbdsr, --今年累计其中：输变电工程销售收入
       imdz.m10214 gjsbdepcsr, --今年累计输变电-EPC模式销售收入
       imdz.m10188 gjsbdbtsr, --今年累计输变电-BT模式销售收入
       imdz.m10162 gjsbdqtsr, --今年累计输变电-其他模式销售收入
       imd0.m10180 gjgfsr, --今年累计今年累计 光伏工程销售收入
       imdz.m10136 gjgfepcsr, --今年累计光伏-EPC模式销售收入
       imdz.m10110 gjgfbtsr, --今年累计光伏-BT模式销售收入
       imdz.m10084 gjgfqtsr, --今年累计光伏-其他模式销售收入
       imd0.m10154 gjfdsr, --今年累计 风电工程销售收入
       imdz.m10058 gjfdepcsr, --今年累计风电-EPC模式销售收入
       imdz.m10032 gjfdbtsr, --今年累计风电-BT模式销售收入
       imdz.m10006 gjfdqtsr, --今年累计风电-其他模式销售收入
       imd0.m10128 xjsr --今年累计小计销售收入
  from iufo_measure_data_oxpoeewv imdo
  left join iufo_measure_data_0lmfwcux imd0
    on imdo.alone_id = imd0.alone_id
  left join iufo_measure_data_za2q47m4 imdz
    on imdo.alone_id = imdz.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdo.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 
 -----------------------工程类成本20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdo.m10122 gncb, --今年累计国内工程销售成本
       imd0.m10303 gnsbdcb, --今年累计其中：输变电工程销售成本
       imdo.m10101 gnsbdepccb, --今年累计输变电-EPC模式销售成本
       imdo.m10075 gnsbdbtcb, --今年累计输变电-BT模式销售成本
       imdo.m10049 gnsbdqtcb, --今年累计输变电-其他模式销售成本
       imd0.m10277 gngfcb, --今年累计光伏工程销售成本
       imdo.m10023 gngfepccb, --今年累计光伏-EPC模式销售成本
       imdz.m10352 gngfbtcb, --今年累计光伏-BT模式销售成本
       imdz.m10326 gngfqtcb, --今年累计光伏-其他模式销售成本
       imd0.m10251 gnfdcb, --今年累计风电工程销售成本
       imdz.m10300 gnfdepccb, --今年累计风电-EPC模式销售成本
       imdz.m10274 gnfdbtcb, --今年累计风电-BT模式销售成本
       imdz.m10248 gnfdqtcb, --今年累计风电-其他模式销售成本
       imdz.m10234 gjcb, --今年累计国际工程销售成本
       imd0.m10205 gjsbdcb, --今年累计其中：输变电工程销售成本
       imdz.m10213 gjsbdepccb, --今年累计输变电-EPC模式销售成本
       imdz.m10187 gjsbdbtcb, --今年累计输变电-BT模式销售成本
       imdz.m10161 gjsbdqtcb, --今年累计输变电-其他模式销售成本
       imd0.m10179 gjgfcb, --今年累计光伏工程销售成本
       imdz.m10135 gjgfepccb, --今年累计光伏-EPC模式销售成本
       imdz.m10109 gjgfbtcb, --今年累计光伏-BT模式销售成本
       imdz.m10083 gjgfqtcb, --今年累计光伏-其他模式销售成本
       imd0.m10153 gjfdcb, --今年累计风电工程销售成本
       imdz.m10057 gjfdepccb, --今年累计风电-EPC模式销售成本
       imdz.m10031 gjfdbtcb, --今年累计风电-BT模式销售成本
       imdz.m10005 gjfdqtcb, --今年累计风电-其他模式销售成本
       imd0.m10127 xjcb --今年累计小计销售成本(工程类)
  from iufo_measure_data_oxpoeewv imdo
  left join iufo_measure_data_0lmfwcux imd0
    on imdo.alone_id = imd0.alone_id
  left join iufo_measure_data_za2q47m4 imdz
    on imdo.alone_id = imdz.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdo.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 
 -----------------------新能源收入20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdl.m10056 danjgpsr, --今年累计单晶硅片销售收入
       imdl.m10030 duojgpsr, --今年累计多晶硅片销售收入
       imdl.m10004 zfbsr, --今年累计准方棒销售收入
       imdk.m10333 djdsr, --今年累计多晶锭销售收入
       imdk.m10307 ybsr, --今年累计圆棒销售收入
       imdk.m10281 bwdbqsr, --今年累计并网逆变器销售收入
       imdk.m10255 hlxsr, --今年累计汇流箱销售收入
       imdk.m10229 pdgsr, --今年累计配电柜销售收入
       imdk.m10203 ythjfsr, --今年累计一体化机房销售收入
       imdk.m10177 cdzcpsr, --今年累计充电桩产品销售收入
       imdk.m10151 svgsr, --今年累计SVG销售收入
       imdk.m10125 svgbcpsr, --今年累计SVG半成品销售收入
       imdk.m10099 djgsr, --今年累计多晶硅销售收入
       imdk.m10073 bthsr, --今年累计白炭黑销售收入
       imdk.m10047 jqksr, --今年累计加气块销售收入
       imdk.m10021 qtsr, --今年累计其他销售收入
       imd0.m10350 xjsr --今年累计小计销售收入
  from iufo_measure_data_l1hnj73b imdl
  left join iufo_measure_data_kuxp4aaf imdk
    on imdl.alone_id = imdk.alone_id
  left join iufo_measure_data_0lmfwcux imd0
    on imdl.alone_id = imd0.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdl.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0


 -----------------------新能源成本20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdl.m10055 danjgpcb, --今年累计单晶硅片销售成本
       imdl.m10029 duojgpcb, --今年累计多晶硅片销售成本
       imdl.m10003 zfbcb, --今年累计准方棒销售成本
       imdk.m10332 djdcb, --今年累计多晶锭销售成本
       imdk.m10306 ybcb, --今年累计圆棒销售成本
       imdk.m10280 bwdbqcb, --今年累计并网逆变器销售成本
       imdk.m10254 hlxcb, --今年累计汇流箱销售成本
       imdk.m10228 pdgcb, --今年累计配电柜销售成本
       imdk.m10202 ythjfcb, --今年累计一体化机房销售成本
       imdk.m10176 cdzcpcb, --今年累计充电桩产品销售成本
       imdk.m10150 svgcb, --今年累计SVG销售成本
       imdk.m10124 svgbcpcb, --今年累计SVG半成本品销售成本
       imdk.m10098 djgcb, --今年累计多晶硅销售成本
       imdk.m10072 bthcb, --今年累计白炭黑销售成本
       imdk.m10046 jqkcb, --今年累计加气块销售成本
       imdk.m10020 qtcb, --今年累计其他销售成本
       imd0.m10349 xjcb --今年累计小计销售成本
  from iufo_measure_data_l1hnj73b imdl
  left join iufo_measure_data_kuxp4aaf imdk
    on imdl.alone_id = imdk.alone_id
  left join iufo_measure_data_0lmfwcux imd0
    on imdl.alone_id = imd0.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdl.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0

 -----------------------煤炭产业收入20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10249 dksr, --今年累计大块销售收入
       imd5.m10223 zksr, --今年累计中块销售收入
       imd5.m10197 xzksr, --今年累计小中块销售收入
       imd5.m10171 sbksr, --今年累计三八块销售收入
       imd5.m10145 ewksr, --今年累计二五块销售收入
       imd5.m10119 slksr, --今年累计四六块销售收入
       imd5.m10093 jcmsr, --今年累计锯采煤销售收入
       imd5.m10067 mmsr, --今年累计末煤销售收入
       imd5.m10041 xjsr --今年累计小计销售收入
  from iufo_measure_data_5a801obu imd5
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

 -----------------------煤炭产业成本20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10248 dkcb, --今年累计大块销售成本
       imd5.m10222 zkcb, --今年累计中块销售成本
       imd5.m10196 xzkcb, --今年累计小中块销售成本
       imd5.m10170 sbkcb, --今年累计三八块销售成本
       imd5.m10144 ewkcb, --今年累计二五块销售成本
       imd5.m10118 slkcb, --今年累计四六块销售成本
       imd5.m10092 jcmcb, --今年累计锯采煤销售成本
       imd5.m10066 mmcb, --今年累计末煤销售成本
       imd5.m10040 xjcb --今年累计小计销售成本
  from iufo_measure_data_5a801obu imd5
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


-----------------------运营商类收入20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd0.m10102 zbdcsr, --今年累计自备电厂销售收入
       imd0.m10076 hddcsr, --今年累计火电电厂销售收入
       imd0.m10050 gfdcsr, --今年累计光伏电厂销售收入
       imd0.m10024 fndcsr, --今年累计风能电厂销售收入
       imd5.m10353 cdzsr, --今年累计充电站销售收入
       imd5.m10327 sdgssr, --今年累计售电公司销售收入
       imd5.m10301 grgssr, --今年累计供热公司销售收入
       imd5.m10275 xjsr --今年累计小计销售收入
  from iufo_measure_data_0lmfwcux imd0
  left join iufo_measure_data_5a801obu imd5
    on imd0.alone_id = imd5.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd0.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0


-----------------------运营商类成本20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd0.m10101 zbdccb, --今年累计自备电厂销售成本
       imd0.m10075 hddccb, --今年累计火电电厂销售成本
       imd0.m10049 gfdccb, --今年累计光伏电厂销售成本
       imd0.m10023 fndccb, --今年累计风能电厂销售成本
       imd5.m10352 cdzcb, --今年累计充电站销售成本
       imd5.m10326 sdgscb, --今年累计售电公司销售成本
       imd5.m10300 grgscb, --今年累计供热公司销售成本
       imd5.m10274 xjcb --今年累计小计销售成本
  from iufo_measure_data_0lmfwcux imd0
  left join iufo_measure_data_5a801obu imd5
    on imd0.alone_id = imd5.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd0.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
