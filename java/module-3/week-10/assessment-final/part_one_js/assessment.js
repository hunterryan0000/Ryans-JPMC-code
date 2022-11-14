/**
 * Write a function that takes in an array of strings, which alternates between numbers
 * and math operators to make up a valid mathematical expression. Return the result
 * of evaluating the mathematical expression sequentially. 
 * 
 * Numbers will be in the range of -100 to 100.
 * Math operators are limited to: +, -, *, /, %
 * 
 * You can assume the array expression will be valid.
 * Do not use any operator precedence. Evaluate the expression sequentially.  
 * Do not modify the input array.
 * Do not use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * 
 * Example 1: [2, '+', 2] -> 4
 * Example 2: [2, '*', -3, '+', 33] -> 2 * -3 + 33 -> -6 + 33 -> 27
 * Example 3: [12, '/', 2, '%', 3, '*', 3] -> 12 / 2 % 3 * 3 -> 6 % 3 * 3 -> 0 * 3 -> 0
 */
function arrayCalculator(arr) {
    let result = arr[0];
    for (let i=1; i < arr.length-1; i+=2) {
        let operator = arr[i];
        let number = arr[i+1]
        if (operator === '+') {
            result = result + number;
        }
        if (operator === '-') {
            result = result - number;
        }
        if (operator === '*') {
            result = result * number;
        }
        if (operator === '/') {
            result = result / number;
        }
        if (operator === '%') {
            result = result % number;
        }
    }
    return result;
}

/**
 * Given a state code and an array of order objects, identify the number of orders
 * shipped to that state, the total weight, and the total sales amount from those
 * orders. Only consider orders with a status of 'Delivered' or 'Shipped'.
 * 
 * Order objects have properties for state, status, weight, and cost.
 * Return an object with properties for count, weight, and sales. 
 */
function orderVolume(stateCode, orderArray) {
    let result = {
        count: 0,
        weight: 0,
        sales: 0
    }
    for (const order of orderArray) {
        if (order.state === stateCode && (order.status === 'Delivered' || order.status === 'Shipped')) {
            result.count += 1;
            result.weight += order.weight;
            result.sales += order.cost;
        }
    }
    return result;
}