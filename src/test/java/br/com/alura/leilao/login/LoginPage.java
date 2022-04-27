package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage extends PageObject {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	
	public LoginPage() {
		super(null); /* Informando pro PageObject criar um novo Browser */
		this.browser.navigate().to(URL_LOGIN);
	}

	public void preencheFormularioDeLogin(String username, String password) {
		this.browser.findElement(By.id("username")).sendKeys(username);
		this.browser.findElement(By.id("password")).sendKeys(password);	
	}

	public LeiloesPage efetuaLogin() {
		this.browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser); /* Dashboard */
	}

	public boolean isPaginaDeLogin() {
		/* Busca a URL atual e compara com a URL esperada */
		return this.browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public Object getNomeUsuarioLogado() {
		
		try {
			/* Busca o elemento cujo id é usuario-logado e pega o seu texto. */
			return this.browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaParaPaginaDeLances() {
		this.browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean contemTexto(String texto) {
		/* busca um texto no código da página */
		return this.browser.getPageSource().contains(texto);
	}

	public boolean isPaginaDeLoginComDadosInvalidos() {
		return this.browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}

}
