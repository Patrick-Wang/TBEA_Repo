package com.tbea.datatransfer.controller.servlet.local.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.bl.BLTransferService;
import com.tbea.datatransfer.service.local.fkfs.byq.FKFSBYQFDWTransferService;
import com.tbea.datatransfer.service.local.fkfs.byq.FKFSBYQGWTransferService;
import com.tbea.datatransfer.service.local.fkfs.byq.FKFSBYQNWTransferService;
import com.tbea.datatransfer.service.local.fkfs.xl.FKFSXLFDWTransferService;
import com.tbea.datatransfer.service.local.fkfs.xl.FKFSXLGWTransferService;
import com.tbea.datatransfer.service.local.fkfs.xl.FKFSXLNWTransferService;
import com.tbea.datatransfer.service.local.htxx.HTXXTransferService;
import com.tbea.datatransfer.service.local.mrhk.MRHKTransferService;
import com.tbea.datatransfer.service.local.mrhkhz.MRHKHZTransferService;
import com.tbea.datatransfer.service.local.tbbzjxx.TBBZJXXTransferService;
import com.tbea.datatransfer.service.local.xltb.XLTBTransferService;
import com.tbea.datatransfer.service.local.xlwg.XLWGTransferService;
import com.tbea.datatransfer.service.local.xm.XMTransferService;
import com.tbea.datatransfer.service.local.ydhkjhjgb.YDHKJHJGBTransferService;
import com.tbea.datatransfer.service.local.ydsjhkqk.YDSJHKQKTransferService;
import com.tbea.datatransfer.service.local.yszkpzgh.YSZKPZGHTransferService;
import com.tbea.datatransfer.service.local.yszktz.YSZKTZTransferService;
import com.tbea.datatransfer.service.local.ztyszkfxb.ZTYSZKFXBTransferService;

@Controller
@RequestMapping(value = "testTransfer")
public class TestTransferController {

	@Autowired
	private BLTransferService blTransferService;

	@Autowired
	private FKFSBYQFDWTransferService fkfsbyqfdwTransferService;

	@Autowired
	private FKFSBYQGWTransferService fkfsbyqgwTransferService;

	@Autowired
	private FKFSBYQNWTransferService fkfsbyqnwTransferService;

	@Autowired
	private FKFSXLFDWTransferService fkfsxlfdwTransferService;

	@Autowired
	private FKFSXLGWTransferService fkfsxlgwTransferService;

	@Autowired
	private FKFSXLNWTransferService fkfsxlnwTransferService;

	@Autowired
	private HTXXTransferService htxxTransferService;

	@Autowired
	private MRHKTransferService mrhkTransferService;

	@Autowired
	private MRHKHZTransferService mrhkhzTransferService;

	@Autowired
	private TBBZJXXTransferService tbbzjxxTransferService;

	@Autowired
	private XLTBTransferService xltbTransferService;

	@Autowired
	private XLWGTransferService xlwgTransferService;

	@Autowired
	private XMTransferService xmTransferService;

	@Autowired
	private YDHKJHJGBTransferService ydhkjhjgbTransferService;

	@Autowired
	private YDSJHKQKTransferService ydsjhkqkTransferService;

	@Autowired
	private YSZKPZGHTransferService yszkpzghTransferService;

	@Autowired
	private YSZKTZTransferService yszktzTransferService;

	@Autowired
	private ZTYSZKFXBTransferService ztyszkfxbTransferService;

	private String view = "testTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "testTransfer.do", method = RequestMethod.GET)
	public ModelAndView fkfsbyqTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean bl = blTransferService.transferBL();
		boolean byqfdw = fkfsbyqfdwTransferService.transferFKFSBYQFDW();
		boolean byqgw = fkfsbyqgwTransferService.transferFKFSBYQGW();
		boolean byqnw = fkfsbyqnwTransferService.transferFKFSBYQNW();
		boolean xlfdw = fkfsxlfdwTransferService.transferFKFSXLFDW();
		boolean xlgw = fkfsxlgwTransferService.transferFKFSXLGW();
		boolean xlnw = fkfsxlnwTransferService.transferFKFSXLNW();
		boolean htxx = htxxTransferService.transferHTXX();
		boolean mrhk = mrhkTransferService.transferMRHK();
		boolean mrhkhz = mrhkhzTransferService.transferMRHKHZ();
		boolean tbbzjxx = tbbzjxxTransferService.transferTBBZJXX();
		boolean xltb = xltbTransferService.transferXLTB();
		boolean xlwg = xlwgTransferService.transferXLWG();
		boolean xm = xmTransferService.transferXM();
		boolean ydhkjhjgb = ydhkjhjgbTransferService.transferYDHKJHJGB();
		boolean ydsjhkqk = ydsjhkqkTransferService.transferYDSJHKQK();
		boolean yszkpzgh = yszkpzghTransferService.transferYSZKPZGH();
		boolean yszktz = yszktzTransferService.transferYSZKTZ();
		boolean ztyszkfxb = ztyszkfxbTransferService.transferZTYSZKFXB();
		boolean result = bl && byqfdw && byqgw && byqnw && xlfdw && xlgw
				&& xlnw && htxx && mrhk && mrhkhz && tbbzjxx && xltb && xlwg
				&& xm && ydhkjhjgb && ydsjhkqk && yszkpzgh && yszktz
				&& ztyszkfxb;
		System.out.println("bl:\t" + bl);
		System.out.println("byqfdw:\t" + byqfdw);
		System.out.println("byqgw:\t" + byqgw);
		System.out.println("byqnw:\t" + byqnw);
		System.out.println("xlfdw:\t" + xlfdw);
		System.out.println("xlgw:\t" + xlgw);
		System.out.println("xlnw:\t" + xlnw);
		System.out.println("htxx:\t" + htxx);
		System.out.println("mrhk:\t" + mrhk);
		System.out.println("mrhkhz:\t" + mrhkhz);
		System.out.println("tbbzjxx:\t" + tbbzjxx);
		System.out.println("xltb:\t" + xltb);
		System.out.println("xlwg" + xlwg);
		System.out.println("xm:\t" + xm);
		System.out.println("ydhkjhjgb:\t" + ydhkjhjgb);
		System.out.println("ydsjhkqk:\t" + ydsjhkqk);
		System.out.println("yszkpzgh:\t" + yszkpzgh);
		System.out.println("yszktz:\t" + yszktz);
		System.out.println("ztyszkfxb:\t" + ztyszkfxb);
		return new ModelAndView(view, commandName, result);
	}

}
