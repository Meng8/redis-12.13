package cn.com.taiji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.com.taiji.entity.User;
/**
 * 
 * 类名称：RedisRervice   
 * 类描述：   
 * 创建人：Meng   
 * 创建时间：2017年12月13日 下午2:51:41 
 * @version
 */
@Service
public class RedisService {

	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 
	 * @Description: 增加user
	 * @param user
	 * @return String  
	 * @throws
	 * @author Meng
	 * @date 2017年12月13日
	 */
	public String Test(User user) {
		String uuid= UUID.randomUUID().toString();
		
//		redisTemplate.opsForValue().set(uuid, "测试",1000);
//		redisTemplate.opsForValue().set("ttt","t123");
		HashMap<String, Object> map = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();
//		map.put("name", "隔壁老吴");
//		map.put("age", "33");
//		map.put("qq", "54188888");
//		
//		list.add("隔壁老吴");
//		list.add("33");
//		list.add("54188888");
		
//		redisTemplate.opsForHash().putAll("user:007", map);
//		stringRedisTemplate.opsForHash().putAll("user:009", map);
		//User user = new User("张益达", "zhangwei@111.com", 006);
//		map.put("name", user.getUsername());
//		map.put("email", user.getEmail());
//		map.put("id", user.getId());
		redisTemplate.opsForValue().set("user:"+user.getId(), user);
		//redisTemplate.opsForHash().putAll("user:"+user.getId(), map);
		//stringRedisTemplate.opsForHash().putAll("user:"+user.getId(), map);
		return "zailai!!";
		}
	
	/**
	 * 
	 * @Description: 从redis里查询user key
	 * @param key
	 * @return String  
	 * @throws
	 * @author Meng
	 * @date 2017年12月13日
	 */
	public User show(String id) {
		String key="user:"+id;
				System.out.println("key==="+key);
		BoundHashOperations<String, Object, Object> b= stringRedisTemplate.boundHashOps("user:1");
		System.out.println(b.getKey()+"====="+b.values()+"===="+b.keys()+b.toString());
		Map<Object, Object> data = b.entries();  
		System.out.println("data=="+data);
        if (data != null) {  
            User user = new User();  
            user.setId((String)data.get("id")); 
            user.setUsername((String) data.get("username")); 
            user.setEmail((String)data.get("email"));
            System.out.println("user=="+user);
            return user;  
        } else {  
            return null;  
	
      }
	}
}
