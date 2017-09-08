alter table jygk_account add appId varchar(100)

insert into jygk_account (name, password, role, appId) values ('风控系统', '1234', 0, '03365721fe543f89032486fd9e90eb42')
--substring(sys.fn_sqlvarbasetostr(HashBytes('MD5',convert(varchar(100), newid()))),3,32)