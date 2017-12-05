--采购订单执行情况
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'CUX_PURORDER_V')
DROP TABLE CUX_PURORDER_V
CREATE TABLE [dbo].[CUX_PURORDER_V](
	[id] [int] IDENTITY(1,1) NOT NULL,
	person varchar(100), --采购员
	order_num varchar(100), --订单号
	create_date varchar(100), --创建日期
	approve_status varchar(100), --审批状态
	vender varchar(100), --供应商名称
	line_num varchar(100), --行号
	materiel_code varchar(100), --物料编码
	materiel_intr varchar(100), --物料说明
	unit varchar(100), --单位
	num varchar(100), --数量
	tax_rate varchar(100), --税码
	unit_price varchar(100), --单价
	amount varchar(100), --金额
	tax_unit_price varchar(100), --含税单价
	tax_amount varchar(100), --含税总额
	promise_date varchar(100), --承诺日期
	EDD varchar(100), --预计交货日期
	REC_DATE varchar(100), --接收日期
	bill_date varchar(100), --接收日期
	ord_num varchar(100), --已订购数量
	bill_num varchar(100), --已开单数量
	require_check varchar(100), --要求检验
	uncheck_num varchar(100), --未质检数量
	checked_num varchar(100), --已质检数量
	qualified_num varchar(100), --合格数量
	unqualified_num varchar(100), --不合格数量
	cancel_num varchar(100), --已取消数量
	rec_num varchar(100), --已接收数量
	rec_amount varchar(100), --接收金额
	rec_org varchar(100), --接收组织
	stock_num varchar(100), --库存现有量
	stock_available_num varchar(100), --库存可用量
	figure_num varchar(100), --图号
	contract_num varchar(100), --合同产品号
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
