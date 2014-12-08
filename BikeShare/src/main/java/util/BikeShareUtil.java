package util;

import java.util.Random;

public class BikeShareUtil {

    private static int constant = 1000000;
	//need to change this random number logic - logic using current timestamp
	public static int getRandomInteger(){
		Random random = new Random();
		return random.nextInt(Integer.MAX_VALUE)  + 1 ;
	}
	
	public static String generateRandomBikeId(){
		Random random = new Random();
		return "b-"+random.nextInt(Integer.MAX_VALUE)  + 1 ;
	}

    public static String generateTransactionId(){
        constant++;
        return "t-"+constant;
    }

}
