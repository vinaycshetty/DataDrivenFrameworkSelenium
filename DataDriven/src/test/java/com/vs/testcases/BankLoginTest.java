package com.vs.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.vs.base.TestBase;

public class BankLoginTest extends TestBase{

	@Test
	
	public void bankLoginTest() throws IOException, InterruptedException{
		
		verifyEquals("abc", "xyz");
		Thread.sleep(2000);
		log.debug("Inside Bank Manager Login");
		click("bmlbtn_xpath");
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addcustbtn_xpath"))));
		log.debug("Login Successfully");
		//Assert.fail("Login Not Successfull");
		
	}
}
