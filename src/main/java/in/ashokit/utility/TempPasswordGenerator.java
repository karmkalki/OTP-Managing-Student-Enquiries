package in.ashokit.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class TempPasswordGenerator {
	
	
	public static String passwordGenerater() {
	String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	String pwd = RandomStringUtils.random( 8, characters );
	return pwd;
	}

}
