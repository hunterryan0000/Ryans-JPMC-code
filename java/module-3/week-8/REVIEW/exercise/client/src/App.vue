<template>
  <div id="app">
    <header>Hotel Shopper</header>
    <nav>
      <router-link v-bind:to="{ name: 'home' }">
        <font-awesome-icon icon="fa-solid fa-house" class="fa-icon" />
        Home</router-link
      >
    </nav>
    <main>
      <router-view />
    </main>
    <footer>&copy; 2022. All rights reserved.</footer>
  </div>
</template>

<style scoped>
#app {
  height: 100%;
  display: grid;
  grid-template-rows: 80px 35px 1fr 25px;
  grid-template-areas:
    "header"
    "nav"
    "main"
    "footer";
}

header {
  display: flex;
  align-items: center;
  justify-content: center;
  grid-area: header;
  background-color: #bbb;
  font-size: 2rem;
  padding: 0 20px;
}

nav {
  grid-area: nav;
  background-color: rgba(0, 0, 0, 0.7);
  border-top: 1px solid white;
  border-bottom: 2px solid white;
}

nav a {
  display: inline-block;
  text-decoration: none;
  color: white;
  padding: 5px 10px;
}

nav a.router-link-exact-active {
  background-color: rgba(0, 0, 0, 0.8);
}

main {
  grid-area: main;
  padding: 5px 20px;
  background: linear-gradient(rgba(0, 0, 0, 0.05), rgba(0, 0, 0, 0.2));
  overflow: auto;
}

footer {
  grid-area: footer;
  display: flex;
  flex-direction: row;
  align-items: center;
  font-size: 0.8rem;
  padding: 0 10px;
  border-top: 2px solid #777;
  background-color: rgb(0, 0, 0);
  color: rgb(200, 200, 200);
}
</style>