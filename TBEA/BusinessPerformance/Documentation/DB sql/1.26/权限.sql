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

