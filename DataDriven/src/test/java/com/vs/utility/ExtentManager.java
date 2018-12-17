package com.vs.utility;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		String extentreportPath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\extent.html";
		String configfilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\extentconfig\\ReportsConfig.xml";
		if(extent==null) {
			extent = new ExtentReports(extentreportPath, true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(configfilePath));
		}
		
		return extent;
	}

}
