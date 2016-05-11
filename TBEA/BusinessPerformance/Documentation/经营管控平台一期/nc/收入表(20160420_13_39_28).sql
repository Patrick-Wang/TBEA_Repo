--------------------------变压器产业（按电压等级分类）-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd7.m10100 jlbyqsr, --今年累计交流变压器销售收入
       imd7.m10074 jl35sr, --今年累计其中：35KV及以下销售收入
       imd7.m10048 jl66110lsr, --今年累计66-110KV销售收入
       imd7.m10022 jl220sr, --今年累计220KV销售收入
       imdu.m10351 jl330sr, --今年累计330KV销售收入
       imdu.m10325 jl500sr, --今年累计500KV销售收入
       imdu.m10299 jl750sr, --今年累计750kV销售收入
       imdu.m10273 jl1000sr, --今年累计1000kV销售收入
       imdu.m10247 zlbyqsr, --今年累计直流变压器销售收入
       imdu.m10221 zl400sr, --今年累计今年累计其中：±400kv及以下销售收入
       imdu.m10195 zl500sr, --今年累计±500kv销售收入
       imdu.m10169 zl600sr, --今年累计±600kv销售收入
       imdu.m10143 zl800sr, --今年累计±800kv销售收入
       imdu.m10117 zlpbdkqsr, --今年累计平波电抗器销售收入
       imdu.m10091 dkqsr, --今年累计电抗器销售收入
       imdu.m10065 dkq330sr, --今年累计其中：330kV及以下销售收入
       imdu.m10039 dkq500sr, --今年累计500kV电销售收入
       imdu.m10013 dkq750sr, --今年累计750kV电销售收入
       imdz.m10342 dkq1000sr, --今年累计1000kV电销售收入
       imdz.m10316 dydjljsr --今年累计小计销售收入(按电压等级分类)
  from iufo_measure_data_7zz4hjkz imd7
  left join iufo_measure_data_ukdj7hhy imdu
    on imd7.alone_id = imdu.alone_id
  left join iufo_measure_data_zwrb9cyz imdz
    on imd7.alone_id = imdz.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imd7.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 
 
 --------------------------变压器产业（按产品类型分类）-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdz.m10250 gsbyqsr, --今年累计干式变压器销售收入
       imdz.m10224 gsfjsr, --今年累计其中：F级干变销售收入
       imdz.m10198 gshjsr, --今年累计H级干变销售收入
       imdz.m10172 gsxsbdsr, --今年累计箱式变电站销售收入
       imdz.m10146 gsdkqsr, --今年累计干式电抗器销售收入
       imdz.m10120 tzbyqsr, --今年累计特种变压器销售收入
       imdz.m10094 tzgbsr, --今年累计其中：隔爆变销售收入
       imdz.m10068 tzzlsr, --今年累计整流变销售收入
       imdz.m10042 tzqysr, --今年累计牵引变销售收入
       imdz.m10016 tzytsr, --今年累计油田变销售收入
       imdq.m10345 tzqtsr, --今年累计其它销售收入
       imdq.m10319 yslsr, --今年累计延伸类销售收入
       imdq.m10293 yslpdzdhsr, --今年累计配电自动化销售收入
       imdq.m10267 yslkgsr, --今年累计开关销售收入
       imdq.m10241 ysltg, --今年累计套管销售收入
       imdq.m10215 yslhgqsr, --今年累计互感器销售收入
       imdq.m10189 yslwxbjsr, --今年累计维修备件销售收入
       imdq.m10163 cplxflljsr --今年累计小计销售收入(按产品类型分类)
  from iufo_measure_data_qp1i8fs7 imdq
  left join iufo_measure_data_zwrb9cyz imdz
    on imdq.alone_id = imdz.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdq.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 
 
--------------------------线缆产业-------------------------------

select unit_code,
       unit_name,
       inputdate,
       imdq.m10065 dxsr, --今年累计导线销售收入
       imdq.m10039 bdxsr, --今年累计布电线销售收入
       imdq.m10013 jkxsr, --今年累计架空线销售收入
       imdl.m10342 kzdlsr, --今年累计控制电缆销售收入
       imdl.m10316 jldlsr, --今年累计交联电缆销售收入
       imdl.m10290 dldlsr, --今年累计电力电缆销售收入
       imdl.m10264 dcxsr, --今年累计电磁线销售收入
       imdl.m10238 tzdlsr, --今年累计特种电缆销售收入
       imdl.m10212 dlfjsr, --今年累计电缆附件销售收入
       imdl.m10186 tgsr, --今年累计铜杆销售收入
       imdl.m10160 lgsr, --今年累计铝杆销售收入
       imdl.m10134 pvclsr, --今年累计PVC料销售收入
       imdl.m10108 gzlsr, --今年累计工装轮销售收入
       imdl.m10082 xlxjsr --今年累计小计销售收入(线缆)
  from iufo_measure_data_qp1i8fs7 imdq
  left join iufo_measure_data_l1hnj73b imdl
    on imdq.alone_id = imdl.alone_id
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdq.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 
 
-------------------------物流贸易类---------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10015 wlmysr, --今年累计物流贸易销售收入
       imdg.m10344 wlmyxjsr --今年累计小计销售收入
  from iufo_measure_data_5a801obu imd5
  left join iufo_measure_data_gyin4hlu imdg
    on imd5.alone_id = imdg.alone_id
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
 

-------------------------服务类-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdg.m10318 hyfsr, --今年累计会议费收入销售收入
       imdg.m10292 wyfsr, --今年累计物业费收入销售收入
       imdg.m10266 lwfsr, --今年累计劳务收入销售收入
       imdg.m10240 hmsr, --今年累计花苗收入销售收入
       imdg.m10214 zssr, --今年累计住宿收入销售收入
       imdg.m10188 jpdlsr, --今年累计机票代理收入销售收入
       imdg.m10162 rybhsr, --今年累计日用百货收入销售收入
       imdg.m10136 dfsr, --今年累计电费收入销售收入
       imdg.m10110 sqnsr, --今年累计水汽暖收入销售收入
       imdg.m10084 cysr, --今年累计餐饮收入销售收入
       imdg.m10058 qtsr, --今年累计其他收入销售收入
       imdg.m10032 xjsr --今年累计小计销售收入
  from iufo_measure_data_gyin4hlu imdg
  left join (select alone_id,
                    code,
                    inputdate,
                    keyword2,
                    keyword3,
                    time_code,
                    ts,
                    ver
               from iufo_measure_pubdata) imp
    on imdg.alone_id = imp.alone_id
  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui
    on imp.code = iui.unit_id
 where imp.ver = 0
 

-----------------------工程类------------------------------------
select unit_code,
       unit_name,
       inputdate,
imd0.m10304 gnsbdsr,--今年累计其中：输变电工程销售收入（国内）
imd0.m10278 gngfsr,--今年累计 光伏工程销售收入（国内）
imd0.m10252 gnfdsr,--今年累计风电工程销售收入（国内）
imd0.m10206 gwsbdsr,--今年累计其中：输变电工程销售收入（国外）
imd0.m10180 gwgfsr,--今年累计 光伏工程销售收入（国外）
imd0.m10154 gwfdsr,--今年累计 风电工程销售收入（国外）
imd0.m10128 xjsr--今年累计小计销售收入
  from iufo_measure_data_0lmfwcux imd0
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
