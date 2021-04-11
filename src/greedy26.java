import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class greedy26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("배낭에 넣을 물건의 개수를 입력하세요: ");
        int n = sc.nextInt();
        System.out.print("배낭에 들어갈 수 있는 최대 무게를 입력하세요: ");
        int c = sc.nextInt();

        int[] w = new int[n];
        int[] v = new int[n];
        System.out.println("배낭에 넣을 각 물건의 무게와 가치를 입력하세요: ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        double maxValue = backpack(w, v, c);
        System.out.println("배낭에 담긴 물건들의 가치 합 => " + maxValue);
    }
    private static double backpack(int[] w, int[] v, int c) {
        stuff[] thing = new stuff[w.length];

        for (int i = 0; i < w.length; i++) {
            thing[i] = new stuff(w[i], v[i], i);
        }
        Arrays.sort(thing, new Comparator<stuff>() {
            @Override
            public int compare(stuff a, stuff b)
            {
                return b.rate.compareTo(a.rate);
            }
        });

        double totalValue = 0;
        for (stuff i : thing) {
            int tWei = (int)i.w;
            int tVal = (int)i.v;

            if (c - tWei >= 0) {
                c = c - tWei;
                totalValue += tVal;
            }
            else {
                double stuff_part = ((double)c / (double)tWei);
                totalValue += (tVal * stuff_part);
                c = (int)(c - (tWei * stuff_part));
                break;
            }
        }
        return totalValue;
    }
    static class stuff {
        Double rate;
        double w, v, a;

        public stuff(int w, int v, int a) {
            this.w = w;
            this.v = v;
            this.a = a;
            rate = (double) v / (double) w;
        }
    }
}