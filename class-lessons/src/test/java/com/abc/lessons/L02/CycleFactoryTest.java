/**
 * 
 */
package com.abc.lessons.L02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.lessons.ClassLessonsApplication;
import com.abc.lessons.L02.cycle.Cycle;
import com.abc.lessons.L02.cycle.CycleFactory;
import com.abc.lessons.L02.cycle.types.Bicycle;
import com.abc.lessons.L02.cycle.types.Tricycle;

/**
 * @author Soham.Chakravarti
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ClassLessonsApplication.class)
public class CycleFactoryTest {

	@Autowired
	CycleFactory factory;
	
	@Before
	public void before() {
		assertNotNull(factory);
	}
	
	@Test
	public void test_create_bicycle() {
		Cycle cycle = factory.create(2);
		assertNotNull(cycle);
		
		int distanceInMiles = 10;
		int timeTaken = cycle.calculateTimeTaken(distanceInMiles);
		assertEquals(distanceInMiles * 2, timeTaken);
	}
	
	@Test
	public void test_create_tricycle() {
		Cycle cycle = factory.create(3);
		assertNotNull(cycle);
		
		int distanceInMiles = 10;
		int timeTaken = cycle.calculateTimeTaken(distanceInMiles);
		assertEquals(distanceInMiles * 3, timeTaken);
	}
	
	@Test
	public void test_instances() {
		Cycle biCycle = factory.create(2);
		Cycle triCycle = factory.create(3);
		
		assertTrue(Bicycle.class.isInstance(biCycle));
		assertTrue(Tricycle.class.isInstance(triCycle));
	}
	
	@Test
	public void test_unknownType() {
		Cycle cycle = factory.create(4);
		assertNull(cycle);		
	}
}
