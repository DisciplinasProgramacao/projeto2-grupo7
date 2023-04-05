public class App {
    public static void main(String[] args) throws Exception {
        // Grafo g = Grafo.grafoCompleto(2);
        Grafo grafo = new Grafo("");
        // grafo.addVertice(1);
        // grafo.addVertice(2);
        // grafo.addVertice(3);
        // grafo.addAresta(1, 2);
        // grafo.addAresta(1, 3);
        // grafo.addAresta(3, 2);
        // grafo.salvar("grafo.txt");
        // grafo.carregar("grafo.txt");
        // grafo.salvar("dudus.txt");
        System.out.println(grafo.completo());
    }
}
