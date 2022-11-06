import axios from 'axios';

/**
 * This service class is used to interact with the Hotels/Reservations API.
 * All methods return a Promise so that the calling code can handle both success and 
 * error responses appropriately. 
 */
export default {
  // TODO: Add any Axios calls to the API as functions in this service object
  
  getHotels() {
    return axios.get('/hotels');
  },

  getStates() {
    return axios.get('/hotels/states');
  },

  getHotelById(hotelId) {
    return axios.get(`/hotels/${hotelId}`);
  },

  searchHotels(stateCode) {
    return axios.get('/hotels?state=' + stateCode);
  },

  getReservationsByHotel(hotelId) {
    return axios.get(`/hotels/${hotelId}/reservations`);
  },

  getReservationById(reservationId) {
    return axios.get(`/reservations/${reservationId}`);
  },
  
  addReservation(reservation) {
    return axios.post(`/reservations`, reservation);
  },
  
  updateReservation(reservation) {
    return axios.put(`/reservations/${reservation.id}`, reservation);
  },
  
  deleteReservation(reservationId) {
    return axios.delete(`/reservations/${reservationId}`);
  },
}
