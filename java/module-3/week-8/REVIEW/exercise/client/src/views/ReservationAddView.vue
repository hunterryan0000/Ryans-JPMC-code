<template>
  <div class="home">
    <div id="heading-line">
      <h1>
        Add Reservation
        <loading-spinner id="spinner" v-bind:spin="isLoading" />
      </h1>
    </div>
    <hr />
    <form v-on:submit.prevent="addReservation">
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
    <button v-on:click="addReservation">Add</button>&nbsp;
    <button v-on:click="listReservations">Cancel</button>
  </div>
</template>

<script>
import LoadingSpinner from "../components/LoadingSpinner.vue";
export default {
  name: "ReservationAddView",
  components: {
    LoadingSpinner,
  },
  data() {
    return {
      isLoading: false, // true to indicate a call in progress
      reservation: {}, // The reservation that will be added by this view
    };
  },

  methods: {
    addReservation() {
      // Set the indicator that a call is in progress.
      this.isLoading = true;

      // TODO: Use the Service object to `POST /reservations`. When complete, call listReservations to navigate back to the hotel details view. Handle errors.

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
    dateAsString(daysFromToday = 0) {
      const today = new Date();
      return new Date(
        today.getFullYear(),
        today.getMonth(),
        today.getDate() + daysFromToday
      )
        .toISOString()
        .split("T")[0];
    },
  },

  created() {
    // Create a "starting" reservation with default values to begin.
    this.reservation = {
      id: 0,
      hotelId: this.$route.params.hotelId,
      fullName: "",
      checkinDate: this.dateAsString(1),
      checkoutDate: this.dateAsString(2),
      guests: 2,
    };
  },
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