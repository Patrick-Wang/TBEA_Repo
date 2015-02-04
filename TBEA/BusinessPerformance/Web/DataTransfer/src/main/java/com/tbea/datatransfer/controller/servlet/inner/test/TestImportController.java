package com.tbea.datatransfer.controller.servlet.inner.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.blht.BLHTService;
import com.tbea.datatransfer.service.inner.cqk.CQKService;
import com.tbea.datatransfer.service.inner.hkjhjgb.HKJHJGBService;
import com.tbea.datatransfer.service.inner.ndtbbzjqk.NDTBBZJQKService;
import com.tbea.datatransfer.service.inner.rhkxx.RHKXXService;
import com.tbea.datatransfer.service.inner.ydhkjhzxqk.YDHKJHZXQKService;
import com.tbea.datatransfer.service.inner.yqkqsbhb.YQKQSBHBService;
import com.tbea.datatransfer.service.inner.yszkjgqkb.YSZKJGQKBService;
import com.tbea.datatransfer.service.inner.ztyszkfxb.ZTYSZKFXBService;

@Controller
@RequestMapping(value = "testImport")
public class TestImportController {

	@Autowired
	private BLHTService blhtService;

	@Autowired
	private CQKService cqkService;

	@Autowired
	private HKJHJGBService hkjhjgbService;

	@Autowired
	private NDTBBZJQKService ndtbbzjqkService;

	@Autowired
	private RHKXXService rhkxxService;

	@Autowired
	private YDHKJHZXQKService ydhkjhzxqkService;

	@Autowired
	private YQKQSBHBService yqkqsbhbService;

	@Autowired
	private YSZKJGQKBService yszkjgqkbService;

	@Autowired
	private ZTYSZKFXBService ztyszkfxbService;

	private String view = "testImportPage";

	private String commandName = "result";

	@RequestMapping(value = "testImport.do", method = RequestMethod.GET)
	public ModelAndView importBLHT(HttpServletRequest request,
			HttpServletResponse response) {
		boolean bl = blhtService.importBLHT();
		boolean cqk = cqkService.importCQK();
		boolean hkjhjgb = hkjhjgbService.importHKJHJGB();
		boolean ndtbbzjqk = ndtbbzjqkService.importNDTBBZJQK();
		boolean rhkxx = rhkxxService.importRHKXX();
		boolean ydhkjhzxqk = ydhkjhzxqkService.importYDHKJHZXQK();
		boolean yqkqsbhb = yqkqsbhbService.importYQKQSBHB();
		boolean yszkjgqkb = yszkjgqkbService.importYSZKJGQKB();
		boolean ztyszkfxb = ztyszkfxbService.importZTYSZKFXB();
		boolean result = bl && cqk && hkjhjgb && ndtbbzjqk && rhkxx
				&& ydhkjhzxqk && yqkqsbhb && yszkjgqkb && ztyszkfxb;
		System.out.println("bl:\t" + bl);
		System.out.println("cqk:\t" + cqk);
		System.out.println("hkjhjgb:\t" + hkjhjgb);
		System.out.println("ndtbbzjqk:\t" + ndtbbzjqk);
		System.out.println("rhkxx:\t" + rhkxx);
		System.out.println("ydhkjhzxqk:\t" + ydhkjhzxqk);
		System.out.println("yqkqsbhb:\t" + yqkqsbhb);
		System.out.println("yszkjgqkb:\t" + yszkjgqkb);
		System.out.println("ztyszkfxb:\t" + ztyszkfxb);
		return new ModelAndView(view, commandName, result);
	}

}
