package Client;


public class Client {

    public static void main(String args[]) {
        int seed = (int) (Math.random() * 10000);
        Generator generator = new Generator(seed);
        System.out.println(generator.getNextRandVal());

    }
}
