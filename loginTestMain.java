import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTestMain {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/pasindukalubowila/Downloads/QA_file/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testCorrectLogin() {
        loginPage.login("student", "Password123");
        String resultMessage = loginPage.getResultMessage();
        Assert.assertTrue(resultMessage.contains("Logged In Successfully"), "Login was not successful");
        System.out.println("Login was not successful");
    }

    @Test
    public void testWrongLogin() {
        loginPage.login("nimal", "12121234");
        String resultMessage = loginPage.getResultMessage();
        Assert.assertFalse(resultMessage.contains("Logged In Successfully"), "Login was successful, but it was expected to fail");
        System.out.println("Login was successful, but it was expected to fail");
    }

    @Test
    public void testEmptyFields() {
        loginPage.login("", "");
        String resultMessage = loginPage.getResultMessage();
        Assert.assertTrue(resultMessage.contains("Username is required") && resultMessage.contains("Password is required"), "Empty field validation failed");
    }

    @Test
    public void testIncorrectPassword() {
        loginPage.login("student", "WrongPassword");
        String resultMessage = loginPage.getResultMessage();
        Assert.assertFalse(resultMessage.contains("Logged In Successfully"), "Login was successful with incorrect password");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='submit']"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public String getResultMessage() {
        // Wait for the page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(By.tagName("body")).getText();
    }
}
