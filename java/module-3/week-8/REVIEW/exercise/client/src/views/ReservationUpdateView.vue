<template>
  <div class="home">
    <div id="heading-line">
      <h1>
        Update Reservation
        <loading-spinner id="spinner" v-bind:spin="isLoading" />
      </h1>
    </div>
    <reservation-details v-bind:reservation="reservation" />
    <hr />
    <form v-on:submit.prevent="updateReservation">
      <fieldset>
        <label for="fullName">Full name: </label>
        <input
          type="text"
          name="fullName"
          id="fullName"
          v-model="reservation.fullName"
        />
      </fieldset>
      <fieldset>
        <label for="checkinDate">Checkin date: </label>
        <input
          type="date"
          name="checkinDate"
          id="checkinDate"
          v-model="reservation.checkinDate"
        />
      </fieldset>
      <fieldset>
        <label for="checkoutDate">Checkout date: </label>
        <input
          type="date"
          name="checkoutDate"
          id="checkoutDate"
          v-model="reservation.checkoutDate"
        />
      </fieldset>
      <fieldset>
        <label for="guests">Number of guests: </label>
        <input
          type="number"
          min="1"
          max="5"
          name="guests"
          id="guests"
          v-model="reservation.guests"
        />
      </fieldset>
    </form>
    <button v-on:click="updateReservation">Update</button>&nbsp;
    <button v-on:click="listReservations">Cancel</button>
  </div>
</template>

<script>
import LoadingSpinner from "../components/LoadingSpinner.vue";
import ReservationDetails from "../components/ReservationDetails.vue";
export default {
  name: "ReservationUpdateView",
  components: {
    LoadingSpinner,
    ReservationDetails,
  },
  data() {
    return {
      isLoading: false, // true to indicate a call in progress
      reservation: {}, // the reservation to be updated by this view
    };
  },

  methods: {
    getReservation(id) {
      console.log(id);  // prevents a "no-unused-vars" error
      // Set the indicator that a call is in progress.
      this.isLoading = true;

      // TODO: Use the Service object to `GET /reservations/{id}`. Set the body of the result to `this.reservation`. Handle errors.

      // When done (whether success or error) set the indicator that the call is complete.
      this.isLoading = false;
    },
    updateReservation() {
      // Set the indicator that a call is in progress.
      this.isLoading = true;

      // TODO: Use the Service object to `PUT /reservations/{id}`. When done, call listReservations() to navigate to Hotel Details. Handle errors.

      // When done (whether success or error) set the indicator that the call is complete.
      this.isLoading = false;
    },

    listReservations() {
      // Navigate to the Hotel Details page, with a parameter that says to display reservations too.
      this.$router.push({
        name: "hotel",
        params: { id: this.reservation.hotelId },
        query: { showRes: true },
      });
    },
  },

  // TODO: Create a Vue Lifecycle hook function which calls `this.getReservation()`, passing in the `id` path parameter.


};
</script>

<style scoped>
fieldset {
  border: none;
}
fieldset > label {
  display: inline-block;
  width: 180px;
}
</style>