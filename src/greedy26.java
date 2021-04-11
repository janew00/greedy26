import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class greedy26 {
    public static void main(String[] args) throws Exception {
        
        long startTime = 0;
        long endTime = 0;
        long diffTime = 0;
        startTime = System.currentTimeMillis();
        
        Scanner sc = new Scanner(System.in);
        System.out.print("배낭에 넣을 물건의 개수를 입력하세요: ");
        int n = sc.nextInt(); // 물건 개수
        System.out.print("배낭에 들어갈 수 있는 최대 무게를 입력하세요: ");
        int c = sc.nextInt(); // 배낭의 최대 무게

        int[] w = new int[n]; // 물건의 무게
        int[] v = new int[n]; // 물건의 가치
        System.out.println("배낭에 넣을 각 물건의 무게와 가치를 입력하세요: ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        double maxValue = backpack(w, v, c); // 배낭의 최대 가치
        System.out.println("배낭에 담긴 물건들의 가치 합 => " + maxValue);
        
        endTime   = System.currentTimeMillis();
        diffTime  = endTime - startTime;
        System.out.println("실행 시간 = "+ diffTime + "ms");
    }
    private static double backpack(int[] w, int[] v, int c) {
        stuff[] thing = new stuff[w.length]; // 물건 개수 크기의 stuff 객체 배열 생성

        // 초기화, rate값 계산 (stuff 생성자)
        for (int i = 0; i < w.length; i++) {
            thing[i] = new stuff(w[i], v[i], i);
        }
        // rate를 기준으로 내림차순 정렬
        Arrays.sort(thing, new Comparator<stuff>() {
            @Override
            public int compare(stuff a, stuff b)
            {
                return b.rate.compareTo(a.rate); // 정렬 기준을 rate로
            }
        });

        double totalValue = 0; // 총 가치를 저장할 변수
        // 가치가 높은 순서로 물건부터 넣기 시작
        for (stuff i : thing) {
            int tWei = (int)i.w;
            int tVal = (int)i.v;

            // 물건을 통채로 넣을 수 있는 경우
            if (c - tWei >= 0) {
                c = c - tWei; // 배낭의 최대무게에서 해당 물건의 무게를 뺌
                totalValue += tVal; // 총 가치에 해당 물건의 가치를 더함
            }
            // 물건 하나를 통채로 넣지 못할 경우
            else {
                double stuff_part = ((double)c / (double)tWei); // 물건을 쪼갬 (비율)
                c = (int)(c - (tWei * stuff_part)); // 배낭의 최대무게에서 쪼갠 물건의 무게만큼 뺌
                totalValue += (tVal * stuff_part); // 총 가치에 쪼갠 물건의 가치만큼 더함
                break;
            }
        }
        return totalValue;
    }
    // stuff 클래스
    static class stuff {
        Double rate; // 단위 무게 당 가치
        double w, v, a; // 무게, 가치, 인덱스

        public stuff(int w, int v, int a) {
            this.w = w;
            this.v = v;
            this.a = a;
            rate = (double) v / (double) w; // 단위 무게 당 가치 계산
        }
    }
}
