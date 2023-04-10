public class App {
    public static void main(String[] args) throws Exception {
        // Grafo g = Grafo.grafoCompleto(2);
        // Grafo grafo = new Grafo("");
        // grafo.addVertice(1);
        // grafo.addVertice(2);
        // grafo.addVertice(3);
        // grafo.addAresta(1, 2);
        // grafo.addAresta(1, 3);
        // grafo.addAresta(3, 2);
        // grafo.salvar("grafo.txt");
        // grafo.carregar("grafo.txt");
        // grafo.salvar("dudus.txt");
        // System.out.println(grafo.completo());
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
        System.out.println(resultado.equals(esperado));
        
    }
}
