<script lang="ts" setup>
import { computed } from 'vue'
import { useCalcStore } from '@/stores/calcStore'
import { OPERATIONS } from '@/stores/calcStore'
import Input from '../Input/Input.vue'
import Button from '../Button/Button.vue'

const calc = useCalcStore()
</script>

<template>
  <div class="calc-block">
    <h4 v-if="calc.errorMsg" class="error-text">
      {{ calc.errorMsg }}
    </h4>
  </div>

  <div>
    <Input
      :num="calc.numberA"
      :on-change="
        (e: Event) => calc.setNumberA(Number((e.currentTarget as HTMLInputElement).value))
      "
    />
    <ul class="grid">
      <li v-for="op in OPERATIONS" class="item">
        <Button :content="op" :callback="() => calc.setOperation(op)" />
      </li>
    </ul>
    <Input
      :num="calc.numberB"
      :on-change="
        (e: Event) => calc.setNumberB(Number((e.currentTarget as HTMLInputElement).value))
      "
    />
    <div class="bottom">
      <Button :content="'='" :callback="() => calc.calculate()" />
      <Button :content="'AC'" :callback="() => calc.reset()" />
    </div>

    <Input v-if="calc.result !== null" :num="calc.result" :is-disabled="true" />
  </div>
</template>

<style scoped>
.error-text {
  opacity: 75%;
  font-size: 16px;
  margin-bottom: 4px;
}
.grid {
  background: linear-gradient(120deg, rgb(251, 251, 255) 0%, rgb(215, 223, 252) 100%);
  display: grid;
  gap: 4px;
  padding: 16px;
  border-radius: 16px;
  grid-template-columns: 1fr 1fr;
  place-items: center;
  margin-bottom: 6px;
}
.bottom {
  display: flex;
  align-items: center;
  justify-content: space-around;
}
</style>
