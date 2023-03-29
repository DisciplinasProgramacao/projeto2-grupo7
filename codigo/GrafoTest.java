import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

  @Test()
  public void deveRetornarOrdemDoGrafo() {
    Grafo grafo = Grafo.grafoCompleto(4);
    Assertions.assertEquals(4, grafo.ordem());
  }

  @Test()
  public void deveRetornarTamanhoDoGrafo() {
    Grafo grafo = Grafo.grafoCompleto(5);
    Assertions.assertEquals(10, grafo.tamanho());
  }

  /*
   * @Test()
   * public void deveSalvarEmArquivoECarregar() {
   * Grafo grafo = Grafo.grafoCompleto(5);
   * // grafo.salvar("grafo.txt");
   * assertDoesNotThrow(() -> grafo.salvar("grafo.txt"));
   * }
   */

  @Test
  public void CriarSubgrafo() {
    Grafo grafo = new Grafo("");
    Lista<Integer> vertices = new Lista<Integer>();
    for (int i = 0; i <= 10; i++) {
      grafo.addVertice(i);
      vertices.add(i);
    }
    for (int j = 0; j <= 10; j++) {
      grafo.addAresta(j, j + 1);
    }
    Grafo subgrafo = grafo.subGrafo(vertices);
    assertNotNull(subgrafo);
  }

  @Test
  public void CriarSubgrafoComArestas() {
    Grafo grafo = new Grafo("");
    Lista<Integer> vertices = new Lista<Integer>();
    for (int i = 0; i <= 10; i++) {
      grafo.addVertice(i);
      vertices.add(i);
    }
    for (int j = 0; j <= 10; j++) {
      grafo.addAresta(j, j + 1);
    }
    Grafo subgrafo = grafo.subGrafo(vertices);
    assertNotNull(subgrafo.existeAresta(1, 2));
  }
}
