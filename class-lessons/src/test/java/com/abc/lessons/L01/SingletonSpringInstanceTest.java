package com.abc.lessons.L01;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.lessons.ClassLessonsApplication;

/**
 * @author Soham.Chakravarti
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ClassLessonsApplication.class)
public class SingletonSpringInstanceTest {

	@Autowired
	SingletonSpringInstance helloBean;
	
	@Autowired
	SingletonSpringInstance helloAnotherBean;
	
	
	@Test
	public void contextLoads() {
		assertNotNull(helloBean);
		assertNotNull(helloAnotherBean);
	}

	@Test
	public void test_singleton() {
		assertSame(helloBean, helloAnotherBean);
	}
	
	@Test
	public void test_hello() {
		assertSame(SingletonSpringInstance.K_VAL, helloBean.sayHello());
		assertSame(SingletonSpringInstance.K_VAL, helloAnotherBean.sayHello());
	}
	
	@Test
	public void test_counter() {
		assertSame(0, helloAnotherBean.getCounter());
		assertSame(1, helloBean.incrementCounter());
		assertSame(1, helloAnotherBean.getCounter());
	}
}
