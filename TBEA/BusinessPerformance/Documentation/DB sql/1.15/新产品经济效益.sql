IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'new_product_benefit')
DROP TABLE new_product_benefit
CREATE TABLE [dbo].[new_product_benefit](
	[id] [int] IDENTITY(1,1) NOT NULL,--NoT NULL,
	nf [int],
	yf [int],
	dwid [int],
	ctrtAmountNew [numeric](18, 4),
	saleRevenueNew [numeric](18, 4),
	profitNew [numeric](18, 4),
	productionNew [numeric](18, 4),
	coAmount [numeric](18, 4),
	saleRevenue [numeric](18, 4),
	profit [numeric](18, 4),
	production [numeric](18, 4),
	time [datetime]
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
