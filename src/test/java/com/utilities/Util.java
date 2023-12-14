package com.utilities;

import java.util.Date;

public class Util {
	
	public final static int IMPLICIT_WAIT = 5;
	public final static int PAGELOAD_TIME = 20;
	public final static int SCRIPT_TIME = 20;
	
	
	
	
	public static String emailWithDynamicNumbers() {
		Date date = new Date();
		String dynamicnumbers = date.toString().replace(' ', '_').replace(':', '_');
		return "seleniumpanda" + dynamicnumbers + "@gmail.com";		
	}}
