package com.tbea.ic.operation.service.xnych;

import com.tbea.ic.operation.service.xnych.XnychService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(XnychServiceImpl.NAME)
@Transactional("transactionManager")
public class XnychServiceImpl implements XnychService {
	public final static String NAME = "XnychServiceImpl";

}
