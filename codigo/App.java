public class App {
    public static void main(String[] args) throws Exception {

        GrafoCompleto g = new GrafoCompleto(4);
        System.out.println("ordem do grafo = " + g.ordem()); // deve ser 4
        System.out.println("tamanho do grafo = " + g.tamanho()); // deve ser 10 (contar arestas não direcionadas)
        for (Vertice vertice : g.vertices.allElements(new Vertice[g.vertices.size()])) {
            System.out.println("grau do vertice " + vertice.getId() + ": " + vertice.grau()); // deve ser 3 em todos
            if (vertice.grau() != g.ordem() - 1) {
                System.out.println("FALHOU");
            }
        }
        System.out.println(g.completo());

        System.out.println("");
        System.out.println("");
        GrafoDirecionado gd = new GrafoDirecionado("");
        gd.addVertice(0);
        gd.addVertice(1);
        gd.addVertice(2);
        gd.addVertice(3);
        gd.addAresta(0, 1);
        gd.addAresta(0, 2);
        gd.addAresta(0, 3);
        gd.addAresta(1, 0); // deve contar as arestas paralelas individualmente
        gd.addAresta(2, 0);

        System.out.println("ordem do grafo 2 = " + gd.ordem()); // deve ser 4
        System.out.println("tamanho do grafo 2 = " + gd.tamanho()); // deve ser 9
    }
}
