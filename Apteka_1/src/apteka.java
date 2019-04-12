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

    // Each MapEntry object is a pair consisting of a imeLek (a Comparable
    // object) and a value (an arbitrary object).
    K imeLek;
    E value;

    public MapEntry (K imeLek, E val) {
        this.imeLek = imeLek;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.imeLek.compareTo(other.imeLek);
    }

    public String toString () {
        return "<" + imeLek + "," + value + ">";
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

    private int hash(K imeLek) {
        // Translate imeLek to an index of the array buckets.
        return Math.abs(imeLek.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetimeLek) {
        // Find which if any node of this CBHT contains an entry whose imeLek is
        // equal
        // to targetimeLek. Return a link to that node (or null if there is none).
        int b = hash(targetimeLek);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetimeLek.equals(((MapEntry<K, E>) curr.element).imeLek))
                return curr;
        }
        return null;
    }

    public void insert(K imeLek, E val) {		// Insert the entry <imeLek, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(imeLek, val);
        int b = hash(imeLek);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (imeLek.equals(((MapEntry<K, E>) curr.element).imeLek)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K imeLek) {
        // Delete the entry (if any) whose imeLek is equal to imeLek from this CBHT.
        int b = hash(imeLek);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (imeLek.equals(((MapEntry<K,E>) curr.element).imeLek)) {
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
/* Sample input
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
forteo 1 0 0
FORVITAC 1 0 150
CHIROCAINE 1 0 10
BRONLES 1 0 0
BELOGENT 0 143 30
BEDOXIN 1 0 100
HYDROCYKLIN 20 0 113 20

Farmacevt vnes

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
Napravena naracka*/

class lek{

    public String ime;
    public int lista;
    public int cena;
    public int zaliha;

    public lek(String ime, int lista, int cena, int zaliha) {
        this.ime = ime.toUpperCase() + "- Lek od Alkaloid";
        this.lista = lista;
        this.cena = cena;
        this.zaliha = zaliha;
    }

    public int getZaliha() {
        return zaliha;
    }

    public void setZaliha(int zaliha) {
        this.zaliha = zaliha;
    }
}

class imeLek implements Comparable<imeLek> {

    public String ime;

    public imeLek(String ime) {
        this.ime = ime.toUpperCase();
    }
    @Override
    public int hashCode(){
        return (29*(29*(29*0+ime.charAt(0))+ime.charAt(1))+ime.charAt(2))%102780;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        imeLek imeLek = (imeLek) obj;
        return ime.equals(imeLek.ime);
    }

    @Override
    public int compareTo(imeLek o) {
        if (o == null)
            return 1;
        return ime.compareTo(o.ime);
    }
}



public class apteka {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<imeLek, lek> tabela = new CBHT<imeLek, lek>(2*N);

        for(int i = 0; i<N; i++){

            String line = br.readLine();
            String pom[] = line.split(" ");

            lek object = new lek(pom[0], Integer.parseInt(pom[1]),Integer.parseInt(pom[2]),Integer.parseInt(pom[3]));

            imeLek samoimenalek = new imeLek(pom[0]);
            tabela.insert(samoimenalek, object);

        }

        while (true){
            String line = br.readLine();
            if(line.equals("KRAJ")){
                break;
            }
            int kolku = Integer.parseInt(br.readLine());

            imeLek pom = new imeLek(line);
            SLLNode<MapEntry<imeLek, lek>> jazol = tabela.search(pom);
            if(jazol!=null){
                int zaliha = jazol.element.value.zaliha;
                if(kolku<=zaliha){

                    System.out.println(jazol.element.value.ime);

                    int dali = jazol.element.value.lista;
                    if(dali==1){
                        System.out.println("POS");
                    }else {
                        System.out.println("NEG");
                    }
                    System.out.println(jazol.element.value.cena);
                    System.out.println(jazol.element.value.zaliha);
                    System.out.println("Napravena naracka");

                    jazol.element.value.setZaliha(zaliha-kolku);


                }else {
                    System.out.println("Nema tolku na zaliha.");
                }
            }else {
                System.out.println("Nema takov lek");
            }

        }

    }
}
