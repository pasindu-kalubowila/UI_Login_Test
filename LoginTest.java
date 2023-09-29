import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Users/pasindukalubowila/Downloads/QA_file/chromedriver_mac64/chromedriver");


        WebDriver driver = new ChromeDriver();


        driver.get("https://practicetestautomation.com/practice-test-login/");

        // Correct Test Case

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));

        usernameField.sendKeys("student");
        passwordField.sendKeys("Password123");


        submitButton.click();

        // Wait for the page loads
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check logged in successfully
        String landingPageText = driver.findElement(By.tagName("body")).getText();
        if (landingPageText.contains("Logged In Successfully")) {
            System.out.println("Correct Test Case: Login Successful");
        } else {
            System.out.println("Correct Test Case: Login Failed");
        }

        // Wrong Test Case

        driver.navigate().refresh();

        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        submitButton = driver.findElement(By.xpath("//*[@id='submit']"));

        usernameField.sendKeys("nimal");
        passwordField.sendKeys("12121234");


        submitButton.click();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        landingPageText = driver.findElement(By.tagName("body")).getText();
        if (landingPageText.contains("Logged In Successfully")) {
            System.out.println("Wrong Test Case: Login Successful (Expected: Login Failed)");
        } else {
            System.out.println("Wrong Test Case: Login Failed (Expected: Login Failed)");
        }


        driver.quit();
    }
}
