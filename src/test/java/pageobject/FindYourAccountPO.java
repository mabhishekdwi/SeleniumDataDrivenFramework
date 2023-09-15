package pageobject;

import org.openqa.selenium.By;

public class FindYourAccountPO extends BaseClass {
	By lblFind=By.className("uiHeaderTitle");
	
	public boolean isFindYorACcountDisplayed() {
		return isElementDisplayed(lblFind);
		
	}

}
