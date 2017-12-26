package test;

import utils.cache.MemcachedUtils;

public class MemcachedTest {

	public static void main(String[] args) {
		MemcachedUtils cache = MemcachedUtils.getInstance();
		// cache.set("user", new User(1, "yangc", "yangc"));
		User user = cache.get("user", User.class);
		System.out.println(user);
	}

}
