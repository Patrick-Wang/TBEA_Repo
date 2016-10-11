
package com.apex.livebos.ws;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2016-10-09T16:48:57.094+08:00
 * Generated source version: 3.1.7
 * 
 */
public final class LBEBusinessService_LBEBusinessServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://ws.livebos.apex.com/", "LBEBusinessWebService");

    private LBEBusinessService_LBEBusinessServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = LBEBusinessWebService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        LBEBusinessWebService ss = new LBEBusinessWebService(wsdlURL, SERVICE_NAME);
        LBEBusinessService port = ss.getLBEBusinessServiceImplPort();  
        
        {
        System.out.println("Invoking queryTasks...");
        java.lang.String _queryTasks_sessionId = "";
        java.lang.String _queryTasks_caller = "";
        com.apex.livebos.ws.QueryOption _queryTasks_queryOption = null;
        com.apex.livebos.ws.QueryResult _queryTasks__return = port.queryTasks(_queryTasks_sessionId, _queryTasks_caller, _queryTasks_queryOption);
        System.out.println("queryTasks.result=" + _queryTasks__return);


        }
        {
        System.out.println("Invoking logout...");
        java.lang.String _logout_sessionId = "";
        com.apex.livebos.ws.LogoutResult _logout__return = port.logout(_logout_sessionId);
        System.out.println("logout.result=" + _logout__return);


        }
        {
        System.out.println("Invoking validateUser...");
        java.lang.String _validateUser_sessionId = "";
        java.lang.String _validateUser_userId = "";
        java.lang.String _validateUser_password = "";
        java.lang.String _validateUser_algorithm = "";
        java.lang.String _validateUser_securityCode = "";
        com.apex.livebos.ws.QueryResult _validateUser__return = port.validateUser(_validateUser_sessionId, _validateUser_userId, _validateUser_password, _validateUser_algorithm, _validateUser_securityCode);
        System.out.println("validateUser.result=" + _validateUser__return);


        }
        {
        System.out.println("Invoking queryWorkflowDescribe...");
        java.lang.String _queryWorkflowDescribe_sessionId = "";
        java.lang.String _queryWorkflowDescribe_uid = "";
        boolean _queryWorkflowDescribe_startupOnly = false;
        com.apex.livebos.ws.WorkflowDescribeResponse _queryWorkflowDescribe__return = port.queryWorkflowDescribe(_queryWorkflowDescribe_sessionId, _queryWorkflowDescribe_uid, _queryWorkflowDescribe_startupOnly);
        System.out.println("queryWorkflowDescribe.result=" + _queryWorkflowDescribe__return);


        }
        {
        System.out.println("Invoking queryTaskListByCondition...");
        java.lang.String _queryTaskListByCondition_sessionId = "";
        java.lang.String _queryTaskListByCondition_caller = "";
        com.apex.livebos.ws.WorkCondition _queryTaskListByCondition_condition = null;
        com.apex.livebos.ws.QueryOption _queryTaskListByCondition_queryOption = null;
        com.apex.livebos.ws.QueryResult _queryTaskListByCondition__return = port.queryTaskListByCondition(_queryTaskListByCondition_sessionId, _queryTaskListByCondition_caller, _queryTaskListByCondition_condition, _queryTaskListByCondition_queryOption);
        System.out.println("queryTaskListByCondition.result=" + _queryTaskListByCondition__return);


        }
        {
        System.out.println("Invoking doWorkAction...");
        java.lang.String _doWorkAction_sessionId = "";
        java.lang.String _doWorkAction_workflowName = "";
        long _doWorkAction_instanceId = 0;
        int _doWorkAction_actionId = 0;
        java.util.List<com.apex.livebos.ws.LbParameter> _doWorkAction_params = null;
        java.lang.String _doWorkAction_caller = "";
        java.lang.String _doWorkAction_summary = "";
        com.apex.livebos.ws.WorkActionResult _doWorkAction__return = port.doWorkAction(_doWorkAction_sessionId, _doWorkAction_workflowName, _doWorkAction_instanceId, _doWorkAction_actionId, _doWorkAction_params, _doWorkAction_caller, _doWorkAction_summary);
        System.out.println("doWorkAction.result=" + _doWorkAction__return);


        }
        {
        System.out.println("Invoking create...");
        java.lang.String _create_sessionId = "";
        java.lang.String _create_objectName = "";
        java.util.List<com.apex.livebos.ws.LbParameter> _create_params = null;
        com.apex.livebos.ws.CreateResult _create__return = port.create(_create_sessionId, _create_objectName, _create_params);
        System.out.println("create.result=" + _create__return);


        }
        {
        System.out.println("Invoking getUserInfo...");
        java.lang.String _getUserInfo_sessionId = "";
        java.lang.String _getUserInfo_loginId = "";
        com.apex.livebos.ws.UserInfo _getUserInfo__return = port.getUserInfo(_getUserInfo_sessionId, _getUserInfo_loginId);
        System.out.println("getUserInfo.result=" + _getUserInfo__return);


        }
        {
        System.out.println("Invoking getWorkOwner...");
        java.lang.String _getWorkOwner_sessionId = "";
        long _getWorkOwner_instanceId = 0;
        int _getWorkOwner_stepId = 0;
        com.apex.livebos.ws.WorkOwnerResponse _getWorkOwner__return = port.getWorkOwner(_getWorkOwner_sessionId, _getWorkOwner_instanceId, _getWorkOwner_stepId);
        System.out.println("getWorkOwner.result=" + _getWorkOwner__return);


        }
        {
        System.out.println("Invoking doWorkActionByObject...");
        java.lang.String _doWorkActionByObject_sessionId = "";
        java.lang.String _doWorkActionByObject_objectName = "";
        java.lang.String _doWorkActionByObject_id = "";
        int _doWorkActionByObject_actionId = 0;
        java.util.List<com.apex.livebos.ws.LbParameter> _doWorkActionByObject_params = null;
        java.lang.String _doWorkActionByObject_caller = "";
        java.lang.String _doWorkActionByObject_summary = "";
        com.apex.livebos.ws.WorkActionResult _doWorkActionByObject__return = port.doWorkActionByObject(_doWorkActionByObject_sessionId, _doWorkActionByObject_objectName, _doWorkActionByObject_id, _doWorkActionByObject_actionId, _doWorkActionByObject_params, _doWorkActionByObject_caller, _doWorkActionByObject_summary);
        System.out.println("doWorkActionByObject.result=" + _doWorkActionByObject__return);


        }
        {
        System.out.println("Invoking execBizProcess...");
        java.lang.String _execBizProcess_sessionId = "";
        java.lang.String _execBizProcess_bizProcessName = "";
        java.lang.String _execBizProcess_id = "";
        java.util.List<com.apex.livebos.ws.LbParameter> _execBizProcess_params = null;
        java.util.List<com.apex.livebos.ws.LbParameter> _execBizProcess_variables = null;
        com.apex.livebos.ws.BizProcessResult _execBizProcess__return = port.execBizProcess(_execBizProcess_sessionId, _execBizProcess_bizProcessName, _execBizProcess_id, _execBizProcess_params, _execBizProcess_variables);
        System.out.println("execBizProcess.result=" + _execBizProcess__return);


        }
        {
        System.out.println("Invoking getWorkAvailableAction...");
        java.lang.String _getWorkAvailableAction_sessionId = "";
        long _getWorkAvailableAction_instanceId = 0;
        com.apex.livebos.ws.AvailableWorkActionResponse _getWorkAvailableAction__return = port.getWorkAvailableAction(_getWorkAvailableAction_sessionId, _getWorkAvailableAction_instanceId);
        System.out.println("getWorkAvailableAction.result=" + _getWorkAvailableAction__return);


        }
        {
        System.out.println("Invoking query...");
        java.lang.String _query_sessionId = "";
        java.lang.String _query_objectName = "";
        java.util.List<com.apex.livebos.ws.LbParameter> _query_params = null;
        java.lang.String _query_condition = "";
        com.apex.livebos.ws.QueryOption _query_queryOption = null;
        com.apex.livebos.ws.QueryResult _query__return = port.query(_query_sessionId, _query_objectName, _query_params, _query_condition, _query_queryOption);
        System.out.println("query.result=" + _query__return);


        }
        {
        System.out.println("Invoking update...");
        java.lang.String _update_sessionId = "";
        java.lang.String _update_objectName = "";
        java.lang.String _update_id = "";
        java.util.List<com.apex.livebos.ws.LbParameter> _update_params = null;
        com.apex.livebos.ws.LbeResult _update__return = port.update(_update_sessionId, _update_objectName, _update_id, _update_params);
        System.out.println("update.result=" + _update__return);


        }
        {
        System.out.println("Invoking login...");
        java.lang.String _login_userid = "";
        java.lang.String _login_password = "";
        java.lang.String _login_scheme = "";
        java.lang.String _login_algorithm = "";
        java.lang.String _login_securityCode = "";
        com.apex.livebos.ws.LoginResult _login__return = port.login(_login_userid, _login_password, _login_scheme, _login_algorithm, _login_securityCode);
        System.out.println("login.result=" + _login__return);


        }
        {
        System.out.println("Invoking delete...");
        java.lang.String _delete_sessionId = "";
        java.lang.String _delete_objectName = "";
        java.lang.String _delete_id = "";
        com.apex.livebos.ws.LbeResult _delete__return = port.delete(_delete_sessionId, _delete_objectName, _delete_id);
        System.out.println("delete.result=" + _delete__return);


        }

        System.exit(0);
    }

}
