-----------------------------��������-----------------------------------(����ԭֵΪ����+��ֵ��
select unit_code,
       unit_name,
       inputdate,
       imda.m10083, --���澻��
       imdo.m10000 --��ֵ
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


-----------------------------��Դ���-----------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd8.m10006 yclnc, --ԭ���ϣ������
       imd8.m10010 kcspnc, --�����Ʒ�������
       imd8.m10002 dpbtfnc, --�����ɱ�-����������������
       imd8.m10008 fcspnc, --������Ʒ�������
       imd8.m10000 dhnc, --�ͺģ������
       imd8.m10004 hjnc, --�ϼƣ������
       imd8.m10007 ycl, --ԭ���ϣ���ĩ��
       imd8.m10011 kcsp, --�����Ʒ����ĩ��
       imd8.m10003 dpbtf, --�����ɱ�-�������������ĩ��
       imd8.m10009 fcsp, --������Ʒ����ĩ��
       imd8.m10001 dh, --�ͺģ���ĩ��
       imd8.m10005 hj --�ϼƣ���ĩ��
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
