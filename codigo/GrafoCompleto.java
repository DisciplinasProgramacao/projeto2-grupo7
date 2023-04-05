public class GrafoCompleto extends Grafo {
    GrafoCompleto(String nome) {
        super(nome);
    }

    /**
     * Retorna um grafo completo de ordem n, com todos os vértices conectados entre
     * si
     * 
     * @param ordem
     * @return Grafo
     */
    public Grafo grafoCompleto(int ordem) {
        GrafoMutavel grafo = new GrafoMutavel("graph");

        for (int i = 0; i < ordem; i++) {
            grafo.addVertice(i);
        }

        for (int i = 0; i < ordem; i++) {
            for (int j = i + 1; j < ordem; j++) {
                grafo.addAresta(i, j);
            }
        }
        return grafo;
    }
}
