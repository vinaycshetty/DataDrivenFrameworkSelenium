package com.vs.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.vs.base.TestBase;
import com.vs.utility.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException {

		click("addcustbtn_xpath");
		type("firstname_xpath", data.get("Firstname"));
		type("lastname_xpath", data.get("Lastname"));
		type("postcode_xpath", data.get("Postcode"));
		click("addbtn_xpath");
		//Thread.sleep(2000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertText")), "Customer not added");
		Thread.sleep(2000);
		alert.accept();
		
		//Assert.fail();
	}

}
