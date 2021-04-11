# greedy26
<p align="center"><img width="30%" alt="이름표" src="https://user-images.githubusercontent.com/80371590/114296062-11fdd180-9ae4-11eb-8832-cf359270f35c.jpg"></p>

### 부분 배낭 문제(Fractional Knapsack Problem)
배낭 문제란 배낭에 담을 수 있는 무게의 최대값이 정해져 있고, 일정한 가치와 무게가 정해져있는 짐들을 배낭에 담을 때, 가치의 합이 최대가 되도록 짐을 고르는 방법을 찾는 문제이다.

배낭 문제는 크게 
> 1) `물건을 쪼갤 수 있는 배낭문제(부분 배낭 문제`)와
> 2) `물건을 쪼갤 수 없는 배낭문제(0/1 배낭 문제)`으로 나뉜다.
> 

이 중, 우리는 그리디 알고리즘으로 해결할 수 있는 `1번(물건을 쪼갤 수 있는 배낭문제(부분 배낭 문제))`를 알아보았다.
 
 
### 부분 배낭의 알고리즘 작동 구조는 다음과 같다.
  
  부분 배낭 문제를 해결하기위한 효율적인 방법은 Greedy 방식을 사용하는 것이다. 
  
- 그리디 접근 방식의 기본 개념은 각 항목의 가치/무게의 비율을 계산하고 이 비율을 기준으로 항목을 정렬한다
- 가장 가치가 높은 비율 항목부터 가방에 담는다. 
- 가방에 담을때 현재 가방의 용량이 담을 항목의 무게보다 크다면 모두 선택하고 항목의 가치를 모두 더한다.
- 그렇지 않다면 현재 가방에 담을 수 있는 용량 만큼 쪼개서 담고 가치는 비율만큼 계산하여 더해주고 루프를 빠져나온다.

### 예시)


<p align="center"><img width="500" alt="예시" src=https://user-images.githubusercontent.com/80371590/114306640-5a82b280-9b17-11eb-865b-d37fd06c21b6.png>
 
 ->  최종적으로 배낭에 담긴 물건들의 리스트와 배낭에 담긴 물건들의 가치의 합을 리턴하여 얻게된다.


### 시간복잡도)

<img width="500" alt="부분 배낭" src=https://user-images.githubusercontent.com/80371590/114297319-e500ed00-9aea-11eb-865b-5a673d8e6a0c.png>
  
  n개의 물건 각각의 단위 무게당 가치를 계산하는 데는 `O(n)` , 
  
  물건 단위 무게당 가치에 대해서 내림차순으로 정렬하기 위해 `O(nlogn)` ,
  
  루프 내부의 수행 `O(1)` ,
  
  배낭에 물건을 부분적으로 더 담을 수 있는지 확인 후, 마지막으로 리턴까지의 시간 `O(1)`
 > ==> `O(n) + O(nlogn) + n × O(1) + O(1) = O(nlogn)`이 걸리게 된다.

### 코드 설명
: 물건의 개수과 배낭의 용량을 입력한 후 각 물건의 무게와 가치를 입력하면 backpack 함수를 통해 배낭에 넣을 수 있는 최대의 가치를 구한다.
- 입력 : 물건의 개수, 배낭의 용량, 각 물건의 무게와 가치
- 출력 : 배낭에 담긴 물건의 총 가치

![result](https://postfiles.pstatic.net/MjAyMTA0MTFfMjEz/MDAxNjE4MTQ2Nzc5ODU1.kQwzLAPijzIhsU2nwWITjVwx_ztXof658rvpNjGGWGwg.Bo7ni4qUMp6UFE2BZgGJ1F8Q4MMm9WPdvElDDAESA68g.PNG.hongsubakgame/image.png?type=w966)

### 소스코드
- stuff 클래스
```java
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
```
- greedy 알고리즘을 이용한 backpack 함수
```java
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
```
- main 함수
```java
public static void main(String[] args) {
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
    }
```
### 실행시간 측정
-> 물건의 개수 외에는 모두 랜덤으로 입력받음
```java
long startTime = 0;
        long endTime = 0;
        long diffTime = 0;
        
        startTime = System.currentTimeMillis();
        double maxValue = backpack(w, v, c); // 배낭의 최대 가치
        System.out.println("배낭에 담긴 물건들의 가치 합 => " + maxValue);

        endTime   = System.currentTimeMillis();
        diffTime  = endTime - startTime;
        System.out.println("실행 시간 = "+ diffTime + "ms");
```
### 물건의 개수에 따른 실행시간
  | 물건의 개수 | 5   | 10   | 15   | 20   | 25   | 30   |
  | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
  | 시간(ms) | 17    | 18    | 20    | 21    | 23    | 22    |

![graph](https://postfiles.pstatic.net/MjAyMTA0MTJfODkg/MDAxNjE4MTU5NDYwNTcw.NnRo4wrQP5BRWejftOrJiK6_25dOhwwsuXSUcJgaaFQg.79YLAudwyQi5gASSmb5p4J-oPo5myz6IBtXMb1cJ9M8g.PNG.hongsubakgame/image.png?type=w966)

### 이론값
  | 물건의 개수 | 5   | 10   | 15   | 20   | 25   | 30   |
  | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
  | 시간(ms) | 11.6    | 33.2    | 58.6    | 86.4    | 116    | 147    |

![graph](https://postfiles.pstatic.net/MjAyMTA0MTJfMTg4/MDAxNjE4MTU5NDExMjA2.C4hC304Ynk_ahJd9ukHwmK1ZTD6hdl2qmogItQKTbqEg.Qb6sq_6qpCjmIIlvoHHT86L4PsBaLDBf1x3ocIZZJKMg.PNG.hongsubakgame/image.png?type=w966)

### 측정값과 이론값 비교
![graph](https://postfiles.pstatic.net/MjAyMTA0MTJfMTAw/MDAxNjE4MTU5MzUyMjM2.bogBfSX3TkrWsbyYztjYfpOHqJudqTZ5PEEpydp3LhYg.5K-j1EgSeFDgQvnf916uiLhxsbjP4rFJonpGGtjKzM0g.PNG.hongsubakgame/image.png?type=w966)
