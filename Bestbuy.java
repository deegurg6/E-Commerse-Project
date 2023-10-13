import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import java.util.List;
import java.time.Duration;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import java.util.List;
import java.time.Duration;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class Bestbuy { 
             Logger logger = Logger.getLogger(Bestbuy.class.getName());
             static WebDriver driver;
             static WebElement username;
             static WebElement password;
             static WebElement signin;
             static Properties prop;
             static WebElement element;


             public static void LoginAction(String user,String pswd){
                    username = driver.findElement(By.name("fld-e"));
                    password = driver.findElement(By.name("fld-p1-text"));
                    signin = driver.findElement(By.xpath("/html/body/div[1]/div/section/main/div[2]/div/div/div[1]/div/div/div/div/div/form/div[3]/button"));                   
                    signin.click();
             }
             
                      @BeforeMethod
                      public void setup() throws Exception {

                                  System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));                        
                                  driver=new ChromeDriver();         
                                  String url ="https://www.bestbuy.com/";
                                  driver.get(url);
                                  driver.manage().window().maximize();         
                                  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                      }
                      
                      @Test(priority = 1)
                      public void navigateToSigninUrlPage() {
                                  String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-16035595\"]/div/div[1]/header/div[1]/div/a/svg/path[3]")).getText();
                                  Assert.assertEquals(title1,"BEST BUY");
                                  logger.log(Level.INFO, "1.The Head Title near the login box: "+ title1);
                      }
                     
                      @Test(priority = 2)
                      public void messageOnclickSigninButton() {
                                  
                                  String msg1 = driver.findElement(By.xpath("//*[@id=\"fld-e-text\"]")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Please enter a valid email address");
                                  assertTrue(emsg);
                                  logger.log(Level.SEVERE, "2.The message is: "+ msg1);    
                      }
                      
                      @Test(priority = 3)
                      public void messageOnUsernameBox() {
                                  LoginAction("blankuser","password");
                                  String msg1 = driver.findElement(By.xpath("//*[@id=\\\"fld-e-text\\\"]")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Please enter a valid email address.");
                                  assertTrue(emsg);
                                  logger.log(Level.SEVERE, "3.The message is: "+ msg1);    
                      }
                      
                      @Test(priority = 4)
                      public void messageOnPasswordBox() {
                                  LoginAction("username","blankpass");
                                  String msg1 = driver.findElement(By.xpath("//*[@id=\"fld-p1-text\"]/p")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Please enter your password.");
                                  assertTrue(emsg);
                                  logger.log(Level.SEVERE, "4.The message is: "+ msg1);    
                      }
                      
                      @Test(priority = 5)
                      public void incorrectUsername() {
                                  LoginAction("invaliduser","password");
                                  String msg1 = driver.findElement(By.xpath("//*[@id=\\\"fld-e-text\\\"]")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Please enter a valid email address.");
                                  assertTrue(emsg);
                                  logger.log(Level.SEVERE, "5.The message is: "+ msg1);    
                      }
                      
                      @Test(priority = 6)
                      public void incorrectPassword() {
                                  LoginAction("username","invalidpswd");
                                  String msg1 = driver.findElement(By.xpath("//*[@id=\"fld-p1-text\"]/p")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Please enter correct password.");
                                  assertTrue(emsg);
                                  logger.log(Level.SEVERE, "6.The message is: "+ msg1);    
                      }
                      
                      @Test(priority = 7)
                      public void incorrectUsernamePassword() {
                                  LoginAction("invaliduser","invalidpswd");
                                  String msg1 = driver.findElement(By.xpath("//*[@id='errorDiv']")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Invalid Username/Password");
                                  assertTrue(emsg);
                                  logger.log(Level.SEVERE, "7.The message is: "+ msg1);
                      }
                      
                      @Test(priority = 8)
                      public void correctUsernamePassword() {
                                  LoginAction("username","password");
                                  String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/a/svg/path[3]")).getText();
                                  Boolean emsg = title1.equalsIgnoreCase("BEST BUY");
                                  assertTrue(emsg);
                                  logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);
                      }
                      
                      @Test(priority = 9)
                      public void clickOnSignoutLink() {
                                  LoginAction("username","password");
                                  driver.findElement(By.xpath("//*[@id='top-menu-icon-div']/div[5]/div")).click();
                                  driver.findElement(By.linkText("Sign Out")).click();
                                  String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/a/svg/path[3]")).getText();
                                  Boolean emsg = title1.equalsIgnoreCase("BEST BUY");
                                  assertTrue(emsg);
                                  logger.log(Level.INFO, "9.The Head Title in the page: "+ title1);           
                      }
                      
                      @Test(priority = 10)
                      public void clickOnMenu() {
                                  
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/button")).click();
                                  String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/button")).getText();
                                  Boolean emsg = title1.equalsIgnoreCase("Deals");
                                  assertTrue(emsg);
                                  logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
                      }
                      
                      @Test(priority = 11)
                      public void clickOnDeal() {
                          
                          driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Deals");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 12)
                      public void clickOnSupportservice() {
                          
                          driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[2]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Support & Services");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 13)
                      public void clickOnBrands() {
                          
                          driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[3]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Brands");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 14)
                      public void clickOnAppliances() {
                          
                          driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[5]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Appliances");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 15)
                      public void clickOnTvHometheatre() {
                          
                          driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[6]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("TV & Home Theater");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 16)
                      public void clickOnComputerTablets() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[7]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Computers & Tablets");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 17)
                      public void clickOnCellphones() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[8]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Cell Phones");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 18)
                      public void clickOnAudio() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[9]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Audio");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 19)
                      public void clickOnVideogames() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[10]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Video Games");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 20)
                      public void clickOnCameras() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[11]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Cameras, Camcorders & Drones");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 21)
                      public void clickOnHome() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[12]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Home, Furniture & Office");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 22)
                      public void clickOnSmarthome() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[13]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Smart Home, Security & Wi-Fi");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 23)
                      public void clickOnCarElectronics() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[14]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Car Electronics & GPS");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 24)
                      public void clickOnMovies() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[15]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Movies & Music");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 25)
                      public void clickOnWearable() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[16]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Wearable Technology");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 26)
                      public void clickOnHealth() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[17]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Health, Wellness & Fitness");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 27)
                      public void clickOnOutdoor() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[18]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Outdoor Living");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 28)
                      public void clickOnToys() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[19]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Toys, Games & Collectibles");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 29)
                      public void clickOnElectric() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[20]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Electric Transportation");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 30)
                      public void clickOnNew() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[2]/div/nav/div/div/ul/li[21]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[1]/h2")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("New & Featured");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 31)
                      public void clickOnClose() {
                    	  driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[22]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-header-98734641\"]/div/div[1]/header/div[2]/div/nav/button")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Menu");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the page below the logo: "+ title1);           
              }
                      
                      @Test(priority = 32)
                      public void clickOnAccessibility() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[1]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Accessibility - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                       
                      @Test(priority = 33)
                      public void clickOnTermsCondition() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[2]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("BestBuy.com Terms and Conditions");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 34)
                      public void clickOnPrivacy() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[3]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Privacy Policy Hub - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 35)
                      public void clickOnInterestBased() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[4]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Interest-Based Ads - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 36)
                      public void clickOnStatePrivacy() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[5]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("State Privacy Rights - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 37)
                      public void clickOnHealthData() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[6]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Privacy Policy Hub - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 38)
                      public void clickOnDoNot() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[7]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Star Request - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 39)
                      public void clickOnLimitUse() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[8]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 40)
                      public void clickOnTargeted() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[9]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("Star Request - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 41)
                      public void clickOnCAsupply() throws Exception {
                    	  driver.findElement(By.xpath("//*[@id=\"footer\"]/div[2]/div[2]/a[10]")).click();
                          String title1 = driver.getTitle();
                          Boolean emsg = title1.equalsIgnoreCase("California Supply Chain Transparency Act - Best Buy");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title of the page: "+ title1);           
              }
                      
                      @Test(priority = 42)
                      public void ClickOnAddtoCart() {
                    	  driver.findElement(By.xpath("//*[@id=\"fulfillment-add-to-cart-undefined-6552867\"]/div/div/div[1]/button")).click();
                          String title1 = driver.findElement(By.xpath("//*[@id=\"shop-commerce-elements-384146\"]/div/div/span")).getText();
                          Boolean emsg = title1.equalsIgnoreCase("Added to cart");
                          assertTrue(emsg);
                          logger.log(Level.INFO, "8.The Title in the Add to cart window: "+ title1);           
              }
                      
                      @Test(priority = 43)
                      public void clickOnShopbyDepartment() {                                
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-83934791\"]/div/div[1]/header/div[1]/div/nav/button")).click();
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-83934791\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[8]/button")).click(); 
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-83934791\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[6]/button")).click();
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-83934791\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[2]/a")).click();
                                  JavascriptExecutor js = (JavascriptExecutor) driver;
                                  js.executeScript("window.scrollBy(0, 500);");
                                  js.executeScript("document.getElementById('<a href=\"/site/apple-iphone-15-pro-max-512gb-natural-titanium-at-t/6525427.p?skuId=6525427\" class=\"btn btn-outline btn-sm\" role=\"button\" data-track=\"[context:linkRegion=Persistent,linkPlacement=Persistent,linkContent=iPhone15ProMax]\">iPhone 15 Pro Max</a>').scrollIntoView();");
                                  driver.findElement(By.xpath("//*[@id=\"site-control-content\"]/div[4]/div/div/div/div[2]/div/div/section/div/div/a[2]")).click();
                                  driver.findElement(By.xpath("//*[@id=\"fulfillment-add-to-cart-button-99608815\"]/div/div/div/button")).click();
                                  String msg1 = driver.findElement(By.xpath("//*[@id='pwd']")).getText();
                                  Boolean emsg = msg1.equalsIgnoreCase("Please enter valid password");
                                  assertTrue(emsg);
                                  logger.log(Level.WARNING, "43.The message is: "+ msg1);
                      }
                      
                      @Test(priority = 44)
                      public void clickOnShopbyBands() throws Exception {
                                  LoginAction("username","password");
                                  driver.findElement(By.xpath("//*[@id=\\\"shop-header-83934791\\\"]/div/div[1]/header/div[1]/div/nav/button")).click();
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-37201057\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[3]/button")).click(); 
                                  driver.findElement(By.xpath("//*[@id=\"shop-header-79332326\"]/div/div[1]/header/div[1]/div/nav/div/div/ul/li[3]/a")).click();
                                  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
                                  wait.until(ExpectedConditions.stalenessOf(element));
                                  String title1 = driver.getTitle();
                                  Boolean emsg = title1.equalsIgnoreCase("Apple - Best Buy");
                                  assertTrue(emsg);
                                  logger.log(Level.INFO, "44.The Title of the page: "+ title1);
                      }                                  
                                                               
                      @AfterMethod
                      public void tearDown() {
                                   List<WebElement> lgut = driver.findElements(By.xpath("//*[@id='top-menu-icon-div']/div[5]/div"));
                                   if(lgut.size() != 0) {
                                   driver.findElement(By.xpath("//*[@id='top-menu-icon-div']/div[5]/div")).click();
                                   driver.findElement(By.linkText("Sign Out")).click();
                                   }
                                   driver.quit();
                      }
                      
}


