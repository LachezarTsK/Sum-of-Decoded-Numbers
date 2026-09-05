
using System;

public class Solution
{
    private static readonly int MODULO_VALUE = (int)Math.Pow(10, 9) + 7;

    public int SumDecoded(long[] input)
    {
        long sumOfDecodedNumbers = 0;
        foreach (long value in input)
        {
            sumOfDecodedNumbers = (sumOfDecodedNumbers + Decode(value)) % MODULO_VALUE;
        }
        return (int)sumOfDecodedNumbers;
    }

    private static long Decode(long value)
    {
        long digits = value / 10;
        int digitsWidth = (int)Math.Log10(digits) + 1;
        int baseValueWidth = (int)(value % 10);

        int baseValue = (int)(digits / Math.Pow(10, digitsWidth - baseValueWidth));
        int exponent = (int)(digits % Math.Pow(10, digitsWidth - baseValueWidth));

        return CalculateBinaryExponentiationWithModulo(baseValue, exponent);
    }

    private static long CalculateBinaryExponentiationWithModulo(int baseValue, int exponent)
    {
        long subexponent = 1;
        long baseRaisedToSubexponent = baseValue;

        while (exponent > 0)
        {
            if ((exponent & 1) == 1)
            {
                subexponent = subexponent * baseRaisedToSubexponent % MODULO_VALUE;
            }
            baseRaisedToSubexponent = baseRaisedToSubexponent * baseRaisedToSubexponent % MODULO_VALUE;
            exponent >>= 1;
        }
        return subexponent;
    }
}
