package almosafer;




import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class mytestcases {
     
	String AlMosafer = "https://global.almosafer.com/en";
	
	WebDriver driver = new ChromeDriver();
	
	SoftAssert softassert = new SoftAssert();
	Random rand = new Random();
	
	String [] arabicCitiesNames = {"دبي", "جدة"};
	String [] englishCitiesNames = {"Dubai", "Jeddah" , "Riyadh"};
	int randAr = rand.nextInt(arabicCitiesNames.length);
	int randEn = rand.nextInt(englishCitiesNames.length);
	
	
	
	@BeforeTest
	public void Setup() {
		
		driver.get(AlMosafer);
		driver.manage().window().maximize();
		WebElement WelcomeScreen = driver.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		WelcomeScreen.click();
	}
	
	
	@Test(priority = 1)
	public void CheckTheLanguage() {
		
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLanguage = "en";
		
		softassert.assertEquals(ActualLanguage, ExpectedLanguage,"the language is not en" );
	}
	
	@Test(priority = 1)
	public void CheckTheCurrency() {
		String ExpectedCurrency = "SAR";
		WebElement CurrencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		
		String ActualCurrency = CurrencyElement.getText();
		
		softassert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	
	@Test(priority = 1)
	public void CheckContactNumber() {
		String ExpectedContactNumber = "+966554400000";
		WebElement ContactNumberElement = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		
		String ActualContactNumber = ContactNumberElement.getText();
		
		softassert.assertEquals(ActualContactNumber, ExpectedContactNumber);
		
	}
	
	
	@Test(priority = 1)
	public void CheckQitaffLogo() {
		WebElement QittafLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']"));
		
		boolean ActualQittafLogoIsDisplayed = QittafLogo.isDisplayed();
		boolean ExpectedQittafLogoIsDisplayed = true;
		
		softassert.assertEquals(ActualQittafLogoIsDisplayed, ExpectedQittafLogoIsDisplayed);
		
	}
	
	@Test(priority = 2)
	public void HotelTabIsNotSelected() throws InterruptedException {
		WebElement Hotel = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualHotelTab = Hotel.getAttribute("aria-selected");
		String ExpectedHotelTab = "false";
		
		Thread.sleep(2000);
		
		Assert.assertEquals(ActualHotelTab, ExpectedHotelTab);
	}
	
	
	@Test(priority = 3)
	public void CheckTheDate() {
		
		
		//part one
		
		WebElement ActualDepatureDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement ActualReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
		
		int ActualDepatureDateValue = Integer.parseInt(ActualDepatureDate.getText());
		int ActualReturnDateValue = Integer.parseInt(ActualReturnDate.getText());
		
		
		LocalDate today =  LocalDate.now();
		
		int ExpectedDepatureDateValue =	today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDateValue   =	today.plusDays(2).getDayOfMonth();
		
		Assert.assertEquals(ActualDepatureDateValue, ExpectedDepatureDateValue);
		Assert.assertEquals(ActualReturnDateValue, ExpectedReturnDateValue);
		
		
		//part two
		
		String DayDepatureElement = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']")).getText().toUpperCase();
		String DayReturnElement = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-eSePXt ljMnJa']")).getText().toUpperCase();
		
		
		Assert.assertEquals(DayDepatureElement, today.plusDays(1).getDayOfWeek().toString());
		Assert.assertEquals(DayReturnElement, today.plusDays(2).getDayOfWeek().toString());
		
		
		
		
		//part three
		
		String MonthDepatureElement  = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-hvvHee cuAEQj']")).getText().toUpperCase();
		
		String MonthReturnElement  = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-hvvHee cuAEQj']")).getText().toUpperCase();
		
		  Assert.assertEquals(MonthDepatureElement, today.getMonth().toString());
		  Assert.assertEquals(MonthReturnElement, today.getMonth().toString());
		
		
		
	}
	
	
	@Test(priority = 4)
	public void CheckTheLang() throws InterruptedException {
		String[] myUrls = {"https://global.almosafer.com/ar","https://global.almosafer.com/en"};
		
		int randomIndex = rand.nextInt(myUrls.length);
		
		driver.get(myUrls[randomIndex]);
		
		
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		
		HotelTab.click();
		
		Thread.sleep(2000);
		
		WebElement SearchHotelInput = driver.findElement(By.className("phbroq-2"));
		
	if(driver.getCurrentUrl().contains("ar")) {
			
			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLang,"ar");
			SearchHotelInput.sendKeys(arabicCitiesNames[randAr]);
			
			Thread.sleep(2000);
			WebElement CityList = driver.findElement(By.className("phbroq-4"));
	       List <WebElement> MyItems = CityList.findElements(By.tagName("li"));
	       MyItems.get(1).click();
			
		}else {
			String ActualLang = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLang,"en");
			SearchHotelInput.sendKeys(englishCitiesNames[randEn]);
			Thread.sleep(2000);
			WebElement CityList = driver.findElement(By.className("phbroq-4"));
		       List <WebElement> MyItems = CityList.findElements(By.tagName("li"));
		       MyItems.get(1).click();
		}
	WebElement VistorInput = driver.findElement(By.className("tln3e3-1"));
	Select select = new Select(VistorInput);
	int RandomIndexValue = rand.nextInt(2);
	
	select.selectByIndex(RandomIndexValue);
	
	WebElement SearchButton = driver.findElement(By.className("sc-1vkdpp9-6"));
	SearchButton.click();

	Thread.sleep(16000);
	
	String HotelSearchResult = driver.findElement(By.className("sc-cClmTo")).getText();
	
	if(driver.getCurrentUrl().contains("ar")) {
	boolean ActualMsg = HotelSearchResult.contains("وجدنا");
	boolean ExpectedMsg = true;
	
	Assert.assertEquals(ActualMsg, ExpectedMsg);
	
	WebElement LowestPrice = driver.findElement(By.className("kgqEve"));
	LowestPrice.click();
	}else {
	boolean ActualMsg =	HotelSearchResult.contains("found");
	boolean ExpectedMsg = true;
	
	Assert.assertEquals(ActualMsg, ExpectedMsg);
	WebElement LowestPrice = driver.findElement(By.className("jurIdk"));
	LowestPrice.click();
	}
	
	
	
	Thread.sleep(3000);
	
	WebElement Prices = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

	List<WebElement> myPrices = Prices.findElements(By.className("Price__Value"));

	
		
		int lowestPrice = Integer.parseInt(myPrices.get(0).getText());
		int highestPrice = Integer.parseInt(myPrices.get(myPrices.size() - 1).getText());
		
		System.out.println(lowestPrice + "this is a lowest price");
		System.out.println(highestPrice + "this is a highest price");

		Assert.assertEquals(highestPrice > lowestPrice, true);
	
	
	
	}
	
	
	@AfterTest
	public void MyAfterTest() {
		
		softassert.assertAll();
	}
	
}
