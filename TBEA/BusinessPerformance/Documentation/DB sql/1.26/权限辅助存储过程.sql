drop PROCEDURE [dbo].[addAuth]
GO
CREATE PROCEDURE [dbo].[addAuth]
    @role varchar(200),  
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
    declare @roleId int
	declare @compId int

 
    set @str=ltrim(rtrim(@comps))  
    --PRINT @str;  
    set @location1=charindex(@split1,@str)  -- ��һ���ָ�����һ����λ��  
    --PRINT @location1;  
    SET @temp1 = SUBSTRING(@str,0,CHARINDEX(@split1,@str))  --��һ��������Ϣ  
    --PRINT @temp1;  
      
	set @roleId = (select id from roles where roleName = @role);

	if (@roleId is not null) 
	begin

    WHILE @location1<>0  
    BEGIN  
        --PRINT @temp1;
		--PRINT @role;
		--PRINT @auth;
		set @compId = (select id from jygk_dwxx where id = CONVERT(int, @temp1));
		
		--print @compId;
		if (@compId is not null)
		begin
			if (not exists (select * from system_extend_auth_t where roleId = @roleId and companyId = @compId and authId = @auth))
			begin
				insert into system_extend_auth_t (roleId, companyId, authId) values	(@roleId, @compId, @auth);
				print @role + ' ��ɫ�� ' + @temp1 + ' ��˾�� ' + CONVERT(varchar(100), @auth) + ' Ȩ�޲���ɹ�'
			end
			else
			begin
				print @role + ' ��ɫ�� ' + @temp1 + ' ��˾�� ' + CONVERT(varchar(100), @auth) + ' Ȩ���Ѿ�����'
			end
		end
		else
		begin
			print @role + ' ��ɫ�� ' + @temp1 + ' ��˾�� ' + CONVERT(varchar(100), @auth) + ' Ȩ�޲���ʧ��'
		end
        --print @str  
        SET @str = right(@str,len(@str) - charindex(@split1,@str));  
        SET @location1=charindex(@split1,@str);  
        SET @temp1 = SUBSTRING(@str,0,CHARINDEX(@split1,@str)) 
       
    END 
	set @compId = (select id from jygk_dwxx where id = CONVERT(int, @str));
	if (@compId is not null)
		begin
			if (not exists (select * from system_extend_auth_t where roleId = @roleId and companyId = @compId and authId = @auth))
			begin
				insert into system_extend_auth_t (roleId, companyId, authId) values
				(@roleId, @compId, @auth);
				print @role + ' ��ɫ�� ' + @str + ' ��˾�� ' + CONVERT(varchar(100), @auth) + ' Ȩ�޲���ɹ�'
			end
			else
			begin
				print @role + ' ��ɫ�� ' + @str + ' ��˾�� ' + CONVERT(varchar(100), @auth) + ' Ȩ���Ѿ�����'
			end
		end
	else
		begin
			print @roleId + ' ��ɫ�� ' + @str + ' ��˾�� ' + CONVERT(varchar(100), @auth) + ' Ȩ�޲���ʧ��'
		end
	end
	else
	begin
		print @roleId + ' ��ɫ������'
	end
	--PRINT @str;
	--PRINT 1231;
    --UPDATE MaterialControl.��������ҵ���ؼ���Ϣ�޸ļ�¼�� SET ��¼״̬���� = 'C',ȷ����=@Comfirmer,ȷ��ʱ��=@ComfirmTime WHERE ����� = @SheetNumber;  
END TRY  
  
BEGIN CATCH  
    PRINT '�����쳣�������ţ�' + convert(varchar,error_number()) + ',������Ϣ��' + error_message()  
   
END CATCH  
  
--IF(@tran_error > 0)  
--    BEGIN  
--        --ִ�г����ع�����  
--        ROLLBACK TRAN;  
--        SET @iRel = -1;  
--    END  
--ELSE  
--    BEGIN  
--        --û���쳣���ύ����  
--        COMMIT TRAN;  
--        SET @iRel = 1;  
--    END  
    
END  



