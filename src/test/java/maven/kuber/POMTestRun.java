package maven.kuber;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Java.Kuber.Utils.LibFunctions;

public class POMTestRun {
	



@Test
public void Login() throws Exception {
		
	//pageobjectmodel
	WebElement signIn= PomFactory.getInstance().getPageObjectModel().SignIn;
	
	 WebDriver driver=LibFunctions.initiateBrowser("Chrome");
	
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     driver.get("https://www.facebook.com");
    
     driver.manage().window().maximize();	
     Thread.sleep(3000);

	
	try {
		//spom.loginFacebook(driver,"priyankakiranesd@gmail.com","bittudada");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
