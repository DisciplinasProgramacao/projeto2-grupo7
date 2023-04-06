import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.*;

public class GrafoTest {

  @Test()
  public void testaSeOGrafoEhCompleto() {
    GrafoCompleto grafo = new GrafoCompleto(5);
    Assertions.assertTrue(grafo.completo());
  }

  @Test()
  public void deveAdicionarVertice() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    assertNotNull(grafo.existeVertice(1));
  }

  @Test()
  public void deveAdicionarAresta() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addAresta(1, 2);
    assertNotNull(grafo.existeAresta(1, 2));
  }

  @Test()
  public void testeArestaNaoDeveSerDirecionada() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addAresta(1, 2);
    assertNotNull(grafo.existeAresta(2, 1));
  }

  @Test()
  public void arestaNaoDeveExistir() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addAresta(1, 2);
    assertNull(grafo.existeAresta(2, 3));
  }

  @Test()
  public void testeQuandoNaoExisteVertice() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    assertNull(grafo.existeVertice(2));
  }

  @Test()
  public void naoDeveCriarDoisVerticesComIDsIguais() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    assertFalse(grafo.addVertice(1));
  }

  @Test()
  public void deveRetornarOrdemDoGrafo() {
    GrafoCompleto grafo = new GrafoCompleto(4);
    Assertions.assertEquals(4, grafo.ordem());
  }

  @Test()
  public void deveRetornarTamanhoDoGrafo() {
    GrafoCompleto grafo = new GrafoCompleto(5);
    Assertions.assertEquals(15, grafo.tamanho());
  }

  @Test()
  public void deveSalvarEmArquivo() {
    GrafoMutavel grafo = new GrafoMutavel("");
    assertDoesNotThrow(() -> grafo.salvar("grafo.txt"));
  }

  @Test()
  public void deveCarregarDeArquivo() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.carregar("grafo.txt");
    assertEquals(grafo.completo(), grafo.completo());
  }

  @Test
  public void CriarSubgrafo() {
    GrafoMutavel grafo = new GrafoMutavel("");
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
    GrafoMutavel grafo = new GrafoMutavel("");
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

  @Test
  public void arestaDoGrafoDirecionadoDeveSerUnidirecional() {
    GrafoDirecionado g = new GrafoDirecionado("");
    g.addVertice(1);
    g.addVertice(2);
    g.addAresta(1, 2);
    assertNull(g.existeAresta(2, 1)); 
  }
}
