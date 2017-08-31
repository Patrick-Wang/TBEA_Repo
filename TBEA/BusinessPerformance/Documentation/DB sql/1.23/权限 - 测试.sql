
insert into navigator_item (name, url, extend, parent) values ('经营驾驶舱', 'redirector/redirect.do?url=jsp/ui2/dashboard/test/dashboard.jsp', 0, 3)

SET IDENTITY_INSERT [dbo].[auth_instruction] ON
INSERT INTO [dbo].[auth_instruction] (id, [instruction]) VALUES (87, '报表测试')
SET IDENTITY_INSERT [dbo].[auth_instruction] OFF

insert into system_extend_auth (account_id, company_id, auth_type) values
((select id from jygk_account where name='test'), null, 87)


insert into navigator_positive_auth (itemId, authId) values 
(153, 87)

update navigator_positive_auth set authId = 0 where id = 3
update navigator_positive_auth set authId = 77 where id = 4

delete from system_extend_auth where auth_type = 77 and account_id=(select id from jygk_account where name='test')