package org.seckill.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和Junit整合,junit启动时加载springIOC容器
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	//注入dao实现依赖
	@Resource
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber() throws Exception{
		int updateCount = seckillDao.reduceNumber(1000L, new Date());
		System.out.println(updateCount);
	}
	
	@Test
	public void testQueryByid(){
		long id = 1000;
		Seckill seckill = seckillDao.queryByid(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void testQueryAll() {
		List<Seckill> list = seckillDao.queryAll(0, 100);
		for(Seckill seckill : list) {
			System.out.println(seckill);
		}
	}
}
