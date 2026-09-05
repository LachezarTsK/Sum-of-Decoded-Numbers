
#include <cmath>
#include <vector>
using namespace std;

class Solution {

    inline static int MODULO_VALUE = pow(10, 9) + 7;

public:
    int sumDecoded(vector<long long>& input) {
        long long sumOfDecodedNumbers = 0;
        for (const auto& value : input) {
            sumOfDecodedNumbers = (sumOfDecodedNumbers + decode(value)) % MODULO_VALUE;
        }
        return sumOfDecodedNumbers;
    }

private:
    static long long decode(long long value) {
        long long digits = value / 10;
        int digitsWidth = log10(digits) + 1;
        int baseWidth = (value % 10);

        int base = digits / pow(10, digitsWidth - baseWidth);
        int exponent = digits % static_cast<int>(pow(10, digitsWidth - baseWidth));

        return calculateBinaryExponentiationWithModulo(base, exponent);
    }

    static long long calculateBinaryExponentiationWithModulo(int base, int exponent) {
        long long subexponent = 1;
        long long baseRaisedToSubexponent = base;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                subexponent = subexponent * baseRaisedToSubexponent % MODULO_VALUE;
            }
            baseRaisedToSubexponent = baseRaisedToSubexponent * baseRaisedToSubexponent % MODULO_VALUE;
            exponent >>= 1;
        }
        return subexponent;
    }
};
