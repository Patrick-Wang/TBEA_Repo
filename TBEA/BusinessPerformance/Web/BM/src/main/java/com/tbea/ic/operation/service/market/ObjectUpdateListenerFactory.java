package com.tbea.ic.operation.service.market;

import java.util.Calendar;

import com.tbea.ic.operation.model.dao.market.bidInfo.MktBidInfoDao;
import com.tbea.ic.operation.model.dao.market.projectInfo.MktProjectInfoDao;
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.MarketServiceImpl.OnUpdateMktObjectListener;

public class ObjectUpdateListenerFactory {

	public static OnUpdateMktObjectListener createBidUpdateListener(
			MktBidInfoDao bidInfoDao) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktBidInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				MktBidInfo mbi = (MktBidInfo) mktObject;
				MktBidInfo mbiOld = bidInfoDao.getById(mbi.getBidNo());
				if (mbiOld == null || mbiOld.getStartdate() == null) {
					mbi.setStartdate(new java.sql.Date(Calendar.getInstance()
							.getTimeInMillis()));
				} else {
					mbi.setStartdate(mbiOld.getStartdate());
				}

				if (mbiOld == null || mbiOld.getEnddate() == null) {
					mbi.setEnddate(mbi.getStartdate());
				} else {
					mbi.setEnddate(mbiOld.getEnddate());
				}

				bidInfoDao.update(mbi);
			}
		};
	}

	public static OnUpdateMktObjectListener createSignUpdateListener(
			MktSignContractDao signContractDao) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktSignContract.class;
			}

			@Override
			public void update(Object mktObject) {
				MktSignContract msc = (MktSignContract) mktObject;
				MktSignContract mscOld = signContractDao.getById(msc
						.getContractNo());
				if (mscOld == null || mscOld.getStartdate() == null) {
					msc.setStartdate(new java.sql.Date(Calendar.getInstance()
							.getTimeInMillis()));
				} else {
					msc.setStartdate(mscOld.getStartdate());
				}

				if (mscOld == null || mscOld.getEnddate() == null) {
					msc.setEnddate(msc.getStartdate());
				} else {
					msc.setEnddate(mscOld.getEnddate());
				}
				signContractDao.update(msc);
			}

		};
	}

	public static OnUpdateMktObjectListener createProjectUpdateListener(
			MktProjectInfoDao projectInfoDao) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktProjectInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				MktProjectInfo mpi = (MktProjectInfo) mktObject;
				MktProjectInfo mpiOld = projectInfoDao.getById(mpi
						.getProjectNo());
				if (mpiOld == null || mpiOld.getStartdate() == null) {
					mpi.setStartdate(new java.sql.Date(Calendar.getInstance()
							.getTimeInMillis()));
				} else {
					mpi.setStartdate(mpiOld.getStartdate());
				}

				if (mpiOld == null || mpiOld.getEnddate() == null) {
					mpi.setEnddate(mpi.getStartdate());
				} else {
					mpi.setEnddate(mpiOld.getEnddate());
				}
				projectInfoDao.update(mpi);
			}
		};
	}
	
	public static OnUpdateMktObjectListener createBidAddListener(
			MktBidInfoDao bidInfoDao) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktBidInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				MktBidInfo mbi = (MktBidInfo) mktObject;
				mbi.setStartdate(new java.sql.Date(Calendar.getInstance()
						.getTimeInMillis()));
				mbi.setEnddate(mbi.getStartdate());
				bidInfoDao.update(mbi);
			}
		};
	}

	public static OnUpdateMktObjectListener createSignAddListener(
			MktSignContractDao signContractDao) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktSignContract.class;
			}

			@Override
			public void update(Object mktObject) {
				MktSignContract msc = (MktSignContract) mktObject;
				msc.setStartdate(new java.sql.Date(Calendar.getInstance()
						.getTimeInMillis()));
				msc.setEnddate(msc.getStartdate());
				signContractDao.update(msc);
			}

		};
	}

	public static OnUpdateMktObjectListener createProjectAddListener(
			MktProjectInfoDao projectInfoDao) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktProjectInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				MktProjectInfo mpi = (MktProjectInfo) mktObject;
				mpi.setStartdate(new java.sql.Date(Calendar.getInstance()
						.getTimeInMillis()));
				mpi.setEnddate(mpi.getStartdate());
				projectInfoDao.update(mpi);
			}
		};
	}
	
	public static OnUpdateMktObjectListener createBidEditListener(
			MktBidInfoDao bidInfoDao, MktBidInfo mbiOld) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktBidInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				MktBidInfo mbi = (MktBidInfo) mktObject;
				if (mbiOld == null || mbiOld.getStartdate() == null) {
					mbi.setStartdate(new java.sql.Date(Calendar.getInstance()
							.getTimeInMillis()));
				} else {
					mbi.setStartdate(mbiOld.getStartdate());
				}

				if (mbiOld == null || mbiOld.getEnddate() == null) {
					mbi.setEnddate(mbi.getStartdate());
				} else {
					mbi.setEnddate(mbiOld.getEnddate());
				}

				bidInfoDao.update(mbi);
			}
		};
	}

	public static OnUpdateMktObjectListener createSignEditListener(
			MktSignContractDao signContractDao, MktSignContract mscOld) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktSignContract.class;
			}

			@Override
			public void update(Object mktObject) {
				MktSignContract msc = (MktSignContract) mktObject;
	
				if (mscOld == null || mscOld.getStartdate() == null) {
					msc.setStartdate(new java.sql.Date(Calendar.getInstance()
							.getTimeInMillis()));
				} else {
					msc.setStartdate(mscOld.getStartdate());
				}

				if (mscOld == null || mscOld.getEnddate() == null) {
					msc.setEnddate(msc.getStartdate());
				} else {
					msc.setEnddate(mscOld.getEnddate());
				}
				signContractDao.update(msc);
			}

		};
	}

	public static OnUpdateMktObjectListener createProjectEditListener(
			MktProjectInfoDao projectInfoDao, MktProjectInfo mpiOld) {
		return new OnUpdateMktObjectListener() {

			@Override
			public Class<?> onGetClass() {
				return MktProjectInfo.class;
			}

			@Override
			public void update(Object mktObject) {
				MktProjectInfo mpi = (MktProjectInfo) mktObject;
				if (mpiOld == null || mpiOld.getStartdate() == null) {
					mpi.setStartdate(new java.sql.Date(Calendar.getInstance()
							.getTimeInMillis()));
				} else {
					mpi.setStartdate(mpiOld.getStartdate());
				}

				if (mpiOld == null || mpiOld.getEnddate() == null) {
					mpi.setEnddate(mpi.getStartdate());
				} else {
					mpi.setEnddate(mpiOld.getEnddate());
				}
				projectInfoDao.update(mpi);
			}
		};
	}
}
