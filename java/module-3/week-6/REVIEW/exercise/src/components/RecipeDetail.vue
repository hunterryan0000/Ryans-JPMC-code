<template>
    <div id="details">
        <section id="title">
            <h2 id="title">{{recipe.label}}</h2>
            <a>(hide image)</a>
            <img v-show="showImage" v-bind:src='require(`../assets/${recipe.image}`)' />
        </section>
        <section id="info"></section>
        <section id="ingredients"></section>
    </div>
</template>

<script>
import recipeData from "../assets/RecipeData";

export default {
    name: 'RecipeDetail',
    data() {
        return {
            recipe: recipeData.getRecipeData(),
            showImage: true,
            ingredientUnit: "imperial",
        }
    },
}
</script>

<style scoped>
#details {
    text-align: left;
    display: grid;
    grid-template-columns: 1fr 2fr;
    grid-template-areas:
        "title title"
        "img img"
        "info ingredients";
}

#unit {
    display: flex;
}

img {
    grid-area: img;
    max-height: 1050px;
    max-width: 100%;
}

#title {
    text-align: center;
    grid-area: title;
}

#title h2 {
    display: inline-block;
    margin-right: 8px;
}

#info {
    grid-area: info;
}

#ingredients {
    grid-area: ingredients;
}

#ingredients h3 {
    text-align: center;
}

#unit-btns {
    display: flex;
    justify-content: center;
}
</style>