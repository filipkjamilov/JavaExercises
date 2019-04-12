import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

// Implementacija na redica

interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();

    // Vrakja true ako i samo ako redicata e prazena.

    public int size();

    // Ja vrakja dolzinata na redicata.

    public E peek();

    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();

    // Ja prazni redicata.

    public void enqueue(E x);

    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue() {
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear() {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)
                rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

class Graph<E> {

    int num_nodes; // broj na jazli
    E nodes[]; // informacija vo jazlite - moze i ne mora?
    int adjMat[][]; // matrica na sosednost
    public boolean poseteni[];
    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = (E[]) new Object[num_nodes];
        adjMat = new int[num_nodes][num_nodes];
        poseteni = new boolean[num_nodes];
        for (int i = 0; i < this.num_nodes; i++){
            for (int j = 0; j < this.num_nodes; j++) {
                adjMat[i][j] = 0;
            }}
    }

    int adjacent(int x, int y) { // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjMat[x][y] != 0) ? 1 : 0;
    }

    void addEdge(int x, int y) { // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y] = 1;

    }

    void deleteEdge(int x, int y) {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y] = 0;
        adjMat[y][x] = 0;
    }

    E get_node_value(int x) { // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    void set_node_value(int x, E a) { // ja postavuva informacijata vo jazelot
        // so indeks na a
        nodes[x] = a;
    }

    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < num_nodes; i++)
            ret += nodes[i] + " ";
        ret += "\n";
        for (int i = 0; i < num_nodes; i++) {
            ret += nodes[i] + " ";
            for (int j = 0; j < num_nodes; j++)
                ret += adjMat[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

    public void printMatrix() {
        for (int i = 0; i < num_nodes; i++) {
            for (int j = 0; j < num_nodes; j++)
                System.out.print(adjMat[i][j] + " ");
            System.out.println();
        }
    }




    public int sendFishes(int pocetno) {
        int brEzera = 0;
        boolean [] poseteni = new boolean[num_nodes];
        for (int i=0 ;i<num_nodes; i++){
            if(adjacent(pocetno, i)==1){
                poseteni[i] = true;
                brEzera++;
                if(!poseteni[i]){
                    brEzera+=sendFishes(i);
                }


            }else{
                poseteni[i]=false;
            }
        }



        return brEzera;

    }
}

public class DedoMrazIRibite {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Graph<Integer> graph = new Graph<>(N);

        for(int i=0; i<M; i++){
            String line = br.readLine();
            String []pom = line.split(" ");

            graph.addEdge(Integer.parseInt(pom[0]),Integer.parseInt(pom[1]));

        }

        int ezero = Integer.parseInt(br.readLine());

        System.out.println(graph.sendFishes(ezero)-1);
        System.out.println("Srekna Nova Godina");

    }

}
