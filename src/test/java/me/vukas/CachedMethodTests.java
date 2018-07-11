package me.vukas;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.testing.FakeTicker;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import(CachedMethodConfig.class)
public class CachedMethodTests {

	@SpyBean
	private CachedMethod cachedMethod;

	@Autowired
	private FakeTicker fakeTicker;

	@Test
	public void shouldUseCache(){
		cachedMethod.printInts();
		verify(cachedMethod, times(1)).actualExpensiveOp();
		cachedMethod.printInts();
		verify(cachedMethod, times(1)).actualExpensiveOp();
		fakeTicker.advance(30, TimeUnit.SECONDS);
		cachedMethod.printInts();
		verify(cachedMethod, times(2)).actualExpensiveOp();
		cachedMethod.printInts();
		verify(cachedMethod, times(2)).actualExpensiveOp();
	}

}
