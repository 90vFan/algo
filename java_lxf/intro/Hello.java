public class Hello {
    public static void main(String[] args) {
        int x = 1;
        System.out.println(x);

        for (String arg : args) {
            if ("-version".equals(arg)) {
                System.out.println("Version 1.0");
            }
        }
    }
}