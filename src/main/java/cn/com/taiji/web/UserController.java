package cn.com.taiji.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.taiji.RedisService;
import cn.com.taiji.entity.User;

@Controller
public class UserController {
	
	@Autowired
	RedisService redisService;
	
	@RequestMapping("/user")
	public String showUser() {
		return "user";
	}
	/**
	 * 
	 * @Description: 添加用户
	 * @param user
	 * @return String  
	 * @throws
	 * @author Meng
	 * @date 2017年12月13日
	 */
	@RequestMapping("/adduser")
	public String addUser(User user) {
		System.out.println("user==="+user);
		redisService.Test(user);
		return "ok";
	}
	/**
	 * 
	 * @Description:查找用户
	 * @param id
	 * @return String  
	 * @throws
	 * @author Meng
	 * @date 2017年12月13日
	 */
	@RequestMapping("/finduser")
	public String showUser(String id) {
		
		System.out.println(id);
		redisService.show(id);
		
		return "showuser";
	}

}
