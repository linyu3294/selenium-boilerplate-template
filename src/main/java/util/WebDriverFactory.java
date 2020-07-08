package util;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
public static WebDriver make (String webDriverType) {
   webDriverType = webDriverType.toUpperCase();
   WebDriver newWebDriver = null;
   switch (webDriverType) {

      case "FIREFOX":
//         System.setProperty("webdriver.firefox.marionette");
         FirefoxOptions ffOptions = new FirefoxOptions();
         newWebDriver = new FirefoxDriver(ffOptions);
         break;

      case "IE":
//         System.setProperty("webdriver.ie.driver", exePath);
         DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
         //If IE fail to work, please remove this and remove enable
         // protected mode for all the 4 zones from Internet options
         dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
         newWebDriver = new InternetExplorerDriver(dc);
         break;

      case "CHROME":
         WebDriverManager.chromedriver().setup();
         ChromeOptions chOptions = new ChromeOptions();
//         chOptions.addArguments("--headless");
//         System.setProperty("webdriver.chrome.driver", exePath);
         newWebDriver = new ChromeDriver(chOptions);
         newWebDriver.manage().window().setPosition(new Point(0, 0));
         newWebDriver.manage().window().maximize();
         break;
   }

   return newWebDriver;
}
}
