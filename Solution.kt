
import kotlin.math.pow
import kotlin.math.log10

class Solution {

    private companion object {
        val MODULO_VALUE: Int = (10.0).pow(9.0).toInt() + 7
    }

    fun sumDecoded(input: LongArray): Int {
        var sumOfDecodedNumbers: Long = 0
        for (value in input) {
            sumOfDecodedNumbers = (sumOfDecodedNumbers + decode(value)) % MODULO_VALUE
        }
        return sumOfDecodedNumbers.toInt()
    }

    private fun decode(value: Long): Long {
        val digits = (value / 10)
        val digitsWidth = (log10(digits.toDouble()) + 1).toInt()
        val baseWidth = (value % 10).toInt()

        val base = (digits / (10.0).pow(digitsWidth - baseWidth)).toInt()
        val exponent = (digits % (10.0).pow(digitsWidth - baseWidth)).toInt()

        return calculateBinaryExponentiationWithModulo(base, exponent)
    }

    private fun calculateBinaryExponentiationWithModulo(base: Int, exponent: Int): Long {
        var exponent = exponent
        var subexponent: Long = 1
        var baseRaisedToSubexponent: Long = base.toLong()

        while (exponent > 0) {
            if ((exponent and 1) == 1) {
                subexponent = subexponent * baseRaisedToSubexponent % MODULO_VALUE
            }
            baseRaisedToSubexponent = baseRaisedToSubexponent * baseRaisedToSubexponent % MODULO_VALUE
            exponent = exponent shr 1
        }
        return subexponent
    }
}
