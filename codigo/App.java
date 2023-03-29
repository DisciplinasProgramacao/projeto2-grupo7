public class App {
    public static void main(String[] args) throws Exception {
        Grafo g = Grafo.grafoCompleto(2);

        g.salvar("dudu.txt");
        g.carregar("dudu.txt");
        System.out.println(g.completo());
    }
}
