package me.vukas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CachedMethod {

	public Set<Integer> actualExpensiveOp(){
		return new HashSet<>(Arrays.asList(1,2,3,4,5));
	}

	@Cacheable("cachedInts")
	public Set<Integer> cachedInts(){	//can be private also
		System.out.println("getting not cached");
		return actualExpensiveOp();
	}

	@Scheduled(fixedDelay = 5_000, initialDelay = 1_000)
	public void printInts(){
		Set<Integer> ints = cachedInts();
		System.out.println(ints);
	}

}
