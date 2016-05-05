select iui.unit_code,
       iui.unit_name, --��λ����
       inputdate,
       imdc.m10028   tfblbpcbsj, --�������뱬�Ƴɱ�ʵ��
       imdc.m10004   ymbpcbsj, --ԭú���Ƴɱ�ʵ��
       imdc.m10040   ymcycbsj, --ԭú���˳ɱ�ʵ��
       imdc.m10016   hsdycbsj, --��ɸ���˳ɱ�ʵ��
       imdc.m10052   zccbsj, --װ���ɱ�ʵ��
       imdc.m10034   zjcbxjsj, --ֱ�ӳɱ�С��ʵ��
       imdc.m10010   fkkcbsj, --�ǿɿسɱ�ʵ��
       imdc.m10046   kkcbsj, --�ɿسɱ�ʵ��
       imdc.m10022   zzfyxjsj, --�������С��ʵ��
       imdc.m10058   sccbhjsj, --�����ɱ��ϼ�ʵ��
       imdc.m6gtjtk  dyrklsj --���������ʵ��
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
