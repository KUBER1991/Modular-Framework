package Maven_kuber;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import Java.Kuber.Utils.LibFunctions;

public class POMTestRun {
	
/*public static void main(String []args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver" , "C:\\Users\\ACER\\Downloads\\chromedriver_win32\\chromedriver.exe");
	WebDriver driver =new ChromeDriver();
	
	driver.get("https://www.facebook.com");
	Thread.sleep(3000);
	
}*/


@Test
public void Login() throws Exception {
		
	
	 WebDriver driver=LibFunctions.initiateBrowser("Chrome");
	
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     driver.get("https://www.facebook.com");
    
     driver.manage().window().maximize();	
     Thread.sleep(3000);
	PageObjectModel pom =PageFactory.initElements(driver,PageObjectModel.class);
	
	try {
		pom.loginFacebook(driver,"priyankakiranesd@gmail.com","bittudada");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
