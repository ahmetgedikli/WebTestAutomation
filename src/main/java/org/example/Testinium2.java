package org.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

//TESTINIUM CASE STUDY ASSIGNMENT
//ADAY: AHMET GEDIKLI


@FixMethodOrder(MethodSorters.JVM)
public class Testinium2 {

    //Homepage in objesi
    HomePage home = new HomePage(driver);

    //Tüm @testlerde kullanmak için driver kurulumu
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\Desktop\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void seturTest() throws InterruptedException, IOException {

        //browserdan websitesine giris
        driver.get("https://www.setur.com.tr/");
        driver.manage().window().maximize();

        //sitenin yuklenmesini bekleme
        Thread.sleep(5000);

        //reklam kapatma
        home.closeAd();

        //url kontrol etme
        String expectedUrl = "https://www.setur.com.tr/";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,expectedUrl);

        //çerezleri reddetme
        WebElement cookiesDecline = driver.findElement(By.xpath("//*[@id=\"CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll\"]"));
        if (cookiesDecline.isDisplayed()){
            drawBorderandClick(driver,"//*[@id=\"CybotCookiebotDialogBodyLevelButtonLevelOptinDeclineAll\"]");
        }
        //sonraki test için bekleme süresi
        Thread.sleep(5000);

        //otel tabi kontrol etme ve açık değilse tıklama
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), \"Ne Kadar Kalacaksınız?\")]")));
        WebElement oteltext= driver.findElement(By.xpath("//*[contains(text(), \"Ne Kadar Kalacaksınız?\")]"));
        if (!oteltext.isDisplayed()){
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[1]/button[1]/span[2]")).click();
        }
        Assert.assertTrue(oteltext.isDisplayed());


        //konum ve tarih girme işlemleri, detay için HomePage.java bakiniz
        home.locationInput("Antalya");
        home.dateInput();

        //yetişkin sayisi arttirma ve kontrol etme
        boolean isNumberBigger = home.personInput();
        Assert.assertTrue(isNumberBigger);


        //arama butonunu kontrol edip tiklama
        WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(50));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class=\"sc-8de9de7b-0 dYTYAP\"]")));
        Assert.assertTrue(driver.findElement(home.searchButton).isDisplayed());
        Thread.sleep(500);
        drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[3]/button");

        //yüklenme için bekleme ve yeni url kontrolü
        Thread.sleep(7000);
        String currenturl2 = driver.getCurrentUrl();
        Assert.assertTrue(currenturl2.contains("antalya"));

        //random tiklama için element listesi ve seçim
        String random1 = "//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/div[1]/div/span/span";
        String random2 = "//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/div[2]/div/span/span";
        String random3 = "//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/div[3]/div/span/span";
        String random4 = "//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/div[2]/div[4]/div/span/span";
        String[] randoms = {random1,random2,random3,random4};

        Random generator = new Random();
        drawBorderandClick(driver,randoms[(generator.nextInt(randoms.length))]);

        //eğer arama sonucu yeterince fazla ise parantez içi numaralari karsilastirma
        Thread.sleep(1500);
        if(!driver.findElements(By.xpath("(//span[@class=\"sc-21021e1e-3 eqVmWj\"])[1]")).isEmpty()){

            String text1 = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/h3")).getText();
            Integer number1 = Integer.valueOf(text1.substring(text1.indexOf("(")+1, text1.indexOf(")")));

            //scroll
            Thread.sleep(2000);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            WebElement element= (driver.findElement(By.xpath("(//span[@class=\"sc-21021e1e-3 eqVmWj\"])[1]")));
            jse.executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);
            jse.executeScript("window.scrollBy(0,-350)", "");
            String text2 =element.getText();
            Integer number2 = Integer.valueOf(text2);

            //karşılaştırma
            Assert.assertEquals(number1,number2);

        }
        Thread.sleep(2000);
        driver.quit();


    }



    //Tiklanilan elementleri sari ile gösterme fonksiyonu
    public static void drawBorderandClick(WebDriver driver, String xpath) throws InterruptedException {
        WebElement element_node = driver.findElement(By.xpath(xpath));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].style.border='5px solid yellow'", element_node);
        Thread.sleep(1000);
        jse.executeScript("arguments[0].style.border=''", element_node);
        driver.findElement(By.xpath(xpath)).click();
    }




}