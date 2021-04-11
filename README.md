# greedy26
<p align="center"><img width="30%" alt="이름표" src="https://user-images.githubusercontent.com/80371590/114296062-11fdd180-9ae4-11eb-8832-cf359270f35c.jpg"></p>

### 부분 배낭 문제(Fractional Knapsack Problem)
배낭 문제란 배낭에 담을 수 있는 무게의 최대값이 정해져 있고, 일정한 가치와 무게가 정해져있는 짐들을 배낭에 담을 때, 가치의 합이 최대가 되도록 짐을 고르는 방법을 찾는 문제이다.  

  알고리즘의 작동 구조는 다음과 같다.
  
  부분 배낭 문제를 해결하기위한 효율적인 방법은 Greedy 방식을 사용하는 것이다. 
  
- 그리디 접근 방식의 기본 개념은 각 항목의 가치/무게의 비율을 계산하고 이 비율을 기준으로 항목을 정렬한다
- 가장 가치가 높은 비율 항목부터 가방에 담는다. 
- 가방에 담을때 현재 가방의 용량이 담을 항목의 무게보다 크다면 모두 선택하고 항목의 가치를 모두 더한다.
- 그렇지 않다면 현재 가방에 담을 수 있는 용량 만큼 쪼개서 담고 가치는 비율만큼 계산하여 더해주고 루프를 빠져나온다.

### 예시)
<p align="center"><img width="300" alt="부분 배낭" src=https://user-images.githubusercontent.com/80371590/114296868-7c187580-9ae8-11eb-9e51-22982f65ad29.png> 
<img width="1100" alt="배열" src=https://user-images.githubusercontent.com/80371590/114296876-80dd2980-9ae8-11eb-9ec8-c57eb86673f0.png> 
 
 ->  최종적으로 배낭에 담긴 물건들의 리스트와 배낭에 담긴 물건들의 가치의 합을 리턴하여 얻게된다.


### 시간복잡도 )

<p align="center"><img width="500" alt="부분 배낭" src=https://user-images.githubusercontent.com/80371590/114297319-e500ed00-9aea-11eb-865b-5a673d8e6a0c.png>
  
  n개의 물건 각각의 단위 무게당 가치를 계산하는 데는 `O(n)` , 
  
  물건 단위 무게당 가치에 대해서 내림차순으로 정렬하기 위해 `O(nlogn)` 
  
  루프 내부의 수행 `O(1)` 
  
  마지막 리턴까지의 시간 `O(1)`
 >> ==> `O(n) + O(nlogn) + n × O(1) + O(1) = O(nlogn)`이 걸리게 된다.



