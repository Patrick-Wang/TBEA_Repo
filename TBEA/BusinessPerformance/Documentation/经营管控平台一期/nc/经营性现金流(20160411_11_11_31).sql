---------------------------��Ӫ���ֽ���������ֵ��--------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd9.m10138 xssptglwsd, --������Ʒ���ṩ�����յ����ֽ�
       imd9.m10052 sdsffh, --�յ���˰�ѷ���
       imd5.m10025 sddqtyjyhdygxj, --�յ��������뾭Ӫ��йص��ֽ�
       imd9.m10181 fksdxj, --���У��������յ����ֽ�
       imd5.m10008 zfbzsd, --���У������������յ����ֽ�
       imd9.m10242 sdbdwtbbzj, --���У��յ�����λ����Ͷ���˻����յ���Ͷ�걣֤��
       imd9.m10148 sdwdwtbbzj, --���У��յ��ⵥλͶ�걣֤�����յ����ֽ�
       imd9.m10100 rcywjzthssdxj, --���У��ճ�ҵ���֧�˻����յ����ֽ�
       imd5.m10053 yhcklxssdxj, --���У����д����Ϣ���յ������ֽ�
       imd5.m10025 sddqtyjyhdygxj, --���У��յ��������뾭Ӫ��йص��ֽ�
       imd9.m10142 jyhdxjlr, --�ֽ�����С��
       imd9.m10150 gmspjslwszfxj, --������Ʒ������������֧�����ֽ�
       imd5.m10062 zfgzxj, --֧����ְ���Լ�Ϊְ��֧�����ֽ�
       imd9.m10309 zfgxsf, --֧���ĸ���˰��
       imd9.m10210 zfqtjyhdygxj, --֧���������뾭Ӫ��йص��ֽ�
       imd9.m10337 zfbdwtbbzj, --���У�����λ����Ͷ����֧����Ͷ�걣֤��
       imd5.m10040 tfwdwtbbzj, --���У��˸��ⵥλͶ�걣֤����֧�����ֽ�
       imd9.m10111 dlzxfzfxj, --���У�������ѯ����֧�����ֽ�
       imd9.m10198 zbfwfzfxj, --���У��б�������֧�����ֽ�
       imd9.m10177 rcywjzzfxj, --���У��ճ�ҵ���֧��֧�����ֽ�
       imd5.m10059 yhxgywsxfzfxj, --���У��������ҵ����������֧�����ֽ�
       imd5.m10039 qtjyhdygxj, --���У�֧���������뾭Ӫ��йص��ֽ�
       imd5.m10061 jyhdxjlc, --�ֽ�����С��
       imd9.m10029 jyhdcsdxjllje --��Ӫ��������ֽ���������
  from iufo_measure_data_9hzo24a7 imd9
  left join iufo_measure_data_56m8ewc1 imd5
    on imd9.alone_id = imd5.alone_id
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
/*   and iui.unit_name like '%�½���ѹ����%'
   and substr(inputdate, 1, 7) = '2016-03'*/


---------------------------��Ӫ���ֽ����������ۼ�����--------------------------------
select unit_code,
       unit_name,
       inputdate,
       imd5.m10034 xssptglwsdlj, --������Ʒ���ṩ�����յ����ֽ�(�����ۼ���)
       imd9.m10056 sdsffhlj, --�յ���˰�ѷ���(�����ۼ���)
       imd9.m10304 sddqtyjyhdygxjlj, --�յ��������뾭Ӫ��йص��ֽ�(�����ۼ���)
       imd9.m10178 fksdxjlj, --���У��������յ����ֽ�(�����ۼ���)
       imd9.m10197 zfbzsdlj, --���У������������յ����ֽ�(�����ۼ���)
       imd9.m10283 sdbdwtbbzjlj, --���У��յ�����λ����Ͷ���˻����յ���Ͷ�걣֤��(�����ۼ���)
       imd9.m10037 sdwdwtbbzjlj, --���У��յ��ⵥλͶ�걣֤�����յ����ֽ�(�����ۼ���)
       imd9.m10345 rcywjzthssdxjlj, --���У��ճ�ҵ���֧�˻����յ����ֽ�(�����ۼ���)
       imd9.m10232 yhcklxssdxjlj, --���У����д����Ϣ���յ������ֽ�(�����ۼ���)
       imd5.m10035 sddqtyjyhdygxjlj, --���У��յ��������뾭Ӫ��йص��ֽ�(�����ۼ���)
       imd9.m10120 jyhdxjlrlj, --�ֽ�����С��(�����ۼ���)
       imd9.m10228 gmspjslwszfxjlj, --������Ʒ������������֧�����ֽ�(�����ۼ���)
       imd9.m10008 zfgzxjlj, --֧����ְ���Լ�Ϊְ��֧�����ֽ�(�����ۼ���)
       imd9.m10066 zfgxsflj, --֧���ĸ���˰��(�����ۼ���)
       imd5.m10046 zfqtjyhdygxjlj, --֧���������뾭Ӫ��йص��ֽ�(�����ۼ���)
       imd5.m10052 zfbdwtbbzjlj, --���У�����λ����Ͷ����֧����Ͷ�걣֤��(�����ۼ���)
       imd9.m10306 tfwdwtbbzjlj, --���У��˸��ⵥλͶ�걣֤����֧�����ֽ�(�����ۼ���)
       imd9.m10108 dlzxfzfxjlj, --���У�������ѯ����֧�����ֽ�(�����ۼ���)
       imd9.m10224 zbfwfzfxjlj, --���У��б�������֧�����ֽ�(�����ۼ���)
       imd9.m10110 rcywjzzfxjlj, --���У��ճ�ҵ���֧��֧�����ֽ�(�����ۼ���)
       imd9.m10124 yhxgywsxfzfxjlj, --���У��������ҵ����������֧�����ֽ�(�����ۼ���)
       imd5.m10046 qtjyhdygxjlj, --���У�֧���������뾭Ӫ��йص��ֽ�(�����ۼ���)
       imd5.m10064 jyhdxjlclj, --�ֽ�����С��(�����ۼ���)
       imd9.m10114 jyhdcsdxjlljelj --��Ӫ��������ֽ���������(�����ۼ���)
  from iufo_measure_data_9hzo24a7 imd9
  left join iufo_measure_data_56m8ewc1 imd5
    on imd9.alone_id = imd5.alone_id
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
/*and iui.unit_name like '%�½���ѹ����%'
and substr(inputdate,1,7) = '2016-03'*/
