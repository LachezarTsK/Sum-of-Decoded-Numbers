
/**
 * @param {number[]} input
 * @return {number}
 */
var sumDecoded = function (input) {
    let sumOfDecodedNumbers = 0;
    for (let value of input) {
        sumOfDecodedNumbers = (sumOfDecodedNumbers + decode(value)) % Util.MODULO_VALUE;
    }
    return  sumOfDecodedNumbers;
};

/**
 * @param {number} value
 * @return {number}
 */
function decode(value) {
    const digits = Math.floor(value / 10);
    const digitsWidth = Math.floor(Math.log10(digits)) + 1;
    const baseWidth = (value % 10);

    const base = Math.floor(digits / Math.pow(10, digitsWidth - baseWidth));
    const exponent = digits % Math.pow(10, digitsWidth - baseWidth);

    return calculateBinaryExponentiationWithModulo(base, exponent);
}

/**
 * @param {number} base
 * @param {number} exponent
 * @return {number}
 */
function calculateBinaryExponentiationWithModulo(base, exponent) {
    let subexponent = BigInt(1);
    let baseRaisedToSubexponent = BigInt(base);

    while (exponent > 0) {
        if ((exponent % 2) === 1) {
            subexponent = subexponent * baseRaisedToSubexponent % BigInt(Util.MODULO_VALUE);
        }
        baseRaisedToSubexponent = baseRaisedToSubexponent * baseRaisedToSubexponent % BigInt(Util.MODULO_VALUE);
        exponent = Math.floor(exponent / 2);
    }
    return Number(subexponent);
}

class Util {
    static MODULO_VALUE = Math.pow(10, 9) + 7;
}
