package pageobject;

import org.openqa.selenium.By;

public class LoginFBPO extends BaseClass {

	By lblFaceBookHelps = By.xpath("//h2[contains(.,'Facebook helps you connect')]");
	By txtUserName = By.id("email");
	By txtPass = By.id("pass");
	By btnLoginIn = By.name("login");

	public void enterUserName(String username) {
		enterDetails(txtUserName, username);
	}

	public void enterPass(String pass) {
		enterDetails(txtPass, pass);
	}

	public void Login() {
		click(btnLoginIn);
	}

	public void doLogin(String username, String pass) {
		enterDetails(txtUserName, username);
		enterDetails(txtPass, pass);
		click(btnLoginIn);
	}
	public boolean isFaceBookLabelDisplayed() {
	return 	isElementDisplayed(lblFaceBookHelps);
	}

}
