package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

	@Test
	public void hello() {
		
		/* Definindo o caminho para achar o navegador específico do chromedriver */
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
		
		/*Interface WebDriver para utilizar o Selenium*/
		WebDriver browser = new ChromeDriver();
		browser.navigate().to("http://localhost:8080/leiloes");
		browser.quit();
	}

}
