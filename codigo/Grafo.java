import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
     * Retorna um grafo completo de ordem n, com todos os vértices conectados entre
     * si
     * 
     * @param ordem
     * @return Grafo
     */
    public static Grafo grafoCompleto(int ordem) {
        Grafo grafo = new Grafo("graph");

        for (int i = 0; i < ordem; i++) {
            Vertice vertice = new Vertice(i);
            grafo.vertices.add(i, vertice);

            for (int j = 0; j < ordem; j++) {
                if (i != j) {
                    Aresta aresta = new Aresta(i, j);
                    vertice.getArestas().add(j, aresta);
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


/**
     * Metodo responsavel por carregar os dados do grafo de um arquivo de texto.
     * 
     * @param nomeArquivo
     * @return void
     * @throws IOException
     */
    public void carregar(String nomeArquivo) {
        File arquivo;
        Vertice vertice;
        try {
            arquivo = new File(nomeArquivo);

            Scanner scanner = new Scanner(arquivo);

            String idVertices = scanner.nextLine();
            String[] subIds = idVertices.split(";");
            for (String id : subIds) {
                vertice = new Vertice(Integer.parseInt(id));
                this.vertices.add(Integer.parseInt(id), vertice);
            }
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] subString = linha.split(";");
                vertice = vertices.find(Integer.parseInt(subString[0]));
                vertice.addAresta(Integer.parseInt(subString[1]), Integer.parseInt(subString[2]));
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo responsavel por salvar os dados do grafo em um arquivo de texto.
     * 
     * @param nomeArquivo
     * @return void
     * @throws IOException
     */
    public void salvar(String nomeArquivo) {
        StringBuilder verticesGrafo = new StringBuilder();
        StringBuilder textoCaminhos = new StringBuilder();
        FileWriter arquivo;

        try {
            arquivo = new FileWriter(nomeArquivo);
            for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
                verticesGrafo.append(vertice.getId() + ";");
                if (vertice.getArestas().size() > 0) {
                    for (Aresta arestaVertice : vertice.getArestas()
                            .allElements(new Aresta[vertice.getArestas().size()]))
                        textoCaminhos.append(String.format("%o;%o;%o\n", vertice.getId(), arestaVertice.destino(),
                                arestaVertice.peso()));
                }
            }
            textoCaminhos.deleteCharAt(textoCaminhos.length() - 1);
            arquivo.write(verticesGrafo.toString() + "\n");
            arquivo.write(textoCaminhos.toString());
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

    /**
     * Remove um vértice com o id especificado. Retorna nulo caso o vértice não
     * exista/não possa ser removido.
     * 
     * @param id O identificador do vértice a ser removido
     * @return Vertice (removido) / null (não removido)
     */
    public Vertice removeVertice(int id) {
        Vertice vertice = this.vertices.find(id);
        if (vertice != null) {
            for (Vertice vertice2 : this.vertices.allElements(new Vertice[this.vertices.size()])) {
                if (vertice2.getArestas().find(id) != null)
                    vertice2.getArestas().remove(id);
            }
            this.vertices.remove(id);
            return vertice;
        } else
            return null;
    }

    /**
     * Retorna o vértice com o id especificado. Retorna nulo caso o vértice não
     * exista.
     * 
     * @param id O identificador do vértice a ser conferido.
     * @return Vertice (encontrado) / null (não encontrado)
     */

    public Vertice existeVertice(int idVertice) {
        Vertice vertice = this.vertices.find(idVertice);
        if (vertice != null)
            return vertice;
        else
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
        return adicionarAresta(origem, destino, peso);
    }

    private boolean adicionarAresta(int origem, int destino, int peso) {
        boolean adicionou = false;
        Vertice saida = this.existeVertice(origem);
        Vertice chegada = this.existeVertice(destino);
        if (saida != null && chegada != null) {
            adicionou = (saida.addAresta(destino, peso) && chegada.addAresta(origem, peso));
        }
        return adicionou;
    }

    public boolean addAresta(int origem, int destino) {
        return adicionarAresta(origem, destino, -1);
    }

    /**
     * Remove uma aresta entre dois vértices do grafo, caso os dois vértices existam
     * 
     * @param origem
     * @param destino
     * @return Aresta
     * @return null
     */
    public Aresta removeAresta(int origem, int destino) {
        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            if (vertice.getId() == origem) {
                return vertice.removeAresta(destino);
            }
        }

        return null;
    }

    /**
     * Verifica se existe uma aresta entre dois vértices do grafo
     * 
     * @param verticeA
     * @param verticeB
     * @return Aresta
     * @return null
     */
    public Aresta existeAresta(int verticeA, int verticeB) {
        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            if (vertice.getId() == verticeA) {
                return vertice.existeAresta(verticeB);
            }
        }

        return null;
    }

    /**
     * Retorna se um grafo é completo ou não
     * 
     * @return boolean
     */
    public boolean completo() {
        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            if (vertice.getArestas().size() != this.vertices.size() - 1) {
                return false;
            }
        }

        return true;
    }

    public Grafo subGrafo(Lista<Integer> vertices) {
        Grafo subgrafo = new Grafo("Subgrafo de " + this.nome);
        for (Integer vertice : vertices.allElements(new Integer[vertices.size()])) {
            subgrafo.addVertice(vertice);
        }

        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            for (Aresta aresta : vertice.getArestas().allElements(new Aresta[vertice.getArestas().size()])) {
                if (subgrafo.existeVertice(aresta.destino()) != null) {
                    subgrafo.addAresta(vertice.getId(), aresta.destino(), aresta.peso());
                }
            }
        }

        return subgrafo;
    }

    /**
     * Retorna o tamanho do grafo
     * 
     * @return int
     */
    public int tamanho() {
        int tamanho = 0;
        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            tamanho += vertice.getArestas().size();
        }

        return tamanho / 2;
    }

    /**
     * Retorna a ordem do grafo
     * 
     * @return int
     */
    public int ordem() {
        return this.vertices.size();
    }

}
