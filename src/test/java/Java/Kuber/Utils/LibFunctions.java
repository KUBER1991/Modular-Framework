package Java.Kuber.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import Maven_kuber.ScriptsDriver;

public class LibFunctions {
	static WebDriver driver;
	public static Properties pointerdata = new Properties();
	public static Properties Configdata = new Properties();
	public static ThreadLocal<String> browser = new ThreadLocal<String>();
	public static String DirPath= System.getProperty("user.dir").toString().replace("//", "/");
	public static String Environment;
	public static String UserName;
	public static String PassWord;
	public static  String URL;
	public static SoftAssert sasset=new SoftAssert();
	static WebDriverWait wait;
	public static LinkedHashSet<String> classLevelTextResults=new LinkedHashSet<String>();

	@BeforeSuite
	public static void suiteStart() throws Exception {
		pointerDataLoad();
		ConfigDataLoad();

		Environment= pointerdata.getProperty("Environment");

		UserName= Configdata.getProperty("UserName");
		PassWord= Configdata.getProperty("PassWord");
		URL= Configdata.getProperty("URL");
	}

	@AfterSuite()
	public static void suiteEnd() throws Exception {	
		/*pointerdata.clear();
		Configdata.clear();*/
	}

	//Text Report
	public static void TestReport(String TescaseName,String discription,String expected,String Actual,String Status)
			throws InvalidPropertiesFormatException,FileNotFoundException,NullPointerException,Exception {
		String Delimator="\t";

		String testcaseresult=TescaseName+Delimator+discription+Delimator+expected+Delimator+Actual+Delimator+Status+
				Delimator+timeStamp();

		classLevelTextResults.add(testcaseresult);
		Reporter.log(testcaseresult,true);

	}




	public static void pointerDataLoad() throws InvalidPropertiesFormatException,Exception {
		File file= new File(DirPath+"//src//test//resource//Pointer.xml");

		if(!file.exists()) {
			throw new FileNotFoundException("File is not Availabe");
		}
		else {
			FileInputStream filLoc= new FileInputStream(file);
			try {
				pointerdata.loadFromXML(filLoc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}

	}

	public static void ConfigDataLoad() throws InvalidPropertiesFormatException, Exception {
		File file= new File(DirPath+"//src//test//resource//DEV//DataSheet.xml");
		if(!file.exists()) {
			throw new FileNotFoundException("File is not Availabe");
		}
		else {
			FileInputStream filLoc= new FileInputStream(file);
			Configdata.loadFromXML(filLoc);}

	}


	public static void Click(String Object) throws Exception {

		if(Object.equals(null)) {

			System.out.println("Object is empty");}
		else {
			elementTobeClickable(Object);
			LibFunctions.getObject(Object,3).click();

		}
	}



	public static void elementTobeClickable(String Object) throws Exception {

		wait= new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.elementToBeClickable(getObject(Object,5)));
	}


	public static void Login(WebDriver driver,String url,String Object) throws Exception {
		driver.get(url);


	}

	public static void SetText(String Object, String Text) throws Exception {

		if(Object.equals(null)||Text.equals(null)) {

			System.out.println("Object or Text is empty");}
		else {
			LibFunctions.getObject(Object,5).sendKeys(Text);

		}
	}


	// Get WebElement in below function

	public static WebElement getObject(String Object,int TimeOut) throws NullPointerException, Exception {

		String Identifier;
		String Locatorvalue;	
		By searchBy=null;
		WebElement element=null;
		if(Object.equals(null)||Object.trim().equals("")) {
			throw new Exception("There is no Object");
		}
		else {
			Identifier=Object.split("~")[0].trim();
			Locatorvalue=Object.split("~")[1].trim();
			if(Identifier.equals("xpath")) {
				searchBy=By.xpath(Locatorvalue);
			}
			else if(Identifier.equals("id")) {
				searchBy=By.id(Locatorvalue);
			}
			else if(Identifier.equals("cssSelector")) {
				searchBy=By.cssSelector(Locatorvalue);
			}
			else if(Identifier.equals("name")) {
				searchBy=By.name(Locatorvalue);
			}
			else if(Identifier.equals("className")) {
				searchBy=By.className(Locatorvalue);
			}
			else if(Identifier.equals("linkText")) {
				searchBy=By.linkText(Locatorvalue);
			}
			else if(Identifier.equals("partialLinkText")) {
				searchBy=By.partialLinkText(Locatorvalue);
			}
			else if(Identifier.equals("tagName")) {
				searchBy=By.tagName(Locatorvalue);
			}
			else {
				throw new Exception("Identifier is not found");
			}
			wait= new WebDriverWait(driver,TimeOut);
			element =wait.until(ExpectedConditions.visibilityOfElementLocated(searchBy));
			return element;		
		}
	}
	public static List<WebElement>getObjects(WebDriver driver,String Object) throws Exception {

		String Identifier=null;
		String Locatorvalue=null;
		By SearchBy=null;
		List<WebElement> element = new ArrayList<WebElement>();

		if(Object.equals(null)||Object.trim().equals("")) {
			throw new Exception("There is no Object");
		}
		else {
			Identifier=Object.split("~")[0].trim();
			Locatorvalue=Object.split("~")[1].trim();
			if(Identifier.equals("xpath")) {
				SearchBy=By.xpath(Locatorvalue);
			}
			else if(Identifier.equals("id")) {
				SearchBy=By.id(Locatorvalue);
			}
			else if(Identifier.equals("cssSelector")) {
				SearchBy=By.cssSelector(Locatorvalue);
			}
			else if(Identifier.equals("name")) {
				SearchBy=By.name(Locatorvalue);
			}
			else if(Identifier.equals("className")) {
				SearchBy=By.className(Locatorvalue);
			}
			else if(Identifier.equals("linkText")) {
				SearchBy=By.linkText(Locatorvalue);
			}
			else if(Identifier.equals("partialLinkText")) {
				SearchBy=By.partialLinkText(Locatorvalue);
			}
			else if(Identifier.equals("tagName")) {
				SearchBy=By.tagName(Locatorvalue);
			}
			else {
				throw new Exception("Identifier is not found");
			}
			element=driver.findElements(SearchBy);
			return element;		
		}}

	// To get The URL Functions

	public static  void getURL(String Browser) throws Exception{

		driver= LibFunctions.initiateBrowser(Browser);
		driver.get(URL);
		Thread.sleep(2000);
		driver.manage().window().maximize();
		Thread.sleep(3000);	

	}



	public static  WebDriver initiateBrowser(String Browser) throws Exception{

		if(Browser==null) {
			browser.set("FF");
		}
		else {
			browser.set(Browser);
		}
		switch(browser.get().toUpperCase()) {

		case "CHROME":
			System.setProperty("webdriver.chrome.driver", DirPath+"\\Drivers//chromedriver.exe");
			DesiredCapabilities desir= DesiredCapabilities.chrome();
			desir.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
			desir.setAcceptInsecureCerts(true);
			driver= new ChromeDriver();
			Thread.sleep(2000);
			break;
		case "IE":
			System.setProperty("webdriver.chrome.driver", DirPath+"\\Drivers//IEDriverServer.exe");
			DesiredCapabilities desir1= DesiredCapabilities.internetExplorer();
			desir1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			driver= new InternetExplorerDriver();
			Thread.sleep(2000);
			break;

		case "FF":
			System.setProperty("webdriver.firefox.driver", DirPath+"\\Drivers//geckodriver.exe");
			/*DesiredCapabilities desir= DesiredCapabilities.chrome();
			desir.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/

			driver= new FirefoxDriver();
			Thread.sleep(2000);
			break;

		default:
			break;
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
		return  driver;
	}



	// Below Screenshot Function
	public static  void takeScreenshot(WebDriver driver, String ScreenshotName){

		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(src, new File(ScriptsDriver.Sharedreportsceenshot+"//"+ScreenshotName+".png"));

		} catch (IOException e) {

			e.getMessage();
		}	
		try {
			ScriptsDriver.Test.addScreenCaptureFromPath(ScriptsDriver.Sharedreportsceenshot+"//"+ScreenshotName+".png",ScreenshotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static  void validationPoint(String Testcase,String Discription,String actual,String Expected) throws InvalidPropertiesFormatException, FileNotFoundException, NullPointerException, Exception{

		if(actual!=null&& Expected!=null) {
			if(actual.equals(Expected)) {
				sasset.assertEquals(actual, Expected);
				if(actual.equals(Expected)) {
					TestReport(Testcase,Discription,actual,Expected,"PASS");
					ScriptsDriver.Test.log(Status.PASS,"Actual value is "+actual+" and Expected Value is: "+Expected+"");
				}
				else
				{
					ScriptsDriver.Test.log(Status.FAIL,"Actual value is "+actual+" and Expected Value is: "+Expected+"");
					TestReport(Testcase,Discription,actual,Expected,"FAIL");	
				}
				sasset.assertAll();}


		}   
	}

	public static  void windowHandel(String ParentURL ) {
		Set<String>Childs=driver.getWindowHandles();
		for(String Child: Childs) {
			if(!ParentURL.equals(Child))
					{
			driver.switchTo().window(Child);
		}
		}
	}
	public static  String timeStamp( ) {
		Date date=Calendar.getInstance().getTime();
		String timestamp = 
				new SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(date);

		return timestamp;
	}

	public static  void createFolder(String FolderName) {
		if(FolderName!=null) {
			new File(FolderName).mkdir();
		}
		else {
			Reporter.log("FolderName is null");}

	}

	//Get Latest File
	public static File getLatestFile (String FolderName){

		File [] files= new File(FolderName).listFiles();

		if(!(files==null) && !(files.length<0)) {

			File Latestfile= files[0];

			for(int i=1;i<files.length;i++) {
				if(Latestfile.lastModified() < files[i].lastModified()) {

					Latestfile = files[i];
				}}
			return Latestfile;

		}
		else { return null;}

	}

	// Get Excel Data
	public static Recordset getExcelData(String FilePAth, String Query) throws FilloException{

		Fillo filo= new Fillo();
		Connection con = null;
		try {
			con = filo.getConnection(FilePAth);

		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Recordset rs= con.executeQuery(Query);
		return rs;
	}

	public static Map<String, String> putExcelDataInMap(String FilePAth, String Query) throws FilloException{

		HashMap<String,String> Data= new HashMap<>();
		Fillo filo= new Fillo();
		Connection con = filo.getConnection(FilePAth);
		Recordset rs= con.executeQuery(Query);
		List<String>arraylist= rs.getFieldNames();
		int RowCount=0;
		while(rs.next()) {
			RowCount++;
			String FieldValus="";
			for(int i=0;i<arraylist.size();i++) {
				if(FieldValus=="") {
					FieldValus= rs.getField(i).toString();
				}
				if(!(FieldValus=="") && !((FieldValus.trim())==null)) {
					FieldValus= FieldValus+";" +rs.getField(i).toString();	
				}	
			}
			Data.put("Row"+RowCount+"",FieldValus);
			FieldValus="";
		}
		return Data;
	}

	public static boolean isInvalid(String Value) {

		Boolean flag=true;
		if(!(Value.equals(null)) && (Value.trim().isEmpty())) {
			flag=false;
		}
		return flag;
	}

	/*public static  void excelExecutionReport(String Datasheet,String SheetName, Object[]input ){

		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet(SheetName);

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < input.length; i++) {
            Cell cell = headerRow.createCell(i);
           // cell.setCellValue(input[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        //for(Employee employee: employees) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.getName());

            row.createCell(1)
                    .setCellValue(employee.getEmail());

            Cell dateOfBirthCell = row.createCell(2);
            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
            dateOfBirthCell.setCellStyle(dateCellStyle);

            row.createCell(3)
                    .setCellValue(employee.getSalary());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < input.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
	 */
	// Send report on Mail
	public static void sendMailFromOutLook(String sendTo, String ccTo, String bccTo, String subject, String emailBody,String attachment) throws Exception{
		final String user = System.getProperty("user.name")  + "@ntrs.com";// "bj64@ntrs.com";
		Properties props = new Properties();
		props.put("mail.smtp.host","appomail.ntrs.com");
		props.put("mail.smtp.auth","false");
		Session session= javax.mail.Session.getDefaultInstance(props,null);
		session.setDebug(true);
		//compose the message
		MimeMessage message = new MimeMessage(session);
		message.saveChanges();
		message.setFrom(new InternetAddress(user));
		message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(sendTo));
		if (ccTo !=null && ccTo.trim() !="") {
			message.addRecipient(javax.mail.Message.RecipientType.CC, new InternetAddress(ccTo));
		}
		if (bccTo !=null && 
				bccTo.trim() !="") {
			message.addRecipient(javax.mail.Message.RecipientType.BCC, new InternetAddress(bccTo));
		}
		message.setSubject(subject);
		//create the message part
		BodyPart messageBodyPart=new MimeBodyPart();
		//now set the actual message
		Multipart multipart= new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		//part two is attachment
		if (attachment !=null && attachment.trim() !="") {
			if (new File(attachment).exists()) {
				messageBodyPart= new MimeBodyPart();
				FileDataSource source = new FileDataSource(attachment);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(attachment);
				multipart.addBodyPart(messageBodyPart);
			}

		}
		//send the complete message part
		message.setContent(multipart);
		// send the message
		Transport.send(message);
	}



	public static  void excelDataCompare(String Filepath1,String sheetName1,String Filepath2,String sheetName2) throws FilloException{


		Map<String, String>map1= new HashMap<>();
		Map<String, String>map2= new HashMap<>();
		Fillo filo= new Fillo();
		Connection con= filo.getConnection(DirPath+"//Data//Data_file.xlsx");
		String Query ="SELECT *FROM "+sheetName1;
		Recordset rs=con.executeQuery(Query);
		List<String>Headers= rs.getFieldNames();

		while(rs.next()) {
			for(int i=0;i<Headers.size(); i++) {
				map1.put(Headers.get(i),rs.getField(Headers.get(i)));

			}
		}

	}
}