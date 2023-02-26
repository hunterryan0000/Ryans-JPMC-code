/*
1. **iqTest** Bob is preparing to pass an IQ test. The most frequent task in this test 
    is to find out which one of the given numbers differs from the others. Bob observed
    that one number usually differs from the others in evenness. Help Bob — to check his 
    answers, he needs a program that among the given numbers finds one that is different in 
    evenness, and return the position of this number. _Keep in mind that your task is to help 
    Bob solve a real IQ test, which means indexes of the elements start from 1 (not 0)_

		iqTest("2 4 7 8 10") → 3 //third number is odd, while the rest are even
		iqTest("1 2 1 1") → 2 // second number is even, while the rest are odd
		iqTest("") → 0 // there are no numbers in the given set
        iqTest("2 2 4 6") → 0 // all numbers are even, therefore there is no position of an odd number
*/

function iqTest(valsStr) {
//seperate the numbers to look like e.g 1 2 3 4
   const vals = valsStr.split(" ");
   let evenPos = 0;
   let oddPos = 0;
 
   //Loop through the numbers
   for (let i = 0; i < vals.length; ++i) {
     //if the number is even take note of that position
       if (vals[i] % 2 == 0) {
           evenPos = i;
           
           //else take note of that odd position
       } else { 
           oddPos = i;
       }
   }
   
  if (evenPos > oddPos && oddPos != 0) {
       return oddPos + 1;
       //What number is the only odd?
   } else if (oddPos > evenPos && evenPos != 0) {
       return evenPos + 1;
   } 
   //If all numbers are even return 0
   //Return 0 when no numbers are given
  else return 0;
 }

/*
2. **titleCase** Write a function that will convert a string into title case, given an optional 
    list of exceptions (minor words). The list of minor words will be given as a string with each 
    word separated by a space. Your function should ignore the case of the minor words string -- 
    it should behave in the same way even if the case of the minor word string is changed.


* First argument (required): the original string to be converted.
* Second argument (optional): space-delimited list of minor words that must always be lowercase
except for the first word in the string. The JavaScript tests will pass undefined when this 
argument is unused.

		titleCase('a clash of KINGS', 'a an the of') // should return: 'A Clash of Kings'
		titleCase('THE WIND IN THE WILLOWS', 'The In') // should return: 'The Wind in the Willows'
        titleCase('the quick brown fox') // should return: 'The Quick Brown Fox'
*/
function titleCase(title, exceptionWords) {
    // condition ? exprIfTrue : exprIfFalse
    // If no exeception words then "", if so then lowercase
    let exceptions = "";
    if (exceptionWords === undefined){
        exceptionWords = exceptions; 
    } else exceptions = exceptionWords.toLowerCase().split(' ');
    // let exceptions = exceptionWords.toLowerCase().split(' ');
    
    let words = title.toLowerCase().split(' ');
  
    for (let i = 0; i < words.length; i++) {
      //if the word isnt an exception or if its the first word in the Title then capitalize it
      if (!exceptions.includes(words[i]) || i === 0) {
        //capitalizes first letter and then adds the rest of the word
        words[i] = words[i].charAt(0).toUpperCase() + words[i].slice(1);
     }
    }
  
    return words.join(' ');
  }