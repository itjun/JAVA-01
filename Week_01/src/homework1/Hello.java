package homework1;

public class Hello {
    public static final boolean a = true;
    protected static final byte b = 8;
    private static final short c = 32767;
    public final int d = -1024;

    public String test(Long l, String s) {
        return s + l;
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        int x = b + c;
        if (x > 0) {
            for (int i = 0; i < b; i++) {
                double dx = 0.5;
                float fx = 0.2F;
                double y = x + dx - fx;
                y = y * b;
                y = c / y;
                Long l = Long.parseLong(y + "");
                System.out.println("this is " + hello.test(l, "abc-"));
            }
        }
    }
}
