package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {
	
	protected WebDriver browser;

	public PageObject(WebDriver browser) {
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
		if(browser == null) {
			this.browser = new ChromeDriver();
		} else {
			this.browser = browser;
		}
		/* chamando as configurações do WebDriver para configurar o tempo em que será executado */
		this.browser.manage().timeouts()
				.implicitlyWait(5, TimeUnit.SECONDS) /* Aguarda buscar um elemento da página por 5 segundos */
				.pageLoadTimeout(10, TimeUnit.SECONDS); /* Aguarda a página carregar 10 segundos */
	}
	
	public void fechar() {
		this.browser.quit();
	}
}
