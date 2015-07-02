package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.MainPage;

public class UserUtils {
private static Logger log = Logger.getLogger(UserUtils.class);
	
	public static void highlightElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor  = element.getCssValue("backgroundColor");
            changeColor("rgb(255,0,255)", element, js);
            makeScreenshot(driver);
            changeColor(bgcolor, element, js);
    }
    public static void changeColor(String color, WebElement element,  JavascriptExecutor js) {
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);

        try {
            Thread.sleep(20);
        }  catch (InterruptedException e) {
        }
     }
    
    public static void makeScreenshot(WebDriver driver){
		try{
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenshot, new File("screenshoots"));
			log.info("Screenshot - "+screenshot.getName()+" save to "+screenshot.getAbsolutePath());
		} catch (Exception e){
			e.getMessage();
			log.error("Cannot save screenshot due to "+e.getMessage());
		}
		
	}

}
