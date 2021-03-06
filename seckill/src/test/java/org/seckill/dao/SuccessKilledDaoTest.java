package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Autowired
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		int insertCount = successKilledDao.insertSuccessKilled(1000, 15955153715L);
		System.out.println(insertCount);
	}
	
	@Test
	public void testQueryByIdWithSeckill() {
		SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1000, 15955153715L);
		System.out.println(successKilled.getSeckill());
		System.out.println(successKilled);
	}
}
