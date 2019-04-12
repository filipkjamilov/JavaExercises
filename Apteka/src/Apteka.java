import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;

    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class Lek{
    public String imeLek;
    public int lista;
    public int cena;
    public int kolicina;

    public Lek(String imeLek, int lista, int cena, int kolicina) {
        this.imeLek = imeLek.toUpperCase();
        this.lista = lista;
        this.cena = cena;
        this.kolicina = kolicina;
    }


}
class LekIme{

    public String ime;

    public LekIme(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public int hashCode() {
        return (29 * (29 * (29 * 0 + ime.charAt(0)) + ime.charAt(1))+ ime.charAt(2)) % 102780;
    }

}

/*
Sample input
20
ACEROLA 0 100 1000
ACIKLOVIR 1 1650 87
ACIPAN 1 300 25
ADIMICIN 0 500 0
VENTOR 1 0 25
VALSACOR 1 1090 10
TYVERB 0 62696 1
ULCODIN 1 47 100
TRICAL 0 0 0
RUBENS 0 2315 0
IBALGIN 1 0 100
HYDROCYKLIN 0 55 10
GENTAMICIN 1 152 90
FORTEO 1 0 0
FORVITAC 1 0 150
CHIROCAINE 1 0 10
BRONLES 1 0 0
BELOGENT 0 143 30
BEDOXIN 1 0 100
HYDROCYKLIN20 0 113 20
hydroCyklinn
2
hydroCyklin20
2
KRAJ
Sample output
Nema takov lek
HYDROCYKLIN20
NEG
113
20
Napravena naracka
*/
public class Apteka {


    public static int hesiraj(String s){
        return (29 * (29 * (29 * 0 + s.charAt(0)) + s.charAt(1))+ s.charAt(2)) % 102780;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        CBHT<Integer, Lek> tabela = new CBHT<Integer, Lek>(N*2);

        for(int i=0; i<N; i++){

            String []line = br.readLine().split(" ");

            Lek lek = new Lek(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3]));

            Integer hesime = hesiraj(line[0]);

            tabela.insert(hesime, lek);

        }

        while (true){
            String line = br.readLine().toUpperCase();
            if(line.equals("KRAJ")){
                break;
            }
            int kolku = Integer.parseInt(br.readLine());

            SLLNode<MapEntry<Integer, Lek>> index = tabela.search(hesiraj(line));
            if(index!=null){
                System.out.println("VRAKJA INDEX"+ index.element.value.imeLek);
                for (SLLNode<MapEntry<Integer, Lek>> i = tabela.search(hesiraj(line)) ; i.succ!=null; i.succ=i.succ.succ){
                    System.out.println("Vleguva vo for i  "+ index + "  i i e "+i);
                    if(i.element.value.imeLek == line){
                        System.out.println("Ima lek");
                    }
                }


            }else {
                System.out.println("Nema takov lek");
            }

        }



    }
}
