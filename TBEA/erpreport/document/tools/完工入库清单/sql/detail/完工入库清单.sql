--完工入库清单
IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'CUX_DEVBALANCE_V')
DROP TABLE CUX_DEVBALANCE_V
CREATE TABLE [dbo].[CUX_DEVBALANCE_V](
	[id] [int] IDENTITY(1,1) NOT NULL,
	serial varchar(100), --序号
	source_project_id varchar(100), --生产号
	project_model varchar(100), --产品型号
	pic_model varchar(100), --图号
	order_company varchar(100), --定货单位
	transaction_date date, --完工时间
	transaction_quantity int, --台数
	output_value int, --产值
	output int, --产量
	electric_level varchar(100), --电压等级
	product_center varchar(100), --生产中心
	wip_entity_name varchar(100), --工单号
	materiel_code varchar(100), --物料编码
	materiel_desc varchar(100), --物料说明
	resp_peorson varchar(100), --完工人
	[_src] [varchar](50),
	[_time] [datetime]
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]