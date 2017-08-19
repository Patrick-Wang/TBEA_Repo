package com.tbea.ic.operation.model.dao.navigator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.navigator.NavigatorItem;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

@Repository
@Transactional("transactionManager")
public class NavigatorItemDaoImpl extends AbstractReadWriteDaoImpl<NavigatorItem> implements NavigatorItemDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	
	public Account getAccount(String usrName){
		EntityManager entityManager = this.getEntityManager();
		Query q = entityManager.createQuery("from Account where name = :userName");
		q.setParameter("userName", usrName);
		List<Account> account = q.getResultList();
		if (!account.isEmpty()){
			return account.get(0);
		}
		return null;
	}

	@Override
	public List<Integer> getExcludedItem(List<Integer> auths, List<String> openAuths) {
		EntityManager entityManager = this.getEntityManager();
		String sqlAuth = "";
		if (!auths.isEmpty()) {
			sqlAuth = " (type = 1 and authId in (:auths)) or (type = 0 and authId not in (:auths)) ";
		}
		
		String sqlAuthNames = "";
		if (!openAuths.isEmpty()) {
			sqlAuthNames = " (type = 1 and authName in (:authNames))  or (type = 0 and authName not in (:authNames)) ";
		}
		
		String sqlAuthTogether = "";
		if (!sqlAuth.isEmpty() && !openAuths.isEmpty()) {
			sqlAuthTogether = sqlAuth + " or " + sqlAuthNames;
		}else if (!sqlAuth.isEmpty() || !openAuths.isEmpty()){
			sqlAuthTogether = sqlAuth + sqlAuthNames;
		}
		
		String sql = "select distinct itemId from navigator_negative_auth ";
		
		if (!sqlAuthTogether.isEmpty()) {
			sql += " where " + sqlAuthTogether;
		}
		
		Query q = entityManager.createNativeQuery(sql);
		if (!auths.isEmpty()) {
			q.setParameter("auths", auths);
		}
		if (!openAuths.isEmpty()) {
			q.setParameter("authNames", openAuths);
		}
		return q.getResultList();
	}

	@Override
	public List<Integer> getIncludedItems(List<Integer> auths, List<String> openAuths, List<Integer> excludedItems) {
		EntityManager entityManager = this.getEntityManager();

		String sqlAuth = "";
		if (!auths.isEmpty()) {
			sqlAuth = " authId in (:auths) ";
		}
		
		String sqlAuthNames = "";
		if (!openAuths.isEmpty()) {
			sqlAuthNames = " authName in (:authNames) ";
		}
		
		String sqlAuthTogether = "";
		if (!sqlAuth.isEmpty() && !openAuths.isEmpty()) {
			sqlAuthTogether = sqlAuth + " or " + sqlAuthNames;
		}else if (!sqlAuth.isEmpty() || !openAuths.isEmpty()){
			sqlAuthTogether = sqlAuth + sqlAuthNames;
		}
		
		String sqlWhere = "";
		if (!excludedItems.isEmpty()) {
			sqlWhere = "  itemId not in (:excludes) ";
		}
		
		if (!sqlWhere.isEmpty() && !sqlAuthTogether.isEmpty()) {
			sqlWhere = sqlWhere + " and (" + sqlAuthTogether + ")";
		}else {
			sqlWhere = sqlWhere + sqlAuthTogether;
		}
				
		String sql = "select distinct itemId from navigator_positive_auth ";
		
		if (!sqlWhere.isEmpty()) {
			sql += " where " + sqlWhere;
		}
		
		Query q = entityManager.createNativeQuery(sql);
		
		if (!excludedItems.isEmpty()) {
			q.setParameter("excludes", excludedItems);
		}
		if (!auths.isEmpty()) {
			q.setParameter("auths", auths);
		}
		if (!openAuths.isEmpty()) {
			q.setParameter("authNames", openAuths);
		}
		return q.getResultList();
	}

	@Override
	public List<NavigatorItem> getNaviItems(List<Integer> includeItems) {
		EntityManager entityManager = this.getEntityManager();
		Query q = entityManager.createQuery("from NavigatorItem "
				+ "where id in (:includes) and (parent is null or parent in (:includes))" );
		q.setParameter("includes", includeItems);
		return q.getResultList();
	}

}
