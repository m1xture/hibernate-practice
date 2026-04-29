<script setup lang="ts">
interface Props {
  num: number | null
  onChange?: (event: Event) => void
  isDisabled?: boolean
}

const props = defineProps<Props>()

const handleBeforeInput = (e: InputEvent) => {
  if (e.data && !/^[0-9.]$/.test(e.data)) {
    e.preventDefault()
  }
}
</script>
<template>
  <input
    class="input"
    type="text"
    :value="$props.num ?? ''"
    :disabled="props.isDisabled"
    @beforeinput="handleBeforeInput"
    @input="props.onChange?.($event)"
  />
</template>
<style scoped>
.input {
  background: linear-gradient(120deg, rgb(251, 251, 255) 0%, rgb(215, 223, 252) 100%);
  padding: 12px 16px;
  width: calc(100% - 32px);
  border-radius: 16px;
  margin-bottom: 6px;
  &:last-child {
    margin-bottom: 0;
  }
}
</style>
