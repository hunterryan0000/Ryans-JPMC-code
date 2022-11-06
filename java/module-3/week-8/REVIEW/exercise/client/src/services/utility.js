/**
 * This JavaScript file contains common functions used across components.
 */
export default {
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
