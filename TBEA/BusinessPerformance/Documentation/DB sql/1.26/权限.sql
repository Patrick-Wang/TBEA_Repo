update auth_instruction set instruction = '������Ϣ�鿴' where id = 66
update auth_instruction set instruction = '�²�Ʒ��Ϣ�鿴' where id = 67

update auth_instruction set instruction = 'ָ������' where id = 6
update auth_instruction set instruction = '��Դ�ܱ��г����¼��' where id = 18
update auth_instruction set instruction = '��Դ�ܱ��г�����鿴' where id = 19


SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (88, '�û�Ȩ�޹���')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF



SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (89, '��ѹ��Ԥ��̨��¼��')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (90, '��ѹ��Ԥ��̨�˲鿴')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (91, '����Ԥ��̨��¼��')
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (92, '����Ԥ��̨�˲鿴')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF


--���Ȩ�� ʾ��
--EXEC	addAuth
--		@role = '928006',
--		@auth = 89,
--		@comps = '1,115,106,111,113';

EXEC	addAuth	'��乫˾',	89	,	'1'
EXEC	addAuth	'������ó�׳��׹�˾',	89	,	'101'
EXEC	addAuth	'������طֹ�˾',	89	,	'102'
EXEC	addAuth	'��俵�λ�����',	89	,	'103'
EXEC	addAuth	'�����׹�',	89	,	'104'
EXEC	addAuth	'�������Զ�����˾',	89	,	'105'
EXEC	addAuth	'�Ͽ���˾',	89	,	'106'
EXEC	addAuth	'�����������',	89	,	'107'
EXEC	addAuth	'����ִ�������˾',	89	,	'108'
EXEC	addAuth	'�ر�繤������������������޹�˾',	89	,	'109'
EXEC	addAuth	'�����ֹ�˾',	89	,	'110'
EXEC	addAuth	'������͹�˾',	89	,	'111'
EXEC	addAuth	'�����ҵ��˾',	89	,	'112'
EXEC	addAuth	'�����о���',	89	,	'113'
EXEC	addAuth	'�������Դ',	89	,	'114'
EXEC	addAuth	'�ر�繤ӡ����Դ���޹�˾',	89	,	'115'
EXEC	addAuth	'��乫˾',	89	,	'2'
EXEC	addAuth	'�������ֹ�˾',	89	,	'201'
EXEC	addAuth	'���Ϲ���������˾',	89	,	'202'
EXEC	addAuth	'���Ϲ��̹�˾',	89	,	'203'
EXEC	addAuth	'�����ҵ��˾',	89	,	'204'
EXEC	addAuth	'�������ܵ�����˾',	89	,	'205'
EXEC	addAuth	'�Ͼ����ܵ�����˾',	89	,	'206'
EXEC	addAuth	'����԰�ֹ�˾',	89	,	'207'
EXEC	addAuth	'�±䳧',	89	,	'3'
EXEC	addAuth	'��乫˾',	89	,	'301'
EXEC	addAuth	'�±����ع�˾',	89	,	'302'
EXEC	addAuth	'�±���乫˾',	89	,	'303'
EXEC	addAuth	'�±���ʳ��׹��̹�˾',	89	,	'304'
EXEC	addAuth	'�±���ڹ��̼��޹�˾',	89	,	'305'
EXEC	addAuth	'�½����ع�������ó�׹�˾',	89	,	'306'
EXEC	addAuth	'������ҵ��',	89	,	'308'
EXEC	addAuth	'������',	89	,	'309'
EXEC	addAuth	'³�¹�˾',	91	,	'4'
EXEC	addAuth	'ͨ�õ�����Ŀ��˾',	91	,	'401'
EXEC	addAuth	'���ܵ�����Ŀ��˾',	91	,	'402'
EXEC	addAuth	'���׵�����Ŀ��˾',	91	,	'403'
EXEC	addAuth	'���¾�����ҵ��',	91	,	'404'
EXEC	addAuth	'���¹��̹�˾',	91	,	'405'
EXEC	addAuth	'ɽ���������̹�˾',	91	,	'406'
EXEC	addAuth	'���³�',	91	,	'5'
EXEC	addAuth	'������Ŀ��˾',	91	,	'501'
EXEC	addAuth	'ͨ����Ŀ��˾',	91	,	'502'
EXEC	addAuth	'��Ĺ�˾',	91	,	'503'
EXEC	addAuth	'��ó��',	91	,	'504'
EXEC	addAuth	'���¹��ʹ��̹�˾',	91	,	'505'
EXEC	addAuth	'����������˾',	91	,	'506'
EXEC	addAuth	'�����ܹ�˾',	91	,	'507'
EXEC	addAuth	'�½��ֹ�˾',	91	,	'508'
EXEC	addAuth	'�����ֹ�˾',	91	,	'509'
EXEC	addAuth	'���ڷֹ�˾',	91	,	'510'
EXEC	addAuth	'���¹�˾',	91	,	'6'
EXEC	addAuth	'���¹�˾',	91	,	'601'
EXEC	addAuth	'���ع�˾',	91	,	'602'
EXEC	addAuth	'���¹��̹�˾',	91	,	'603'
EXEC	addAuth	'���ϵ������̹�˾',	91	,	'604'

EXEC	addAuth	'��乫˾',	90	,	'1'
EXEC	addAuth	'������ó�׳��׹�˾',	90	,	'101'
EXEC	addAuth	'������طֹ�˾',	90	,	'102'
EXEC	addAuth	'��俵�λ�����',	90	,	'103'
EXEC	addAuth	'�����׹�',	90	,	'104'
EXEC	addAuth	'�������Զ�����˾',	90	,	'105'
EXEC	addAuth	'�Ͽ���˾',	90	,	'106'
EXEC	addAuth	'�����������',	90	,	'107'
EXEC	addAuth	'����ִ�������˾',	90	,	'108'
EXEC	addAuth	'�ر�繤������������������޹�˾',	90	,	'109'
EXEC	addAuth	'�����ֹ�˾',	90	,	'110'
EXEC	addAuth	'������͹�˾',	90	,	'111'
EXEC	addAuth	'�����ҵ��˾',	90	,	'112'
EXEC	addAuth	'�����о���',	90	,	'113'
EXEC	addAuth	'�������Դ',	90	,	'114'
EXEC	addAuth	'�ر�繤ӡ����Դ���޹�˾',	90	,	'115'
EXEC	addAuth	'��乫˾',	90	,	'2'
EXEC	addAuth	'�������ֹ�˾',	90	,	'201'
EXEC	addAuth	'���Ϲ���������˾',	90	,	'202'
EXEC	addAuth	'���Ϲ��̹�˾',	90	,	'203'
EXEC	addAuth	'�����ҵ��˾',	90	,	'204'
EXEC	addAuth	'�������ܵ�����˾',	90	,	'205'
EXEC	addAuth	'�Ͼ����ܵ�����˾',	90	,	'206'
EXEC	addAuth	'����԰�ֹ�˾',	90	,	'207'
EXEC	addAuth	'�±䳧',	90	,	'3'
EXEC	addAuth	'��乫˾',	90	,	'301'
EXEC	addAuth	'�±����ع�˾',	90	,	'302'
EXEC	addAuth	'�±���乫˾',	90	,	'303'
EXEC	addAuth	'�±���ʳ��׹��̹�˾',	90	,	'304'
EXEC	addAuth	'�±���ڹ��̼��޹�˾',	90	,	'305'
EXEC	addAuth	'�½����ع�������ó�׹�˾',	90	,	'306'
EXEC	addAuth	'������ҵ��',	90	,	'308'
EXEC	addAuth	'������',	90	,	'309'
EXEC	addAuth	'³�¹�˾',	92	,	'4'
EXEC	addAuth	'ͨ�õ�����Ŀ��˾',	92	,	'401'
EXEC	addAuth	'���ܵ�����Ŀ��˾',	92	,	'402'
EXEC	addAuth	'���׵�����Ŀ��˾',	92	,	'403'
EXEC	addAuth	'���¾�����ҵ��',	92	,	'404'
EXEC	addAuth	'���¹��̹�˾',	92	,	'405'
EXEC	addAuth	'ɽ���������̹�˾',	92	,	'406'
EXEC	addAuth	'���³�',	92	,	'5'
EXEC	addAuth	'������Ŀ��˾',	92	,	'501'
EXEC	addAuth	'ͨ����Ŀ��˾',	92	,	'502'
EXEC	addAuth	'��Ĺ�˾',	92	,	'503'
EXEC	addAuth	'��ó��',	92	,	'504'
EXEC	addAuth	'���¹��ʹ��̹�˾',	92	,	'505'
EXEC	addAuth	'����������˾',	92	,	'506'
EXEC	addAuth	'�����ܹ�˾',	92	,	'507'
EXEC	addAuth	'�½��ֹ�˾',	92	,	'508'
EXEC	addAuth	'�����ֹ�˾',	92	,	'509'
EXEC	addAuth	'���ڷֹ�˾',	92	,	'510'
EXEC	addAuth	'���¹�˾',	92	,	'6'
EXEC	addAuth	'���¹�˾',	92	,	'601'
EXEC	addAuth	'���ع�˾',	92	,	'602'
EXEC	addAuth	'���¹��̹�˾',	92	,	'603'
EXEC	addAuth	'���ϵ������̹�˾',	92	,	'604'


EXEC	addAuth	'��乫˾'	,	90	,	'101,102,103,104,105,106,107,108,109,110,111,112,113,114,115'
EXEC	addAuth	'��乫˾'	,	90	,	'201,202,203,204,205,206,207'
EXEC	addAuth	'�±䳧'	,	90	,	'301,302,303,304,305,306,308,309'
EXEC	addAuth	'³�¹�˾'	,	92	,	'401,402,403,404,405,406'
EXEC	addAuth	'���³�'	,	92	,	'501,502,503,504,505,506,507,508,509,510'
EXEC	addAuth	'���¹�˾'	,	92	,	'601,602,603,604'

EXEC	addAuth '130182' , 90, '1,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,2,201,202,203,204,205,206,207,3,301,302,303,304,305,306,308,309'
EXEC	addAuth '130182' , 92, '4,401,402,403,404,405,406,5,501,502,503,504,505,506,507,508,509,510,6,601,602,603,604'

EXEC	addAuth '155165' , 90, '1,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,2,201,202,203,204,205,206,207,3,301,302,303,304,305,306,308,309'
EXEC	addAuth '155165' , 92, '4,401,402,403,404,405,406,5,501,502,503,504,505,506,507,508,509,510,6,601,602,603,604'

delete from system_extend_auth_t where authId in (89,90,91,92) and companyId in (select id from jygk_dwxx where parent_ID = 12345)

