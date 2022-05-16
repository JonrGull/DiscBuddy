package discbuddy;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) {
        sayGreeting("Jon");
    }

    public static void sayGreeting(String name) {
        System.out.println("Hello, " + name + "!");
    }
}



