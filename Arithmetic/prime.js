
/*
  소수인지 판별하는 함수
*/
var arg = function isPrime(target){
  var mod;
  var sqrt;
  var prime = true;
  for( mod = 2, sqrt = Math.sqrt(target); mod <= sqrt && prime; mod++){
    if(target % mod == 0){
      prime = false;
    }
  }
  return prime;
}

/*
  ~까지 등장하는 소수의 합을 계산하는 함수
*/
function sumPrime(callback, endPoint){
  var sum = 0;
  for(var i = 1; i <= endPoint; i++){
    if(callback(i)){
      sum += i;
    }
  }
  return sum;
}


/*
  ~까지 등장하는 소수의 개수를 세는 함수
*/

function countPrime(callback, endPoint){
  var count = 0;
  for(var i = 1; i <= endPoint; i++){
    if(callback(i)){
      count += 1;
    }
  }
  return count;
}

console.log(countPrime(arg, 100));
