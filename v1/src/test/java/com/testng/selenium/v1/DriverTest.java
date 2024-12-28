package com.testng.selenium.v1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DriverTest {

	public String username = "garimatiwari.1902";
    public String accesskey = "r9hkcqDIhpj47DvfxntXmWn4ZcdBx3t7xH02Dvt3FvgaWlcPxZ";
   // public static RemoteWebDriver driver = null;
    //public String gridURL = "@hub.lambdatest.com/wd/hub";
    
    String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    
    protected WebDriver driver;

    @Parameters({"browser", "browserVersion", "os"})
        
    @BeforeMethod
    
    public void setUp() throws Exception {
    	ChromeOptions browserOptions = new ChromeOptions();
    	browserOptions.setPlatformName("Windows 10");
    	browserOptions.setBrowserVersion("dev");
    	HashMap<String, Object> ltOptions = new HashMap<String, Object>();
    	ltOptions.put("username", "garimatiwari.1902");
    	ltOptions.put("accessKey", "r9hkcqDIhpj47DvfxntXmWn4ZcdBx3t7xH02Dvt3FvgaWlcPxZ");
    	ltOptions.put("geoLocation", "IN");
    	ltOptions.put("visual", true);
    	ltOptions.put("video", true);
    	ltOptions.put("build", "DriverTestApp");
    	ltOptions.put("project", "v1");
    	ltOptions.put("name", "DriverTest");
    	String[] customTags = {"@test"};
    	ltOptions.put("tags", customTags);
    	ltOptions.put("tunnel", false);
    	ltOptions.put("w3c", true);
    	ltOptions.put("plugin", "java-testNG");
    	browserOptions.setCapability("LT:Options", ltOptions);
           try {
        	   System.out.println("grid URl is :"+gridURL );
              // String gridURL = "https://<username>:<accesskey>@hub.lambdatest.com/wd/hub";
               driver = new RemoteWebDriver(new URL(gridURL), browserOptions);
               driver.manage().window().maximize();

        	   
        	   System.out.println( gridURL);

        	   
               driver = new RemoteWebDriver(new URL(gridURL), browserOptions);
        	              

           } catch (MalformedURLException e) {
               System.out.println("Invalid grid URL");
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
        
    }
    
    @Test(timeOut = 20000)
    public void testSimpleFormDemo() {
    	try {
    		
    		 driver.get("https://www.lambdatest.com/selenium-playground");
    	 }
        catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }
    		
    		@AfterMethod
    		public void tearDown() {
    		    if (driver != null) {
    		        driver.quit();
    		    }
    		}
    	}
	

