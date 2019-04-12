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

        return Math.abs(key.hashCode()) % buckets.length;
        // Napishete ja vie HASH FUNKCIJATA
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

public class KumanovskiDijalekt {
    public static void main (String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in));
        int N = Integer.parseInt(br.readLine());

        CBHT<String, String> tabela = new CBHT<String, String>(2*N);

        for(int i=0;i<N;i++){

            String line = br.readLine();
            String [] pom = line.split(" ");

            tabela.insert(pom[0], pom[1]);
        }

        String tekst=br.readLine();

        //Vasiot kod tuka

        String zbor="";

        String recenica = "";

        if(N==0){
            System.out.println(tekst);
            return;
        }
        for (int i=0; i<tekst.length(); i++){
            if(tekst.charAt(i)!='.' && tekst.charAt(i)!=',' && tekst.charAt(i)!=' '){
                zbor+=tekst.charAt(i);//bija

            }else {

                if(zbor!=""){

                    if(zbor.charAt(0) >= 65 && zbor.charAt(0) <= 90){

                        //String output = zbor.substring(0, 1).toLowerCase() + zbor.substring(1);
                        String output = zbor.toLowerCase();

                        //zbor = output.replaceAll("\\s+","");
                        SLLNode<MapEntry<String, String>> index = tabela.search(output);

                        if(index!=null){

                            String input = index.element.value;
                            String out = input.substring(0, 1).toUpperCase() + input.substring(1);
                            recenica+= out;
                        }else {
                            recenica+= zbor;
                        }

                    }else {
                        SLLNode<MapEntry<String, String>> index = tabela.search(zbor);

                        if(index!=null){
                            String input = index.element.value;
                            recenica+=input;
                        }else {
                            recenica+= zbor;
                        }
                    }




                    //System.out.println(zbor);
                }
                if(tekst.charAt(i)=='.'){
                    recenica+=".";
                }else if(tekst.charAt(i)==','){
                    recenica+=",";
                }else if(tekst.charAt(i)=='!'){
                    recenica+="!";
                }else if(tekst.charAt(i)=='?'){
                    recenica+="?";
                }else if(tekst.charAt(i)==' '){
                    recenica+=" ";
                }



                zbor = "";

            }

            //System.out.println(i);
        }

        System.out.println(recenica);



    }
}
