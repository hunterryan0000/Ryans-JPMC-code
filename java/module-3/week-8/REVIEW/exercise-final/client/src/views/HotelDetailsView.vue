<template>
  <div>
    <div id="heading-line">
      <img
        id="hotel-image"
        v-bind:src="'/images/' + hotel.coverImage"
        alt="Hotel photo"
      />
      <h1>
        {{ hotel.name }}
        <loading-spinner id="spinner" v-bind:spin="isLoading" />
      </h1>
    </div>
    <section id="hotel-details">
      <header>Address</header>
      <article>
        {{ hotel.address.address }}<br />
        {{ hotel.address.address2 }}<br v-if="hotel.address.address2" />
        {{ hotel.address.city }}<br />
        {{ hotel.address.state }}<br />
        {{ hotel.address.zip }}
      </article>
      <header>Cost</header>
      <article>{{ formatCurrency(hotel.costPerNight) }} per night</article>
      <header>Capacity</header>
      <article>{{ hotel.roomsAvailable }} rooms</article>
      <header>Rating</header>
      <article>
        <img
          class="rating-star"
          v-for="n in hotel.stars"
          v-bind:key="n"
          src="/images/star.png"
          alt="Stars"
          v-bind:title="hotel.stars + ' stars'"
        />
      </article>
    </section>

    <section id="hotel-reservations">
      <a v-if="!reservations" href="#" v-on:click.prevent="getReservations"
        >Show hotel reservations</a
      >
      <div id="reservations" v-else>
        <h2>Reservations</h2>
        <p>
          <em
            >{{ reservations.length }} reservation{{
              reservations.length == 1 ? "" : "s"
            }}
            found</em
          >
        </p>
        <router-link
          v-bind:to="{ name: 'add-reservation', params: { hotelId: hotel.id } }"
          >Book new reservation</router-link
        >
        <article
          id="reservation-list"
          v-for="res in reservations"
          v-bind:key="res.id"
        >
          <hr />
          <reservation-details
            id="reservation-details"
            v-bind:reservation="res"
          />
          <router-link
            v-bind:to="{
              name: 'update-reservation',
              params: { id: res.id },
            }"
            ><font-awesome-icon
              icon="fa-solid fa-pen-to-square"
              class="fa-icon"
          /></router-link>
          <router-link
            v-bind:to="{
              name: 'delete-reservation',
              params: { id: res.id },
            }"
          >
            <font-awesome-icon icon="fa-solid fa-trash-can" class="fa-icon"
          /></router-link>
        </article>
      </div>
    </section>
  </div>
</template>

<script>
import hotelService from "../services/HotelService";
import utility from "../services/utility";
import LoadingSpinner from "../components/LoadingSpinner.vue";
import ReservationDetails from "../components/ReservationDetails.vue";
export default {
  components: { LoadingSpinner, ReservationDetails },
  name: "HotelDetailsView",
  data() {
    return {
      hotel: {
        address: {},
      }, // The single hotel object displayed in this view
      isLoading: false, // true to indicate a call in progress
      reservations: null, // The list of reservations for this hotel.
      showRes: false, // The user requested to view reservations
    };
  },
  methods: {
    /***
     * Get a single hotel from the API.
     */
    getHotel(id) {
      // Set the indicator that a call is in progress.
      this.isLoading = true;
      // TODO: Use the Service object to `GET /hotels/{id}`. Set the body of the result to `this.hotel`. Handle errors.
      hotelService
        .getHotelById(id)
        .then((response) => {
          this.hotel = response.data;
          // Indicate the call is complete.
          this.isLoading = false;
          if (this.showRes) {
            this.getReservations();
          }
        })
        .catch((error) => {
          // Indicate the call is complete.
          this.isLoading = false;
          utility.handleAxiosError(error, "Get hotel");
        });
    },
    getReservations() {
      // The "?" in "this.hotel?.id" returns a `null` if `hotel` is null or undefined, 
      // instead of throwing an error. This is called the optional chaining operator.
      if (!this.hotel?.id) {
        return;
      }
      // Set the indicator that a call is in progress.
      this.isLoading = true;
      // TODO: Use the Service object to `GET /hotels/{id}/reservations`. Set the body of the result to `this.reservations`. Handle errors.
      hotelService
        .getReservationsByHotel(this.hotel.id)
        .then((response) => {
          this.reservations = response.data;
          // Indicate the call is complete.
          this.isLoading = false;
        })
        .catch((error) => {
          // Indicate the call is complete.
          this.isLoading = false;
          utility.handleAxiosError(error, "Get reservations");
        });
    },
    formatCurrency(value) {
      return utility.formatCurrency(value);
    },
  },
  created() {
    // If this page was called with "?showRes=true" as a query string, automatically get the reservations when the page is loaded.
    if (this.$route.query.showRes) {
      this.showRes = true;
    }
    // Fetch the hotel based on the path parameter
    this.getHotel(this.$route.params.id);
  },
};
</script>

<style scoped>
#reservation-details {
  display: inline-block;
}

#heading-line {
  display: flex;
  align-items: center;
}

#hotel-details {
  display: grid;
  grid-template-columns: 200px auto;
  padding: 10px 30px;
  row-gap: 20px;
}

#hotel-reservations {
  padding: 10px 30px;
}

#hotel-details > * {
  border-top: 1px solid rgb(175, 175, 175);
  padding-top: 15px;
}

#hotel-image {
  max-height: 100px;
}

section > header {
  font-size: 1.1rem;
  font-weight: bolder;
}

img.rating-star {
  height: 50px;
}

.fa-icon {
  font-size: 1.4rem;
  margin-right: 8px;
}
</style>