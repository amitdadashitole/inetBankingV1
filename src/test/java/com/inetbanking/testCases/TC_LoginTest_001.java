package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void loginTest() throws IOException
	{
		
		
		LoginPage lp= new LoginPage(driver);
		
		
		
		lp.setUseName(username);
		logger.info("Username entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("login test passed");
		}
		else{
			
			captureScreen(driver,"loginTest");
			Assert.assertFalse(false);
			logger.info("login test failed");
		}
		
	}
}
