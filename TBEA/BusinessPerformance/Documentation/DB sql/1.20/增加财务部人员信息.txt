---------------------财务部马敏权限------------------
INSERT INTO [dbo].[jygk_account] ([name],[password],[role],[deprecated] ) VALUES ('836105', 1234, 0, 0)

INSERT INTO [dbo].[jygk_dw_refer_account] ([account_ID],[dwid]) VALUES ('304', 0)

insert into system_extend_auth (account_id, auth_type) values
((select id from jygk_account where name='836105'), 75),
((select id from jygk_account where name='836105'), 76),
((select id from jygk_account where name='836105'), 56),
((select id from jygk_account where name='836105'), 23)

----------------------------------------------------------------

