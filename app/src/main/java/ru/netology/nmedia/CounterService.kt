package ru.netology.nmedia

import java.math.RoundingMode
import java.text.DecimalFormat

object CounterService {

    private fun counterIntegers(value: Float): String {
      val result =  when(value) {
          in 0f..999f -> value
          in 1000f..9999f -> value/1000
          in 10000f..99999f -> value/1000
          in 100000f..999999f -> value/1000
          in 1000000f..9999999f -> value/1000000

          else -> 0f
        }
        return result.toLong().toString()

    }

    fun counterWithRemains(value: Float): String {
        val result =  if (value in 1000f..9999f && value%1000 != 0f) {

            value/1000
            thousandsAndMillions(value/1000)

        }
        else if (value in 10_000f..99_999f && value%1000 != 0f) {

            value/1000
            tensThousands(value/1000)

        }else if (value in 100_000f..999_999f && value%1000 != 0f) {

            value/1000
            tensThousands(value/1000)

        }else if (value > 999_999 ) {

            value/1_000_000
            thousandsAndMillions(value/1_000_000)

        }
        else counterIntegers(value)

        return result + letterTheEnd(value)
    }

    private fun thousandsAndMillions (value:Float): String {

        val tenths = DecimalFormat("#.#")
        tenths.roundingMode = RoundingMode.DOWN

        return tenths.format(value)
    }

    private fun tensThousands (value:Float): String {

        val integer = DecimalFormat("#")
        integer.roundingMode = RoundingMode.DOWN

        return integer.format(value)
    }


    private fun letterTheEnd (value: Float): String {

        val result = if (value in 1000f..999_999f) {
            "K"
        }else if (value > 999_999f) {
            "M"
        }else ""

        return result
    }
}