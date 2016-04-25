package com.tbea.ic.operation.model.dao.identifier.cbfx.cbfl;
import com.tbea.ic.operation.model.entity.identifier.cbfx.CbflEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (0,N'土方剥离爆破成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (1,N'原煤爆破成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (2,N'原煤采运成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (3,N'回筛倒运成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (4,N'装车成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (5,N'直接成本合计')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (6,N'非可控成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (7,N'可控成本')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (8,N'制造费用小计')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (9,N'技改财务费用')
//INSERT [dbo].[identifier_cbfx_cbfl] ([id], [name])VALUES (10,N'生产成本合计')

public enum Cbfl {
	TFBLBPCB,
	YMBPCB,
	YMCYCB,
	HSDYCB,
	ZCCB,
	ZJCBHJ,
	FKKCB,
	KKCB,
	ZZFYXJ,
	JGCWFY,
	SCCBHJ,
	END
}
