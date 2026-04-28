<script setup lang="ts">
interface Props {
  value: number
  setValue: (n: number) => void
}

const props = defineProps<Props>()

const handleBeforeInput = (e: InputEvent) => {
  if (e.data && !/^[0-9.]$/.test(e.data)) {
    e.preventDefault()
  }
}

const handleInput = (e: Event) => {
  const val = Number((e.target as HTMLInputElement).value)
  if (!isNaN(val)) props.setValue(val)
}
</script>
<template>
  <input
    type="text"
    inputmode="decimal"
    pattern="[0-9]*\.?[0-9]*"
    class="input"
    :value="props.value"
    @beforeinput="handleBeforeInput"
    @input="handleInput"
  />
</template>
<style lang="css" scoped>
.input {
  width: calc(100% - 24px);
  border-radius: 12px;
  margin-bottom: 4px;
  background: linear-gradient(120deg, rgb(251, 251, 255) 0%, rgb(215, 223, 252) 100%);
  padding: 12px 12px;
  font-size: 16px;
}
</style>
