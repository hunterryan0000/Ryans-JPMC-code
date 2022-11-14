<template>
  <div id="hotel-list">
    <h1>
      Hotels
      <loading-spinner id="spinner" v-bind:spin="isLoading" />
    </h1>

    <!--Hotels table -->
    <div id="hotel-grid">
      <div class="table-heading">Hotel Name</div>
      <div class="table-heading">
        State:
        <select id="stateDropdown" v-model="filter" v-on:change="getHotels">
          <option value="">All</option>
          <option
            v-for="state in states"
            v-bind:key="state"
            v-bind:value="state"
          >
            {{ state }}
          </option>
        </select>
      </div>
      <div class="table-heading">Cost</div>
      <template v-for="hotel in hotels">
        <div v-bind:key="'a' + hotel.id" class="table-data">
          <router-link
            v-bind:to="{ name: 'hotel', params: { id: hotel.id } }"
            >{{ hotel.name }}</router-link
          >
        </div>
        <div v-bind:key="'b' + hotel.id" class="table-data">
          {{ hotel.address.state }}
        </div>
        <div v-bind:key="'c' + hotel.id" class="table-data">
          {{ formatCurrency(hotel.costPerNight) }}
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import hotelService from "../services/HotelService";
import utility from "../services/utility";
import LoadingSpinner from "../components/LoadingSpinner.vue";
export default {
  name: "HotelsView",
  components: {
    LoadingSpinner,
  },
  data() {
    return {
      isLoading: false, // true to indicate a call in progress
      hotels: [], // an array of hotel objects to display
      filter: "", // A state code to filter by, empty if shoing all hotels
      states: [], // An array of strings for all state codes for which we have hotels
    };
  },

  methods: {
    /***
     * Get a list of hotels from the API.
     */
    getHotels() {
      // Call the searchHotels method instead, if there is a filter set (the state dropdown)
      if (this.filter) {
        this.searchHotels();
        return;
      }

      // Set the indicator that a call is in progress.
      this.isLoading = true;

      // TODO: Use the Service object to `GET /hotels`. Set the body of the result to `this.hotels`. Handle errors.
      hotelService
        .getHotels()
        .then((response) => {
          this.hotels = response.data;
          // Indicate the call is complete.
          this.isLoading = false;
        })
        .catch((error) => {
          // Indicate the call is complete.
          this.isLoading = false;
          utility.handleAxiosError(error, "Get hotels");
        });
    },
    /***
     * Get a list of states (an array of strings) in which any of our hotels are located
     */
    getStates() {
      // Set the indicator that a call is in progress.
      this.isLoading = true;
      // TODO: Use the Service object to `GET /hotels/states`. Set the body of the result to `this.states`. Handle errors.
      hotelService
        .getStates()
        .then((response) => {
          this.states = response.data;
          // Indicate the call is complete.
          this.isLoading = false;
        })
        .catch((error) => {
          // Indicate the call is complete.
          this.isLoading = false;
          utility.handleAxiosError(error, "Get states");
        });
    },
    /***
     * Use this.filter to only return hotels in the state indicated by the filter.
     */
    searchHotels() {
      // Set the indicator that a call is in progress.
      this.isLoading = true;
      // TODO: Use the Service object to `GET /hotels?state=xx`. Set the body of the result to `this.hotels`. Handle errors.
      hotelService
        .searchHotels(this.filter)
        .then((response) => {
          this.hotels = response.data;
          // Indicate the call is complete.
          this.isLoading = false;
        })
        .catch((error) => {
          // Indicate the call is complete.
          this.isLoading = false;
          utility.handleAxiosError(error, "Search hotels");
        });
    },
    /***
     * Navigate to the HotelDetails view
     */
    details(id) {
      this.$router.push({ name: "hotelDetails", params: { id: id } });
    },
    /***
     * Use the utility object to display a Number as USD.
     */
    formatCurrency(value) {
      return utility.formatCurrency(value);
    },
  },

  // TODO: Create a Vue Lifecycle hook function which calls `this.getHotels()` and `this.getStates()`
  created() {
    this.getHotels();
    this.getStates();
  },
};
</script>

<style scoped>
#hotel-grid {
  display: grid;
  margin: auto;
  grid-template-columns: auto auto 100px;
  column-gap: 10px;
  row-gap: 6px;
  max-width: 800px;
}

.table-heading {
  font-weight: bold;
}
</style>