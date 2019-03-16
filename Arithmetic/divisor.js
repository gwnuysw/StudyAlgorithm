
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
}

function solution(N){
  var divisor = findDivisor(N);
  console.log(divisor);
  var i;
  var endPoint;
  if(divisor.length % 2 != 0){
    endPoint = divisor.length/2 - 0.5;
  }
  else{
    endPoint = divisor.length/2;
  }
  for ( i = 1,  min = divisor.shift() - divisor.pop(); i <= endPoint; i++){
    if(min < 0){
      min = -1 * min;
    }
    var temp = divisor.shift() - divisor.pop();
    if(temp < 0){
      temp = -1 * temp;
    }
    if(min > temp){
      min = temp;
    }
  }
  if(i % 2 == 1){
    min = 0;
  }
  return min;
}

console.log(solution(3));
