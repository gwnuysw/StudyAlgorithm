
function findDivisor(N){
  var divisor = [];
  var divider;
  var n;
  var endPoint;

  endPoint = N/2;

  for(divider = 1; divider <= endPoint; divider++){
    n = N % divider;
    if(n == 0){
      divisor.push(divider);
    }
  }
  divisor.push(N);
  return divisor;
};
/*
  test1
*/
function solution(N){
  var divisor = findDivisor(N);
  var i;
  var endPoint;
  console.log(divisor);
  if(divisor.length % 2 != 0){
    endPoint = divisor.length/2 - 0.5;
  }
  else{
    endPoint = divisor.length/2;
  }
  console.log(endPoint);
  for ( i = 1,  min = divisor.shift() - divisor.pop(); i <= endPoint; i++){
    if(min < 0){
      min = -1 * min;
    }
    console.log('this is divisor',divisor);
    var temp = divisor.shift() - divisor.pop();

    if(temp < 0){
      temp = -1 * temp;
    }
    if(min > temp){
      min = temp;
    }
    console.log(min);
    console.log('this is temp',temp);
  }
  if(i % 2 == 1){
    min = 0;
  }
  return min;
}

console.log(solution(121));
