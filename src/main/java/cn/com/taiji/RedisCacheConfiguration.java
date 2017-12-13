package cn.com.taiji;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
@PropertySource("classpath:redis.properties")
public class RedisCacheConfiguration extends CachingConfigurerSupport{
	Logger logger=LoggerFactory.getLogger(RedisCacheConfiguration.class);
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${spring.redis.pool.max-wait}")
	private long maxWaitMillis;
	
	@Value("${spring.redis.password}")
	private String password;
	
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		System.out.println("======host="+host);
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		redisConnectionFactory.setHostName(host);
		redisConnectionFactory.setPort(port);
		redisConnectionFactory.setPoolConfig(jedisPoolConfig);
		//redisConnectionFactory.setPassword(password);
		return redisConnectionFactory;
	}
	
	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		System.out.println("redisTemplate========="+redisTemplate);
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		System.out.println("redisTemplate22========="+redisTemplate);
		return redisTemplate;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		System.out.println("redisTemplate==================");
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	/**
	 * 缓存管理器
	 */
	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
		cacheManager.setDefaultExpiration(timeout);
		cacheManager.setLoadRemoteCachesOnStartup(true);//滚动时加载选择缓存
		cacheManager.setUsePrefix(true);//是否使用前缀生成器
		
		return cacheManager;
	}
 
}
