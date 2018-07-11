package me.vukas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching(mode = AdviceMode.ASPECTJ)	//use AspectJ and compile time weaving as config. in pom.xml
public class AspectJCaffeine {
	public static void main(String[] args) {
		SpringApplication.run(AspectJCaffeine.class, args);
	}
}
