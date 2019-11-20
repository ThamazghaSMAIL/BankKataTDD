package main;

public class FizzBuzz {

	private final static int  MULTIPLE_3 = 3;
	private final static int  MULTIPLE_5 = 5;

	public String calculFizzBuzz(int i) {
		
		String result = "";
		
		if( i % MULTIPLE_3 == 0 )
			result += "12Fizz";
		
		if(i % MULTIPLE_5 == 0)
			result += "Buzz";
		
		if (result == "")
			result = Integer.toString(i);
		
		return result;
	}

}
