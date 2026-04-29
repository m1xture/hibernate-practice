import { defineStore } from 'pinia'

export const OPERATIONS = ['+', '-', '*', '/'] as const
export type Operation = (typeof OPERATIONS)[number]

interface CalcState {
  numberA: number | null
  numberB: number | null
  operation: Operation | null
  result: number | null
  errorMsg: string | null
}

export const useCalcStore = defineStore('calc', {
  state: (): CalcState => ({
    numberA: null,
    numberB: null,
    operation: null,
    result: null,
    errorMsg: null,
  }),

  getters: {
    hasResult: (state): boolean => state.result !== null,
  },

  actions: {
    setNumberA(value: number): void {
      this.numberA = value
    },

    setNumberB(value: number): void {
      this.numberB = value
    },

    setOperation(op: Operation): void {
      this.operation = op
    },

    calculate(): void {
      if (this.numberA === null || this.numberB === null || this.operation === null) {
        this.errorMsg = 'Both numbers and an operation must be set before calculating.'
        return
      }

      switch (this.operation) {
        case '+':
          this.result = this.numberA + this.numberB
          break
        case '-':
          this.result = this.numberA - this.numberB
          break
        case '*':
          this.result = this.numberA * this.numberB
          break
        case '/':
          if (this.numberB === 0) {
            this.errorMsg = 'Division by zero is forbidden'
            this.result = null
          } else {
            this.result = this.numberA / this.numberB
          }
          break
      }
    },

    reset(): void {
      this.numberA = null
      this.numberB = null
      this.operation = null
      this.result = null
    },
  },
})
