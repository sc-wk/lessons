/**
 * 
 */
package com.abc.lessons.L03;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.lessons.ClassLessonsApplication;
import com.abc.lessons.L03.car.Car;
import com.abc.lessons.L03.car.CarFactory;
import com.abc.lessons.L03.car.CarFactoryFactory;
import com.abc.lessons.L03.car.electric.ElectricCarFactory;
import com.abc.lessons.L03.car.gasoline.GasolineCarFactory;

/**
 * @author Soham.Chakravarti
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ClassLessonsApplication.class)
public class CarFactoryTest {

	@Autowired
	CarFactoryFactory factoryFactory;
	
	@Before
	public void before() {
		assertNotNull(factoryFactory);
	}
	
	@Test
	public void test_factoryInstances() {
		CarFactory electricFactory = factoryFactory.createCarFactory(true);
		assertNotNull(electricFactory);
		assertTrue(ElectricCarFactory.class.isInstance(electricFactory));
		
		CarFactory gasolineFactory = factoryFactory.createCarFactory(false);
		assertNotNull(gasolineFactory);
		assertTrue(GasolineCarFactory.class.isInstance(gasolineFactory));
	}
	
	@After
	public void after() {
		CarFactory electricFactory = factoryFactory.createCarFactory(true);
		Car electricSedan = electricFactory.build(true);
		Car electricSUV = electricFactory.build(false);
		
		CarFactory gasolineFactory = factoryFactory.createCarFactory(false);
		Car gasolineSedan = gasolineFactory.build(true);
		Car gasolineSUV = gasolineFactory.build(false);
		
		electricSedan.print(System.out);
		electricSUV.print(System.out);
		gasolineSedan.print(System.out);
		gasolineSUV.print(System.out);
	}
}
