--------------------------变压器产业（按电压等级分类）-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd7.m10099 jlbyqcb, --今年累计交流变压器销售成本
       imd7.m10073 jl35cb, --今年累计其中：35KV及以下销售成本
       imd7.m10047 jl66110lcb, --今年累计66-110KV销售成本
       imd7.m10021 jl220cb, --今年累计220KV销售成本
       imdu.m10350 jl330cb, --今年累计330KV销售成本
       imdu.m10324 jl500cb, --今年累计500KV销售成本
       imdu.m10298 jl750cb, --今年累计750kV销售成本
       imdu.m10272 jl1000cb, --今年累计1000kV销售成本
       imdu.m10246 zlbyqcb, --今年累计直流变压器销售成本
       imdu.m10220 zl400cb, --今年累计其中：±400kv及以下销售成本
       imdu.m10194 zl500cb, --今年累计±500kv销售成本
       imdu.m10168 zl600cb, --今年累计±600kv销售成本
       imdu.m10142 zl800cb, --今年累计±800kv销售成本
       imdu.m10116 zlpbdkqcb, --今年累计平波电抗器销售成本
       imdu.m10090 dkqcb, --今年累计电抗器销售成本
       imdu.m10064 dkq330cb, --今年累计其中：330kV及以下销售成本
       imdu.m10038 dkq500cb, --今年累计500kV电销售成本
       imdu.m10012 dkq750cb, --今年累计750kV电销售成本
       imdz.m10341 dkq1000cb, --今年累计1000kV电销售成本
       imdz.m10315 dydjljcb --今年累计小计销售成本（按产品电压等级分类）
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
       imdz.m10249 gsbyqcb, --今年累计干式变压器销售成本
       imdz.m10223 gsfjcb, --今年累计其中：F级干变销售成本
       imdz.m10197 gshjcb, --今年累计H级干变销售成本
       imdz.m10171 gsxsbdcb, --今年累计箱式变电站销售成本
       imdz.m10145 gsdkqcb, --今年累计干式电抗器销售成本
       imdz.m10119 tzbyqcb, --今年累计特种变压器销售成本
       imdz.m10093 tzgbcb, --今年累计其中：隔爆变销售成本
       imdz.m10067 tzzlcb, --今年累计整流变销售成本
       imdz.m10041 tzqycb, --今年累计牵引变销售成本
       imdz.m10015 tzytcb, --今年累计油田变销售成本
       imdq.m10344 tzqtcb, --今年累计其它销售成本
       imdq.m10318 yslcb, --今年累计延伸类销售成本
       imdq.m10292 yslpdzdhcb, --今年累计配电自动化销售成本
       imdq.m10266 yslkgcb, --今年累计开关销售成本
       imdq.m10240 ysltgcb, --今年累计套管销售成本
       imdq.m10214 yslhgqcb, --今年累计互感器销售成本
       imdq.m10188 yslwxbjcb, --今年累计维修备件销售成本
       imdq.m10162 cplxflljcb --今年累计小计销售成本（按产品类型分类）
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
       imdq.m10064 dxcb, --今年累计导线销售成本
       imdq.m10038 bdxcb, --今年累计布电线销售成本
       imdq.m10012 jkxcb, --今年累计架空线销售成本
       imdl.m10341 kzdlcb, --今年累计控制电缆销售成本
       imdl.m10315 jldlcb, --今年累计交联电缆销售成本
       imdl.m10289 dldlcb, --今年累计电力电缆销售成本
       imdl.m10263 dcxcb, --今年累计电磁线销售成本
       imdl.m10237 tzdlcb, --今年累计特种电缆销售成本
       imdl.m10211 dlfjcb, --今年累计电缆附件销售成本
       imdl.m10185 tgcb, --今年累计铜杆销售成本
       imdl.m10159 lgcb, --今年累计铝杆销售成本
       imdl.m10133 pvclcb, --今年累计PVC料销售成本
       imdl.m10107 gzlcb, --今年累计工装轮销售成本
       imdl.m10081 xlxjcb --今年累计小计销售成本
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
       imd5.m10014 wlmycb, --今年累计物流贸易销售成本
       imdg.m10343 wlmyxjcb --今年累计小计销售成本
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
       imdg.m10317 hyfcb, --今年累计会议费收入销售成本
       imdg.m10291 wyfcb, --今年累计物业费收入销售成本
       imdg.m10265 lwfcb, --今年累计劳务收入销售成本
       imdg.m10239 hmcb, --今年累计花苗收入销售成本
       imdg.m10213 zscb, --今年累计住宿收入销售成本
       imdg.m10187 jpdlcb, --今年累计机票代理收入销售成本
       imdg.m10161 rybhcb, --今年累计日用百货收入销售成本
       imdg.m10135 dfcb, --今年累计电费收入销售成本
       imdg.m10109 sqncb, --今年累计水汽暖收入销售成本
       imdg.m10083 cycb, --今年累计餐饮收入销售成本
       imdg.m10057 qtcb, --今年累计其他收入销售成本
       imdg.m10031 xjcb --今年累计小计销售成本
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
       imd0.m10303 gnsbdcb, --今年累计输变电工程销售成本(国内)
       imd0.m10277 gngfcb, --今年累计光伏工程销售成本(国内)
       imd0.m10251 gnfdcb, --今年累计风电工程销售成本(国内)
       imd0.m10205 gwsbdcb, --今年累计输变电工程销售成本(国际)
       imd0.m10179 gwgfcb, --今年累计光伏工程销售成本(国际)
       imd0.m10153 gwfdcb, --今年累计风电工程销售成本(国际)
       imd0.m10127 xjcb --今年累计小计销售成本
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
