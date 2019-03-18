function DecToBin(decimal){
  var n = [];
  while(decimal != 1){
    n.unshift(decimal % 2);
    decimal = parseInt(decimal/2);
  }
  n.unshift(1);
  return n;
}
function conversion(decimal, target){
  var n = [];
  while(decimal >= target){
    n.unshift(hex(decimal, target));
    decimal = parseInt(decimal/target);
  }
  n.unshift(hex(decimal, target));
  return n;
}
function hex(decimal, target){
  var n;
  if(decimal % target == 10){
    n = 'a';
  }
  else if(decimal % target == 11){
    n = 'b';
  }
  else if(decimal % target == 12){
    n = 'c';
  }
  else if(decimal % target == 13){
    n = 'd';
  }
  else if(decimal % target == 14){
    n = 'e';
  }
  else if(decimal % target == 15){
    n = 'f';
  }
  else{
    n = decimal % target;
  }
  return n;
}
console.log(conversion(9, 2));

jdkjasdkjsa
