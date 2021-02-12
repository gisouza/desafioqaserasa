package testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SerasaTest {
    WebDriver driver;
    final String url = "https://www.serasa.com.br/";
    final String webdriver ="webdriver.chrome.driver";
    final String caminho = "C:\\Users\\giane.souza\\Desktop\\Teste\\driver\\chromedriver.exe";
    final long tempo = 5;

    @Before
    public void init() {
        System.setProperty(webdriver,caminho);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(tempo, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testeCPFVazio() {
        String mensagem = "Por favor preencha seu CPF.";
        driver.get(url);
        driver.findElement(By.className("ecs_hl_ei")).click();
        WebElement campoCPF = driver.findElement(By.id("cpf"));
        campoCPF.submit();
        String cpfVazio = driver.findElement(By.className("_1fpPRNS1")).getText();
        assertEquals(mensagem, cpfVazio);
    }

    @Test
    public void testeCPFInvalido() {
        String mensagem = "Você precisa informar um CPF válido.";
        driver.get(url);
        driver.findElement(By.className("ecs_hl_ei")).click();
        WebElement campoCPF = driver.findElement(By.id("cpf"));
        campoCPF.sendKeys("83364783922");
        campoCPF.submit();
        String cpfInvalido = driver.findElement(By.className("_1fpPRNS1")).getText();
        assertEquals(mensagem, cpfInvalido);

    }
    @Test
    public void testeCPFValido() {
        driver.get(url);
        driver.findElement(By.className("ecs_hl_ei")).click();
        WebElement campoCPF = driver.findElement(By.id("cpf"));
        campoCPF.sendKeys("09944675911");
        campoCPF.submit();
        assertNotNull(driver.findElement(By.className("ky9mFFmn")));
    }

    @After
    public void quit() {
        driver.quit();
    }
}
