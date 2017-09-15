
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'user_roles')
DROP TABLE user_roles
CREATE TABLE [dbo].[user_roles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	userId int, --用户
	roleId int --角色
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'roles')
DROP TABLE roles
CREATE TABLE [dbo].[roles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	roleName varchar(100) unique NOT NULL,  --角色名称
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'system_extend_auth_t')
DROP TABLE system_extend_auth_t
CREATE TABLE [dbo].[system_extend_auth_t](
	[id] [int] IDENTITY(1,1) NOT NULL,
	roleId int, --角色
	companyId int,
	authId int
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

insert into roles (roleName) select distinct name from jygk_account where id in (select account_id from system_extend_auth)

insert into user_roles (userId, roleId) select acc.id, rol.id from roles rol left join jygk_account acc on rol.roleName = acc.name

insert into system_extend_auth_t (roleId, companyId, authId) 
select ur.roleId, company_id, auth_type from system_extend_auth sea left join user_roles ur on sea.account_id = ur.userId 

GO
drop view system_extend_auth_v
GO
create view system_extend_auth_v as
select seat.id, ur.userId account_id, seat.companyId, seat.authId from user_roles ur left join system_extend_auth_t seat on ur.roleId = seat.roleId 