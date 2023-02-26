/**
 *
 * @param {String} input Space delimited string of numbers
 */
 function iqTest(valsStr) {
   //Return 0 when no numbers are given
  if (valsStr == '') {
      return 0;
  }

  //seperate the numbers to look like e.g 1 2 3 4
  let vals = valsStr.split(" ");
  let numEvens = 0;
  let numOdds = 0;
  let evenPos = 0;
  let oddPos = 0;

  //Loop through the numbers
  for (let i = 0; i < vals.length; ++i) {
    //if the number is even add to the number of evens and take note of that position
      if (vals[i] % 2 == 0) {
          numEvens++;
          evenPos = i+1;
          //else add to the number of odds and take note of that position
      } else {
          numOdds++;
          oddPos = i+1;
      }
  }

  //What number is the only even?
  if (numEvens == 1) {
      return evenPos;
      //What number is the only odd?
  } else if (numOdds == 1) {
      return oddPos;
  }

  //If all numbers are even return 0
  return 0;
}



/**
 * Title cases a list of strings.
 * @param {String} title String to convert
 * @param {String} exceptionWords Space delimited words to ignore
 */
function titleCase(title, exceptionWords) {
  // // condition ? exprIfTrue : exprIfFalse
  // // If no exeception words then "", if so then lowercase
  // const exceptions = exceptionWords === undefined ? "" : exceptionWords.toLowerCase().split(' ');
  
  // let words = title.toLowerCase().split(' ');

  // for (let i = 0; i < words.length; i++) {
  //   //if the title isnt an exception or if its the first word in the Title then capitalize it
  //   if (!exceptions.includes(words[i]) || i === 0) {
  //     //capitalizes first letter and then adds the rest of the word
  //     words[i] = words[i].charAt(0).toUpperCase() + words[i].slice(1);
  //     // console.log(words);
  //     // console.log(i);
  //     // console.log(words[i]);
  //   }
  // }

  // return words.join(' ');
}
