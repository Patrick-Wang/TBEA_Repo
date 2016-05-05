--------------------------��ѹ����ҵ������ѹ�ȼ����ࣩ-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd7.m10099 jlbyqcb, --�����ۼƽ�����ѹ�����۳ɱ�
       imd7.m10073 jl35cb, --�����ۼ����У�35KV���������۳ɱ�
       imd7.m10047 jl66110lcb, --�����ۼ�66-110KV���۳ɱ�
       imd7.m10021 jl220cb, --�����ۼ�220KV���۳ɱ�
       imdu.m10350 jl330cb, --�����ۼ�330KV���۳ɱ�
       imdu.m10324 jl500cb, --�����ۼ�500KV���۳ɱ�
       imdu.m10298 jl750cb, --�����ۼ�750kV���۳ɱ�
       imdu.m10272 jl1000cb, --�����ۼ�1000kV���۳ɱ�
       imdu.m10246 zlbyqcb, --�����ۼ�ֱ����ѹ�����۳ɱ�
       imdu.m10220 zl400cb, --�����ۼ����У���400kv���������۳ɱ�
       imdu.m10194 zl500cb, --�����ۼơ�500kv���۳ɱ�
       imdu.m10168 zl600cb, --�����ۼơ�600kv���۳ɱ�
       imdu.m10142 zl800cb, --�����ۼơ�800kv���۳ɱ�
       imdu.m10116 zlpbdkqcb, --�����ۼ�ƽ���翹�����۳ɱ�
       imdu.m10090 dkqcb, --�����ۼƵ翹�����۳ɱ�
       imdu.m10064 dkq330cb, --�����ۼ����У�330kV���������۳ɱ�
       imdu.m10038 dkq500cb, --�����ۼ�500kV�����۳ɱ�
       imdu.m10012 dkq750cb, --�����ۼ�750kV�����۳ɱ�
       imdz.m10341 dkq1000cb, --�����ۼ�1000kV�����۳ɱ�
       imdz.m10315 dydjljcb --�����ۼ�С�����۳ɱ�������Ʒ��ѹ�ȼ����ࣩ
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
 
 --------------------------��ѹ����ҵ������Ʒ���ͷ��ࣩ-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdz.m10249 gsbyqcb, --�����ۼƸ�ʽ��ѹ�����۳ɱ�
       imdz.m10223 gsfjcb, --�����ۼ����У�F���ɱ����۳ɱ�
       imdz.m10197 gshjcb, --�����ۼ�H���ɱ����۳ɱ�
       imdz.m10171 gsxsbdcb, --�����ۼ���ʽ���վ���۳ɱ�
       imdz.m10145 gsdkqcb, --�����ۼƸ�ʽ�翹�����۳ɱ�
       imdz.m10119 tzbyqcb, --�����ۼ����ֱ�ѹ�����۳ɱ�
       imdz.m10093 tzgbcb, --�����ۼ����У����������۳ɱ�
       imdz.m10067 tzzlcb, --�����ۼ����������۳ɱ�
       imdz.m10041 tzqycb, --�����ۼ�ǣ�������۳ɱ�
       imdz.m10015 tzytcb, --�����ۼ���������۳ɱ�
       imdq.m10344 tzqtcb, --�����ۼ��������۳ɱ�
       imdq.m10318 yslcb, --�����ۼ����������۳ɱ�
       imdq.m10292 yslpdzdhcb, --�����ۼ�����Զ������۳ɱ�
       imdq.m10266 yslkgcb, --�����ۼƿ������۳ɱ�
       imdq.m10240 ysltgcb, --�����ۼ��׹����۳ɱ�
       imdq.m10214 yslhgqcb, --�����ۼƻ��������۳ɱ�
       imdq.m10188 yslwxbjcb, --�����ۼ�ά�ޱ������۳ɱ�
       imdq.m10162 cplxflljcb --�����ۼ�С�����۳ɱ�������Ʒ���ͷ��ࣩ
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
 
--------------------------���²�ҵ-------------------------------

select unit_code,
       unit_name,
       inputdate,
       imdq.m10064 dxcb, --�����ۼƵ������۳ɱ�
       imdq.m10038 bdxcb, --�����ۼƲ��������۳ɱ�
       imdq.m10012 jkxcb, --�����ۼƼܿ������۳ɱ�
       imdl.m10341 kzdlcb, --�����ۼƿ��Ƶ������۳ɱ�
       imdl.m10315 jldlcb, --�����ۼƽ����������۳ɱ�
       imdl.m10289 dldlcb, --�����ۼƵ����������۳ɱ�
       imdl.m10263 dcxcb, --�����ۼƵ�������۳ɱ�
       imdl.m10237 tzdlcb, --�����ۼ����ֵ������۳ɱ�
       imdl.m10211 dlfjcb, --�����ۼƵ��¸������۳ɱ�
       imdl.m10185 tgcb, --�����ۼ�ͭ�����۳ɱ�
       imdl.m10159 lgcb, --�����ۼ��������۳ɱ�
       imdl.m10133 pvclcb, --�����ۼ�PVC�����۳ɱ�
       imdl.m10107 gzlcb, --�����ۼƹ�װ�����۳ɱ�
       imdl.m10081 xlxjcb --�����ۼ�С�����۳ɱ�
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
 
-------------------------����ó����---------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10014 wlmycb, --�����ۼ�����ó�����۳ɱ�
       imdg.m10343 wlmyxjcb --�����ۼ�С�����۳ɱ�
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
 

-------------------------������-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdg.m10317 hyfcb, --�����ۼƻ�����������۳ɱ�
       imdg.m10291 wyfcb, --�����ۼ���ҵ���������۳ɱ�
       imdg.m10265 lwfcb, --�����ۼ������������۳ɱ�
       imdg.m10239 hmcb, --�����ۼƻ����������۳ɱ�
       imdg.m10213 zscb, --�����ۼ�ס���������۳ɱ�
       imdg.m10187 jpdlcb, --�����ۼƻ�Ʊ�����������۳ɱ�
       imdg.m10161 rybhcb, --�����ۼ����ðٻ��������۳ɱ�
       imdg.m10135 dfcb, --�����ۼƵ���������۳ɱ�
       imdg.m10109 sqncb, --�����ۼ�ˮ��ů�������۳ɱ�
       imdg.m10083 cycb, --�����ۼƲ����������۳ɱ�
       imdg.m10057 qtcb, --�����ۼ������������۳ɱ�
       imdg.m10031 xjcb --�����ۼ�С�����۳ɱ�
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
 

-----------------------������------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd0.m10303 gnsbdcb, --�����ۼ����繤�����۳ɱ�(����)
       imd0.m10277 gngfcb, --�����ۼƹ���������۳ɱ�(����)
       imd0.m10251 gnfdcb, --�����ۼƷ�繤�����۳ɱ�(����)
       imd0.m10205 gwsbdcb, --�����ۼ����繤�����۳ɱ�(����)
       imd0.m10179 gwgfcb, --�����ۼƹ���������۳ɱ�(����)
       imd0.m10153 gwfdcb, --�����ۼƷ�繤�����۳ɱ�(����)
       imd0.m10127 xjcb --�����ۼ�С�����۳ɱ�
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
