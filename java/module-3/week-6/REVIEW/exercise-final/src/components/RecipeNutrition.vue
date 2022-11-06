<template>
    <div>
        <h1>Nutrition Info</h1>
        <div id="serving-size">
            <button class="tile-btn" v-on:click="isPerServing=false"
            v-bind:disabled="isPerServing == false">Total</button>
            <button class="tile-btn" v-on:click="isPerServing=true"
            v-bind:disabled="isPerServing == true">Per Serving</button>
        </div>
        <section id="ingredients">
            <div v-for="ingredient in nutrients" v-bind:ingredient="ingredient" v-bind:key="ingredient.label">
                <p>{{ ingredient.label }}: {{ formatQty(ingredient.quantity) }} {{ ingredient.unit }}</p>
            </div>
        </section>
    </div>
</template>

<script>
import recipeData from "../assets/RecipeData";
import nutritionData from "../assets/NutritionData";

export default {
    name: 'RecipeNutrition',
    data() {
        return {
            recipe: recipeData.getRecipeData(),
            nutrients: nutritionData.getNutritionData(),
            isPerServing: false,
        }
    },
    methods: {
        formatQty(quantity) {
            if(this.isPerServing) {
                return Math.trunc(quantity / this.recipe.yield);
            } else {
                return Math.trunc(quantity);
            }
        }
    }
}
</script>

<style scoped>
#ingredients div {
    display: flex;
    justify-content: center;
}
</style>