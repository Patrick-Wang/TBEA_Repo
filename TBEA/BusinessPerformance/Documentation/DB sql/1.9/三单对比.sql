alter table JYGK_SDDB_ORDER_EXECUTION_DETAILS add serial_num varchar(30)
alter table JYGK_SDDB_ORDER_EXECUTION_DETAILS alter column contract_no nvarchar(100)
alter table JYGK_SDDB_ORDER_EXECUTION_DETAILS alter column code nvarchar(100)
create view JYGK_SDDB_ORDER_EXECUTION_VIEW as
select *
from (
	select 
		*,
		row_number() over (partition by code order by convert(int, substring(serial_num, 3, 6)) desc) rn
	from 
		JYGK_SDDB_ORDER_EXECUTION_DETAILS
	where 
		code is not null
) t
where 
	rn = 1