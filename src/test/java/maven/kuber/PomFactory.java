package maven.kuber;

public class PomFactory {

	private static PageObjectModel pageobjectmodel;
	private static PomFactory pagefactory= new PomFactory();
	
	
       public static PomFactory getInstance() {
		
		return pagefactory;
	}
	
	
	public PageObjectModel getPageObjectModel() {
		
		return (pageobjectmodel==null) ? new PageObjectModel():pageobjectmodel;
	}
	
}
