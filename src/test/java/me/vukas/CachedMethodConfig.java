package me.vukas;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import com.google.common.testing.FakeTicker;

@TestConfiguration
public class CachedMethodConfig {

	@Bean
	public CacheManager cacheManager() {
		CaffeineCache caffeineCache = new CaffeineCache("cachedInts", Caffeine.newBuilder()
				.expireAfterWrite(30, TimeUnit.SECONDS)
				.ticker(ticker())
				.build());
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		simpleCacheManager.setCaches(Collections.singletonList(caffeineCache));
		return simpleCacheManager;
	}

	@Bean
	public Ticker ticker(){
		return fakeTicker()::read;
	}

	@Bean
	public FakeTicker fakeTicker(){
		return new FakeTicker();
	}

}
