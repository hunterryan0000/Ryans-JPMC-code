<template>
  <div id="reservation-details">
    <section class="set">
      <div>Confirmation:</div>
      <div>{{ reservation.id }}</div>
    </section>
    <section class="set">
      <div>Name:</div>
      <div>{{ reservation.fullName }}</div>
    </section>
    <section class="set"></section>
    <section class="set">
      <div>Dates:</div>
      <div>{{ reservation.checkinDate }} to {{ reservation.checkoutDate }}</div>
    </section>
    <section class="set"></section>
    <section class="set">
      <div>Guests:</div>
      <div>{{ reservation.guests }}</div>
    </section>
  </div>
</template>

<script>
export default {
  name: "ReservationDetails",

  props: {
    // A reservation is required to be passed from the parent component as a prop.
    reservation: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
#reservation-details {
  display: grid;
  grid-template-columns: 200px 1fr;
  padding: 5px 0;
}
section.set {
  display: flex;
  gap: 8px;
}

section.set > div:first-child {
  font-weight: bold;
}
</style>