-----------------------����������20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdo.m10123 gnsr, --�����ۼƹ��ڹ�����������
       imd0.m10304 gnsbdsr, --�����ۼ����У����繤����������
       imdo.m10102 gnsbdepcsr, --�����ۼ�����-EPCģʽ��������
       imdo.m10076 gnsbdbtsr, --�����ۼ�����-BTģʽ��������
       imdo.m10050 gnsbdqtsr, --�����ۼ�����-����ģʽ��������
       imd0.m10278 gngfsr, --������ۼƹ��������������
       imdo.m10024 gngfepcsr, --�����ۼƹ��-EPCģʽ��������
       imdz.m10353 gngfbtsr, --�����ۼƹ��-BTģʽ��������
       imdz.m10327 gngfqtsr, --�����ۼƹ��-����ģʽ��������
       imd0.m10252 gnfdsr, --�����ۼƷ�繤����������
       imdz.m10301 gnfdepcsr, --�����ۼƷ��-EPCģʽ��������
       imdz.m10275 gnfdbtsr, --�����ۼƷ��-BTģʽ��������
       imdz.m10249 gnfdqtsr, --�����ۼƷ��-����ģʽ��������
       imdz.m10235 gjsr, --�����ۼƹ��ʹ�����������
       imd0.m10206 gjsbdsr, --�����ۼ����У����繤����������
       imdz.m10214 gjsbdepcsr, --�����ۼ�����-EPCģʽ��������
       imdz.m10188 gjsbdbtsr, --�����ۼ�����-BTģʽ��������
       imdz.m10162 gjsbdqtsr, --�����ۼ�����-����ģʽ��������
       imd0.m10180 gjgfsr, --�����ۼƽ����ۼ� ���������������
       imdz.m10136 gjgfepcsr, --�����ۼƹ��-EPCģʽ��������
       imdz.m10110 gjgfbtsr, --�����ۼƹ��-BTģʽ��������
       imdz.m10084 gjgfqtsr, --�����ۼƹ��-����ģʽ��������
       imd0.m10154 gjfdsr, --�����ۼ� ��繤����������
       imdz.m10058 gjfdepcsr, --�����ۼƷ��-EPCģʽ��������
       imdz.m10032 gjfdbtsr, --�����ۼƷ��-BTģʽ��������
       imdz.m10006 gjfdqtsr, --�����ۼƷ��-����ģʽ��������
       imd0.m10128 xjsr --�����ۼ�С����������
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
 
 -----------------------������ɱ�20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdo.m10122 gncb, --�����ۼƹ��ڹ������۳ɱ�
       imd0.m10303 gnsbdcb, --�����ۼ����У����繤�����۳ɱ�
       imdo.m10101 gnsbdepccb, --�����ۼ�����-EPCģʽ���۳ɱ�
       imdo.m10075 gnsbdbtcb, --�����ۼ�����-BTģʽ���۳ɱ�
       imdo.m10049 gnsbdqtcb, --�����ۼ�����-����ģʽ���۳ɱ�
       imd0.m10277 gngfcb, --�����ۼƹ���������۳ɱ�
       imdo.m10023 gngfepccb, --�����ۼƹ��-EPCģʽ���۳ɱ�
       imdz.m10352 gngfbtcb, --�����ۼƹ��-BTģʽ���۳ɱ�
       imdz.m10326 gngfqtcb, --�����ۼƹ��-����ģʽ���۳ɱ�
       imd0.m10251 gnfdcb, --�����ۼƷ�繤�����۳ɱ�
       imdz.m10300 gnfdepccb, --�����ۼƷ��-EPCģʽ���۳ɱ�
       imdz.m10274 gnfdbtcb, --�����ۼƷ��-BTģʽ���۳ɱ�
       imdz.m10248 gnfdqtcb, --�����ۼƷ��-����ģʽ���۳ɱ�
       imdz.m10234 gjcb, --�����ۼƹ��ʹ������۳ɱ�
       imd0.m10205 gjsbdcb, --�����ۼ����У����繤�����۳ɱ�
       imdz.m10213 gjsbdepccb, --�����ۼ�����-EPCģʽ���۳ɱ�
       imdz.m10187 gjsbdbtcb, --�����ۼ�����-BTģʽ���۳ɱ�
       imdz.m10161 gjsbdqtcb, --�����ۼ�����-����ģʽ���۳ɱ�
       imd0.m10179 gjgfcb, --�����ۼƹ���������۳ɱ�
       imdz.m10135 gjgfepccb, --�����ۼƹ��-EPCģʽ���۳ɱ�
       imdz.m10109 gjgfbtcb, --�����ۼƹ��-BTģʽ���۳ɱ�
       imdz.m10083 gjgfqtcb, --�����ۼƹ��-����ģʽ���۳ɱ�
       imd0.m10153 gjfdcb, --�����ۼƷ�繤�����۳ɱ�
       imdz.m10057 gjfdepccb, --�����ۼƷ��-EPCģʽ���۳ɱ�
       imdz.m10031 gjfdbtcb, --�����ۼƷ��-BTģʽ���۳ɱ�
       imdz.m10005 gjfdqtcb, --�����ۼƷ��-����ģʽ���۳ɱ�
       imd0.m10127 xjcb --�����ۼ�С�����۳ɱ�(������)
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
 
 -----------------------����Դ����20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdl.m10056 danjgpsr, --�����ۼƵ�����Ƭ��������
       imdl.m10030 duojgpsr, --�����ۼƶྦྷ��Ƭ��������
       imdl.m10004 zfbsr, --�����ۼ�׼������������
       imdk.m10333 djdsr, --�����ۼƶྦྷ����������
       imdk.m10307 ybsr, --�����ۼ�Բ����������
       imdk.m10281 bwdbqsr, --�����ۼƲ����������������
       imdk.m10255 hlxsr, --�����ۼƻ�������������
       imdk.m10229 pdgsr, --�����ۼ�������������
       imdk.m10203 ythjfsr, --�����ۼ�һ�廯������������
       imdk.m10177 cdzcpsr, --�����ۼƳ��׮��Ʒ��������
       imdk.m10151 svgsr, --�����ۼ�SVG��������
       imdk.m10125 svgbcpsr, --�����ۼ�SVG���Ʒ��������
       imdk.m10099 djgsr, --�����ۼƶྦྷ����������
       imdk.m10073 bthsr, --�����ۼư�̿����������
       imdk.m10047 jqksr, --�����ۼƼ�������������
       imdk.m10021 qtsr, --�����ۼ�������������
       imd0.m10350 xjsr --�����ۼ�С����������
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


 -----------------------����Դ�ɱ�20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imdl.m10055 danjgpcb, --�����ۼƵ�����Ƭ���۳ɱ�
       imdl.m10029 duojgpcb, --�����ۼƶྦྷ��Ƭ���۳ɱ�
       imdl.m10003 zfbcb, --�����ۼ�׼�������۳ɱ�
       imdk.m10332 djdcb, --�����ۼƶྦྷ�����۳ɱ�
       imdk.m10306 ybcb, --�����ۼ�Բ�����۳ɱ�
       imdk.m10280 bwdbqcb, --�����ۼƲ�����������۳ɱ�
       imdk.m10254 hlxcb, --�����ۼƻ��������۳ɱ�
       imdk.m10228 pdgcb, --�����ۼ��������۳ɱ�
       imdk.m10202 ythjfcb, --�����ۼ�һ�廯�������۳ɱ�
       imdk.m10176 cdzcpcb, --�����ۼƳ��׮��Ʒ���۳ɱ�
       imdk.m10150 svgcb, --�����ۼ�SVG���۳ɱ�
       imdk.m10124 svgbcpcb, --�����ۼ�SVG��ɱ�Ʒ���۳ɱ�
       imdk.m10098 djgcb, --�����ۼƶྦྷ�����۳ɱ�
       imdk.m10072 bthcb, --�����ۼư�̿�����۳ɱ�
       imdk.m10046 jqkcb, --�����ۼƼ��������۳ɱ�
       imdk.m10020 qtcb, --�����ۼ��������۳ɱ�
       imd0.m10349 xjcb --�����ۼ�С�����۳ɱ�
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

 -----------------------ú̿��ҵ����20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10249 dksr, --�����ۼƴ����������
       imd5.m10223 zksr, --�����ۼ��п���������
       imd5.m10197 xzksr, --�����ۼ�С�п���������
       imd5.m10171 sbksr, --�����ۼ����˿���������
       imd5.m10145 ewksr, --�����ۼƶ������������
       imd5.m10119 slksr, --�����ۼ���������������
       imd5.m10093 jcmsr, --�����ۼƾ��ú��������
       imd5.m10067 mmsr, --�����ۼ�ĩú��������
       imd5.m10041 xjsr --�����ۼ�С����������
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

 -----------------------ú̿��ҵ�ɱ�20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10248 dkcb, --�����ۼƴ�����۳ɱ�
       imd5.m10222 zkcb, --�����ۼ��п����۳ɱ�
       imd5.m10196 xzkcb, --�����ۼ�С�п����۳ɱ�
       imd5.m10170 sbkcb, --�����ۼ����˿����۳ɱ�
       imd5.m10144 ewkcb, --�����ۼƶ�������۳ɱ�
       imd5.m10118 slkcb, --�����ۼ����������۳ɱ�
       imd5.m10092 jcmcb, --�����ۼƾ��ú���۳ɱ�
       imd5.m10066 mmcb, --�����ۼ�ĩú���۳ɱ�
       imd5.m10040 xjcb --�����ۼ�С�����۳ɱ�
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


-----------------------��Ӫ��������20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd0.m10102 zbdcsr, --�����ۼ��Ա��糧��������
       imd0.m10076 hddcsr, --�����ۼƻ��糧��������
       imd0.m10050 gfdcsr, --�����ۼƹ���糧��������
       imd0.m10024 fndcsr, --�����ۼƷ��ܵ糧��������
       imd5.m10353 cdzsr, --�����ۼƳ��վ��������
       imd5.m10327 sdgssr, --�����ۼ��۵繫˾��������
       imd5.m10301 grgssr, --�����ۼƹ��ȹ�˾��������
       imd5.m10275 xjsr --�����ۼ�С����������
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


-----------------------��Ӫ����ɱ�20160422------------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd0.m10101 zbdccb, --�����ۼ��Ա��糧���۳ɱ�
       imd0.m10075 hddccb, --�����ۼƻ��糧���۳ɱ�
       imd0.m10049 gfdccb, --�����ۼƹ���糧���۳ɱ�
       imd0.m10023 fndccb, --�����ۼƷ��ܵ糧���۳ɱ�
       imd5.m10352 cdzcb, --�����ۼƳ��վ���۳ɱ�
       imd5.m10326 sdgscb, --�����ۼ��۵繫˾���۳ɱ�
       imd5.m10300 grgscb, --�����ۼƹ��ȹ�˾���۳ɱ�
       imd5.m10274 xjcb --�����ۼ�С�����۳ɱ�
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
