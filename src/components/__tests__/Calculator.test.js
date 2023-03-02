import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import Calculator from 'src/components/CalculatorComponents.vue'

test("add two numbers", async () => {
  const wrapper = mount(Calculator)
  const button1 = wrapper.find('.calcBtn:nth-child(5)')
  await button1.trigger('click')
  const button2 = wrapper.find('.calcBtn:nth-child(6)')
  await button2.trigger('click')
  expect(wrapper.find('.result').text()).toBe('12')
})

test("Add number and remove it", async () => {
  const wrapper = mount(Calculator)
  const button1 = wrapper.find('.calcBtn:nth-child(5)')
  await button1.trigger('click')
  const button2 = wrapper.find('.calcBtn:nth-child(1)')
  await button2.trigger('click')
  expect(wrapper.find('.result').text()).toBe('0')
})