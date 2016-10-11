
package com.apex.livebos.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.apex.livebos.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Create_QNAME = new QName("http://ws.livebos.apex.com/", "create");
    private final static QName _CreateResponse_QNAME = new QName("http://ws.livebos.apex.com/", "createResponse");
    private final static QName _Delete_QNAME = new QName("http://ws.livebos.apex.com/", "delete");
    private final static QName _DeleteResponse_QNAME = new QName("http://ws.livebos.apex.com/", "deleteResponse");
    private final static QName _DoWorkAction_QNAME = new QName("http://ws.livebos.apex.com/", "doWorkAction");
    private final static QName _DoWorkActionByObject_QNAME = new QName("http://ws.livebos.apex.com/", "doWorkActionByObject");
    private final static QName _DoWorkActionByObjectResponse_QNAME = new QName("http://ws.livebos.apex.com/", "doWorkActionByObjectResponse");
    private final static QName _DoWorkActionResponse_QNAME = new QName("http://ws.livebos.apex.com/", "doWorkActionResponse");
    private final static QName _ExecBizProcess_QNAME = new QName("http://ws.livebos.apex.com/", "execBizProcess");
    private final static QName _ExecBizProcessResponse_QNAME = new QName("http://ws.livebos.apex.com/", "execBizProcessResponse");
    private final static QName _GetUserInfo_QNAME = new QName("http://ws.livebos.apex.com/", "getUserInfo");
    private final static QName _GetUserInfoResponse_QNAME = new QName("http://ws.livebos.apex.com/", "getUserInfoResponse");
    private final static QName _GetWorkAvailableAction_QNAME = new QName("http://ws.livebos.apex.com/", "getWorkAvailableAction");
    private final static QName _GetWorkAvailableActionResponse_QNAME = new QName("http://ws.livebos.apex.com/", "getWorkAvailableActionResponse");
    private final static QName _GetWorkOwner_QNAME = new QName("http://ws.livebos.apex.com/", "getWorkOwner");
    private final static QName _GetWorkOwnerResponse_QNAME = new QName("http://ws.livebos.apex.com/", "getWorkOwnerResponse");
    private final static QName _Login_QNAME = new QName("http://ws.livebos.apex.com/", "login");
    private final static QName _LoginResponse_QNAME = new QName("http://ws.livebos.apex.com/", "loginResponse");
    private final static QName _Logout_QNAME = new QName("http://ws.livebos.apex.com/", "logout");
    private final static QName _LogoutResponse_QNAME = new QName("http://ws.livebos.apex.com/", "logoutResponse");
    private final static QName _Query_QNAME = new QName("http://ws.livebos.apex.com/", "query");
    private final static QName _QueryResponse_QNAME = new QName("http://ws.livebos.apex.com/", "queryResponse");
    private final static QName _QueryTaskListByCondition_QNAME = new QName("http://ws.livebos.apex.com/", "queryTaskListByCondition");
    private final static QName _QueryTaskListByConditionResponse_QNAME = new QName("http://ws.livebos.apex.com/", "queryTaskListByConditionResponse");
    private final static QName _QueryTasks_QNAME = new QName("http://ws.livebos.apex.com/", "queryTasks");
    private final static QName _QueryTasksResponse_QNAME = new QName("http://ws.livebos.apex.com/", "queryTasksResponse");
    private final static QName _QueryWorkflowDescribe_QNAME = new QName("http://ws.livebos.apex.com/", "queryWorkflowDescribe");
    private final static QName _QueryWorkflowDescribeResponse_QNAME = new QName("http://ws.livebos.apex.com/", "queryWorkflowDescribeResponse");
    private final static QName _Update_QNAME = new QName("http://ws.livebos.apex.com/", "update");
    private final static QName _UpdateResponse_QNAME = new QName("http://ws.livebos.apex.com/", "updateResponse");
    private final static QName _ValidateUser_QNAME = new QName("http://ws.livebos.apex.com/", "validateUser");
    private final static QName _ValidateUserResponse_QNAME = new QName("http://ws.livebos.apex.com/", "validateUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.apex.livebos.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link DoWorkAction }
     * 
     */
    public DoWorkAction createDoWorkAction() {
        return new DoWorkAction();
    }

    /**
     * Create an instance of {@link DoWorkActionByObject }
     * 
     */
    public DoWorkActionByObject createDoWorkActionByObject() {
        return new DoWorkActionByObject();
    }

    /**
     * Create an instance of {@link DoWorkActionByObjectResponse }
     * 
     */
    public DoWorkActionByObjectResponse createDoWorkActionByObjectResponse() {
        return new DoWorkActionByObjectResponse();
    }

    /**
     * Create an instance of {@link DoWorkActionResponse }
     * 
     */
    public DoWorkActionResponse createDoWorkActionResponse() {
        return new DoWorkActionResponse();
    }

    /**
     * Create an instance of {@link ExecBizProcess }
     * 
     */
    public ExecBizProcess createExecBizProcess() {
        return new ExecBizProcess();
    }

    /**
     * Create an instance of {@link ExecBizProcessResponse }
     * 
     */
    public ExecBizProcessResponse createExecBizProcessResponse() {
        return new ExecBizProcessResponse();
    }

    /**
     * Create an instance of {@link GetUserInfo }
     * 
     */
    public GetUserInfo createGetUserInfo() {
        return new GetUserInfo();
    }

    /**
     * Create an instance of {@link GetUserInfoResponse }
     * 
     */
    public GetUserInfoResponse createGetUserInfoResponse() {
        return new GetUserInfoResponse();
    }

    /**
     * Create an instance of {@link GetWorkAvailableAction }
     * 
     */
    public GetWorkAvailableAction createGetWorkAvailableAction() {
        return new GetWorkAvailableAction();
    }

    /**
     * Create an instance of {@link GetWorkAvailableActionResponse }
     * 
     */
    public GetWorkAvailableActionResponse createGetWorkAvailableActionResponse() {
        return new GetWorkAvailableActionResponse();
    }

    /**
     * Create an instance of {@link GetWorkOwner }
     * 
     */
    public GetWorkOwner createGetWorkOwner() {
        return new GetWorkOwner();
    }

    /**
     * Create an instance of {@link GetWorkOwnerResponse }
     * 
     */
    public GetWorkOwnerResponse createGetWorkOwnerResponse() {
        return new GetWorkOwnerResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link Query }
     * 
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link QueryResponse }
     * 
     */
    public QueryResponse createQueryResponse() {
        return new QueryResponse();
    }

    /**
     * Create an instance of {@link QueryTaskListByCondition }
     * 
     */
    public QueryTaskListByCondition createQueryTaskListByCondition() {
        return new QueryTaskListByCondition();
    }

    /**
     * Create an instance of {@link QueryTaskListByConditionResponse }
     * 
     */
    public QueryTaskListByConditionResponse createQueryTaskListByConditionResponse() {
        return new QueryTaskListByConditionResponse();
    }

    /**
     * Create an instance of {@link QueryTasks }
     * 
     */
    public QueryTasks createQueryTasks() {
        return new QueryTasks();
    }

    /**
     * Create an instance of {@link QueryTasksResponse }
     * 
     */
    public QueryTasksResponse createQueryTasksResponse() {
        return new QueryTasksResponse();
    }

    /**
     * Create an instance of {@link QueryWorkflowDescribe }
     * 
     */
    public QueryWorkflowDescribe createQueryWorkflowDescribe() {
        return new QueryWorkflowDescribe();
    }

    /**
     * Create an instance of {@link QueryWorkflowDescribeResponse }
     * 
     */
    public QueryWorkflowDescribeResponse createQueryWorkflowDescribeResponse() {
        return new QueryWorkflowDescribeResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link ValidateUser }
     * 
     */
    public ValidateUser createValidateUser() {
        return new ValidateUser();
    }

    /**
     * Create an instance of {@link ValidateUserResponse }
     * 
     */
    public ValidateUserResponse createValidateUserResponse() {
        return new ValidateUserResponse();
    }

    /**
     * Create an instance of {@link AvailableWorkActionResponse }
     * 
     */
    public AvailableWorkActionResponse createAvailableWorkActionResponse() {
        return new AvailableWorkActionResponse();
    }

    /**
     * Create an instance of {@link LbeResult }
     * 
     */
    public LbeResult createLbeResult() {
        return new LbeResult();
    }

    /**
     * Create an instance of {@link WorkAction }
     * 
     */
    public WorkAction createWorkAction() {
        return new WorkAction();
    }

    /**
     * Create an instance of {@link QueryOption }
     * 
     */
    public QueryOption createQueryOption() {
        return new QueryOption();
    }

    /**
     * Create an instance of {@link QueryResult }
     * 
     */
    public QueryResult createQueryResult() {
        return new QueryResult();
    }

    /**
     * Create an instance of {@link LbMetaData }
     * 
     */
    public LbMetaData createLbMetaData() {
        return new LbMetaData();
    }

    /**
     * Create an instance of {@link ColInfo }
     * 
     */
    public ColInfo createColInfo() {
        return new ColInfo();
    }

    /**
     * Create an instance of {@link LbRecord }
     * 
     */
    public LbRecord createLbRecord() {
        return new LbRecord();
    }

    /**
     * Create an instance of {@link WorkflowDescribeResponse }
     * 
     */
    public WorkflowDescribeResponse createWorkflowDescribeResponse() {
        return new WorkflowDescribeResponse();
    }

    /**
     * Create an instance of {@link WorkflowDescribe }
     * 
     */
    public WorkflowDescribe createWorkflowDescribe() {
        return new WorkflowDescribe();
    }

    /**
     * Create an instance of {@link WorkOwnerResponse }
     * 
     */
    public WorkOwnerResponse createWorkOwnerResponse() {
        return new WorkOwnerResponse();
    }

    /**
     * Create an instance of {@link LbParameter }
     * 
     */
    public LbParameter createLbParameter() {
        return new LbParameter();
    }

    /**
     * Create an instance of {@link WorkActionResult }
     * 
     */
    public WorkActionResult createWorkActionResult() {
        return new WorkActionResult();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link CreateResult }
     * 
     */
    public CreateResult createCreateResult() {
        return new CreateResult();
    }

    /**
     * Create an instance of {@link LoginResult }
     * 
     */
    public LoginResult createLoginResult() {
        return new LoginResult();
    }

    /**
     * Create an instance of {@link WorkCondition }
     * 
     */
    public WorkCondition createWorkCondition() {
        return new WorkCondition();
    }

    /**
     * Create an instance of {@link BizProcessResult }
     * 
     */
    public BizProcessResult createBizProcessResult() {
        return new BizProcessResult();
    }

    /**
     * Create an instance of {@link LogoutResult }
     * 
     */
    public LogoutResult createLogoutResult() {
        return new LogoutResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoWorkAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "doWorkAction")
    public JAXBElement<DoWorkAction> createDoWorkAction(DoWorkAction value) {
        return new JAXBElement<DoWorkAction>(_DoWorkAction_QNAME, DoWorkAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoWorkActionByObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "doWorkActionByObject")
    public JAXBElement<DoWorkActionByObject> createDoWorkActionByObject(DoWorkActionByObject value) {
        return new JAXBElement<DoWorkActionByObject>(_DoWorkActionByObject_QNAME, DoWorkActionByObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoWorkActionByObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "doWorkActionByObjectResponse")
    public JAXBElement<DoWorkActionByObjectResponse> createDoWorkActionByObjectResponse(DoWorkActionByObjectResponse value) {
        return new JAXBElement<DoWorkActionByObjectResponse>(_DoWorkActionByObjectResponse_QNAME, DoWorkActionByObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoWorkActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "doWorkActionResponse")
    public JAXBElement<DoWorkActionResponse> createDoWorkActionResponse(DoWorkActionResponse value) {
        return new JAXBElement<DoWorkActionResponse>(_DoWorkActionResponse_QNAME, DoWorkActionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecBizProcess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "execBizProcess")
    public JAXBElement<ExecBizProcess> createExecBizProcess(ExecBizProcess value) {
        return new JAXBElement<ExecBizProcess>(_ExecBizProcess_QNAME, ExecBizProcess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecBizProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "execBizProcessResponse")
    public JAXBElement<ExecBizProcessResponse> createExecBizProcessResponse(ExecBizProcessResponse value) {
        return new JAXBElement<ExecBizProcessResponse>(_ExecBizProcessResponse_QNAME, ExecBizProcessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "getUserInfo")
    public JAXBElement<GetUserInfo> createGetUserInfo(GetUserInfo value) {
        return new JAXBElement<GetUserInfo>(_GetUserInfo_QNAME, GetUserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "getUserInfoResponse")
    public JAXBElement<GetUserInfoResponse> createGetUserInfoResponse(GetUserInfoResponse value) {
        return new JAXBElement<GetUserInfoResponse>(_GetUserInfoResponse_QNAME, GetUserInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkAvailableAction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "getWorkAvailableAction")
    public JAXBElement<GetWorkAvailableAction> createGetWorkAvailableAction(GetWorkAvailableAction value) {
        return new JAXBElement<GetWorkAvailableAction>(_GetWorkAvailableAction_QNAME, GetWorkAvailableAction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkAvailableActionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "getWorkAvailableActionResponse")
    public JAXBElement<GetWorkAvailableActionResponse> createGetWorkAvailableActionResponse(GetWorkAvailableActionResponse value) {
        return new JAXBElement<GetWorkAvailableActionResponse>(_GetWorkAvailableActionResponse_QNAME, GetWorkAvailableActionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkOwner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "getWorkOwner")
    public JAXBElement<GetWorkOwner> createGetWorkOwner(GetWorkOwner value) {
        return new JAXBElement<GetWorkOwner>(_GetWorkOwner_QNAME, GetWorkOwner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWorkOwnerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "getWorkOwnerResponse")
    public JAXBElement<GetWorkOwnerResponse> createGetWorkOwnerResponse(GetWorkOwnerResponse value) {
        return new JAXBElement<GetWorkOwnerResponse>(_GetWorkOwnerResponse_QNAME, GetWorkOwnerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Query }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "query")
    public JAXBElement<Query> createQuery(Query value) {
        return new JAXBElement<Query>(_Query_QNAME, Query.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryResponse")
    public JAXBElement<QueryResponse> createQueryResponse(QueryResponse value) {
        return new JAXBElement<QueryResponse>(_QueryResponse_QNAME, QueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryTaskListByCondition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryTaskListByCondition")
    public JAXBElement<QueryTaskListByCondition> createQueryTaskListByCondition(QueryTaskListByCondition value) {
        return new JAXBElement<QueryTaskListByCondition>(_QueryTaskListByCondition_QNAME, QueryTaskListByCondition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryTaskListByConditionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryTaskListByConditionResponse")
    public JAXBElement<QueryTaskListByConditionResponse> createQueryTaskListByConditionResponse(QueryTaskListByConditionResponse value) {
        return new JAXBElement<QueryTaskListByConditionResponse>(_QueryTaskListByConditionResponse_QNAME, QueryTaskListByConditionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryTasks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryTasks")
    public JAXBElement<QueryTasks> createQueryTasks(QueryTasks value) {
        return new JAXBElement<QueryTasks>(_QueryTasks_QNAME, QueryTasks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryTasksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryTasksResponse")
    public JAXBElement<QueryTasksResponse> createQueryTasksResponse(QueryTasksResponse value) {
        return new JAXBElement<QueryTasksResponse>(_QueryTasksResponse_QNAME, QueryTasksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWorkflowDescribe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryWorkflowDescribe")
    public JAXBElement<QueryWorkflowDescribe> createQueryWorkflowDescribe(QueryWorkflowDescribe value) {
        return new JAXBElement<QueryWorkflowDescribe>(_QueryWorkflowDescribe_QNAME, QueryWorkflowDescribe.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWorkflowDescribeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "queryWorkflowDescribeResponse")
    public JAXBElement<QueryWorkflowDescribeResponse> createQueryWorkflowDescribeResponse(QueryWorkflowDescribeResponse value) {
        return new JAXBElement<QueryWorkflowDescribeResponse>(_QueryWorkflowDescribeResponse_QNAME, QueryWorkflowDescribeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "validateUser")
    public JAXBElement<ValidateUser> createValidateUser(ValidateUser value) {
        return new JAXBElement<ValidateUser>(_ValidateUser_QNAME, ValidateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.livebos.apex.com/", name = "validateUserResponse")
    public JAXBElement<ValidateUserResponse> createValidateUserResponse(ValidateUserResponse value) {
        return new JAXBElement<ValidateUserResponse>(_ValidateUserResponse_QNAME, ValidateUserResponse.class, null, value);
    }

}
