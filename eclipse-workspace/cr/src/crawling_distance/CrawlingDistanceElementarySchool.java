package crawling_distance;

//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlingDistanceElementarySchool {
	//private static final String filePath = "c:\\KOPO\\git_tarcking\\기본프로그래밍_java\\Pro\\Data.csv";
	private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private static final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";

	public static void main(String[] args) {
		//File CSVfile = null;
		//BufferedWriter bw = null;
		//String NewLine = System.lineSeparator();
		
		//CSVfile = new File(filePath);
		//bw = new BufferedWriter(new FileWriter(CSVfile));
		
		try {
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		
		driver.get("https://new.land.naver.com/complexes?ms=37.566427,126.977872,13&a=APT:ABYG:JGC&e=RETAIL");
		
		// 광역시 배너 클릭
		clickSelectionCity(wait);
		
		// 광역시 중 서울시 선택 -> 자동으로 구 선택을 넘어감
		selectSeoul(wait);

		// 총 구의 개수 확인
		int guSize = checkRegionSize(driver);
		
		// 구 개수 만큼 반복
		for(int i = 1 ; i < guSize ; i++) {
			// 서울인지 확인 다르면 처리
			// 추가 예정
			
			// 구 선택 -> 자동으로 동 선택으로 넘아감
			selectGu(wait, i);
			String guName = getGuName(driver);
			
			// 총 동의 개수 확인, 26
			int dongSize = checkRegionSize(driver);
			
			// 동 개수 만큼 반복
			for(int j = 13 ; j <= dongSize ; j++) {
				// 구 확인 다들면 처리
				// 추가 예정
				
				// 동 선택 -> 자동으로 단지 선택으로 넘어감
				selectDong(wait, j);
				String dongName = getDongName(driver);
				
				// 총 단지의 개수 확인
				int complexSize = checkComplexSize(driver);

				for(int k = 1 ; k <= complexSize ; k++) {
					checkDongNameAndRearrange(dongName, i);
					checkGuNameAndRearrange(guName, i);
					
					
					String location = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/a")).getText();
					
					if(k != 1) {
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div[1]/div/div/a/span[4]"))).click();
						wait100ms();
					}
					
					// 단지 선택 - 자동으로 단지 정보로 펼쳐짐
					selectComplex(wait, k);
					
					// 학군정보 배너 클릭 - 유동적
					if( k == 1) {
						clickSchoolDistrict(driver, wait);
					}
					
					// 초등학교까지 거리 수집
					String schoolData = collectDistance(wait);
					String distance = schoolData.split("도보로")[1].split("분")[0];
					System.out.println(guName + " - " + dongName + " - " + distance);
					
					// 현재 단지 닫기
					closeComplexInformation(wait);
				}
			}
			
		}
		
		
		
		
		
		// 닫앗는데 지도 움직임?으로 다른 동으로 동네 설정됬을 수도.
		// 목표하는 구와 돋이 맞는지 확인.
		
		
		
		
		
		
		
		//bw.write("sadf");
		//bw.flush();
		//bw.close();
	}
	
//	private static int checkRegionSize(WebDriver driver) {
//		return driver.findElements(By.xpath("//*[@class=\"area_item\"]")).size();
//	}
	
	private static String getDongName(WebDriver driver) {
		String dongName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div/div[1]/div/a[3]")).getText();
		wait100ms();
		return dongName;
	}

	private static String getGuName(WebDriver driver) {
		String guName = driver.findElement(By.xpath("//*[@id=\"region_filter\"]/div/div/div[1]/div/a[2]")).getText();
		wait100ms();
		return guName;
	}

	private static void closeComplexInformation(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/button"))).click();
		wait100ms();
	}

	private static String collectDistance(WebDriverWait wait) {
		String distance = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"detailContents5\"]/div/div[1]"))).getText();
		wait100ms();
		return distance;
	}

	private static void clickSchoolDistrict(WebDriver driver, WebDriverWait wait) {
//		List bannerName = driver.findElements(By.xpath("//*[@id=\"ct\"]/div[2]/div[2]/div/div[2]/div[2]/div/div/a"));
//		for(int i = 0 ; i < bannerName.size() ; i++) {
//			System.out.println(bannerName.get(i));
//		}
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[4]/span")));
		
		
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[4]/span"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div[2]/div/div[2]/div[2]/div/div/a[2]/span"))).click();
		wait100ms();
	}

	private static void selectComplex(WebDriverWait wait, int k) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[3]/ul/li[" + k + "]/a"))).click();
		wait100ms();
	}
	
	private static int checkComplexSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"complex_item\"]")).size();
	}
	
	private static void selectDong(WebDriverWait wait, int j) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + j + "]/label"))).click();
		wait100ms();
	}


	private static int checkRegionSize(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@class=\"area_item\"]")).size();
	}

	private static void clickSelectionCity(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/a/span[1]"))).click();
		wait100ms();
	}


	private static void selectSeoul(WebDriverWait wait) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[1]/label"))).click();
		wait100ms();
	}


	public static void selectGu(WebDriverWait wait, int guIndex) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div/section/div[2]/div/div[1]/div/div/div/div[2]/ul/li[" + guIndex + "]/label"))).click();
		wait100ms();
	}
	
	public static void wait100ms() {
		try {
			Thread.sleep(500);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
