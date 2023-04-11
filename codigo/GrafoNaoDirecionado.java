public class GrafoNaoDirecionado extends GrafoMutavel {
    GrafoNaoDirecionado(String nome) {
        super(nome);
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
}
