CREATE PROCEDURE [dbo].[addAuth]
    @account varchar(200),  
    @auth int,  
    @comps varchar(1000)
AS  
BEGIN  
    -- SET NOCOUNT ON added to prevent extra result sets from  
    -- interfering with SELECT statements.  
--BEGIN TRAN --begin transacion  
  
DECLARE @tran_error int;  -- error code  
SET @tran_error = 0;  
  
BEGIN TRY   
    declare @location1 int    --split index 1  

    declare @split1 char(1)   --split 1  

    set @split1 = ','  

    declare @str varchar(2000)  
    declare @temp1 varchar(2000)  
    declare @accId int
	declare @compId int

 
    set @str=ltrim(rtrim(@comps))  
    --PRINT @str;  
    set @location1=charindex(@split1,@str)  -- 第一级分隔符第一个的位置  
    --PRINT @location1;  
    SET @temp1 = SUBSTRING(@str,0,CHARINDEX(@split1,@str))  --第一行银行信息  
    --PRINT @temp1;  
      
	set @accId = (select id from jygk_account where name = @account);

	if (@accId is not null) 
	begin

    WHILE @location1<>0  
    BEGIN  
        --PRINT @temp1;
		--PRINT @account;
		--PRINT @auth;
		set @compId = (select id from jygk_dwxx where id = CONVERT(int, @temp1));
		
		print @compId;
		if (@compId is not null)
		begin
		insert into system_extend_auth (account_id, company_id, auth_type) values
		(@accId, @compId, @auth);
		end
		else
		begin
			print @account + ' 用户的 ' + @temp1 + ' 公司的 ' + CONVERT(varchar(100), @auth) + ' 权限插入失败'
		end
        --print @str  
        SET @str = right(@str,len(@str) - charindex(@split1,@str));  
        SET @location1=charindex(@split1,@str);  
        SET @temp1 = SUBSTRING(@str,0,CHARINDEX(@split1,@str))  --下一个银行信息;  
       
    END 
	set @compId = (select id from jygk_dwxx where id = CONVERT(int, @str));
	if (@compId is not null)
		begin
		insert into system_extend_auth (account_id, company_id, auth_type) values
		(@accId, @compId, @auth);
		end
	else
		begin
			print @account + ' 用户的 ' + @str + ' 公司的 ' + CONVERT(varchar(100), @auth) + ' 权限插入失败'
		end
	end
	else
	begin
		print @account + ' 用户不存在'
	end
	--PRINT @str;
	--PRINT 1231;
    --UPDATE MaterialControl.供货商企业级关键信息修改记录表 SET 记录状态代码 = 'C',确认人=@Comfirmer,确认时间=@ComfirmTime WHERE 表单编号 = @SheetNumber;  
END TRY  
  
BEGIN CATCH  
    PRINT '出现异常，错误编号：' + convert(varchar,error_number()) + ',错误消息：' + error_message()  
   
END CATCH  
  
--IF(@tran_error > 0)  
--    BEGIN  
--        --执行出错，回滚事务  
--        ROLLBACK TRAN;  
--        SET @iRel = -1;  
--    END  
--ELSE  
--    BEGIN  
--        --没有异常，提交事务  
--        COMMIT TRAN;  
--        SET @iRel = 1;  
--    END  
    
END  





CREATE PROCEDURE [dbo].[copyAuth] 
	-- Add the parameters for the stored procedure here
	@fromUsr varchar(50), 
	@destUsr varchar(50)
AS
BEGIN
	declare @fromUid int;
	declare @destUid int;
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET @fromUid = (select id from jygk_account where name = @fromUsr); 
	SET @destUid = (select id from jygk_account where name = @destUsr);
	if (@fromUid is not null and @destUid is not null)
	begin
		insert into system_extend_auth (account_id, company_id, auth_type)  
		(select @destUid account_id, company_id, auth_type 
		from system_extend_auth 
		where account_id = @fromUid) ;
	end
	else
	begin
		print @fromUsr + ' to ' + @destUsr + ' failed';
	end

END


