package maven.kuber;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import Java.Kuber.Utils.LibFunctions;

public class ScriptsDriver {
	static WebDriver driver;
	public static ExtentReports extent;
	static ExtentHtmlReporter report;
	public static ExtentTest Test;
	public static String TestCaseName=null;
	public static String TestReportPath=null;
	public static String SharedreportPath=null;
	public static String Sharedreportsceenshot;
	public static Method mathod;


	@BeforeSuite(alwaysRun=true) 
	public static void beforeSuite() throws Exception {
		Java.Kuber.Utils.LibFunctions.pointerDataLoad();
		Java.Kuber.Utils.LibFunctions.ConfigDataLoad();
		Java.Kuber.Utils.LibFunctions.suiteStart();
	}


	@BeforeMethod
	public static void Login() throws Exception {

		if(LibFunctions.Environment.equals("DEV")) { 
			try {

				LibFunctions.getURL("Chrome");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LibFunctions.SetText(Oreps.Username, LibFunctions.UserName);
			LibFunctions.SetText(Oreps.Password, LibFunctions.PassWord);
			LibFunctions.Click(Oreps.SignIn);
			Thread.sleep(2000);

		}
		else {

			try {
				LibFunctions.getURL("Chrome");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LibFunctions.SetText( Oreps.Username, LibFunctions.UserName);
			LibFunctions.SetText( Oreps.Password, LibFunctions.PassWord);
			LibFunctions.Click(Oreps.SignIn);
			Thread.sleep(2000);
		}
		TestCaseName=mathod.getName().toString();
		TestReportPath= ""+LibFunctions.DirPath+"//test-output//Extend-Report//";
		SharedreportPath=""+TestReportPath+"//"+TestCaseName+LibFunctions.timeStamp();
		Sharedreportsceenshot=""+SharedreportPath+"//Screenshot";

		if(new File(TestReportPath).exists()) {
			new File(TestReportPath).delete();
		}
		if(new File(SharedreportPath).exists()) {
			new File(SharedreportPath).delete();
		}
		if(new File(Sharedreportsceenshot).exists()) {
			new File(Sharedreportsceenshot).delete();
		}

            LibFunctions.createFolder(TestReportPath);
            LibFunctions.createFolder(SharedreportPath);
            LibFunctions.createFolder(Sharedreportsceenshot);
            
            File htmlfile= new File(SharedreportPath+"//"+TestCaseName+LibFunctions.timeStamp()+".html");
            
            report= new ExtentHtmlReporter(htmlfile);
            extent=new ExtentReports();
            extent.attachReporter(report);
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environment", LibFunctions.Environment);
            report.config().setDocumentTitle("Test Report");
            report.config().getTimeStampFormat();
            report.config().setTestViewChartLocation(ChartLocation.TOP);
            report.config().setReportName(TestCaseName);
            
            Test=extent.createTest(TestCaseName);
            
            
	}

	@Test(enabled=true)
	public static void Test1() {

		System.out.println("PAssed");
	}




	@AfterMethod
	public static void afterMathod() {
		 extent.flush();
		 
		 driver.close();
	}

	@AfterClass(alwaysRun=true)
	public static void textResults() {
		BufferedWriter bufwrite;
		File file= new File(""+LibFunctions.DirPath+"//test-output//TextResults//ClassResults.txt");
		try {
			bufwrite=new BufferedWriter(new FileWriter(file));
			bufwrite.write("TestCaseName+\tDescription+\tExpectedResult+\tActualResult+\tStatus");
			bufwrite.newLine();
			for(String TextLine: LibFunctions.classLevelTextResults) {
				bufwrite.write(TextLine);
				bufwrite.newLine();
			}
			bufwrite.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@AfterSuite(alwaysRun=true)
	public static void endSuit() {
		driver.quit();
	}



}
