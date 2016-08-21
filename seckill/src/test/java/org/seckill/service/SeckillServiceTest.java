package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring/spring-dao.xml", 
	"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList(){
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list:{}", list);
	}
	
	@Test
	public void testGetById(){
		Seckill seckill = seckillService.getById(1000L);
		logger.info("seckill：{}", seckill);
	} 
	
	@Test
	public void testExportSeckillUrl(){
		Exposer exposer = seckillService.exportSeckillUrl(1000L);
		logger.info("exposer:{}", exposer);
		/*
		 *Exposer [exposed=true, md5=35c04df2abd2f6a59a562f61c202c4a7, seckillId=1000, now=0, start=0, end=0] 
		 */
	}
	
	@Test
	public void testExecuteSeckill() {
		try {
			long id = 1000;
			long phone = 15955153716l;
			String md5 = "35c04df2abd2f6a59a562f61c202c4a7";
			SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
			logger.info("seckillExecution:{}", seckillExecution);
			//seckillExecution:SeckillExecution [seckillId=1000, state=1, stateInfo=秒杀成功, 
			//successKilled=SuccessKilled [seckillId=1000, userPhone=15955153716, state=0, createTime=Sun Aug 21 18:49:24 CST 2016]]
			
		} catch (SeckillException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSkillLogic(){
		Exposer exposer = seckillService.exportSeckillUrl(1000L);
		if(null != exposer && exposer.isExposed()) {
			logger.info("exposer:{}", exposer);
			try {
				long id = exposer.getSeckillId();
				long phone = 15955153717l;
				String md5 = exposer.getMd5();
				SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
				logger.info("seckillExecution:{}", seckillExecution);
				//seckillExecution:SeckillExecution [seckillId=1000, state=1, stateInfo=秒杀成功, 
				//successKilled=SuccessKilled [seckillId=1000, userPhone=15955153716, state=0, createTime=Sun Aug 21 18:49:24 CST 2016]]
				
			} catch (SeckillException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
