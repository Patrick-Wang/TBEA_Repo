update auth_instruction set instruction = '������Ϣ�鿴' where id = 66
update auth_instruction set instruction = '�²�Ʒ��Ϣ�鿴' where id = 67

update auth_instruction set instruction = 'ָ������' where id = 6
update auth_instruction set instruction = '��Դ�ܱ��г����¼��' where id = 18
update auth_instruction set instruction = '��Դ�ܱ��г�����鿴' where id = 19


SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (88, '�û�Ȩ�޹���')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

