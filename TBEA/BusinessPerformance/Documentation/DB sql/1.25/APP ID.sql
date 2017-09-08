alter table jygk_account add appId varchar(100)
--substring(sys.fn_sqlvarbasetostr(HashBytes('MD5',convert(varchar(100), newid()))),3,32)