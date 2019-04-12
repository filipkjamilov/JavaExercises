import java.awt.image.ImageConsumer;
import java.util.Arrays;
import java.util.Scanner;

public class ZadacaMaraton {

    public static void main(String[] args) {

        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        Atleticar[] atleticari = new Atleticar[n];

        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;

        input.nextLine();

        for(int i=0;i<n;i++)
        {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i]=new Atleticar(ime,pol,vozrast,vreme,zemja);
        }

        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();

        Maraton m1 = new Maraton(mesto, godina, atleticari);
        System.out.print(m1.toString());

        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.najdobroVreme().toString());
        System.out.println("Ima vkupno " + m1.atleticariOd(zemjaP) + " atleticar/i od " + zemjaP);

    }
}
interface IMaraton{

    Atleticar najdobroVreme();
    int atleticariOd(String s);

}
class Atleticar{
    private String ime;
    private String pol;
    private int vozrast;
    private double vreme;
    private String zemja;

    public Atleticar(String ime, String pol, int vozrast, double vreme, String zemja) {
        this.ime = ime;
        this.pol = pol;
        this.vozrast = vozrast;
        this.vreme = vreme;
        this.zemja = zemja;
    }

    public Atleticar(){}

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public int getVozrast() {
        return vozrast;
    }

    public void setVozrast(int vozrast) {
        this.vozrast = vozrast;
    }

    public double getVreme() {
        return vreme;
    }

    public void setVreme(double vreme) {
        this.vreme = vreme;
    }

    public String getZemja() {
        return zemja;
    }

    public void setZemja(String zemja) {
        this.zemja = zemja;
    }

    @Override
    public String toString() {
        return getIme() + "\n" + getVozrast() + "\n" + getZemja() + "\n" + getVreme() + "\n";
    }
}

class Maraton implements IMaraton{
    private String mesto;
    private int godina;
    private Atleticar []atleticari;

    public Maraton(String mesto, int godina, Atleticar[] atleticari) {
        this.mesto = mesto;
        this.godina = godina;
        this.atleticari = atleticari;
    }
    public Maraton(){}

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public Atleticar[] getAtleticari() {
        return atleticari;
    }

    public void setAtleticari(Atleticar[] atleticari) {
        this.atleticari = atleticari;
    }

    @Override
    public String toString() {

        StringBuilder rez = new StringBuilder();
        rez.append(mesto + "\n" + godina + "\n");
        for(int i=0; i< atleticari.length; i++){
            rez.append(atleticari[i].toString());
        }
        return rez.toString();

    }

    public Atleticar najdobroVreme(){
        Atleticar min = atleticari[0];
        for (int i = 0; i<atleticari.length; i++){
            if(atleticari[i].getVreme() < min.getVreme()){
                min=atleticari[i];
            }
        }
        return min;
    }
    public int atleticariOd(String s){

        int broj=0;
        for (int i=0; i<atleticari.length; i++){
            if(atleticari[i].getZemja().compareTo(s)==0){
                broj++;
            }
        }
        return broj;
    }
}