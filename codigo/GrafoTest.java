import org.junit.jupiter.api.*;

public class GrafoTest {

  @Test()
  public void testaSeOGrafoEhCompleto() {
    Grafo grafo = Grafo.grafoCompleto(10);
    Assertions.assertTrue(grafo.completo());
  }
}