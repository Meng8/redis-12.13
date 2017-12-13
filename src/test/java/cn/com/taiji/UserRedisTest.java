package cn.com.taiji;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RedisApplication.class)
public class UserRedisTest {

	@Autowired
	RedisService redisService;
	
	/**
	 * 
	 * @Description: 增加用户  
	 * @throws
	 * @author Meng
	 * @date 2017年12月13日
	 */
	@Test
	public void  userRedisTest() {
		User user = new User("张益达", "zhangwei@111.com", "3");
		redisService.Test(user);
	}
	/**
	 * 
	 * @Description: 查看用户
	 * @throws
	 * @author Meng
	 * @date 2017年12月13日
	 */
	@Test
	public void  userShowTest() {
		redisService.show("3");
	}
}
