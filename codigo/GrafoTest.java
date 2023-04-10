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

  @Test
  public void testBuscaEmProfundidade() {
    GrafoMutavel grafo = new GrafoMutavel("Grafo de teste");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addVertice(3);
    grafo.addVertice(4);
    grafo.addVertice(5);

    grafo.addAresta(1, 2);
    grafo.addAresta(1, 4);
    grafo.addAresta(2, 3);
    grafo.addAresta(2, 4);
    grafo.addAresta(3, 5);
    grafo.addAresta(4, 5);

    Lista<Vertice> resultado = grafo.dfs(1);
    Lista<Integer> esperado = new Lista<Integer>();
    esperado.add(1);
    esperado.add(2);
    esperado.add(3);
    esperado.add(5);
    esperado.add(4);

    assertEquals(esperado, resultado);
  }

  @Test
  public void BuscaEmLargura() {
    GrafoMutavel grafo = new GrafoMutavel("");
    grafo.addVertice(1);
    grafo.addVertice(2);
    grafo.addVertice(3);
    grafo.addVertice(4);
    grafo.addVertice(5);
    grafo.addVertice(6);
    grafo.addVertice(7);
    grafo.addVertice(8);
    grafo.addVertice(9);
    grafo.addVertice(10);
    grafo.addAresta(1, 2);
    grafo.addAresta(1, 3);
    grafo.addAresta(1, 4);
    grafo.addAresta(2, 5);
    grafo.addAresta(2, 6);
    grafo.addAresta(3, 7);
    grafo.addAresta(3, 8);
    grafo.addAresta(4, 9);
    grafo.addAresta(4, 10);
    Lista<Integer> lista = new Lista<Integer>();
    lista.add(1);
    lista.add(2);
    lista.add(3);
    lista.add(4);
    lista.add(5);
    lista.add(6);
    lista.add(7);
    lista.add(8);
    lista.add(9);
    lista.add(10);
    assertEquals(lista, grafo.bfs(1));
  }
}
