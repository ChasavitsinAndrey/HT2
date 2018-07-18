package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage extends AbstractPage {


    private final String BASE_URL = "http://http://192.168.100.4:8080/securityRealm/";

    @FindBy(xpath = "//*[@id='yui-gen1-button']")
    private WebElement buttonDelete;


    private WebElement userName;
    private WebElement deleteHref;

    private WebDriver driver;


    public UsersPage(WebDriver driver, String username)
    {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.userName = driver.findElement(By.xpath("//*[@id='people']/tbody/tr/td[2]/a[@href='user/" + username + "/']"));
        this.deleteHref = driver.findElement(By.xpath("//*[@id='people']/tbody/tr/td/a[@href='user/" + username + "/delete']"));
    }


    public String findNewUser()
    {
        return userName.getText();
    }


    public String deleteUser()
    {
        deleteHref.click();
        return driver.findElement(By.xpath("//*[@id='main-panel']/form[1]")).getText().split("\n")[0];
    }

    public void clickDeleteButton()
    {
        buttonDelete.click();
    }

    public String getButtonBG()
    {
        return Color.fromString(buttonDelete.getCssValue("background-color")).asHex();
    }

    public boolean checkUserNameInTable()
    {
        try
        {
            return userName.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean checkDeleteHref()
    {
        try
        {
            return deleteHref.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }



    public boolean checkAdminDeleteLink()
    {
        try
        {
            return driver.findElement(By.xpath("//*[@id='people']/tbody/tr/td/a[@href='user/admin/delete']")).isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
