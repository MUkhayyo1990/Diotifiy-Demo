import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import  org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class DuotifyDemo {

    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        String title = driver.getTitle();
        System.out.println(title);

        WebElement signUpLink = driver.findElement(By.id("hideLogin"));
        signUpLink.click();

        Faker faker = new Faker();
         String userName = faker.name().username();
       // System.out.println(username);
        String firstName = faker.name().firstName();
        //System.out.println();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String confirmEmail = faker.internet().emailAddress();
        String password = faker.internet().password();
        String confirmPass = faker.internet().password();

        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("email2")).sendKeys(confirmEmail);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("password2")).sendKeys(confirmPass);

        driver.findElement(By.name("signupButton")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");

        String navBarName = driver.findElement(By.className("navBar__username")).getText();
        Assert.assertEquals(navBarName, firstName + " " + lastName);


        driver.findElement(By.className("navBar__username")).click();
        String mainUsername = driver.findElement(By.id("username")).getText();
        Assert.assertEquals(mainUsername, userName);


        driver.findElement(By.id("logoutButton")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");


        driver.findElement(By.name("loginUsername")).sendKeys(userName);
        driver.findElement(By.name("loginPassword")).sendKeys(password);
        driver.findElement(By.name("loginButton")).click();


        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("You Might Also Like"));

        driver.findElement(By.className("navBar__username")).click();
        driver.findElement(By.id("logoutButton")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");


        driver.quit();




















    }
}
