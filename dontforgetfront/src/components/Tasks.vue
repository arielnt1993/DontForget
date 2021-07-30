<template>
  <div class="d-flex justify-content-center">
    <b-card v-for="activity in activities" :key="activity.id" class="mx-2 mt-5">
      <b-card-body>
        <Task v-bind:task="activity" />
      </b-card-body>
    </b-card>

    <div v-if="exemptions.length !== 0">
      <p>{{ exemptions.message }}</p>
    </div>
  </div>
</template>

<script>
import Task from "@/components/Task";
export default {
  name: "Tasks",
  components: { Task },
  data() {
    return {
      activities: [],
      exemptions: [],
    };
  },
  mounted() {
    fetch("http://localhost:8080/api/manager/activities", {
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((res) => res.json())
      .then((data) => (this.activities = data))
      .catch((ex) => (this.exemptions = ex));
  },
};
</script>

<style scoped></style>
