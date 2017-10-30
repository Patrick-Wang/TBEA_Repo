package com.tbea.erp.report.model.dao.navigateitem;


import com.tbea.erp.report.model.dao.navigateitem.NavigateItemDao;
import com.speed.frame.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.NavigateItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Repository(NavigateItemDaoImpl.NAME)
@Transactional("transactionManager")
public class NavigateItemDaoImpl extends AbstractReadWriteDaoImpl<NavigateItemEntity> implements NavigateItemDao {
	public final static String NAME = "NavigateItemDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}



	@Override
	public NavigateItemEntity getItem(Integer item) {
		Query q = this.getEntityManager().createQuery("from NavigateItemEntity where id = :id");
		q.setParameter("id", item);
		List ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return (NavigateItemEntity) ret.get(0);
	}

    @Override
    public List<NavigateItemEntity> getItems(Account account) {
        Query q = this.getEntityManager().createNativeQuery("select distinct navigator_item_id " +
                "from CUX_NAVIGATEAUTHORITY_T where authority = :role");
        q.setParameter("role", account.getRole());
        List<BigDecimal> navItemIds = q.getResultList();

        List<Integer> ids = new ArrayList<Integer>();
        for (BigDecimal bd : navItemIds){
            ids.add(bd.intValue());
        }

        q = this.getEntityManager().createQuery("from NavigateItemEntity where url is null or id in :ids");
        q.setParameter("ids", ids);
        return q.getResultList();
    }
}
