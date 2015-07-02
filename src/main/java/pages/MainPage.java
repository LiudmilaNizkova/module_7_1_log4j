package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

import utils.UserUtils;

public class MainPage extends AbstractPage{
	
//	static {
//		new DOMConfigurator().doConfigure("log4j.properties",LogManager.getLoggerRepository());
//	}

	private final String BASE_URL = "http://www.yandex.ru/";
	private Logger log = Logger.getLogger(MainPage.class);
	
	
	@FindBy(name = "login")
	private WebElement inputLogin;

	@FindBy(name = "passwd")
	private WebElement inputPassword;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement loginButton;
	
	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}
	
	public void loginToMail(String username, String password){
		UserUtils.makeScreenshot(driver);
		inputLogin.sendKeys(username);
		UserUtils.highlightElement(inputLogin, driver);
		inputPassword.sendKeys(password);
		loginButton.click();
		log.info("User logged under :"+username);	
	}
	
	/*public void highlightElement(WebElement element){
		String bg = element.getCssValue("backgroundColor");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '"+"rgb(255,0,255)"+"'",  element);
		makeScreenshot();
		js.executeScript("argument[0].style.backgroundColor=' "+bg+"'", element);
	}*/
	
	/*public void makeScreenshot(){
		try{
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenshot, new File("screenshoots"));
			log.info("Screenshot - "+screenshot.getName()+" save to "+screenshot.getAbsolutePath());
		} catch (Exception e){
			e.getMessage();
			log.error("Cannot save screenshot due to "+e.getMessage());
		}
		
	}*/

	//@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		log.info("Open url : "+BASE_URL);
		
	}


}
