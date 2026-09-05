
public class Solution {

    private static final int MODULO_VALUE = (int) Math.pow(10, 9) + 7;

    public int sumDecoded(long[] input) {
        long sumOfDecodedNumbers = 0;
        for (long value : input) {
            sumOfDecodedNumbers = (sumOfDecodedNumbers + decode(value)) % MODULO_VALUE;
        }
        return (int) sumOfDecodedNumbers;
    }

    private static long decode(long value) {
        long digits = value / 10;
        int digitsWidth = (int) Math.log10(digits) + 1;
        int baseWidth = (int) (value % 10);

        int base = (int) (digits / Math.pow(10, digitsWidth - baseWidth));
        int exponent = (int) (digits % Math.pow(10, digitsWidth - baseWidth));

        return calculateBinaryExponentiationWithModulo(base, exponent);
    }

    private static long calculateBinaryExponentiationWithModulo(int base, int exponent) {
        long subexponent = 1;
        long baseRaisedToSubexponent = base;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                subexponent = subexponent * baseRaisedToSubexponent % MODULO_VALUE;
            }
            baseRaisedToSubexponent = baseRaisedToSubexponent * baseRaisedToSubexponent % MODULO_VALUE;
            exponent >>= 1;
        }
        return subexponent;
    }
}
