package test;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisSentinelTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("10.23.6.12", 6379);
		List<String> list = jedis.hmget("menu", "menuTop", "menuMain");
		System.out.println(list.isEmpty());
		System.out.println(list.size());
		System.out.println(list.get(0));
		System.out.println(list.get(0) == null);
		jedis.close();
	}

}
