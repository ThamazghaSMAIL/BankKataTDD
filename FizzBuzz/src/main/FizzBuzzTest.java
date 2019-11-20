package main;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class FizzBuzzTest {
	FizzBuzz fizzBuzz;

	@Before
	public void setUp() {
		fizzBuzz = new FizzBuzz();
	}

	@Test
	public void shouldReturn1(){
		assertEquals(fizzBuzz.calculFizzBuzz(1), "1");
	}

	@Test
	public void shouldReturnBuzz() {
		assertEquals(fizzBuzz.calculFizzBuzz(5), "Buzz");
	}

	@Ignore
	@Test
	public void shouldReturnFizzBuzzWhenInputIs15() {
		assertEquals(fizzBuzz.calculFizzBuzz(15), "FizzBuzz");
	}
	
	@Test
	public void shouldReturn12FizzWhenInputIs3() {
		assertEquals(fizzBuzz.calculFizzBuzz(3), "12Fizz");
	}
	
	@Test
	public void shouldReturn12FizzWhenInputIs6() {
		assertEquals(fizzBuzz.calculFizzBuzz(3), "12Fizz4BuzzFizz");
	}

}
