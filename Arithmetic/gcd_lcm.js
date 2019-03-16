function gcd_lcm(a, b){
  var gcd, lcm;
  var big, small;
  var remainder;
  if(a < b){
    big = b;
    small = a;
  }
  else{
    big = a;
    small = b;
  }
  while(true){
    remainder = big % small;

    if(remainder == 0){
      gcd = small;
      lcm = (a * b) / gcd;
      break;
    }
    else{
      big = small;
      small = remainder;
    }
  }
  return [gcd, lcm];
}

console.log(gcd_lcm(22,15));
