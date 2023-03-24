public class App {
    public static void main(String[] args) throws Exception {
       Grafo g = Grafo.grafoCompleto(4);

       if(g == null) {
           System.out.println("Grafo nulo");
       } else {
           System.out.println("Grafo não nulo");
       }
    }
}
