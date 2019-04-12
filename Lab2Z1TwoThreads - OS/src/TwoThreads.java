public class TwoThreads {
    public static class ThreadAB{
        String a;
        ThreadAB(String a) {
            this.a=a;
        }
        public void run(){
            if(this.a.equals("t1")){
                for(int i='a'; i<='z'; i++){
                    System.out.println((char)i);
                }
            }else{
                for(int i=1; i<=26; i++){
                    System.out.println(i);
                }
            }
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            public void run() {
                new ThreadAB("t1").run();
            }
        };

        Thread t2 = new Thread(){
            public void run(){
                new ThreadAB("t2").run();
            }
        };


        try{
            t1.start();
            t2.start();
            //t1.join();
        }catch (Exception a){
           a.printStackTrace();
        }

    }

}