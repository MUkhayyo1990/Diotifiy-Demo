import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import  org.openqa.selenium.WebDriver;
import org.testng.Assert;

// new change
public class DuotifyDemo {

    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        Assert.assertEquals(driver.getTitle(), "Welcome to Duotify!");

        WebElement signUpLink = driver.findElement(By.id("hideLogin"));
        signUpLink.click();

        Faker faker = new Faker();
        String userName = faker.name().username();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();


        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("email2")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(password);
        driver.findElement(By.name("registerButton")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://duotify.us-east-2.elasticbeanstalk.com/browse.php?" );

        String fullName = (driver.findElement(By.id("nameFirstAndLast"))).getText();
        if (fullName.equals(firstName + " " + lastName)) {
            System.out.println("First and Last name match!");
        } else {
            System.out.println("First and Last name do not match.");
        }

        WebElement usernameLink = (driver.findElement(By.id("nameFirstAndLast")));
        usernameLink.click();

        String actualUsername = (driver.findElement(By.id("nameFirstAndLast"))).getText();
        if (actualUsername.equals(fullName)) {
            System.out.println("Username is correct!");
        } else {
            System.out.println("Username is incorrect.");
        }
        driver.findElement(By.id("rafael")).click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals("http://duotify.us-east-2.elasticbeanstalk.com/register.php")){
            System.out.println("URL matches!");
        } else{
            System.out.println("URL does not match!");
        }
        driver.findElement(By.name("loginUsername")).sendKeys(userName);
        driver.findElement(By.name("loginPassword")).sendKeys(password);
        driver.findElement(By.name("loginButton")).click();

        String pageSource = driver.getPageSource();
        String textToVerify = "You Might Also Like";
        System.out.println(pageSource);

        if (pageSource.contains(textToVerify)) {
            System.out.println("Login successful. Found the text: " + textToVerify);
        } else {
            System.out.println("Login failed. Could not find the text: " + textToVerify);
        }

        WebElement usernameLink2 = (driver.findElement(By.id("nameFirstAndLast")));
        usernameLink2.click();
        driver.findElement(By.id("rafael")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        driver.quit();




























    }
}
