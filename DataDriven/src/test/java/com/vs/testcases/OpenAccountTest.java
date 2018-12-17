package com.vs.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import com.vs.base.TestBase;
import com.vs.utility.TestUtil;

public class OpenAccountTest extends TestBase{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException, IOException {
		click("openaccountbtn_css");
		select("customer_id",data.get("Customer"));
		select("currency_xpath",data.get("Currency"));
		click("process_xpath");
		
		
	}

}
