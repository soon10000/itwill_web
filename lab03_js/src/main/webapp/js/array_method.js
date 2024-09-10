/**
 * array_method.html에 포함
 * 
 * JS array 객체의 함수(메서드)들
 * 
 */

 
 const arr = [1, 2, 3];
 console.log(arr);
 
 // 배열에 새로운 원소를 배열 끝에 추가: 
 arr.push(100); // push(): 원본 배열의 끝에 새로운 원소를 추가. 원본 배열이 수정됨.
 console.log(arr);
 
 let result = arr.concat(200); 
 //concat(): 원본 배열을 수정되지 않고, 원소가 추가된 "새로운"" 배열을 생성하여 리턴.
 
 console.log(arr);
 console.log(result);
 
 
 // 배열의 마지막 원소를 삭제:
 arr.pop(); // pop(): 원본 배열의 마지막 원소를 삭제. 원본 배열이 수정됨
 console.log(arr);
 
 result = arr.slice(0, -1); // slice(): 원본 배열이 수정되지 않고, 원소가 삭제된 "새로운"" 배열을 생성하여 리턴.
 // slice(start, end): 배열에서 start 인덱스부터 end 인덱스까지 잘라낸 "새로운"" 배열을 리턴
 console.log(arr);
 console.log(result);
 
 

 const arr2 = [10, 100, -1, 90];
 console.log(arr2);
 
// toSorted():
 // - 배열의 원소들을 문자열로 변환해서 크기 비교를 함.
 // - 오름차순 정렬된 "새로운" 배열을 리턴
 // - 원본 배열은 변경되지 않음.
 // toSorted(callback): 배열의 원소들의 크기 비교를 할 때 사용할 콜백을 아규먼트로 전달
 
 result = arr2.toSorted((x,y) => x-y); // 원본 배열을 오름차순 정렬한 "새로운" 배열을 리턴.
 console.log(arr2);  // -> toSorted() 메서드는 원본 배열을 변경하지 않음.
 console.log(result);
 
 
 // sort():
 // - 배열의 원소들을 문자열로 변환해서 크기 비교
 // - 원본 배열의 원소 순서를 오름차순으로 변경. 원본 배열이 바뀜.
 // sort(callback): 배열 원소의 크기 비교에서 사용하는 콜백을 아규먼트로 전달
 arr2.sort((x,y) => x-y);
 console.log(arr2);
 
 
 // forEach, filter, map, reduce
 const numbers = [1, 2, 3, 4, 5, 6];
 console.log(numbers);
 
 // 배열 numbers의 원소들 중에서 홀수들로만 이루어진 새로운 배열을 만드세요.
 const odds = []; // let odds = [];
 for(let x of numbers) {
    if (x % 2) {
        odds.push(x); // odds = odds.concat(x);
    }
 }
 console.log(odds);
 
 // result = numbers.filter(function (x) {return x % 2;}); -> 익명함수 사용
 result = numbers.filter((x) => x % 2); // 'true'의 값을 result 변수에 포함
 console.log(result);
  
 // 배열 bumbers의 원소들의 제곱을 원소로 갖는 새로운 배열을 만드세요.
 const squares = [];
 for(let x of numbers) {
    squares.push(x*x);
 }
 console.log(squares);
 
 result = numbers.map((x) => x * x); // 리턴값을 result 변수에 포함
 console.log(result);
 
 numbers.forEach((x) => console.log(x)); // forEach의 경우 배열이 값을 가지고 하는 행동을 풀어줌
 
 
 // 배열 numbers의 모든 원소들의 합계 (reduce 사용)
 let sum = 0; 
 for(let value of numbers){
    sum += value;
 }
 console.log(`sum = ${sum}`);
 
 
 // reduce(callback, initalValue) >> 0으로 시작해서 배열의 원소들을 끝까지 콜백을 반복해서 진행하겠다
 sum = numbers.reduce((acc, cur) => acc + cur, 0);
 console.log(`sum = ${sum}`);
 
 // numbers의 모든 원소들의 곱: 1 * 2 ... * 6
 
 
result = 1;
for (let value of numbers){
    result = result * value;
}
console.log(`result = ${result}`);

result = numbers.reduce((acc, cur) => acc * cur, 1);
console.log(`result = ${result}`);


// numbers의 원소들 중에서 짝수들의 합:
result = numbers.filter((x) => x % 2 === 0).reduce((acc,cur) => acc + cur, 0);
console.log(`짝수 합 = ${result}`);

// number의 원소들의 제곱의 합:
result = numbers.map((x) => x * x).reduce((acc, cur) => acc + cur, 0);
console.log(`제곱 합 = ${result}`);

// number의 원소들 중 짝수들의 제곱의 합:
result = numbers.filter((x) => x % 2 === 0).map((x) => x * x).reduce((acc, cur) => acc + cur, 0);
console.log(`짝수들의 제곱 합 = ${result}`);


// filter > 콜백 조건부에 맞는것(true)만 원소로 저장한다.
// map > 콜백 함수를 실행한 값을 모든 원소로 저장한다.
// reduce > 시작값(initialvalue) 변수를 설정하고 해당 변수에 콜백함수값을 실행하고 반복헤서 끝까지 실행하고 최종 값을 리턴


