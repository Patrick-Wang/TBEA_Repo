package com.tbea.ic.operation.controller.servlet.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.service.account.AccountService;

@Controller
@RequestMapping(value = "Account")
public class ResetPasswordServlet {

	@Autowired
	private AccountService accountService;

	private boolean checkPassword(String psw) {
		boolean result = false;
		String regex = "^[a-zA-Z0-9]+$";
		if (null != psw) {
			result = psw.matches(regex);
		}
		return result;
	}

	@RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "j_username") String j_username,
			@RequestParam(value = "j_password") String loadOldPassword,
			@RequestParam(value = "loadNewPassword") String loadNewPassword,
			@RequestParam(value = "reloadNewPassword") String reloadNewPassword) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;

		if (checkPassword(loadOldPassword) && checkPassword(loadNewPassword)
				&& checkPassword(reloadNewPassword)) {
			if (loadOldPassword.equals(loadNewPassword)) {
				map.put("message", "请不要与原密码相同");
			} else if (!loadNewPassword.equals(reloadNewPassword)) {
				map.put("message", "请确认两次输入新密码一致");
			} else {
				if (accountService.resetpassword(j_username, loadOldPassword,
						loadNewPassword)) {
					map.put("message", "您的密码已经修改成功，请点击确认重新登录！");
					result = true;
				} else {
					map.put("message", "用户不存在或原密码错误，请重新输入");
				}
			}
		} else {
			map.put("message", "请输入正确信息，密码只可以是字母或者数字");
		}
		map.put("result", result);
		return new ModelAndView("resetPassword", map);
	}

}
