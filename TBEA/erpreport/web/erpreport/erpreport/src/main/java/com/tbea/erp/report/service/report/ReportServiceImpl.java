package com.tbea.erp.report.service.report;

import com.datasource.dynamic.HikariCPDataSourceFactory;
import com.tbea.erp.report.service.report.handlers.ContextHandlers;
import com.tbea.erp.report.service.report.handlers.RequestContextHandler;
import com.tbea.erp.report.service.report.handlers.UtilContextHandler;
import com.util.tools.DataNode;
import com.util.tools.PathUtil;
import com.xml.frame.report.ReportLogger;
import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.controller.Scheduler;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.manager.ComponentManager;
import com.xml.frame.report.component.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Service(ReportServiceImpl.NAME)
@Transactional("transactionManager")
public class ReportServiceImpl implements ReportService, Scheduler {
    public final static String NAME = "ReportServiceImpl";

    ComponentManager compMgr;

    @Autowired
    public void init() throws Exception {
        String resPath = PathUtil.getClassRoot() + "META-INF/components/";
        String dsConfigPath = PathUtil.getClassRoot() + "META-INF/datasource.xml";
        compMgr = ComponentManager.create(this, resPath, HikariCPDataSourceFactory.create(dsConfigPath));
    }


    @Override
    public void onSchedule(Context context, Controller controller) throws Exception {
        ReportLogger.trace().info(" on schedule " + controller.getId());
        ContextHandlers handlers = new ContextHandlers();
        handlers.add(new UtilContextHandler());
        context.put("isSchedule", true);
        handlers.onHandle(context);
        controller.run(context);
    }


    @Override
    public DataNode getCSN() {
        return compMgr.getCSN();
    }


    @Override
    public Context doController(HttpServletRequest request, HttpServletResponse response, String controllor) throws Exception {
        return doController(request, response, controllor, new Context());
    }

    @Override
    public Context doController(HttpServletRequest request, HttpServletResponse response, String controllor, Context context) throws Exception {
        Controller controller = compMgr.createController(null, controllor);
        if (null != controller) {
            ContextHandlers handlers = new ContextHandlers();
            handlers.add(new RequestContextHandler(request, response));
            handlers.add(new UtilContextHandler());
            context.put("isSchedule", false);
            handlers.onHandle(context);
            controller.run(context);
        }
        return context;
    }

    @Override
    public Context doService(String service) throws Exception {
        return doService(new Context(), service);
    }

    @Override
    public Context doService(Context context, String serviceId) throws Exception {
        Service service = compMgr.createService(null, serviceId);
        if (null != service) {
            ContextHandlers handlers = new ContextHandlers();
            handlers.add(new UtilContextHandler());
            context.put("isSchedule", false);
            handlers.onHandle(context);
            service.run(context);
        }
        return context;
    }


    @Override
    public Context doService(HttpServletRequest request, HttpServletResponse response, String serviceId)
            throws Exception {
        Service service = compMgr.createService(null, serviceId);
        Context context = new Context();
        if (null != service) {
            ContextHandlers handlers = new ContextHandlers();
            handlers.add(new RequestContextHandler(request, response));
            context.put("isSchedule", false);
            handlers.onHandle(context);
            service.run(context);
        }
        return context;
    }
}
