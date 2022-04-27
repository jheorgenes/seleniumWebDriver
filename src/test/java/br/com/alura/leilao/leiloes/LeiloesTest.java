package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastro;
	
	@BeforeEach
	public void beforeEach() {
		
		/* Efetua o login */
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormularioDeLogin("fulano", "pass");
		
		/* Aqui já recebe a página do dashboard (Com o browser na mesma aba) */
		this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
		
		/* Acessa o botão Novo Leilao (Continua na mesma aba do browser)  */
		this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}

	@Test
	public void deveriaCadastrarLeilao() {
		
		/* Prepara os dados para cadastrar um novo Leilao */
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); //Formatando a data recebida para String
		String nome = "Leilao do dia " + hoje;
		String valor = "500.00";
		
		/* Cadastra um Leilão e retorna para a página de Leilões (utilizando o botão submit) */
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		
		/* Valida se o último leião da tabela é o leilão que acabou de ser inserido */
		Assert.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
		/* Validando se ainda está na página de cadastro */
		Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual());
		/* Validando se voltou pra página de leilões */
		Assert.assertTrue(this.paginaDeLeiloes.isPaginaAtual());
		/* Validando se nos inputs, está aparecendo os alerts de valores esperados */
		Assert.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis()); 
	}
}
