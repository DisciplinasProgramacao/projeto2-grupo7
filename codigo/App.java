public class App {
    public static void main(String[] args) throws Exception {

        GrafoCompleto g = new GrafoCompleto(4);
        System.out.println("ordem do grafo = " + g.ordem());
        System.out.println("tamanho do grafo = " + g.tamanho());
        for (Vertice vertice : g.vertices.allElements(new Vertice[g.vertices.size()])) {
            System.out.println("grau do vertice " + vertice.getId() + ": " + vertice.grau());
            if (vertice.grau() != g.ordem() - 1) {
                System.out.println("FALHOU");
            }
        }
        System.out.println(g.completo());
    }
}
