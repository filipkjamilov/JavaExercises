import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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

class OBHT<K extends Comparable<K>,E> {
    private MapEntry<K,E>[] buckets;
    // buckets[b] is null if bucket b has never been occupied.
// buckets[b] is former if bucket b is formerly-occupied
// by an entry that has since been deleted (and not yet replaced).
    private static final MapEntry former = new MapEntry(null, null);
    private int occupancy = 0;


    public void vrati(int index, int k, String ime){

        String [] ob = buckets[index].value.toString().split(" ");
        int kolku = Integer.parseInt(ob[3]);

        if(kolku<k || ob[0]!=""){
            System.out.println("Nema takov lek");

        }else{
            System.out.println(ob[0]);
            if(Integer.parseInt(ob[1])==0){
                System.out.println("NEG");
            }else{
                System.out.println("POS");
            }
            System.out.println(ob[2]);
            System.out.println(ob[3]);
            System.out.println("Napravena naracka");


        }




    }

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
// Construct an empty OBHT with m buckets.
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }
    private int hash (K key) {
// Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }
    static final int NONE = -1; // ... distinct from any bucket index.
    public int search (K targetKey) {
// Find which if any bucket of this OBHT is occupied by an entry whose key
//// is equal to targetKey. Return the index of that bucket.
        int b = hash(targetKey); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else{ b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length) return NONE;
            }
        }
    }
    public void insert (K key, E val) {
// Insert the entry <key, val> into this OBHT.
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            }
            else{ b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length) return;
            }
        }
    }
    @SuppressWarnings("unchecked")
    public void delete (K key) {
// Delete the entry (if any) whose key is equal to key from this OBHT.
        int b = hash(key); int n_search=0;
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;//(MapEntry<K,E>)former;
                return;
            }
            else {
                b = (b + 1) % buckets.length;
                n_search++;
                if(n_search==buckets.length) return;
            }
        }
    }
    public OBHT<K,E> clone ()
    { //creates copy of the OBHT and returns the copy
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K,E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }


}


class Lek{
    public String ime;
    public int na_koja;
    public int cena;
    public int kolku;

    public Lek(String ime, int na_koja, int cena, int kolku) {
        this.ime = ime;
        this.na_koja = na_koja;
        this.cena = cena;
        this.kolku = kolku;
    }

    @Override
    public String toString() {
        return ime + " " + na_koja + " " + cena + " " + kolku;
    }
}

public class TestHashtable {

    public static int hesiranje(String imee){

        String name = imee.toUpperCase();

        return (29 * (29 * (29 * 0 + name.charAt(0)) + name.charAt(1))+ name.charAt(2) + name.charAt(3)) % 102780;

    }

    public static void main(String[] args) throws IOException {

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(cin.readLine());


        OBHT<Integer, Lek> tabela = new OBHT<>(N);

        for (int i=0; i<N ; i++){

            String line = cin.readLine();
            String [] pom = line.split(" ");

            String ime = pom[0];
            int na_koja = Integer.parseInt(pom[1]);
            int cena = Integer.parseInt(pom[2]);
            int kolku = Integer.parseInt(pom[3]);

            Lek object = new Lek(ime,na_koja,cena,kolku);

            tabela.insert(hesiranje(ime), object);

        }

        while (true){
            String ime = cin.readLine();
            if(ime.compareTo("KRAJ")==0){
                break;
            }

            int kolku = Integer.parseInt(cin.readLine());


            int hesime = hesiranje(ime);
            if(tabela.search(hesime)==-1){
                System.out.println("Nema takov lek");
            }else{

                tabela.vrati(tabela.search(hesime), kolku, ime);

            }

        }
    }



}
