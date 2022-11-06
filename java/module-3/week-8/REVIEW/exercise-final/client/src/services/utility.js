/**
 * This JavaScript file contains common functions used across components.
 */
export default {

  /**
   * Display an error to the user and on the console.
   * @param {*} error The error object returned to the catch of an Axios call.
   * @param {string} action Text description of the action that was attempted.
   */

  handleAxiosError(error, action) {
    let message;
    if (!error.response) {
      // If the server is down or some other network issue, there will be no response. In that case, `message` 
      // is on the `error` object.
      message = error.message;
    }
    else if (error.response.data?.errors?.length) {
      // For Spring validation errors, the Response object has a `data` property that contains an `errors` array. 
      // Each element in the array has properties: `defaultMessage`, `field`, `objectName`, and `rejectedValue`.
      // The "?" in "error.response.data?.errors?.length" returns a `null` if `data` or `errors` is null or undefined, 
      // instead of throwing an error. This is called the optional chaining operator 
      // (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Optional_chaining).
      message = "Validation error:";
      for (let valError of error.response.data.errors) {
        message += `\n* ${valError.defaultMessage} (${valError.field}: ${valError.rejectedValue})`;
      }
    } else {
      // If there was a response, but not a validation errors array, the message should be in `response.message`.
      message = error.response.message;
    }
    alert(`The action '${action}' failed: ${message}`);
    console.error(`The action '${action} failed: `);
    console.error(error);
  },

  /**
   * Formats a number as currency.
   * @see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat/NumberFormat
   * @param {number} monetaryAmount The number to format.
   * @param {string} [currencyFormat='USD'] The ISO 4217 currency code to format as. Default value is 'USD'.
   * @param {number} [decimalPlaces=2] The number of decimal places to display. Default value is 2.
   * @returns 
   */
  formatCurrency(monetaryAmount, currencyFormat = 'USD', decimalPlaces = 2) {
    return new Intl.NumberFormat('en-US', { style: 'currency', currency: currencyFormat, maximumFractionDigits: decimalPlaces }).format(monetaryAmount);
  },
  
}
