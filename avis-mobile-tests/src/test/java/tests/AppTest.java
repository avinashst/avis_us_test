package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import ObjectRepository.LoginPage;
import core.MobileInstance;
import io.appium.java_client.android.AndroidDriver;

public class AppTest extends MobileInstance{
	
	@Test
	public void Testcase() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		System.out.println("Step 1: Click on Signin and Signup Button.");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginPage.SIGNIN_SIGNUP_ID)));
		driver.findElement(By.id(LoginPage.SIGNIN_SIGNUP_ID)).click();
		
		System.out.println("Step 2: Login into Application.");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginPage.EMAIL_ADDRESS_ID)));
		driver.findElement(By.id(LoginPage.EMAIL_ADDRESS_ID)).click();
		driver.findElement(By.id(LoginPage.EMAIL_ADDRESS_ID)).sendKeys("G9815Q");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginPage.PASSWORD_ID)));
		driver.findElement(By.id(LoginPage.PASSWORD_ID)).click();
		driver.findElement(By.id(LoginPage.PASSWORD_ID)).sendKeys("Avis@12345");
		
		((AndroidDriver)driver).hideKeyboard();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginPage.SIGNIN_ID)));
		driver.findElement(By.id(LoginPage.SIGNIN_ID)).click();
        
		System.out.println("Step 3: Enter the OTP and Click the Submit Button.");
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		List<WebElement> OTP_PopUp = driver.findElements(By.id(LoginPage.SIX_DIGIT_OTP_ID));
		
		if(OTP_PopUp.size()>0) {

			driver.findElement(By.id(LoginPage.SIX_DIGIT_OTP_ID)).click();
			driver.findElement(By.id(LoginPage.SIX_DIGIT_OTP_ID)).sendKeys("");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginPage.OTP_SUBMIT_BUTTON)));
			driver.findElement(By.id(LoginPage.OTP_SUBMIT_BUTTON)).click();
			
		}else {
			System.out.println("=====================+++++");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LoginPage.OK_DIALOG_BOX_ID)));
			driver.findElement(By.id(LoginPage.OK_DIALOG_BOX_ID)).click();
		}
		
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		driver.quit();	
    }
}
