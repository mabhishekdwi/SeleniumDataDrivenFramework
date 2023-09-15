package testsuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BaseClass;
import pageobject.LoginFBPO;

public class LoginFBTest  extends BaseClass{
	LoginFBPO obj=new LoginFBPO();
	@Test
	public void LoginWithValidData() {
		obj.doLogin("abhishek","dwivedi");
		Assert.assertTrue(obj.isFaceBookLabelDisplayed());

	}

}
