import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.*;

public class GrafoTest {

  @Test()
  public void testaSeOGrafoEhCompleto() {
    Grafo grafo = Grafo.grafoCompleto(10);
    Assertions.assertTrue(grafo.completo());
  }

  @Test()
  public void deveAdicionarVertice() {
    Grafo grafo = new Grafo("");
    grafo.addVertice(1);
    assertNotNull(grafo.existeVertice(1));
  }

  @Test()
  public void deveAdicionarAresta() {
    Grafo grafo = new Grafo("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addAresta(1, 2);
    assertNotNull(grafo.existeAresta(1, 2));
  }

  @Test()
  public void testeArestaNaoDeveSerDirecionada() {
    Grafo grafo = new Grafo("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addAresta(1, 2);
    assertNotNull(grafo.existeAresta(2, 1));
  }

  @Test()
  public void arestaNaoDeveExistir() {
    Grafo grafo = new Grafo("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addAresta(1, 2);
    assertNull(grafo.existeAresta(2, 3));
  }

  @Test()
  public void testeQuandoNaoExisteVertice() {
    Grafo grafo = new Grafo("");
    grafo.addVertice(1);
    assertNull(grafo.existeVertice(2));
  }

  @Test()
  public void naoDeveCriarDoisVerticesComIDsIguais() {
    Grafo grafo = new Grafo("");
    grafo.addVertice(1);
    assertFalse(grafo.addVertice(1));
  }
}
