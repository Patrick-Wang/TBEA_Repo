package com.tbea.datatransfer.model.dao.local.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.datatransfer.model.entity.local.BLLocal;

//@Repository
@Transactional("transactionManager")
public class BLLocalDaoImpl extends AbstractReadWriteDaoImpl<BLLocal> implements
		BLLocalDao {

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLLocal> getAllBLLocal() {
		String sql = "From BLLocal";
		Query query = getEntityManager().createQuery(sql);
		List<BLLocal> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public void deleteBLLocalByQY(int qybh) {
		String sql = "Delete From BLLocal Where qybh = :qybh";
		Query query = getEntityManager().createQuery(sql);
		query.setParameter("qybh", qybh);
		query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getBLJE() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny"
				+ ", sum(blje) as dqblje, count(id) as dqblfs"
				+ " from yszk_zj_bl where bldqr is not null"
				+ " group by convert(varchar(6), bldqr, 112) order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getBLHKJE() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny"
				+ ", sum(blhkje) as dqblzyhkje, count(id) as dqblzyhkfs"
				+ " from yszk_zj_bl where bldqr is not null and blhkje <> 0"
				+ " group by convert(varchar(6), bldqr, 112) order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getBLJEByQY() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny, qybh"
				+ ", sum(blje) as dqblje, count(id) as dqblfs"
				+ " from yszk_zj_bl where bldqr is not null"
				+ " group by convert(varchar(6), bldqr, 112), qybh"
				+ " order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getBLHKJEByQY() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny, qybh"
				+ ", sum(blhkje) as dqblzyhkje, count(id) as dqblzyhkfs"
				+ " from yszk_zj_bl where bldqr is not null and blhkje <> 0"
				+ " group by convert(varchar(6), bldqr, 112), qybh"
				+ " order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getKHFXS() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny"
				+ ", sum(blje) as dqblje, count(id) as dqblfs"
				+ " from yszk_zj_bl where bldqr is not null and kxxz = 2"
				+ " group by convert(varchar(6), bldqr, 112) order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getFKHFXS() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny"
				+ ", sum(blje) as dqblje, count(id) as dqblfs"
				+ " from yszk_zj_bl where bldqr is not null and kxxz = 1"
				+ " group by convert(varchar(6), bldqr, 112) order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getKHFXSByQY() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny, qybh"
				+ ", sum(blje) as dqblje, count(id) as dqblfs"
				+ " from yszk_zj_bl where bldqr is not null and kxxz = 2"
				+ " group by convert(varchar(6), bldqr, 112), qybh"
				+ " order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getFKHFXSByQY() throws Exception {
		String sql = "select convert(varchar(6), bldqr, 112) as ny, qybh"
				+ ", sum(blje) as dqblje, count(id) as dqblfs"
				+ " from yszk_zj_bl where bldqr is not null and kxxz = 1"
				+ " group by convert(varchar(6), bldqr, 112), qybh"
				+ " order by ny";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		return result;
	}

}
