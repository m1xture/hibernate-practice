<script setup lang="ts">
import ButtonStyleEnum from './ButtonStyleEnum'

interface Props {
  content: string
  callback: () => void
  btnStyle: ButtonStyleEnum
}

const props = defineProps<Props>()
</script>
<template>
  <button type="button" @click="$props.callback()" :class="['btn', props.btnStyle]">
    {{ $props.content }}
  </button>
</template>
<style>
.btn {
  /* aspect-ratio: 1/1; */
  width: 48px;
  height: 48px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #ffffffa8;
  font-weight: 500;
}
.btn--accent {
  background: linear-gradient(120deg, rgb(251, 251, 255) 0%, rgb(215, 223, 252) 100%);
  outline: 3px solid #fff;
}
.btn--secondary {
  background-color: #ffffff76;
}
</style>
