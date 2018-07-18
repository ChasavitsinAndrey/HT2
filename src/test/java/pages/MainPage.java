package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {


    private final String BASE_URL = "http://http://192.168.100.4:8080/manage";

    @FindBy(xpath = "//*[@id='tasks']/div[4]/a[2]")
    private WebElement href_manage_jenkins;

    @FindBy(xpath = "//*[@id='main-panel']/div[16]/a")
    private WebElement href_manage_users;

    @FindBy(xpath = "//*[@id='main-panel']/div[16]/a/dl/dt")
    private WebElement manage_users_dt;

    @FindBy(xpath = "//*[@id='main-panel']/div[16]/a/dl/dd")
    private WebElement manage_users_dd;

    @FindBy(xpath = "//*[@id='tasks']/div[3]")
    private WebElement create_user;

    @FindBy(xpath = "//*[@id='right-top-nav']/div[1]/a[1]")
    private WebElement auto_refresh;

    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);

    }


    public String getManageUsersDT()
    {
        return manage_users_dt.getText();
    }

    public String getManageUsersDD()
    {
        return manage_users_dd.getText();
    }

    public void clickManageJenkins()
    {
        href_manage_jenkins.click();
    }

    public void clickManageUsers()
    {
        href_manage_users.click();
    }

    public void clickCreateUser()
    {
        create_user.click();
    }

    public void clickAutoRefresh()
    {
        auto_refresh.click();
    }

    public String getHrefAutoRefreshText()
    {
        return auto_refresh.getText();
    }

    public boolean checkCreateUser()
    {
        try
        {
            return create_user.isDisplayed();
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
