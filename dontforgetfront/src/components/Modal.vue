<template>
  <b-modal v-bind:id="modal" hide-header-close>
    <b-form>
      <b-form-input required placeholder="name" label="name" v-model="name">
      </b-form-input>
      <b-form-select v-model="select" :options="folders"></b-form-select>
    </b-form>
  </b-modal>
</template>

<script>
export default {
  name: "Modal",
  props: ["modal"],
  data() {
    return {
      select: null,
      folders: [
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
};
</script>

<style scoped></style>
