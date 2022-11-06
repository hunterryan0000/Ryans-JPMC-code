<template>
  <div id="single-recipe-view">
    <recipe-details :recipe="this.recipe"> </recipe-details>
    <div id="reviews-section">
      <review-section :recipe="this.recipe" />
    </div>
  </div>
</template>

<script>
import ReviewSection from "../components/ReviewSection.vue";
import RecipeDetails from "../components/RecipeDetails.vue";
import recipeService from "../services/RecipeService";

export default {
  name: "SingleRecipeView",
  components: {
    RecipeDetails,
    ReviewSection,
  },

  data() {
    return {
      recipe: {},
    };
  },

  created() {
    this.recipe = recipeService.getRecipe([this.$route.params.id]);
  },
};
</script>

<style scoped>
#reviews-title {
  font-size: 2rem;
  font-weight: 500;
  padding-left: 10px;
}
</style>