package testsuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.FindYourAccountPO;

public class FindYourAccountTest  {
	FindYourAccountPO obj=new FindYourAccountPO();
	
	@Test
	public void FinfYourAccountTest1() {
		Assert.assertTrue(obj.isFindYorACcountDisplayed());
	}
}
