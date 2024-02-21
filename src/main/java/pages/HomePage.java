package pages;

import org.example.Testinium2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Reklam kapatma
    public void closeAd() throws InterruptedException {
        Testinium2.drawBorderandClick(driver,"(//div[@id='close-button-1454703513202'])[2]");
    }

    public By locationForm = By.xpath("//input[@aria-label=\"search-input\"]");
    public By dateForm = By.xpath("//*[contains(text(), \"Ne Kadar Kalacaksınız?\")]");
    public By personForm = By.xpath("//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[3]/div/div[1]/div[2]/span/span");
    public By searchButton = By.xpath("//button[@class=\"sc-8de9de7b-0 dYTYAP\"]");

    public void locationInput(String searchText) throws InterruptedException {

        //lokasyon input girişi
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locationForm));
        Testinium2.drawBorderandClick(driver,"//input[@aria-label=\"search-input\"]");
        driver.findElement(locationForm).sendKeys(searchText);
        Thread.sleep(2000);
        Testinium2.drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div/div/ul/div[1]/div/div");
    }

    public void dateInput() throws InterruptedException {

        //tarih girişi
        Testinium2.drawBorderandClick(driver,"//*[contains(text(), \"Ne Kadar Kalacaksınız?\")]");
        Thread.sleep(1000);

        //iki kere sağ ok butonuna tiklayip nisan ayina ulaşma
        Testinium2.drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/button[2]/span/span");
        Testinium2.drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[1]/button[2]/span/span");
        Thread.sleep(1500);

        //tarihlere tiklama
        Testinium2.drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[3]/div/table/tbody/tr[1]/td[1]/span");
        Testinium2.drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[3]/div/table/tbody/tr[1]/td[7]/span");

    }

    public boolean personInput() throws InterruptedException {

        //driver.findElement(personForm).click();
        Testinium2.drawBorderandClick(driver,"//*[@id=\"__next\"]/div[3]/div[3]/div[1]/div[2]/div/div/div/div[2]/div[3]/div/div[1]/div[2]/span/span");
        Thread.sleep(1000);
        Integer beforeCount = Integer.parseInt(driver.findElement(By.xpath("(//span[@data-testid=\"count-label\"])[1]")).getText());
        //driver.findElement(By.xpath("(//button[@data-testid=\"increment-button\"])[1]")).click();
        Testinium2.drawBorderandClick(driver,"(//button[@data-testid=\"increment-button\"])[1]");
        Thread.sleep(500);
        Integer afterCount = Integer.parseInt(driver.findElement(By.xpath("(//span[@data-testid=\"count-label\"])[1]")).getText());
        if(afterCount==beforeCount+1){
            return true;
        }else{
            return false;
        }

    }





}
