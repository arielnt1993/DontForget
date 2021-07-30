<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand>
        <router-link class="text-white text-decoration-none" to="/">
          DontForget Me
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle
        class="collapseBtn"
        target="nav-collapse"
      ></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item>
            <router-link
              to="/actividades"
              class="text-decoration-none text-white-50"
            >
              Actividades
            </router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link
              to="/carpetas"
              class="text-decoration-none text-white-50"
            >
              Carpetas
            </router-link>
          </b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="search">
          <b-nav-form>
            <b-form-input
              size="sm"
              class="mr-sm-2"
              placeholder="Search"
            ></b-form-input>
          </b-nav-form>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view :key="$route.fullPath" />
  </div>
</template>

<script>
export default {
  name: "Navbar",
};
</script>

<style scoped lang="scss">
.search {
  margin: auto 1% auto auto;
}
.collapseBtn {
  margin-right: 1%;
}
</style>
