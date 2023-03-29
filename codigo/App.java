public class App {
    public static void main(String[] args) throws Exception {
        Grafo g = Grafo.grafoCompleto(9);

        g.salvar("dudu.txt");
        System.out.println(g.completo());
    }
}
