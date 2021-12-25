package ru.netology.nmedia

import java.math.RoundingMode
import java.text.DecimalFormat

object CounterService {

    private fun counterIntegers(value: Int): String {
        val result = when (value) {
            in 0..999 -> value
            in 1_000..9_999 -> value / 1_000
            in 10_000..99_999 -> value / 1_000
            in 100_000..999_999 -> value / 1_000
            in 1_000_000..9_999_999 -> value / 1_000_000

            else -> 0
        }
        return result.toString()
    }

    fun counterWithRemains(value: Int): String {
        val valueToFloat = value.toFloat()

        val re = if (valueToFloat in 1_000f..9_999f && valueToFloat % 1_000 != 0f) {
            value / 1_000
            thousandsAndMillions(valueToFloat / 1_000)

        } else if (valueToFloat in 10_000f..99_999f && valueToFloat % 1_000 != 0f) {
            value / 1_000
            tensThousands(valueToFloat / 1_000)

        } else if (valueToFloat in 100_000f..999_999f && valueToFloat % 1_000 != 0f) {
            value / 1_000
            tensThousands(valueToFloat / 1_000)

        } else if (value > 999_999) {
            value / 1_000_000
            thousandsAndMillions(valueToFloat / 1_000_000)

        } else counterIntegers(value)

        return re.toString() + letterTheEnd(value)
    }

    private fun thousandsAndMillions(value: Float): String {

        val tenths = DecimalFormat("#.#")
        tenths.roundingMode = RoundingMode.DOWN

        return tenths.format(value)
    }

    private fun tensThousands(value: Float): String {

        val integer = DecimalFormat("#")
        integer.roundingMode = RoundingMode.DOWN

        return integer.format(value)
    }


    private fun letterTheEnd(value: Int): String {

        val result = if (value in 1_000..999_999) {
            "K"
        } else if (value > 999_999) {
            "M"
        } else ""

        return result
    }
}