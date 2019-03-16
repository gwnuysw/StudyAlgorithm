/*
  1부터 100까지단순히 차례로 더하는 알고리즘
*/
let sum = 0;
for(let i = 1; i <= 100; i += 2){
  sum += i;
}
console.log('simple sigma',sum);

/*
  덧셈 뺄셈을 번갈아 가며 실행하는 알고리즘
*/
sum = 0;
for(let i = 1; i <= 100; i++){
  if(i%2){
    sum += i;
  }
  else{
    sum += (-1) * i;
  }
}
console.log('plus, minus sigma', sum)

/*
  분수 덧뺄셈 합계
*/
sum = 0;
for(let i = 1; i < 100; i++){
  if(i%2 == 0){
    sum += i/(i+1);
  }
  else{
    sum += (-1) * i/(i+1);
  }
}
console.log('plus, minus fraction sigma', sum)

/*
  항 사이의 증가하는 값이 일정한 비율로 증가하는 수열
*/
sum = 0;
let inc = 1;
for(let i = 0; i <= 19; i++){
  inc += i;
  sum += inc;
}
console.log('increasing increase', sum);

/*
  항이 바뀔 때 마다 빼기와 더하기가 번갈아 나열되는 수열
*/
sum = 0;
inc = 1;
var pm = 1;
for(let i = 0; i <= 19; i++){
  inc += i;
  pm *= -1;
  orInc = pm * inc;
  sum += orInc;
  console.log(orInc);
}
console.log('plus, minus increasing', sum);

/*
  factorial 수열의 합계
*/
sum = 0;
inc = 1;
pm = 0;
for(let i = 1; i <= 10; i++){
  inc *= i;
  sum += inc;
}
console.log('factorial ', sum);

/*
  fibonacci 수열 합계
*/
sum = 0;
var left = 0;
var right = 1;
pm = 0;
for(let i = 0; i < 20; i++){
  sum += right;
  pm = left + right;
  left = right;
  right = pm;
  console.log(right);
}
console.log('fibonacci ', sum);
