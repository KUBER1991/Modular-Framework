package maven.kuber;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	public static WebDriver driver;
	
	public static void main(String args[]) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("start-maximized");
		DesiredCapabilities desir= new DesiredCapabilities();
		desir.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(desir);
		driver= new ChromeDriver(options);
		Thread.sleep(2000);
		driver.get("https://www.facebook.com/login/");
		
	}
	
	

}
