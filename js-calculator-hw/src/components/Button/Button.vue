<script setup lang="ts">
import { useCalcStore } from '@/stores/calcStore'

interface Props {
  content: string
  callback: Function
}

const props = defineProps<Props>()
const calc = useCalcStore()
</script>
<template>
  <button
    type="button"
    @click="$props.callback()"
    :class="{ btn: true, active: content === calc.operation, accent: content === '=' || content === 'AC' }"
  >
    {{ $props.content }}
  </button>
</template>
<style scoped>
.btn {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #ffffffa8;
  outline: none;
  font-weight: 500;
}
.btn.accent {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.btn.active,
.btn.accent {
  background: linear-gradient(120deg, rgb(251, 251, 255) 0%, rgb(215, 223, 252) 100%);
  outline: 3px solid #fff;
}
</style>
