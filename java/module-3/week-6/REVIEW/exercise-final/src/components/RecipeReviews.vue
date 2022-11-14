<template>
  <div id="ratings">
    <h1>Reviews</h1>
    <div id="avg-rating">
      <p class="bold">Avg. Rating: {{averageRating}}</p> 
    </div>
    <div class="review" v-for="review in reviews" v-bind:review="review"
    v-bind:key="review.name">
    <div class="icon-rating" >
      <img v-for="star in review.stars" v-bind:key="star" src="../assets/star-solid.svg" width="15px;" />
    </div>
    <div class="review-name">
      <p>{{review.name}}</p>
    </div>
    <div class="review-location">
      <p>{{review.location}}</p>
    </div>
    <div class="review-text">
      <p>{{review.body}}</p>
    </div>
    </div>
  </div>
</template>

<script>
import reviewData from "../assets/ReviewData";

export default {
  name: 'RecipeReviews',
  data() {
    return {
    reviews: reviewData.getReviewData(),
    }
  },
  computed: {
    averageRating() {
      return this.reviews.reduce((s, elem) => {
        return s + elem.stars;
      }, 0) / this.reviews.length;
    }
  },
}
</script>

<style scoped>
#ratings {
  margin-left: 10px;
}
.bold {
  font-weight: bold;
  text-align: left;
}

img {
  margin-right: 5px;
}

.review {
margin-top: 8px;
padding: 10px;
display: grid;
grid-template-columns: 1fr 1fr;
  grid-template-areas:
      "icon icon"
      "name location"
      "review review";
}

.review-name {
  text-align: left;
  grid-area: name;
}

.review-location {
  text-align: right;
  grid-area: location;
}

.review-text {
  text-align: left;
  grid-area: review;
}

.icon-rating {
  margin: 5px;
  grid-area: icon;
  display: inline-flex;
  width: 100%;
  flex-wrap: wrap;
  flex-direction: row;
  justify-content: left;
}
</style>