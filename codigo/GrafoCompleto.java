public class GrafoCompleto extends Grafo {
    GrafoCompleto(int ordem) {
        super("grafoCompleto");
        
        for (int i = 0; i < ordem; i++) { 
                   
            vertices.add(i,new Vertice(i));
        }

        for (int i = 0; i < ordem; i++) {
            Vertice atual = this.existeVertice(i);
            for (int j = i + 1; j < ordem; j++) {
                atual.addAresta(j);
            }
        }
       
    }

}
