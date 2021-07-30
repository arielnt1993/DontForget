<template>
  <div class="d-flex justify-content-center">
    <b-card v-for="folder in folders" :key="folder.id" class="mx-2 my-5 folder">
      <b-button variant="danger">x</b-button>
      <router-link v-bind:to="'/actividades'" class="text-decoration-none text-dark">
        <img
          class="card-img-top image mx-auto d-block"
          v-if="folder.activities.length !== 0"
          src="../assets/folderfull.svg"
          alt=""
        />
        <img
          class="card-img-top image mx-auto d-block"
          v-if="folder.activities.length === 0"
          src="../assets/folderempty.svg"
          alt=""
        />
        <b-card-body>
          <Folder v-bind:carpeta="folder" />
        </b-card-body>
      </router-link>
    </b-card>

    <b-button
      class="h-50 my-auto button"
      variant="success"
      @click="test"
      v-if="folders.length !== 0"
    >
      +
    </b-button>
    <div v-if="exceptions.length !== 0">
      <p class="text-danger">{{ exceptions }}</p>
    </div>
  </div>
</template>

<script>
import Folder from "@/components/Folder";
export default {
  name: "Folders",
  components: {
    Folder,
  },
  data() {
    return {
      folders: [],
      exceptions: [],
    };
  },
  mounted() {
    try {
      fetch("http://localhost:8080/api/manager/folders", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "GET",
      })
        .then((res) => res.json())
        .then((data) => (this.folders = data))
        .catch((ex) => (this.exceptions = ex));
    } catch (e) {
      this.exceptions = e;
    }
  },
  methods: {
    test: function () {
      return alert("test");
    },
  },
};
</script>

<style lang="scss" scoped>
.image {
  width: 25%;
}
.folder {
  width: 15%;
}
.button {
  font-weight: bold;
}
</style>
