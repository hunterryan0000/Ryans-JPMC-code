<template>
  <div class="home">
    <div id="heading-line">
      <h1>
        Delete Reservation
        <loading-spinner id="spinner" v-bind:spin="isLoading" />
      </h1>
    </div>
    <reservation-details v-bind:reservation="reservation" />
    <hr />
    Are you sure you want to delete this reservation?<br /><br />
    <button id="delete-button" v-on:click="deleteReservation">
      <font-awesome-icon icon="fa-solid fa-trash-can" /> Delete</button
    >&nbsp;
    <button v-on:click="listReservations">Cancel</button>
  </div>
</template>

<script>
import LoadingSpinner from "../components/LoadingSpinner.vue";
import ReservationDetails from "../components/ReservationDetails.vue";
export default {
  name: "ReservationDeleteView",
  components: {
    LoadingSpinner,
    ReservationDetails,
  },
  data() {
    return {
      isLoading: false, // true to indicate a call in progress
      reservation: {}, // the reservation to be deleted by this view
    };
  },

  methods: {
    getReservation(id) {
      console.log(id); // prevents a "no-unused-vars" error
      // Set the indicator that a call is in progress.
      this.isLoading = true;

      // TODO: Use the Service object to `GET /reservations/{id}`. Set the body of the result to `this.reservation`. Handle errors.

      // When done (whether success or error) set the indicator that the call is complete.
      this.isLoading = false;
    },
    deleteReservation() {
      // Set the indicator that a call is in progress.
      this.isLoading = true;

      // TODO: Use the Service object to `DELETE /reservations/{id}`. When done, call listReservations() to navigate to Hotel Details. Handle errors.

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
#delete-button:hover {
  color: red;
}
</style>