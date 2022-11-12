public class Main {
    public static void main(String[] args) {
        for (int i = 2; i <= 100; ++i) {
            if (isSimple(i))
                System.out.print(i + " ");
        }
    }

    private static boolean isSimple(int x) {
        int i = 2;
        while (i * i <= x) {
            if (x % i == 0) {
                return false;
            }
            ++i;
        }
        return true;
    }
}