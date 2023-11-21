package testsuite;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.time.Duration;

public class LoginTest extends BaseTest {

    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setup(){
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully(){
        //find signin element and click
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        String expectedText = "Welcome Back!";
        String actualText = driver.findElement(By.xpath("//h2[@class='page__heading']")).getText();
        Assert.assertEquals("Error", expectedText,actualText);
    }

    @Test
    public void verifyTheErrorMessage(){
        //find email and password and enter invalid email and invalid password
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        driver.findElement(By.xpath("//input[@id='user[email]']")).sendKeys("orangepink@gmail.com");
        driver.findElement(By.xpath("//input[@id='user[password]']")).sendKeys("Orangepurple");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        String expectedText = "Invalid email or password.";
        String actualText = driver.findElement(By.xpath("//li[text()='\n" +
                "        Invalid email or password.\n" +
                "      ']")).getText();
        Assert.assertEquals("Error",expectedText,actualText);

    }
}
