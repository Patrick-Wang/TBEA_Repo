package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service([className].NAME)
@Transactional("transactionManager")
public class serviceImpl implements AccountService {
	public final static String NAME = "[className]";

}
