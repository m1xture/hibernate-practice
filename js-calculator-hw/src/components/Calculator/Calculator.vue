<script setup lang="ts">
import { ref } from 'vue'
import Input from '../Input/Input.vue'
import Button from '../Button/Button.vue'
import ButtonStyleEnum from '../Button/ButtonStyleEnum'

const currentValue = ref<number>(0)
const inputValue = ref<number>(0)
const errorMessage = ref<string>('')

const setInputValue = (n: number) => (inputValue.value = n)

const appendDigit = (digit: number) => {
  inputValue.value = Number(`${inputValue.value}${digit}`)
}


const clear = () => {
  currentValue.value = 0
  inputValue.value = 0
  errorMessage.value = ''
}
const add = () => (currentValue.value += inputValue.value)
const subtract = () => (currentValue.value -= inputValue.value)
const multiply = () => (currentValue.value *= inputValue.value)
const divide = () => {
  if (inputValue.value === 0) {
    errorMessage.value = 'Cannot divide by zero'
    return
  }
  currentValue.value /= inputValue.value
}
// const squareRoot = () => {
//   if (currentValue.value < 0) {
//     errorMessage.value = 'Cannot get a square root from inputValueber < 0'
//     return
//   }
//   currentValue.value = parseFloat(Math.sqrt(currentValue.value).toFixed(3))
// }
</script>

<template>
  <div class="info">
    <p class="result">Result: {{ currentValue }}</p>
    <p class="error">{{ errorMessage }}</p>
  </div>
  <Input :value="inputValue" :set-value="setInputValue" />
  <div class="grid">
    <Button
      v-for="digit in [7, 8, 9, 4, 5, 6, 1, 2, 3, 0]"
      :key="digit"
      :content="String(digit)"
      :callback="() => appendDigit(digit)"
      :btn-style="ButtonStyleEnum.DEFAULT"
      :style="{ gridArea: `d${digit}` }"
    />
    <Button
      content="AC"
      :callback="clear"
      :btn-style="ButtonStyleEnum.ACCENT"
      style="grid-area: ac"
    />
    <Button
      content="+"
      :callback="add"
      :btn-style="ButtonStyleEnum.SECONDARY"
      style="grid-area: add"
    />
    <Button
      content="-"
      :callback="subtract"
      :btn-style="ButtonStyleEnum.SECONDARY"
      style="grid-area: sub"
    />
    <Button
      content="*"
      :callback="multiply"
      :btn-style="ButtonStyleEnum.SECONDARY"
      style="grid-area: mul"
    />
    <Button
      content="/"
      :callback="divide"
      :btn-style="ButtonStyleEnum.SECONDARY"
      style="grid-area: div"
    />
  </div>
</template>
<style scoped>
.grid {
  background: linear-gradient(120deg, rgb(251, 251, 255) 0%, rgb(215, 223, 252) 100%);
  display: grid;
  gap: 4px;
  padding: 16px;
  border-radius: 12px;
  grid-template-columns: repeat(4, 1fr);
  grid-template-areas:
    'd7  d8  d9  add'
    'd4  d5  d6  sub'
    'd1  d2  d3  mul'
    'ac  d0  .   div';
  place-items: center;
}

.span-2 {
  grid-column: span 2;
}
.result,
.error {
  font-size: 15px;
  opacity: 65%;
}
.info {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 2px;
}
</style>
