<template>
  <div id="recipes-view">
    <h1 id="recipes-title">Recipes</h1>
    <recipe-cards :recipes="recipes ? recipes : recipeData" />
  </div>
</template>

<script>
import RecipeCards from "../components/RecipeCards.vue";
import recipeService from "../services/RecipeService";

export default {
  name: "MultipleRecipesView",
  components: {
    RecipeCards,
  },
  props: {
    recipes: [],
  },
  data() {
    return {
      recipeData: []
    };
  },
  methods: {
    getAllRecipes() {
      return recipeService.getRecipes();
    },
  },
  created() {
    if (!this.recipes) {
      this.recipeData = this.getAllRecipes();
    }
  },
};
</script>

<style scoped>
#recipes-title {
  text-align: center;
}
</style>