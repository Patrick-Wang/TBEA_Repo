package com.tbea.ic.weixin.service.weixin;

import com.tbea.ic.WeixinSdkException;
import com.tbea.ic.weixin.model.dao.elinkmsg.ElinkMsgDaoImpl;
import com.tbea.ic.weixin.model.dao.elinkmsg.ElinkMsgDao;
import com.tbea.ic.util.JSON;
import com.tbea.ic.weixin.service.weixin.WeiXinService;
import com.tbea.ic.message.entity.News;
import com.tbea.ic.message.sender.Messager;
import com.tbea.ic.structure.Node;
import com.tbea.ic.structure.Node.Visitor;
import com.tbea.ic.weixin.model.dao.persion.JTPersionDaoImpl;
import com.tbea.ic.weixin.model.dao.persion.PersionDao;
import com.tbea.ic.weixin.model.dao.persion.PersionDaoImpl;
import com.tbea.ic.weixin.model.dao.oragnization.JTOragnizationDaoImpl;
import com.tbea.ic.weixin.model.dao.oragnization.OragnizationDao;
import com.tbea.ic.weixin.model.dao.oragnization.OragnizationDaoImpl;
import com.tbea.ic.weixin.model.entity.ElinkMsgEntity;
import com.tbea.ic.weixin.model.entity.OrganizationEntity;
import com.tbea.ic.weixin.model.entity.PersionEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.annotation.Resource;

import com.tbea.ic.auth.AuthException;
import com.tbea.ic.auth.Connection;
import com.tbea.ic.contacts.dao.DepartmentManager;
import com.tbea.ic.contacts.dao.DepartmentQueryCache;
import com.tbea.ic.contacts.entity.Department;
import com.tbea.ic.contacts.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class WeiXinServiceImpl implements WeiXinService {
	
	@Resource(name=ElinkMsgDaoImpl.NAME)
	ElinkMsgDao elinkMsgDao;

	@Resource(name = OragnizationDaoImpl.NAME)
	OragnizationDao oragnizationDao;

	@Resource(name = JTOragnizationDaoImpl.NAME)
	OragnizationDao jtOragnizationDao;
	
	@Resource(name = JTPersionDaoImpl.NAME)
	PersionDao jtPersionDao;
	
	@Resource(name = PersionDaoImpl.NAME)
	PersionDao persionDao;

	
	@Autowired
	public void init() throws AuthException, IOException{
		Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
	}
	
	
	private List<OrganizationEntity> getChildren(OragnizationDao dao, OrganizationEntity root){
		List<OrganizationEntity> result = new ArrayList<OrganizationEntity>();
		Queue<OrganizationEntity> queue = new LinkedList<OrganizationEntity>();
		queue.offer(root);
		while (!queue.isEmpty()){
			OrganizationEntity entity = queue.poll();
			result.add(entity);
			queue.addAll(dao.getByFatherocod(entity.getOcode()));
		}
		return result;
	}
	
	
	private Integer getOcode(String ocode, Map<String, Integer> ocodeMap){
		Integer ret = 0;
		try{
			ret = Integer.valueOf(ocode);
		}catch (Exception e){
			if (ocodeMap.containsKey(ocode)){
				ret = ocodeMap.get(ocode);
			}else{
				if (ocodeMap.containsKey("base")){
					ocodeMap.get("base");
				}else{
					ocodeMap.put("base", 30000);
				}
				Integer base = ocodeMap.get("base");
				ret = base;
				ocodeMap.put("base", base + 1);
				ocodeMap.put(ocode, ret);
			}
			System.out.println("from " + ocode + " to " + ret);
		}
		return ret;
	}
	
	private Department toDepart(OrganizationEntity entity, Map<String, Integer> ocodeMap){
		Department dep = new Department();
		dep.setId(getOcode(entity.getOcode().replace(" ", ""), ocodeMap));
		String pid = "";
		if (entity.getFatherocod() != null){
			pid = entity.getFatherocod().replace(" ", "");
		}
		
		if (pid.isEmpty()){
			dep.setParentid(1);
		}else{
			dep.setParentid(getOcode(pid, ocodeMap));
		}
		dep.setName(entity.getOname());
		return dep;
	}
	

	private Department createRootDepart(){
		Department rootDep = new Department();
		rootDep.setId(1);
		rootDep.setName("");
		return rootDep;
	}
	
	public static class Elapse {

		private Calendar start;
		private Calendar end;

		public Elapse() {
		}

		public void start() {
			start = Calendar.getInstance();

		}

		public long end(String log) {
			end = Calendar.getInstance();
			System.out.println(log
					+ (end.getTimeInMillis() - start.getTimeInMillis()));
			return end.getTimeInMillis() - start.getTimeInMillis();
		}
	}
	
	@Override
	public void transferOrg() {
		Elapse elapse = new Elapse();
		elapse.start();
		Map<String, Integer> ocodeMap = new HashMap<String, Integer>();
		
		
		List<Department> deps = new ArrayList<Department>();
		deps.add(createRootDepart());
		
		List<OrganizationEntity> orgs = oragnizationDao.getAll();//getChildren(oragnizationDao, oragnizationDao.getByOcode("10000"));
		orgs.addAll(jtOragnizationDao.getAll());
		
		for (OrganizationEntity entity : orgs){
			deps.add(toDepart(entity, ocodeMap));
		}

		Node<Department> node = DepartmentQueryCache.build(1, deps);
		final int[] arr = new int[]{0};
		node.accept(new Visitor<Department>(){

			@Override
			public boolean visit(Node<Department> node) {
				DepartmentManager depMgr = DepartmentManager.getInstance();
				Department dep = node.getData();
				if (dep.getId() != 1){
					//depMgr.create(dep);
					arr[0]++;
					System.out.print("" + node.depth());
					for (int i = 0, depth = node.depth(); i < depth; ++i){
						System.out.print("-");
					}
					System.out.println(JSON.stringify(dep));
				}
				return true;
			}
		});
		System.out.println("total : " + arr[0]);
		elapse.end("transferOrg finshed ");
	}

	@Override
	public void transferPersion() {
		List<PersionEntity> person = persionDao.getAllPersion();
		for (PersionEntity per : person){
			Employee employ = new Employee();
			employ.setName(per.getPsnname());
			employ.setMobile(per.getMobile());
			employ.addDepartment(Integer.valueOf(per.getPk_corp().replace(" ", "")));
			employ.setUserid(per.getPsncode());
			System.out.println(JSON.stringify(employ));
			//EmployeeManager.getInstance().create(employ);
		}
		System.out.println(person.size() + "");
	}


	@Override
	public void sendNews(List<Integer> allIds, String[] usrs) throws WeixinSdkException {
		ElinkMsgEntity ent;
		Messager msger = new Messager(25);
		News<News.Article> news = new News<News.Article>();
		List<News.Article> arts = new ArrayList<News.Article>();
		news.setArticles(arts);
		msger.news(news);
		for(int i = 0; i < allIds.size(); ++i){
			ent = elinkMsgDao.getById(allIds.get(i));
			if (ent != null){
				News.Article art = new News.Article();
				arts.add(art);
				art.setTitle(ent.getTitle());
				art.setUrl("http://10.1.4.107:8080/weixin/weixin/testGetMsg.do?id=" + ent.getId());
				//msger.toUser(ent.getToUsername());
			}
		}
		
		
		for(int i = 0; i < usrs.length; ++i){
			msger.toUser(usrs[i]);
		}

		
		
		msger.send();
	}


	@Override
	public String getMsg(Integer id) {
		ElinkMsgEntity ent = elinkMsgDao.getById(id);
		return ent.getMessage();
	}


}
