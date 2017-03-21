package org.ostenant.yoga.store.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.TestTb;
import org.ostenant.yoga.store.core.dao.TestTbMapper;
import org.ostenant.yoga.store.core.service.TestTbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestTbServiceImpl implements TestTbService {

	@Resource
	private TestTbMapper tbMapper;

	public void addOne() {
		TestTb tb = new TestTb();
		tb.setName("燕青");
		tb.setBirthday(new Date());
		tbMapper.addOne(tb);
	}

	public void add(TestTb testTb) {
		tbMapper.addOne(testTb);
	}

}
