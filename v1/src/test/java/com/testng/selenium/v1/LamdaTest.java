package com.testng.selenium.v1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.time.Duration;


public class LamdaTest {

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
    	if(driver.getTitle() != null)
    	{
        driver.get("https://www.lambdatest.com/selenium-playground");
    		
            

        // Click "Simple Form Demo"
        driver.findElement(By.linkText("Simple Form Demo")).click();

        // Validate URL
        Assert.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"));

        // Enter Message
        String message = "Welcome to LambdaTest";
        driver.findElement(By.id("user-message")).sendKeys(message);
        driver.findElement(By.id("showInput")).click();

        // Validate displayed message
        WebElement displayedMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(displayedMessage.getText(), message);
    	}
    	else Assert.fail("Driver was not initialized.");
    }
     catch (Exception e) {
        System.out.println(e.getMessage());
     }
    }

    @Test(timeOut = 20000)
    public void testDragAndDropSlider() {
    	try {
        driver.get("https://www.lambdatest.com/selenium-playground");
        
       

        // Click "Drag & Drop Sliders"
        driver.findElement(By.linkText("Drag & Drop Sliders")).click();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='range' and @value='15']")));

        // Drag slider to 95
        WebElement slider = driver.findElement(By.xpath("//input[@type='range' and @value='15']"));
        WebElement sliderValue = driver.findElement(By.xpath("//*[@id=\"rangeSuccess\"]"));
        
        System.out.println("Slider value text"+ sliderValue.getText());
        String currentSliderValue = slider.getAttribute("value");
        System.out.println("Slider current value before : " + currentSliderValue);
        
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(95, 0).release().perform();

        // Retrieve the value after the slider move
        //String currentSliderValue = slider.getAttribute("value");
        System.out.println("Slider current value after: " + currentSliderValue);

        // Wait for the displayed slider value to update
        wait.until(ExpectedConditions.textToBePresentInElement(sliderValue, "95"));
                       

        // Validate slider value
        
        Assert.assertEquals(sliderValue.getText(), "95");
    	 }
        catch (Exception e) {
           System.out.println(e.getMessage());
        }
    }

    @Test(timeOut = 20000)
    public void testInputFormSubmit() {
    	try {
        driver.get("https://www.lambdatest.com/selenium-playground");

        // Click "Input Form Submit"
        driver.findElement(By.linkText("Input Form Submit")).click();

        // Submit empty form
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Assert validation message
        WebElement errorField = driver.findElement(By.name("name"));
        Assert.assertTrue(errorField.getAttribute("validationMessage").contains("Please fill out this field"));

        // Fill form and submit
        driver.findElement(By.name("name")).sendKeys("Garima Dwivedi");
        driver.findElement(By.name("email")).sendKeys("test@example.com");
        driver.findElement(By.name("password")).sendKeys("password123");
        driver.findElement(By.name("company")).sendKeys("LambdaTest");
        driver.findElement(By.name("website")).sendKeys("https://www.google.com/");

        // Select Country
        new Select(driver.findElement(By.name("country"))).selectByVisibleText("India");

        // Submit form
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Validate success message
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(),'Thanks for contacting us')]"));
        Assert.assertTrue(successMessage.isDisplayed());
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
