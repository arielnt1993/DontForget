<template>
  <b-modal v-bind:id="modal" hide-header-close>
    <b-form-select v-model="select" :options="options"/>
  </b-modal>
</template>

<script>
export default {
  name: "Modal",
  props: ["modal"],
  data() {
    return {
      select: null,
      options: [
        {
          value: null,
          text: "none",
        },
      ],
    };
  },
  methods: {
    resetModal() {},
  },
  beforeMount() {
    fetch("http://localhost:8080/api/manager/folders", {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    })
      .then((res) => res.json())
      .then((data) => {
        data.forEach((ele) => {
          let op = { value: ele.id, text: ele.name };
          this.options.push(op);
          console.log(op);
          return this.options;
        });
      })
      .catch((err) => console.log(err));
  },
};
</script>

<style scoped></style>
