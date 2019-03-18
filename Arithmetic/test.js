/*
  test1
*/
// function solution(N){
//   var divisor = findDivisor(N);
//   var i;
//   var endPoint;
//   if(divisor.length % 2 != 0){
//     endPoint = divisor.length/2 - 0.5;
//   }
//   else{
//     endPoint = divisor.length/2;
//   }
//   for ( i = 1,  min = divisor.shift() - divisor.pop(); i <= endPoint; i++){
//     if(min < 0){
//       min = -1 * min;
//     }
//     var temp = divisor.shift() - divisor.pop();
//     if(temp < 0){
//       temp = -1 * temp;
//     }
//     if(min > temp){
//       min = temp;
//     }
//   }
//   if(i % 2 == 1){
//     min = 0;
//   }
//   return min;
// }

//console.log(solution(3));
/*
test2
*/
function solution(N){
    var simbol = [];
    var number = [];
    var middle;
    var temp;
    var hasNumber = /\d/;
    var corect=[];
    N = N[0];
    while(N.length > 0){
      temp = N.slice(0,1);
      N = N.slice(1, N.length);

      if(hasNumber.test(temp)){
          number.push(temp);
      }
      else{
          simbol.push(temp);
      }
    }
    var temp1;
    var temp2;
    var numbertemp;
    var simbolcount = simbol.length;

    while(simbol.length != 0 && number.length != 0){
      temp1 = simbol.shift();
      corect.push(temp1);
      if(simbol.length > 0){
        temp2 = simbol[0];
        if(temp2 == temp2.toLowerCase()){
          simbol.shift();
          corect.push(temp2);
        }
      }
      numbertemp = number[0];
      if(parseInt(numbertemp) == 1){
        number.shift();
      }
      else{
        number.shift();
        corect.push(numbertemp);
      }
    }
    if(corect.length < simbolcount){
      return "error";
    }
    else{
      return corect.join("");
    }
}

console.log(solution(["NaCl21"]));

/*
  test3
*/
// function solution(input){
//   var input = [ '6\nid',
//     'name',
//     'occupation\n5',
//     'Brown',
//     'Accountant\n2',
//     'Cony',
//     'Programmer\n3',
//     'Sally',
//     'Doctor\n1',
//     'James',
//     'Singer\n4',
//     'Moon',
//     'Dancer\n7\nid',
//     'city',
//     'zip\n2',
//     'Seoul',
//     '10008\n7',
//     'Busan',
//     '40002\n5',
//     'Gwangju',
//     '20009\n6',
//     'Daegu',
//     '30008\n3',
//     'Seoul',
//     '40005\n1',
//     'Seoul',
//     '50006' ]
//
//   var stringedData = input.toString();
//
//   var cutLine = stringedData.split('\n');
//   var middle
//   var firstTable;
//   var secondTable;
//   var join = [];
//   var flag = 0;
//   var result = '';
//
//   for(var i = 1; cutLine[i].length > 1; i++)
//     ;
//   middle = i;
//   firstTable = cutLine.slice(0,7);
//   secondTable = cutLine.slice(7, cutLine.length+1);
//
//   for(var i = 2; i < firstTable.length; i++){
//     flag = 0;
//     for(var j = 2; j < secondTable.length; j++){
//       if( firstTable[i][0] == secondTable[j][0]){
//         join.push(firstTable[i].concat(secondTable[j].slice(1,secondTable[j].length)));
//         flag = 1;
//       }
//     }
//     if(flag == 0){
//       join.push(firstTable[i].concat(",NULL,NULL"));
//     }
//   }
//   console.log(firstTable);
//   console.log(secondTable);
//   result = result.concat(firstTable[1].replace(/,/gi,' '));
//   result = result.concat(secondTable[1].slice(2, secondTable[1].length+1).replace(/,/gi,' ')+'\n');
//   join.sort(function (a,b){
//     return a[0] - b[0];
//   });
//
//   join.forEach(function(element){
//     result = result + element.replace(/,/gi,' ') + '\n';
//   })
//
//   return result;
// }
