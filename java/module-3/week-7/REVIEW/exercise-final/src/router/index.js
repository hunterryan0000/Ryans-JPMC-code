import Vue from 'vue'
import VueRouter from 'vue-router'

import HomeView from '../views/HomeView.vue'
import SingleRecipeView from '../views/SingleRecipeView.vue'
import MultipleRecipesView from '../views/MultipleRecipesView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/recipes',
    name: 'recipes',
    props: true,
    component: MultipleRecipesView
  },
  {
    path: '/recipe/:id',
    name: 'recipe',
    props: true,
    component: SingleRecipeView,
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router 
