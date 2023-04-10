import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Fila<T> {

    private ArrayList<T> elementos;

    public Fila() {
        this.elementos = new ArrayList<T>();
    }

    public void enfileirar(T elemento) {
        this.elementos.add(elemento);
    }

    public T desenfileirar() {
        if (this.estaVazia()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return this.elementos.remove(0);
    }

    public boolean estaVazia() {
        return this.elementos.size() == 0;
    }

    public int tamanho() {
        return this.elementos.size();
    }

    public T primeiro() {
        if (this.estaVazia()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return this.elementos.get(0);
    }

    public T ultimo() {
        if (this.estaVazia()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return this.elementos.get(this.tamanho() - 1);
    }

}

