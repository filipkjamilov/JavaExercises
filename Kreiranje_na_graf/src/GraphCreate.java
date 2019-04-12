import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Graph {

    int num_nodes; // broj na jazli
    Character nodes[]; // informacija vo jazlite
    int adjMat[][]; // matrica na sosednost

    public Graph(int num_nodes) {

        this.num_nodes = num_nodes;

        nodes = new Character[num_nodes];

        adjMat = new int[num_nodes][num_nodes];

        //i + 'A'

        for(int i=0;i<this.num_nodes;i++){
            for(int j=0;j<this.num_nodes;j++){
                adjMat[i][j]=0;
                nodes[i] = (char)(i + 'A');
            }

        }

    }
    int adjacent(int x,int y)
    { // proveruva dali ima vrska od jazelot so indeks x do jazelot
        //so indeks y
        return (adjMat[x][y]!=0)?1:0;
    }
    void addEdge(int x,int y)
    { // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y]=1;
        adjMat[y][x]=1;
    }
    void deleteEdge(int x,int y)
    {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y]=0;
        adjMat[y][x]=0;
    }
    Character get_node_value(int x)
    { // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }
    void set_node_value(int x, char a)
    { // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x]=a;
    }
    public int getNum_nodes() {
        return num_nodes;
    }
    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }
    public void print(){
        for(int i=0;i<this.num_nodes;i++){
            for(int j=0;j<this.num_nodes;j++){
                System.out.print(adjMat[i][j]+" ");
            }
            System.out.println();
        }
    }
}


public class GraphCreate{
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String komanda = br.readLine();
        String[] pom = komanda.split(" ");
        Graph graf= new Graph(Integer.parseInt(pom[1]));


        for(int i=0;i<N-1;i++){
            komanda = br.readLine();
            pom = komanda.split(" ");

            if(pom[0].equals("ADDEDGE")){

                //System.out.println(pom[1]+"   "+pom[2]);
                graf.addEdge(Integer.parseInt(pom[1]), Integer.parseInt(pom[2]));

            }else if(pom[0].equals("DELETEEDGE")){

                graf.deleteEdge(Integer.parseInt(pom[1]), Integer.parseInt(pom[2]));

            }else if(pom[0].equals("ADJACENT")){

                System.out.println(graf.adjacent(Integer.parseInt(pom[1]), Integer.parseInt(pom[2])));

            }else if(pom[0].equals("PRINTMATRIX")){

                    graf.print();

            }else if(pom[0].equals("PRINTNODE")){

                System.out.println(graf.get_node_value(Integer.parseInt(pom[1])));

            }


        }


    }
}