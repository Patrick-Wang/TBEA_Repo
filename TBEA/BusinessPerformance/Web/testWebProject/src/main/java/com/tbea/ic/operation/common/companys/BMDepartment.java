package com.tbea.ic.operation.common.companys;


class BMDepartment extends AbstractOrganization {

	public BMDepartment() {
		append(getCompany(CompanyType.SBGS, 1)
	    		.append(getCompany(CompanyType.BYQC,101))
	    		.append(getCompany(CompanyType.GJMYCTGS,102))
	    		.append(getCompany(CompanyType.ZTFGS,103))
	    		.append(getCompany(CompanyType.KJHGQ,104))
	    		.append(getCompany(CompanyType.DQZJFGS,105))
	    		.append(getCompany(CompanyType.DLZDHGS,106))
	    		.append(getCompany(CompanyType.SKGS,107))
	    		.append(getCompany(CompanyType.XSZX,108))
	    		.append(getCompany(CompanyType.XDWLGS,109))
	    		.append(getCompany(CompanyType.DLKCSJGS,110))
	    		.append(getCompany(CompanyType.XLGGS,111))
	    		.append(getCompany(CompanyType.GNCTB,112))
	    		.append(getCompany(CompanyType.DLAZB,113))
	    		.append(getCompany(CompanyType.ZXGS,114))
	    		.append(getCompany(CompanyType.HXGS,115))
	    		.append(getCompany(CompanyType.TBDG_YD_NYYXGS,116))
	    		.append(getCompany(CompanyType.SBWYGS,117))
		).append(
				getCompany(CompanyType.HBGS, 2)
				.append(getCompany(CompanyType.DQFGS,201))
				.append(getCompany(CompanyType.HNGJWLGS,202))
				.append(getCompany(CompanyType.HNGCGS,203))
				.append(getCompany(CompanyType.ZYGS,204))
				.append(getCompany(CompanyType.HNZNDQGS,205))
				.append(getCompany(CompanyType.NJZNDQGS,206))
				.append(getCompany(CompanyType.HNYLGS,207))

		).append(
				getCompany(CompanyType.XBC, 3)
				.append(getCompany(CompanyType.TBGS,301))
				.append(getCompany(CompanyType.ZTGS,302))
				.append(getCompany(CompanyType.XBGS,303))
				.append(getCompany(CompanyType.GJCTGCGS,304))
				.append(getCompany(CompanyType.GNGCJXGS,305))
				.append(getCompany(CompanyType.XJXTGJWLMYGS,306))
		).append(
				getCompany(CompanyType.LLGS, 4)
				.append(getCompany(CompanyType.XSZGS,401))
				.append(getCompany(CompanyType.SDFGS,402))
				.append(getCompany(CompanyType.GJCTZGS,403))
				.append(getCompany(CompanyType.TYDXXMGS_LL,404))
				.append(getCompany(CompanyType.JNDXXMGS_LL,405))
				.append(getCompany(CompanyType.DLCLFGS,406))
				.append(getCompany(CompanyType.TZDLYFXMGS,407))
				.append(getCompany(CompanyType.DLJXSYB,408))
				.append(getCompany(CompanyType.XNYSYB,409))
				.append(getCompany(CompanyType.DLGCGS,410))
				.append(getCompany(CompanyType.SDDLGCGS,411))
				.append(getCompany(CompanyType.KYDLXMGS,412))				
		).append(
				getCompany(CompanyType.XLC, 5)
				.append(getCompany(CompanyType.TYDXXMGS_XL,501))
				.append(getCompany(CompanyType.JNDXXMGS_XL,502))
				.append(getCompany(CompanyType.TZDLXMGS,503))
				.append(getCompany(CompanyType.DLDLXMGS,504))
				.append(getCompany(CompanyType.TZDGXJDGCLYXGS,505))
				.append(getCompany(CompanyType.DKHYB,506))
				.append(getCompany(CompanyType.DKHEB,507))
				.append(getCompany(CompanyType.GJMYB,508))
				.append(getCompany(CompanyType.GNGCGS,509))
				.append(getCompany(CompanyType.GJGCGS_XL,510))
				.append(getCompany(CompanyType.ZJZTGJWLYXGS,511))	
		).append(
				getCompany(CompanyType.DLGS, 6)
				.append(getCompany(CompanyType.TLGS,601))
				.append(getCompany(CompanyType.XTGS,602))
				.append(getCompany(CompanyType.GCGS_DL,603))
				.append(getCompany(CompanyType.BB,604))
				.append(getCompany(CompanyType.XSGS,605))
				.append(getCompany(CompanyType.GJYWB,606))
				.append(getCompany(CompanyType.FLSWB,607))
				.append(getCompany(CompanyType.DYCJGJWLMYYXGS,608))
				.append(getCompany(CompanyType.XNDQGS,609))		
		);
	}


	@Override
	public int getDepth() {
		return 2;
	}

}
