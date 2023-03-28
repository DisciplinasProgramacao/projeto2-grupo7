import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 * MIT License
 *
 * Copyright(c) 2021-23 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Classe básica para um Grafo simples não direcionado.
 */
public class Grafo {
    public final String nome;
    private ABB<Vertice> vertices;

    /**
     * Retorna um grafo completo de ordem n, com todos os vértices conectados entre si
     * @param ordem
     * @return Grafo
     */
    public static Grafo grafoCompleto(int ordem) {
        Grafo grafo = new Grafo("graph");

        for (int i = 0; i < ordem; i++) {
            Vertice vertice = new Vertice(i);
            grafo.addVertice(i);

            for (int j = i + 1; j < ordem; j++) {
                if (vertice.existeAresta(j) == null) {
                    vertice.addAresta(j, -1);
                } else {
                    continue;
                }
            }
        }

        return grafo;
    }

    /**
     * Construtor. Cria um grafo vazio com um nome escolhido pelo usuário. Em caso
     * de nome não informado
     * (string vazia), recebe o nome genérico "Grafo"
     */
    public Grafo(String nome) {
        if (nome.length() == 0)
            this.nome = "Grafo";
        else
            this.nome = nome;
        this.vertices = new ABB<>();
    }

    /**
     * Retorna o nome do grafo (string), caso seja necessário em outras
     * classes/sistemas
     * 
     * @return O nome do grafo (uma string)
     */
    public String nome() {
        return this.nome;
    }

    public void carregar(String nomeArquivo) {

    }

    /**
     * Metodo responsavel por salvar os dados do grafo em um arquivo de texto.
     * @param nomeArquivo
     * @return void
     * @throws IOException
     */
    public void salvar(String nomeArquivo) {
        int quantidadeVertices = this.vertices.size();
        StringBuilder verticesGrafo = new StringBuilder();
        List<String> arestasGrafo = new ArrayList<String>();
        FileWriter arquivo;

        try {
            arquivo = new FileWriter(nomeArquivo);
            for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
                verticesGrafo.append(vertice.getId() + ";");
                if (vertice.getArestas().size() > 0) {
                    for (Aresta arestaVertice : vertice.getArestas().allElements(new Aresta[vertice.getArestas().size()]))
                        arestasGrafo.add(String.format("%i;%i;i%", vertice.getId(), arestaVertice.destino(),
                                arestaVertice.peso()));
                }
            }

            arquivo.write(quantidadeVertices);
            arquivo.write(vertices.toString());
            for (String aresta : arestasGrafo)
                arquivo.write(aresta);

            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adiciona um vértice com o id especificado. Ignora a ação e retorna false se
     * já existir
     * um vértice com este id
     * 
     * @param id O identificador do vértice a ser criado/adicionado
     * @return TRUE se houve a inclusão do vértice, FALSE se já existia vértice com
     *         este id
     */
    public boolean addVertice(int id) {
        Vertice novo = new Vertice(id);
        return this.vertices.add(id, novo);
    }

    public Vertice removeVertice(int id) {
        return null;
    }

    public Vertice existeVertice(int idVertice) {
        return null;
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo, caso os dois vértices
     * existam no grafo.
     * Caso a aresta já exista, ou algum dos vértices não existir, o comando é
     * ignorado e retorna FALSE.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     * @param peso    Peso da aresta
     * @return TRUE se foi inserida, FALSE caso contrário
     */
    public boolean addAresta(int origem, int destino, int peso) {
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null) {
            adicionou = (saida.addAresta(destino, peso) && chegada.addAresta(origem, peso));
        }
        return adicionou;
    }

    public Aresta removeAresta(int origem, int destino) {
        for(Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            if(vertice.getId() == origem) {
                return vertice.removeAresta(destino);
            }
        }

        return null;
    }

    public Aresta existeAresta(int verticeA, int verticeB) {
        for(Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            if(vertice.getId() == verticeA) {
                return vertice.existeAresta(verticeB);
            }
        }

        return null;
    }

    public boolean completo() {
        for(Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            if(vertice.getArestas().size() != this.vertices.size() - 1)
                return false;
        }

        return true;
    }

    public Grafo subGrafo(Lista<Integer> vertices) {
        Grafo subgrafo = new Grafo("Subgrafo de " + this.nome);

        return subgrafo;
    }

    public int tamanho() {
        return Integer.MIN_VALUE;
    }

    public int ordem() {
        return Integer.MIN_VALUE;
    }

}
