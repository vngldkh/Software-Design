public class Main {
    public static void main(String[] args) {
        int size = (int) (Math.random() * 20) + 5;
        int[] a = new int[size];
        int min = 101;
        int max = -101;
        double sum = 0;
        for (int i = 0; i < a.length; ++i) {
            a[i] = (int) (Math.random() * 200) - 100;

            if (a[i] < min) {
                min = a[i];
            }
            if (a[i] > max) {
                max = a[i];
            }

            sum += a[i];
        }

        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\nmin = " + min + "; max = " + max + "; avg = " + sum / a.length);
    }
}