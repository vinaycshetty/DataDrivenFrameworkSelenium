package com.vs.rough;

import java.util.Date;

public class TestTimeDemo {

	public static void main(String[] args) {

		Date d = new Date();
		String dd = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		System.out.println(dd);
		System.out.println(d);
		
		String extentreportPath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html";
		String configfilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml";
		
		System.out.println(extentreportPath);
		System.out.println(configfilePath);

	}

}
