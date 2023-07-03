package demoqa;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class NewTest {
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Downloads\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void edit() {
		//text box elements  
		driver.navigate().to("https://letcode.in/edit");
		driver.findElement(By.id("fullName")).sendKeys("Rohith s");
		driver.findElement(By.id("join")).sendKeys("person", Keys.TAB);
		String val =driver.findElement(By.id("getMe")).getAttribute("value");
		System.out.println(val);
		driver.findElement(By.id("clearMe")).clear();
		boolean en = driver.findElement(By.id("noEdit")).isEnabled();
		System.out.println(en);
		String readonly = driver.findElement(By.id("dontwrite")).getAttribute("value");
		System.out.println(readonly);

	}
	@Test
	public void button()
	{
		//location
		driver.navigate().to("https://letcode.in/buttons");
		WebElement ele =driver.findElement(By.id("position"));
		Point pi=ele.getLocation();
		int x=pi.getX();
		int y=pi.getY();
		System.out.println("x="+ x +"y="+y);
		//color
		WebElement btnclr=driver.findElement(By.id("color"));
		String color=btnclr.getCssValue("background-color");
		System.out.println(color);
		//height&width
		Rectangle rect =driver.findElement(By.id("property")).getRect();
		System.out.println("width="+rect.getWidth());
		System.out.println("Height="+rect.getHeight());

	}
	@Test
	public void alrt()
	{
		driver.navigate().to("https://letcode.in/alert");
		//simple alert
		driver.findElement(By.id("accept")).click();
		Alert ale= driver.switchTo().alert();
		ale.accept();
		//confirm alert
		driver.findElement(By.id("confirm")).click();
		ale.dismiss();
		//prompt alert
		driver.findElement(By.id("prompt")).click();
		String name =ale.getText();
		ale.sendKeys("rohith");
		ale.accept();
		String nme = driver.findElement(By.id("myName")).getText();
		System.out.println(nme);

	}
	@Test
	public void frme()
	{
		driver.navigate().to("https://letcode.in/frame");
		WebElement fme= driver.findElement(By.xpath("//iframe[@src='frameUI'] "));
		//enter 1st frame
		driver.switchTo().frame(fme);
		driver.findElement(By.name("fname")).sendKeys("Rohith");
		driver.findElement(By.name("lname")).sendKeys("s");
		WebElement inr= driver.findElement(By.xpath("//iframe[@src='innerFrame'] "));

		driver.switchTo().frame(inr);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("email@email.com");
		driver.switchTo().parentFrame();
		driver.findElement(By.name("lname")).sendKeys(".s");
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Watch tutorial")).click();

	}
	@Test
	public void drag()
	{
		driver.navigate().to("https://letcode.in/dropable");
		WebElement src=driver.findElement(By.id("draggable"));
		WebElement trg=driver.findElement(By.id("droppable"));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(src, trg).perform();
		
		
		}
	@Test
	public void dropdwn()
	{
		driver.navigate().to("https://letcode.in/dropdowns");
		WebElement fruits=driver.findElement(By.id("fruits"));
		Select myfruits= new Select(fruits);
		myfruits.selectByVisibleText("Mango");
		WebElement country=driver.findElement(By.id("country"));
		Select mycountry= new Select(country);
		mycountry.selectByValue("India");
		WebElement selectcountry=mycountry.getFirstSelectedOption();
		System.out.println(selectcountry.getText());
		WebElement hero=driver.findElement(By.id("superheros"));
		Select myhero=new Select(hero);
		myhero.selectByIndex(1);
		myhero.selectByValue("bt");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
