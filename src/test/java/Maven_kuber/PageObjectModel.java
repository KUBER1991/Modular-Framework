package Maven_kuber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Java.Kuber.Utils.LibFunctions;

public class PageObjectModel {
	
	//WebDriver driver =null;

	@FindBy(name="email")
	@CacheLookup
	WebElement UserName;
	
	@FindBy(name="pass") WebElement PassWord;
	
	@FindBy(how=How.ID, using="u_0_2") WebElement SignIn;
	
	
	
	public void loginFacebook(WebDriver driver,String loginId, String pass) throws Exception {
		
		Thread.sleep(3000);
		UserName.sendKeys(loginId);
		PassWord.sendKeys(pass);
		Thread.sleep(3000);
		SignIn.click();
		

	}
	
	
	
}
