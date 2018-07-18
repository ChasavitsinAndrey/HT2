package Tests;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CreateUserPage;
import pages.LoginPage;
import pages.MainPage;
import pages.UsersPage;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Suite
{
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private UsersPage usersPage;
    private CreateUserPage createUserPage;
    private List<WebElement> typeTextElements;
    private List<WebElement> typePasswordElements;

    private final String BG_COLOR_BUTTON = "#4a90e2";
    private final String BG_COLOR_BUTTON1 = "#4b758b";
    private final String BG_COLOR_BUTTON2 = "#4b758b";


    private String userNameLogIn = "andrey8056";
    private String userPasswordLogIn = "2245720zxc";
    private String newUserName = "someuser";
    private String newPassword = "somepassword";
    private String newPasswordConfirm = "somepassword";
    private String newFullName = "Some Full Name";
    private String newEMail = "some@addr.dom";

    @BeforeMethod
    public void beforeMethod()
    {
        this.driver = Driver.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.loginFill(userNameLogIn, userPasswordLogIn);
    }



    @Test
    public void Task()
    {

        loginPage.clickButtonLogin();
        mainPage = new MainPage(driver);
        mainPage.clickManageJenkins();

        Assert.assertEquals(mainPage.getManageUsersDT(), "Управление пользователями");
        Assert.assertEquals(mainPage.getManageUsersDD(), "Создание, удаление и модификция пользователей, имеющих право доступа к Jenkins");

        mainPage.clickManageUsers();

        Assert.assertTrue(mainPage.checkCreateUser());

        mainPage.clickCreateUser();
        createUserPage = new CreateUserPage(driver);

        typeTextElements = createUserPage.getFieldsTypeText();
        typePasswordElements = createUserPage.getFieldsTypePassword();

        Assert.assertEquals(typeTextElements.size(), 3);
        Assert.assertEquals(typePasswordElements.size(), 2);

        for (WebElement i : typeTextElements)
        {
               Assert.assertEquals(i.getAttribute("value"), "");
        }
        for (WebElement i : typePasswordElements)
        {
                Assert.assertEquals(i.getAttribute("value"), "");
        }

        createUserPage.createNewUser(newUserName, newPassword, newPasswordConfirm, newFullName, newEMail);
        createUserPage.clickCreateUserButton();
        usersPage = new UsersPage(driver, newUserName);

        Assert.assertEquals(usersPage.findNewUser(), newUserName);
        Assert.assertEquals(usersPage.deleteUser(), "Вы уверены, что хотите удалить пользователя из Jenkins?");

        usersPage.clickDeleteButton();

        Assert.assertFalse(usersPage.checkUserNameInTable());
        Assert.assertFalse(usersPage.checkDeleteHref());
        Assert.assertFalse(usersPage.checkAdminDeleteLink());
    }

    @Test
    public void checkButtonColour()
    {
        Assert.assertEquals(loginPage.getButtonBG(), BG_COLOR_BUTTON);

        loginPage.clickButtonLogin();
        mainPage = new MainPage(driver);
        mainPage.clickManageJenkins();
        mainPage.clickManageUsers();
        mainPage.clickCreateUser();
        createUserPage = new CreateUserPage(driver);
        createUserPage.createNewUser(newUserName, newPassword, newPasswordConfirm, newFullName, newEMail);

        Assert.assertEquals(createUserPage.getButtonBG(), BG_COLOR_BUTTON1);

        createUserPage.clickCreateUserButton();
        usersPage = new UsersPage(driver, newUserName);
        usersPage.deleteUser();

        Assert.assertEquals(usersPage.getButtonBG(), BG_COLOR_BUTTON2);

        usersPage.clickDeleteButton();

    }

    @Test
    public void checkEmptyName()
    {

        loginPage.clickButtonLogin();
        createUserPage = new CreateUserPage(driver);
        driver.navigate().to(createUserPage.getBASE_URL());
        createUserPage.createNewUser("", newPassword, newPasswordConfirm, newFullName, newEMail);
        createUserPage.clickCreateUserButton();
        Assert.assertEquals(createUserPage.getErrorMSG(), "\"\" is prohibited as a username for security reasons.");
    }


    @Test
    public void checkAutoRefresh() {

        loginPage.clickButtonLogin();
        mainPage = new MainPage(driver);

                Assert.assertEquals(mainPage.getHrefAutoRefreshText(), "Включить автообновление страниц");
                mainPage.clickAutoRefresh();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                Assert.assertEquals(mainPage.getHrefAutoRefreshText(), "Отключить автообновление страниц");
                mainPage.clickAutoRefresh();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                Assert.assertEquals(mainPage.getHrefAutoRefreshText(), "Включить автообновление страниц");
                mainPage.clickAutoRefresh();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                Assert.assertEquals(mainPage.getHrefAutoRefreshText(), "Отключить автообновление страниц");
                mainPage.clickAutoRefresh();
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }


    @AfterMethod
    public void afterMethod()
    {
        Driver.closeDriver();
    }

}
