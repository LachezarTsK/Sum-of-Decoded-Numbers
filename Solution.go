
package main
import "math"

var MODULO_VALUE int = int(math.Pow(10.0, 9.0)) + 7

func sumDecoded(input []int64) int {
    var sumOfDecodedNumbers int64 = 0
    for _, value := range input {
        sumOfDecodedNumbers = (sumOfDecodedNumbers + decode(value)) % int64(MODULO_VALUE)
    }
    return int(sumOfDecodedNumbers)
}

func decode(value int64) int64 {
    digits := (value / 10)
    digitsWidth := int(math.Log10(float64(digits)) + 1)
    baseWidth := int(value % 10)

    base := int(digits / int64(math.Pow(10.0, float64(digitsWidth - baseWidth))))
    exponent := int(digits % int64(math.Pow(10.0, float64(digitsWidth - baseWidth))))

    return calculateBinaryExponentiationWithModulo(base, exponent)
}

func calculateBinaryExponentiationWithModulo(base int, exponent int) int64 {
    subexponent := int64(1)
    baseRaisedToSubexponent := int64(base)

    for exponent > 0 {
        if (exponent & 1) == 1 {
            subexponent = subexponent * baseRaisedToSubexponent % int64(MODULO_VALUE)
        }
        baseRaisedToSubexponent = baseRaisedToSubexponent * baseRaisedToSubexponent % int64(MODULO_VALUE)
        exponent >>= 1
    }
    return subexponent
}
