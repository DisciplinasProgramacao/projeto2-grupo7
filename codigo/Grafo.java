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
    protected ABB<Vertice> vertices;

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
            if (vertice.grau() != this.ordem() - 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Método que cria um subgrafo a partir de uma lista de vértices
     * 
     * @param Lista<Integer> - vertices
     * @return Grafo - subgrafo
     */
    public Grafo subGrafo(Lista<Integer> vertices) {
        GrafoMutavel subgrafo = new GrafoMutavel("Subgrafo de " + this.nome);
        Integer[] verticesArray = vertices.allElements(new Integer[vertices.size()]);
        for (Integer vertice : verticesArray) {
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

    public static Grafo grafoCompleto(int ordem) {
        return new GrafoCompleto(ordem);
    }

    /**
     * Retorna o tamanho do grafo
     * 
     * @return int
     */
    public int tamanho() {
        int quantidadeArestas = 0;
        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            quantidadeArestas += vertice.getArestas().size();
        }

        return (quantidadeArestas / 2)+ this.ordem();
    }

    /**
     * Retorna a ordem do grafo
     * 
     * @return int
     */
    public int ordem() {
        return this.vertices.size();
    }

    /**
     * 
     * @param idVerticeInicio
     * @return
     */
    public Grafo bfs(int idVerticeInicio) {
        GrafoMutavel visita = new GrafoMutavel("Visita do " + this.nome);
    
        // Cria uma fila para armazenar os vértices a serem visitados
        Fila<Vertice> fila = new Fila<>();
    
        // Encontra o vértice de partida
        Vertice verticeInicio = this.existeVertice(idVerticeInicio);
        if (verticeInicio == null) {
            return null;
        }
    
        // Adiciona o vértice inicial na fila e marca como visitado
        fila.enfileirar(verticeInicio);
        verticeInicio.setVisitado(true);
        visita.addVertice(verticeInicio.getId());
    
        // Enquanto a fila não estiver vazia, processa cada vértice
        while (!fila.estaVazia()) {
            // Remove o próximo vértice da fila
            Vertice verticeAtual = fila.desenfileirar();
    
            // Processa todas as arestas do vértice atual
            for (Aresta aresta : verticeAtual.getArestas().allElements(new Aresta[verticeAtual.getArestas().size()])) {
                Vertice verticeAdjacente = this.existeVertice(aresta.destino());
    
                // Verifica se o vértice adjacente já foi visitado
                if (verticeAdjacente != null && !verticeAdjacente.visitado()) {
                    // Marca o vértice adjacente como visitado e adiciona na fila
                    verticeAdjacente.setVisitado(true);
                    fila.enfileirar(verticeAdjacente);
    
                    // Adiciona a aresta no grafo de visita
                    visita.addAresta(verticeAtual.getId(), verticeAdjacente.getId(), aresta.peso());
                    visita.addVertice(verticeAdjacente.getId());
                }
            }
        }
    
        // Marca todos os vértices como não visitados
        for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            vertice.limparVisita();
        }
    
        return visita;
    }

    /**
     * Implementação da busca em profundidade (DFS).
     * 
     * @param idVerticeInicio O identificador do vértice de início da busca.
     * @return Um subgrafo com os vértices alcançáveis a partir do vértice de
     *         início.
     */
    public Grafo dfs(int idVerticeInicio) {
        GrafoMutavel subgrafo = new GrafoMutavel("DFS de " + this.nome);

        // Marca todos os vértices como não visitados.
        for (Vertice v : this.vertices.allElements(new Vertice[this.vertices.size()])) {
            v.limparVisita();
        }

        // Inicia a busca a partir do vértice de início.
        dfsVisit(this.existeVertice(idVerticeInicio), subgrafo);

        return subgrafo;
    }

    /**
     * Função auxiliar para realizar a visita de um vértice e seus vizinhos na busca em profundidade.
     * 
     * @param v O vértice a ser visitado.
     * @param subgrafo O subgrafo em construção.
     */
    private void dfsVisit(Vertice v, GrafoMutavel subgrafo) {
        v.visitar();
        subgrafo.addVertice(v.getId());
        
        for (Aresta a : v.getArestas().allElements(new Aresta[v.getArestas().size()])) {
            int destino = a.destino();
            Vertice vDestino = this.existeVertice(destino);
            
            if (!vDestino.visitado()) {
                subgrafo.addAresta(v.getId(), destino, a.peso());
                dfsVisit(vDestino, subgrafo);
            }
        }
    }
}
