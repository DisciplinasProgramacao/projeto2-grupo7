import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GrafoMutavel extends Grafo {
    GrafoMutavel(String nome) {
        super(nome);
    }

    /**
     * Adiciona um vértice com o id especificado. Ignora a ação e retorna false se
     * já existir
     * um vértice com este id
     * 
     * @param id O identificador do vértice a ser criado/adicionado
     * @return TRUE se houve a inclusão do vértice, FALSE se já existia vértice com
     *         este id
     */
    public boolean addVertice(int id) {
        Vertice novo = new Vertice(id);
        return this.vertices.add(id, novo);
    }

    /**
     * Remove um vértice com o id especificado. Retorna nulo caso o vértice não
     * exista/não possa ser removido.
     * 
     * @param id O identificador do vértice a ser removido
     * @return Vertice (removido) / null (não removido)
     */
    public Vertice removeVertice(int id) {
        Vertice vertice = this.vertices.find(id);
        if (vertice != null) {
            for (Vertice vertice2 : this.vertices.allElements(new Vertice[this.vertices.size()])) {
                if (vertice2.getArestas().find(id) != null)
                    vertice2.getArestas().remove(id);
            }
            this.vertices.remove(id);
            return vertice;
        } else
            return null;
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

    /**
     * Metodo responsavel por carregar os dados do grafo de um arquivo de texto.
     * 
     * @param nomeArquivo
     * @return void
     * @throws IOException
     */
    public void carregar(String nomeArquivo) {
        File arquivo;
        Vertice vertice;
        try {
            arquivo = new File(nomeArquivo);
            if (!arquivo.exists())
                return;

            Scanner scanner = new Scanner(arquivo);

            if (!scanner.hasNext()) {
                scanner.close();
                return;
            }

            String idVertices = scanner.nextLine();
            String[] subIds = idVertices.split(";");
            for (String id : subIds) {
                vertice = new Vertice(Integer.parseInt(id));
                this.vertices.add(Integer.parseInt(id), vertice);
            }
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] subString = linha.split(";");
                vertice = vertices.find(Integer.parseInt(subString[0]));
                if (vertice != null)
                    vertice.addAresta(Integer.parseInt(subString[1]), Integer.parseInt(subString[2]));
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo responsavel por salvar os dados do grafo em um arquivo de texto.
     * 
     * @param nomeArquivo
     * @return void
     * @throws IOException
     */
    public void salvar(String nomeArquivo) {
        StringBuilder verticesGrafo = new StringBuilder();
        StringBuilder textoCaminhos = new StringBuilder();
        FileWriter arquivo;

        try {
            arquivo = new FileWriter(nomeArquivo);
            for (Vertice vertice : this.vertices.allElements(new Vertice[this.vertices.size()])) {
                verticesGrafo.append(vertice.getId() + ";");
                if (vertice.getArestas().size() > 0) {
                    for (Aresta arestaVertice : vertice.getArestas()
                            .allElements(new Aresta[vertice.getArestas().size()]))
                        textoCaminhos.append(String.format("%o;%o;%s\n", vertice.getId(), arestaVertice.destino(),
                                ("" + arestaVertice.peso())));
                }
            }
            if (textoCaminhos.length() > 0)
                textoCaminhos.deleteCharAt(textoCaminhos.length() - 1);

            arquivo.write(verticesGrafo.toString() + "\n");
            arquivo.write(textoCaminhos.toString());
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object BuscaInProfundidade(int i) {
        return null;
    }

}
