
----------------Ӧ��˰��������ڳ�����------------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10064   zzs_c, --��ֵ˰�ڳ�
       imd5.m10040   xfs_c, --����˰�ڳ�
       imd5.m10016   yys_c, --Ӫҵ˰�ڳ�
       imd5.m10120   cjs_c, --�ǽ�˰�ڳ�
       imd5.m10096   jyffj_c, --�����Ѹ���(�����ط������Ѹ���)�ڳ�
       imd5.m10073   qysds_c, --��ҵ����˰�ڳ�
       imd5.m10048   tdsys_c, --����ʹ��˰�ڳ�
       imd5.m10024   tdzzs_c, --������ֵ˰�ڳ�
       imd5.m10000   ccsys_c, --����ʹ��˰�ڳ�
       imd5.m10104   fcs_c, --����˰�ڳ�
       imd5.m10080   yhs_c, --ӡ��˰�ڳ�
       imd5.m10055   grsds_c, --��������˰�ڳ�
       imd5.m10031   zys_c, --��Դ˰�ڳ�
       imd5.m10007   gs_c, --��˰�ڳ�
       imd5.m10087   hj_c --�ϼ��ڳ�������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
  -- and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
 order by unit_code, inputdate desc



---------------Ӧ��˰�ѱ�����Ӧ������----------------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10062   zzs_yj, --��ֵ˰Ӧ��
       imd5.m10038   xfs_yj, --����˰Ӧ��
       imd5.m10014   yys_yj, --Ӫҵ˰Ӧ��
       imd5.m10118   cjs_yj, --�ǽ�˰Ӧ��
       imd5.m10094   jyffj_yj, --�����Ѹ���(�����ط������Ѹ���)Ӧ��
       imd5.m10071   qysds_yj, --��ҵ����˰Ӧ��
       imd5.m10046   tdsys_yj, --����ʹ��˰Ӧ��
       imd5.m10022   tdzzs_yj, --������ֵ˰Ӧ��
       imd5.m10127   ccsys_yj, --����ʹ��˰Ӧ��
       imd5.m10102   fcs_yj, --����˰Ӧ��
       imd5.m10078   yhs_yj, --ӡ��˰Ӧ��
       imd5.m10054   grsds_yj, --��������˰Ӧ��
       imd5.m10030   zys_yj, --��Դ˰Ӧ��
       imd5.m10006   gs_yj, --��˰Ӧ��
       imd5.m10086   hj_yj --�ϼ�Ӧ��������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
--and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
 order by unit_code, inputdate desc
 
 --------------Ӧ��˰�ѱ������ѽ�����-----------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10060   zzs_yij, --��ֵ˰�ѽ�
       imd5.m10036   xfs_yij, --����˰�ѽ�
       imd5.m10012   yys_yij, --Ӫҵ˰�ѽ�
       imd5.m10116   cjs_yij, --�ǽ�˰�ѽ�
       imd5.m10092   jyffj_yij, --�����Ѹ���(�����ط������Ѹ���)�ѽ�
       imd5.m10068   qysds_yij, --��ҵ����˰�ѽ�
       imd5.m10044   tdsys_yij, --����ʹ��˰�ѽ�
       imd5.m10020   tdzzs_yij, --������ֵ˰�ѽ�
       imd5.m10124   ccsys_yij, --����ʹ��˰�ѽ�
       imd5.m10100   fcs_yij, --����˰�ѽ�
       imd5.m10076   yhs_yij, --ӡ��˰�ѽ�
       imd5.m10052   grsds_yij, --��������˰�ѽ�
       imd5.m10028   zys_yij, --��Դ˰�ѽ�
       imd5.m10004   gs_yij, --��˰�ѽ�
       imd5.m10084   hj_yij --�ϼ��ѽ�������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
 
  --------------Ӧ��˰�ѱ�����δ������-----------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10058   zzs_wj, --��ֵ˰δ��
       imd5.m10034   xfs_wj, --����˰δ��
       imd5.m10010   yys_wj, --Ӫҵ˰δ��
       imd5.m10114   cjs_wj, --�ǽ�˰δ��
       imd5.m10090   jyffj_wj, --�����Ѹ���(�����ط������Ѹ���)δ��
       imd5.m10066   qysds_wj, --��ҵ����˰δ��
       imd5.m10042   tdsys_wj, --����ʹ��˰δ��
       imd5.m10018   tdzzs_wj, --������ֵ˰δ��
       imd5.m10122   ccsys_wj, --����ʹ��˰δ��
       imd5.m10098   fcs_wj, --����˰δ��
       imd5.m10074   yhs_wj, --ӡ��˰δ��
       imd5.m10050   grsds_wj, --��������˰δ��
       imd5.m10026   zys_wj, --��Դ˰δ��
       imd5.m10002   gs_wj, --��˰δ��
       imd5.m10082   hj_wj --�ϼ�δ��������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
 
 ----------------Ӧ��˰���������ĩ����------------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
imd5.m10056 zzs_m,--��ֵ˰��ĩ��
imd5.m10032 xfs_m,--����˰��ĩ��
imd5.m10008 yys_m,--Ӫҵ˰��ĩ��
imd5.m10112 cjs_m,--�ǽ�˰��ĩ��
imd5.m10088 jyffj_m,--�����Ѹ���(�����ط������Ѹ���)��ĩ��
imd5.m10063 qysds_m,--��ҵ����˰��ĩ��
imd5.m10039 tdsys_m,--����ʹ��˰��ĩ��
imd5.m10015 tdzzs_m,--������ֵ˰��ĩ��
imd5.m10119 ccsys_m,--����ʹ��˰��ĩ��
imd5.m10095 fcs_m,--����˰��ĩ��
imd5.m10070 yhs_m,--ӡ��˰��ĩ��
imd5.m10047 grsds_m,--��������˰��ĩ��
imd5.m10023 zys_m,--��Դ˰��ĩ��
imd5.m10126 gs_m,--��˰��ĩ��
imd5.m10079 hj_m--�ϼ���ĩ��������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
 
 ---------------Ӧ��˰�ѱ��ۼ�Ӧ������----------------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10061   zzs_ljyj, --��ֵ˰�ۼ�Ӧ��
       imd5.m10037   xfs_ljyj, --����˰�ۼ�Ӧ��
       imd5.m10013   yys_ljyj, --Ӫҵ˰�ۼ�Ӧ��
       imd5.m10117   cjs_ljyj, --�ǽ�˰�ۼ�Ӧ��
       imd5.m10093   jyffj_ljyj, --�����Ѹ���(�����ط������Ѹ���)�ۼ�Ӧ��
       imd5.m10069   qysds_ljyj, --��ҵ����˰�ۼ�Ӧ��
       imd5.m10045   tdsys_ljyj, --����ʹ��˰�ۼ�Ӧ��
       imd5.m10021   tdzzs_ljyj, --������ֵ˰�ۼ�Ӧ��
       imd5.m10125   ccsys_ljyj, --����ʹ��˰�ۼ�Ӧ��
       imd5.m10101   fcs_ljyj, --����˰�ۼ�Ӧ��
       imd5.m10077   yhs_ljyj, --ӡ��˰�ۼ�Ӧ��
       imd5.m10053   grsds_ljyj, --��������˰�ۼ�Ӧ��
       imd5.m10029   zys_ljyj, --��Դ˰�ۼ�Ӧ��
       imd5.m10005   gs_ljyj, --��˰�ۼ�Ӧ��
       imd5.m10085   hj_ljyj --�ϼ��ۼ�Ӧ��������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
--and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
 order by unit_code, inputdate desc
 
 
  ---------------Ӧ��˰�ѱ��ۼ��ѽ�����----------------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10059   zzs_ljyij, --��ֵ˰�ۼ��ѽ�
       imd5.m10035   xfs_ljyij, --����˰�ۼ��ѽ�
       imd5.m10011   yys_ljyij, --Ӫҵ˰�ۼ��ѽ�
       imd5.m10115   cjs_ljyij, --�ǽ�˰�ۼ��ѽ�
       imd5.m10091   jyffj_ljyij, --�����Ѹ���(�����ط������Ѹ���)�ۼ��ѽ�
       imd5.m10067   qysds_ljyij, --��ҵ����˰�ۼ��ѽ�
       imd5.m10043   tdsys_ljyij, --����ʹ��˰�ۼ��ѽ�
       imd5.m10019   tdzzs_ljyij, --������ֵ˰�ۼ��ѽ�
       imd5.m10123   ccsys_ljyij, --����ʹ��˰�ۼ��ѽ�
       imd5.m10099   fcs_ljyij, --����˰�ۼ��ѽ�
       imd5.m10075   yhs_ljyij, --ӡ��˰�ۼ��ѽ�
       imd5.m10051   grsds_ljyij, --��������˰�ۼ��ѽ�
       imd5.m10027   zys_ljyij, --��Դ˰�ۼ��ѽ�
       imd5.m10003   gs_ljyij, --��˰�ۼ��ѽ�
       imd5.m10083   hj_ljyij --�ϼ��ۼ��ѽ�������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
--and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
 order by unit_code, inputdate desc

 ---------------Ӧ��˰�ѱ��ۼ�δ������----------------------
select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imd5.m10057   zzs_ljwj, --��ֵ˰�ۼ�δ��
       imd5.m10033   xfs_ljwj, --����˰�ۼ�δ��
       imd5.m10009   yys_ljwj, --Ӫҵ˰�ۼ�δ��
       imd5.m10113   cjs_ljwj, --�ǽ�˰�ۼ�δ��
       imd5.m10089   jyffj_ljwj, --�����Ѹ���(�����ط������Ѹ���)�ۼ�δ��
       imd5.m10065   qysds_ljwj, --��ҵ����˰�ۼ�δ��
       imd5.m10041   tdsys_ljwj, --����ʹ��˰�ۼ�δ��
       imd5.m10017   tdzzs_ljwj, --������ֵ˰�ۼ�δ��
       imd5.m10121   ccsys_ljwj, --����ʹ��˰�ۼ�δ��
       imd5.m10097   fcs_ljwj, --����˰�ۼ�δ��
       imd5.m10072   yhs_ljwj, --ӡ��˰�ۼ�δ��
       imd5.m10049   grsds_ljwj, --��������˰�ۼ�δ��
       imd5.m10025   zys_ljwj, --��Դ˰�ۼ�δ��
       imd5.m10001   gs_ljwj, --��˰�ۼ�δ��
       imd5.m10081   hj_ljwj --�ϼ��ۼ�δ��������˰��û��ֵ��ֱ���úϼƼ�ȥ��������˰�ѣ�
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
--and iui.unit_name like '%�ر�繤�ɷ����޹�˾�½���ѹ������������%'
 order by unit_code, inputdate desc
