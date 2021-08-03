<template>
  <div class="d-flex justify-content-center">
    <b-card
      v-for="(activity, index) in activities"
      :key="activity.id"
      class="mx-2 my-5 card"
    >
      <b-button variant="danger" @click="aviso(activity, index)">x</b-button>
      <b-card-body>
        <Task v-bind:task="activity" />
      </b-card-body>
    </b-card>

    <div v-if="exemptions.length !== 0">
      <p class="text-danger text-center">{{ exemptions }}</p>
      <b-button class="my-auto button" variant="success" v-b-modal.mod>
        agregar actividades
      </b-button>
    </div>

    <b-button
      v-if="activities.length !== 0"
      v-cloak
      class="my-auto button"
      variant="success"
      v-b-modal.mod
    >
      {{ activities.length === 0 ? "agregar actividad" : "+" }}
    </b-button>

    <Modal v-bind:modal="'mod'" />
  </div>
</template>

<script>
import Task from "@/components/Task";
import Modal from "@/components/Modal";
export default {
  name: "Tasks",
  components: { Task, Modal },
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
      .then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          throw new Error(res.status);
        }
      })
      .then((data) => (this.activities = data))
      .catch((err) => {
        return (this.exemptions = err);
      });
  },
  methods: {
    aviso(activity, index) {
      fetch("http://localhost:8080/api/manager/activity", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "DELETE",
        body: JSON.stringify({
          id: activity.id,
        }),
      });
      this.$delete(this.activities, index);
    },
  },
};
</script>

<style lang="scss" scoped>
.card {
  width: 170px;
}
</style>
