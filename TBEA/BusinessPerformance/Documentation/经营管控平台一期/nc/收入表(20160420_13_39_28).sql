--------------------------��ѹ����ҵ������ѹ�ȼ����ࣩ-------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd7.m10100 jlbyqsr, --�����ۼƽ�����ѹ����������
       imd7.m10074 jl35sr, --�����ۼ����У�35KV��������������
       imd7.m10048 jl66110lsr, --�����ۼ�66-110KV��������
       imd7.m10022 jl220sr, --�����ۼ�220KV��������
       imdu.m10351 jl330sr, --�����ۼ�330KV��������
       imdu.m10325 jl500sr, --�����ۼ�500KV��������
       imdu.m10299 jl750sr, --�����ۼ�750kV��������
       imdu.m10273 jl1000sr, --�����ۼ�1000kV��������
       imdu.m10247 zlbyqsr, --�����ۼ�ֱ����ѹ����������
       imdu.m10221 zl400sr, --�����ۼƽ����ۼ����У���400kv��������������
       imdu.m10195 zl500sr, --�����ۼơ�500kv��������
       imdu.m10169 zl600sr, --�����ۼơ�600kv��������
       imdu.m10143 zl800sr, --�����ۼơ�800kv��������
       imdu.m10117 zlpbdkqsr, --�����ۼ�ƽ���翹����������
       imdu.m10091 dkqsr, --�����ۼƵ翹����������
       imdu.m10065 dkq330sr, --�����ۼ����У�330kV��������������
       imdu.m10039 dkq500sr, --�����ۼ�500kV����������
       imdu.m10013 dkq750sr, --�����ۼ�750kV����������
       imdz.m10342 dkq1000sr, --�����ۼ�1000kV����������
       imdz.m10316 dydjljsr --�����ۼ�С����������(����ѹ�ȼ�����)
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
       imdz.m10250 gsbyqsr, --�����ۼƸ�ʽ��ѹ����������
       imdz.m10224 gsfjsr, --�����ۼ����У�F���ɱ���������
       imdz.m10198 gshjsr, --�����ۼ�H���ɱ���������
       imdz.m10172 gsxsbdsr, --�����ۼ���ʽ���վ��������
       imdz.m10146 gsdkqsr, --�����ۼƸ�ʽ�翹����������
       imdz.m10120 tzbyqsr, --�����ۼ����ֱ�ѹ����������
       imdz.m10094 tzgbsr, --�����ۼ����У���������������
       imdz.m10068 tzzlsr, --�����ۼ���������������
       imdz.m10042 tzqysr, --�����ۼ�ǣ������������
       imdz.m10016 tzytsr, --�����ۼ��������������
       imdq.m10345 tzqtsr, --�����ۼ�������������
       imdq.m10319 yslsr, --�����ۼ���������������
       imdq.m10293 yslpdzdhsr, --�����ۼ�����Զ�����������
       imdq.m10267 yslkgsr, --�����ۼƿ�����������
       imdq.m10241 ysltg, --�����ۼ��׹���������
       imdq.m10215 yslhgqsr, --�����ۼƻ�������������
       imdq.m10189 yslwxbjsr, --�����ۼ�ά�ޱ�����������
       imdq.m10163 cplxflljsr --�����ۼ�С����������(����Ʒ���ͷ���)
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
       imdq.m10065 dxsr, --�����ۼƵ�����������
       imdq.m10039 bdxsr, --�����ۼƲ�������������
       imdq.m10013 jkxsr, --�����ۼƼܿ�����������
       imdl.m10342 kzdlsr, --�����ۼƿ��Ƶ�����������
       imdl.m10316 jldlsr, --�����ۼƽ���������������
       imdl.m10290 dldlsr, --�����ۼƵ���������������
       imdl.m10264 dcxsr, --�����ۼƵ������������
       imdl.m10238 tzdlsr, --�����ۼ����ֵ�����������
       imdl.m10212 dlfjsr, --�����ۼƵ��¸�����������
       imdl.m10186 tgsr, --�����ۼ�ͭ����������
       imdl.m10160 lgsr, --�����ۼ�������������
       imdl.m10134 pvclsr, --�����ۼ�PVC����������
       imdl.m10108 gzlsr, --�����ۼƹ�װ����������
       imdl.m10082 xlxjsr --�����ۼ�С����������(����)
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
       imd5.m10015 wlmysr, --�����ۼ�����ó����������
       imdg.m10344 wlmyxjsr --�����ۼ�С����������
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
       imdg.m10318 hyfsr, --�����ۼƻ����������������
       imdg.m10292 wyfsr, --�����ۼ���ҵ��������������
       imdg.m10266 lwfsr, --�����ۼ�����������������
       imdg.m10240 hmsr, --�����ۼƻ���������������
       imdg.m10214 zssr, --�����ۼ�ס��������������
       imdg.m10188 jpdlsr, --�����ۼƻ�Ʊ����������������
       imdg.m10162 rybhsr, --�����ۼ����ðٻ�������������
       imdg.m10136 dfsr, --�����ۼƵ��������������
       imdg.m10110 sqnsr, --�����ۼ�ˮ��ů������������
       imdg.m10084 cysr, --�����ۼƲ���������������
       imdg.m10058 qtsr, --�����ۼ�����������������
       imdg.m10032 xjsr --�����ۼ�С����������
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
imd0.m10304 gnsbdsr,--�����ۼ����У����繤���������루���ڣ�
imd0.m10278 gngfsr,--�����ۼ� ��������������루���ڣ�
imd0.m10252 gnfdsr,--�����ۼƷ�繤���������루���ڣ�
imd0.m10206 gwsbdsr,--�����ۼ����У����繤���������루���⣩
imd0.m10180 gwgfsr,--�����ۼ� ��������������루���⣩
imd0.m10154 gwfdsr,--�����ۼ� ��繤���������루���⣩
imd0.m10128 xjsr--�����ۼ�С����������
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
