package Maven_kuber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Java.Kuber.Utils.LibFunctions;

public class PageObjectModel {
	WebDriver  driver;
	
	PageObjectModel(){
		PageFactory.initElements(LibFunctions.getdriver(), this);
	}
	//WebDriver driver =null;

	@FindBy(name="email")
	@CacheLookup
	WebElement UserName;
	
	@FindBy(name="pass") 
	public WebElement PassWord;
	
	@FindBy(how=How.ID, using="u_0_2") 
	public WebElement SignIn;
	
	
	@FindAll({
			@FindBy(xpath="//dtata"),
			@FindBy(xpath="//dtsdsa")})
			public WebElement xcv;
	
	
	
	
}
