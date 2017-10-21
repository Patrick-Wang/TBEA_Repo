package com.tbea.erp.report.controller.servlet.login;

import com.tbea.erp.report.controller.servlet.session.SessionKey;
import com.tbea.erp.report.controller.servlet.session.SessionManager;
import com.tbea.erp.report.controller.servlet.session.SessionManager.OnSessionChangedListener;
import com.tbea.erp.report.model.entity.Account;
import com.tbea.erp.report.model.entity.Authority;
import com.tbea.erp.report.model.entity.NavigateItemEntity;
import com.tbea.erp.report.model.entity.UserRequestEntity;
import com.tbea.erp.report.service.login.LoginService;
import com.tbea.erp.report.service.login.LoginServiceImpl;
import net.sf.json.JSONArray;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet {
    @Resource(name = LoginServiceImpl.NAME)
    LoginService loginService;

    @Autowired
    public void init() {
        SessionManager.onChange(new OnSessionChangedListener() {

            @Override
            public void onCreated(HttpSession session) {

            }

            @SuppressWarnings("unchecked")
            @Override
            public void onDestroyed(HttpSession session) {
                if (SessionManager.isOnline(session)) {
                    loginService.logout(
                            SessionManager.getAccount(session),
                            session.getCreationTime(),
                            session.getLastAccessedTime(),
                            (String) session.getAttribute(SessionKey.IP),
                            (List<UserRequestEntity>) session.getAttribute(SessionKey.REQUESTS));
                } else {
                    LoggerFactory.getLogger("ACCOUNT").info("onDestroyed is not online");
                }
            }

        });
    }

    @RequestMapping(value = "timeout.do")
    public ModelAndView timeout(HttpServletRequest request,
                                HttpServletResponse response) {
        return new ModelAndView("timeout");
    }

    @RequestMapping(value = "logout.do")
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) {
        SessionManager.destorySession(request);
    }

    @RequestMapping(value = {"index.do"})
    public ModelAndView index(HttpServletRequest request,
                                    HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("navTree", JSONArray.fromObject(request.getSession(false).getAttribute("navTree")).toString());
        map.put("item", request.getParameter("item"));
        map.put("userName", SessionManager.getAccount(request.getSession(false)).getName());
        request.getSession(false).removeAttribute("item");
        return new ModelAndView("index", map);
    }

    boolean login(String userName, Account account, HttpServletRequest request) {
       // Map<String, Object> map = new HashMap<String, Object>();
        if (null != account) {
            if (!SessionManager.isOnline(request) ||
                    !SessionManager.getAccount(request.getSession(false)).getName().equals(userName)) {
                SessionManager.destorySession(request);
                HttpSession session = SessionManager.createSession(request, account);
                List<Authority> authority = loginService.getAuthority(account);
                session.setAttribute("authority", authority);
                List<NavigateItemEntity> navTree = loginService.getNavigateItems(authority);
                session.setAttribute("navTree", navTree);
            }
            return true;
           // map.put("navTree", JSONArray.fromObject(request.getSession(false).getAttribute("navTree")).toString());
           // return new ModelAndView("index", map);
        }
        return false;
        //return new ModelAndView("userNotExists", map);
    }

    @RequestMapping(value = {"portal.do"})
    public ModelAndView portalLogin(HttpServletRequest request,
                                    HttpServletResponse response) {

        String userName = request.getHeader("SSO_USER");
        if (login(userName, loginService.login(userName), request)){
            return new ModelAndView("redirect:/Login/index.do");
        }
        return new ModelAndView("userNotExists");
    }

    String byteHEX(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    String encoderByMd5(String buf) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digist = MessageDigest.getInstance("MD5");
        byte[] rs = digist.digest(buf.getBytes("UTF-8"));
        StringBuffer digestHexStr = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            digestHexStr.append(byteHEX(rs[i]));
        }
        return digestHexStr.toString();
    }

    boolean validate(String userName, String time, String token) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String srcKey = userName + time + "report";
        String destToken = encoderByMd5(srcKey);
        boolean invalidLink = false;
        if (destToken.equals(token.toUpperCase())) {
            try {
                long span = System.currentTimeMillis() - Long.valueOf(time);
                if (span < 0 || span > 10000) {
                    invalidLink = true;
                }
            } catch (Exception e) {
                invalidLink = true;
            }
        } else {
            invalidLink = true;
        }
        return true;//!invalidLink;
    }

    @RequestMapping(value = {"erp.do"}, method = RequestMethod.GET)
    public ModelAndView erpLogin(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(value = "userName") String userName,
                                 @RequestParam(value = "time") String time,
                                 @RequestParam(value = "token") String token,
                                 @RequestParam(value = "item") String item) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (validate(userName, time, token)) {
            if (login(userName, loginService.login(userName), request)){
                return new ModelAndView("redirect:/Login/index.do?item=" + item);

            }else{
                return new ModelAndView("userNotExists");
            }
        }
        return new ModelAndView("invalidLink");
    }
}