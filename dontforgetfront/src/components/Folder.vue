<template>
  <div>
    <b-card-title class="text-center">{{ folder.name }}</b-card-title>
    <b-card-text class="text-center"> actividades: {{ folder.activities.length }} </b-card-text>
  </div>
</template>

<script>
export default {
  name: "Folder",
  props: ["carpeta"],
  data() {
    return {
      folder: {
        id: this.carpeta.id,
        name: this.carpeta.name,
        activities: this.carpeta.activities,
      },
    };
  },
};
</script>

<style scoped></style>
