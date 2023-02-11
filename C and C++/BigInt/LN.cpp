#include "LN.h"
#include <cstdlib>

LN::LN(long long int number) {
    if (number < 0) {
        m_minus = true;
        number *= -1;
    } else {
        m_minus = false;
    }
    m_digits = (char *) malloc(19 * sizeof(char));
    if (m_digits == nullptr) {
        throw 2;
    }
    m_length = 0;
    int pos = 0;
    if (number == 0) {
        m_digits[0] = 0;
        m_length = 1;
        m_minus = false;
    } else {
        while (number) {
            m_digits[pos] = (char) (number % 10);
            number /= 10;
            pos++;
            m_length++;
        }
    }
}

LN::LN(const char *strNumber) {
    size_t sizeStr = strlen(strNumber);
    int pos;
    m_digits = (char *) malloc(sizeStr * sizeof(char));
    if (m_digits == nullptr) {
        throw 2;
    }
    if (strNumber[0] == '-') {
        if (sizeStr == 2 && strNumber[1] == '0') {
            m_minus = false;
            m_length = 1;
            m_digits[0] = 0;
            return;
        }
        m_minus = true;
        pos = --sizeStr;
    } else {
        m_minus = false;
        pos = ((int) sizeStr) - 1;
    }
    m_length = sizeStr;
    for (size_t i = 0; i < sizeStr; i++) {
        char ch[1];
        ch[0] = strNumber[pos - i];
        char *temp = ch;
        m_digits[i] = (char) atoi(temp);
    }
}

LN::LN(std::string_view strNumber) {
    size_t sizeStr = strNumber.size();
    int pos;
    m_digits = (char *) malloc(sizeStr * sizeof(char));
    if (m_digits == nullptr) {
        throw 2;
    }
    if (strNumber[0] == '-') {
        if (sizeStr == 2 && strNumber[1] == '0') {
            m_minus = false;
            m_length = 1;
            m_digits[0] = 0;
            return;
        }
        m_minus = true;
        pos = --sizeStr;
    } else {
        m_minus = false;
        pos = ((int) sizeStr) - 1;
    }
    m_length = sizeStr;
    for (size_t i = 0; i < sizeStr; i++) {
        char ch[1];
        ch[0] = strNumber[pos - i];
        char *temp = ch;
        m_digits[i] = (char) atoi(temp);
    }
}

LN::LN(const LN &b) {
    m_isNaN = b.m_isNaN;
    m_minus = b.m_minus;
    m_length = b.m_length;
    m_digits = (char *) malloc(m_length * sizeof(char));
    if (m_digits == nullptr) {
        throw 2;
    }
    for (size_t i = 0; i < m_length; i++) {
        m_digits[i] = b.m_digits[i];
    }
}

LN &LN::operator=(const LN &b) {
    if (&b == this) {
        return *this;
    }
    free(m_digits);
    m_isNaN = b.m_isNaN;
    m_minus = b.m_minus;
    m_length = b.m_length;
    m_digits = (char *) malloc(m_length * sizeof(char));
    if (m_digits == nullptr) {
        throw 2;
    }
    for (size_t i = 0; i < m_length; i++) {
        m_digits[i] = b.m_digits[i];
    }
    return *this;
}

LN::LN(LN &&b) {
    m_isNaN = b.m_isNaN;
    m_minus = b.m_minus;
    m_length = b.m_length;
    m_digits = b.m_digits;
    b.m_digits = nullptr;
}

LN &LN::operator=(LN &&b) {
    if (&b == this) {
        return *this;
    }
    free(m_digits);
    m_isNaN = b.m_isNaN;
    m_minus = b.m_minus;
    m_length = b.m_length;
    m_digits = b.m_digits;
    b.m_digits = nullptr;
    return *this;
}



LN LN::operator+(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return createNaN();
    }
    char *resultStr;
    bool sign;
    size_t size;
    if (this->m_length >= b.m_length) {
        if (this->m_minus == b.m_minus) {
            if (!this->m_minus) {
                sign = false;
                resultStr = add(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
            } else {
                sign = true;
                resultStr = add(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
            }
        } else {
            if (this->m_minus) {
                if (this->lessModule(b)) {
                    sign = false;
                    resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
                } else {
                    sign = true;
                    resultStr = subtract(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
                }
            } else {
                if (this->lessModule(b)) {
                    sign = true;;
                    resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
                } else {
                    sign = false;
                    resultStr = subtract(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
                }
            }
        }
    } else {
        if (this->m_minus == b.m_minus) {
            if (!this->m_minus) {
                sign = false;
                resultStr = add(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            } else {
                sign = true;
                resultStr = add(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            }
        } else {
            if (this->m_minus) {
                sign = false;
                resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            } else {
                sign = true;
                resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            }
        }
    }
    try {
        LN result(resultStr, size, sign, false);
        free(resultStr);
        return result;
    } catch (int e) {
        free(resultStr);
        throw;
    }
}

LN LN::operator-(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return createNaN();
    }
    char *resultStr;
    bool sign;
    size_t size;
    if (this->m_length >= b.m_length) {
        if (this->m_minus == b.m_minus) {
            if (!this->m_minus) {
                if (this->lessModule(b)) {
                    sign = true;
                    resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
                } else {
                    sign = false;
                    resultStr = subtract(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
                }
            } else {
                if (this->lessModule(b)) {
                    sign = false;
                    resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
                } else {
                    sign = true;
                    resultStr = subtract(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
                }
            }
        } else {
            if (this->m_minus) {
                if (this->lessModule(b)) {
                    sign = true;
                    resultStr = add(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
                } else {
                    sign = true;
                    resultStr = add(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
                }
            } else {
                if (this->lessModule(b)) {
                    sign = false;
                    resultStr = add(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
                } else {
                    sign = false;
                    resultStr = add(this->m_digits, this->m_length, b.m_digits, b.m_length, &size);
                }
            }
        }
    } else {
        if (this->m_minus == b.m_minus) {
            if (!this->m_minus) {
                sign = true;
                resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            } else {
                sign = false;
                resultStr = subtract(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            }
        } else {
            if (this->m_minus) {
                sign = true;
                resultStr = add(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            } else {
                sign = false;
                resultStr = add(b.m_digits, b.m_length, this->m_digits, this->m_length, &size);
            }
        }
    }
    try {
        LN result(resultStr, size, sign, false);
        free(resultStr);
        return result;
    } catch (int e) {
        free(resultStr);
        throw;
    }
}

char * LN::add(const char *a, size_t sizeA, const char *b, size_t sizeB, size_t *sizeResult) {
    int carry = 0;
    char *resultStr = (char *) malloc((sizeA + 1) * sizeof(char));
    if (resultStr == nullptr) {
        throw 2;
    }
    size_t counter = 0;
    for (size_t i = 0; i < sizeB; i++) {
        int cur = a[i] + b[i] + carry;
        if (cur > 9) {
            resultStr[i] = (char) (cur % 10);
            counter++;
            carry = cur / 10;
        } else {
            resultStr[i] = (char) cur;
            counter++;
            carry = 0;
        }
    }
    for (size_t i = sizeB; i < sizeA; i++) {
        int cur = a[i] + carry;
        if (cur > 9) {
            resultStr[i] = (char) (cur % 10);
            counter++;
            carry = cur / 10;
        } else {
            resultStr[i] = (char) cur;
            counter++;
            carry = 0;
        }
    }
    if (carry != 0) {
        resultStr[counter++] = (char) carry;
    }
    *sizeResult = counter;
    return resultStr;
}

char * LN::subtract(const char *a, size_t sizeA, const char *b, size_t sizeB, size_t *sizeResult) {
    bool borrowed = false;
    char *resultStr = (char *) malloc((sizeA + 1) * sizeof(char));
    if (resultStr == nullptr) {
        throw 2;
    }
    size_t counter = 0;
    int cur;
    for (size_t i = 0; i < sizeB; i++) {
        if (borrowed) {
            cur = a[i] - 1 - b[i];
        } else {
            cur = a[i] - b[i];
        }
        if (cur < 0) {
            resultStr[i] = (char) (cur + 10);
            counter++;
            borrowed = true;
        } else {
            resultStr[i] = (char) cur;
            counter++;
            borrowed = false;
        }
    }
    for (size_t i = sizeB; i < sizeA; i++) {
        if (borrowed) {
            cur = a[i] - 1;
            borrowed = false;
            if (cur < 0) {
                cur += 10;
                borrowed = true;
            }
        } else {
            cur = a[i];
            borrowed = false;
        }
        resultStr[i] = (char) cur;
        counter++;
    }
    for (size_t i = counter - 1; i > 0; i--) {
        if (resultStr[i] == 0) {
            counter--;
        } else {
            break;
        }
    }
    *sizeResult = counter;
    return resultStr;
}

bool LN::operator<(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return false;
    }
    if (this->m_length == 1 && this->m_digits[0] == 0 && b.m_length == 1 && b.m_digits[0] == 0) {
        return false;
    }
    if (this->m_minus && !b.m_minus) {
        return true;
    } else if (!this->m_minus && b.m_minus) {
        return false;
    } else if (!this->m_minus && !b.m_minus) {
        if (this->m_length < b.m_length) {
            return true;
        } else if (this->m_length > b.m_length) {
            return false;
        } else {
            for (long long int i = this->m_length - 1; i >= 0; i--) {
                if (this->m_digits[i] < b.m_digits[i]) {
                    return true;
                }
                if (this->m_digits[i] > b.m_digits[i]) {
                    return false;
                }
            }
            return false;
        }
    } else {
        if (this->m_length < b.m_length) {
            return false;
        } else if (this->m_length > b.m_length) {
            return true;
        } else {
            for (long long int i = this->m_length - 1; i >= 0; i--) {
                if (this->m_digits[i] < b.m_digits[i]) {
                    return false;
                }
                if (this->m_digits[i] > b.m_digits[i]) {
                    return true;
                }
            }
            return false;
        }
    }
}

bool LN::lessModule(const char *a, long long int sizeA, const char *b, long long int sizeB) {
    if (sizeA < sizeB) {
        return true;
    } else if (sizeA > sizeB) {
        return false;
    } else {
        for (long long int i = sizeA - 1; i >= 0; i--) {
            if (a[i] < b[i]) {
                return true;
            }
            if (a[i] > b[i]) {
                return false;
            }
        }
        return false;
    }
}

bool LN::lessModule(const LN &b) const {
    if (this->m_length < b.m_length) {
        return true;
    } else if (this->m_length > b.m_length) {
        return false;
    } else {
        for (long long int i = this->m_length - 1; i >= 0; i--) {
            if (this->m_digits[i] < b.m_digits[i]) {
                return true;
            }
            if (this->m_digits[i] > b.m_digits[i]) {
                return false;
            }
        }
        return false;
    }
}

LN::LN(const char *from, const long long int size, const bool minus, const bool reversed) {
    m_digits = (char *) malloc(size * sizeof(char));
    if (m_digits == nullptr) {
        throw 2;
    }
    if (size == 1 && from[0] == 0) {
        this->m_minus = false;
        this->m_length = size;
        m_digits[0] = 0;
    } else {
        this->m_minus = minus;
        this->m_length = size;
        if (reversed) {
            long long int pos = 0;
            for (long long int i = size - 1; i >= 0; i--) {
                m_digits[pos++] = from[i];
            }
        } else {
            for (size_t i = 0; i < size; i++) {
                m_digits[i] = from[i];
            }
        }
    }
}

bool LN::operator>=(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return false;
    }
    return !(*this < b);
}

bool LN::operator>(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return false;
    }
    if (this->m_length == 1 && this->m_digits[0] == 0 && b.m_length == 1 && b.m_digits[0] == 0) {
        return false;
    }
    if (this->m_minus && !b.m_minus) {
        return false;
    } else if (!this->m_minus && b.m_minus) {
        return true;
    } else if (!this->m_minus && !b.m_minus) {
        if (this->m_length < b.m_length) {
            return false;
        } else if (this->m_length > b.m_length) {
            return true;
        } else {
            for (long long int i = this->m_length - 1; i >= 0; i--) {
                if (this->m_digits[i] < b.m_digits[i]) {
                    return false;
                }
                if (this->m_digits[i] > b.m_digits[i]) {
                    return true;
                }
            }
            return false;
        }
    } else {
        if (this->m_length < b.m_length) {
            return true;
        } else if (this->m_length > b.m_length) {
            return false;
        } else {
            for (long long int i = this->m_length - 1; i >= 0; i--) {
                if (this->m_digits[i] < b.m_digits[i]) {
                    return true;
                }
                if (this->m_digits[i] > b.m_digits[i]) {
                    return false;
                }
            }
            return false;
        }
    }
}

bool LN::operator<=(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return false;
    }
    return !(*this > b);
}

bool LN::operator==(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return false;
    }
    if (this->m_length == 1 && this->m_digits[0] == 0 && b.m_length == 1 && b.m_digits[0] == 0) {
        return true;
    }
    if (this->m_minus != b.m_minus) {
        return false;
    } else {
        if (this->m_length != b.m_length) {
            return false;
        } else {
            for (long long int i = this->m_length - 1; i >= 0; i--) {
                if (this->m_digits[i] < b.m_digits[i]) {
                    return false;
                }
                if (this->m_digits[i] > b.m_digits[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}

bool LN::operator!=(const LN &b) const {
    return !(*this == b);
}

char * LN::multiply(const char *number, size_t lengthOfNumber, int factor, int zeros, size_t * sizeResult) {
    char *resultStr = (char *) malloc((lengthOfNumber + zeros + 1) * sizeof(char));
    if (resultStr == nullptr) {
        throw 2;
    }
    size_t counter = 0;
    int cur = 0;
    for (size_t i = 0; i < zeros; i++) {
        resultStr[i] = char(0);
        counter++;
    }
    for (size_t i = 0; i < lengthOfNumber; i++) {
        cur = factor * number[i] + cur;
        if (cur <= 9) {
            resultStr[i + zeros] = char(cur);
            cur = 0;
            counter++;
        } else {
            resultStr[i + zeros] = char(cur % 10);
            cur = cur / 10;
            counter++;
        }
    }
    if (cur != 0) {
        resultStr[counter++] = char(cur);
    }
    for (size_t i = counter - 1; i > 0; i--) {
        if (resultStr[i] == 0) {
            counter--;
        } else {
            break;
        }
    }
    *sizeResult = counter;
    return resultStr;
}

LN LN::operator*(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return createNaN();
    }
    if (!this->m_minus && this->m_length == 1 && this->m_digits[0] == char(1)) {
        return b;
    }
    if (!b.m_minus && b.m_length == 1 && b.m_digits[0] == char(1)) {
        return *this;
    }
    if ((b.m_length == 1 && b.m_digits[0] == char(0)) || (this->m_length == 1 && this->m_digits[0] == char(0))) {
        return LN(0LL);
    }
    char **array = (char **) malloc (b.m_length * sizeof(char *));
    if (array == nullptr) {
        throw 2;
    }
    size_t *sizes = (size_t *) malloc (b.m_length * sizeof(size_t));
    if (sizes == nullptr) {
        free(array);
        throw 2;
    }
    for (size_t i = 0; i < b.m_length; i++) {
        try {
            array[i] = multiply(this->m_digits, this->m_length, b.m_digits[i], i, &sizes[i]);
        } catch (int e) {
            for (size_t j = 0; j < i; j++) {
                free(array[j]);
            }
            free(array);
            free(sizes);
            throw;
        }
    }
    size_t size;
    char *resultStr = array[b.m_length - 1];
    char *prevResultStr = array[b.m_length - 1];
    size = sizes[b.m_length - 1];
    for (long long int i = b.m_length - 1; i > 0; i--) {
        try {
            resultStr = add(prevResultStr, size, array[i - 1], sizes[i - 1], &size);
            free(prevResultStr);
            prevResultStr = resultStr;
        } catch (int e) {
            free(resultStr);
            for (size_t j = 0; j < b.m_length - 1; j++) {
                free(array[j]);
            }
            free(array);
            free(sizes);
            throw;
        }
    }
    LN result;
    try {
        result = LN(resultStr, size, this->m_minus != b.m_minus, false);
    } catch (int e) {
        free(resultStr);
        for (size_t i = 0; i < b.m_length - 1; i++) {
            free(array[i]);
        }
        free(array);
        free(sizes);
        throw;
    }
    free(resultStr);
    for (size_t i = 0; i < b.m_length - 1; i++) {
        free(array[i]);
    }
    free(array);
    free(sizes);
    return result;
}

char * LN::cut(long long int left, long long int right, size_t size) const {
    char *result = (char *) malloc(size * sizeof(char));
    if (result == nullptr) {
        throw 2;
    }
    long long int pos = 0;
    for (long long int i = right; i <= left; i++) {
        result[pos++] = this->m_digits[i];
    }
    return result;
}

LN LN::operator/(const LN &second) const {
    if (this->m_isNaN || second.m_isNaN) {
        return createNaN();
    }
    if (this->lessModule(second)) {
        return LN(0LL);
    }
    if (second == LN(0LL)) {
        return createNaN();
    }
    LN b;
    if (second.m_minus) {
        b = -second;
    } else {
        b = second;
    }
    LN a;
    if (this->m_minus) {
        a = -*this;
    } else {
        a = *this;
    }
    char *resultStr = (char *) malloc(this->m_length * sizeof(char));
    if (resultStr == nullptr) {
        throw 2;
    }
    size_t sizeResult = 0;
    long long int left = a.m_length - 1;
    long long int right = a.m_length - b.m_length;
    bool isLastZero = false;
    bool curIsFree = false;
    char *curStr;
    try {
        while (a >= b) {
            curIsFree = false;
            curStr = a.cut(left, right, b.m_length + 1);
            long long int sizeCur = left - right + 1;
            if (!isLastZero) {
                if (lessModule(curStr, sizeCur, b.m_digits, b.m_length)) {
                    free(curStr);
                    curIsFree = true;
                    right--;
                    curStr = a.cut(left, right, b.m_length + 1);
                    curIsFree = false;
                    sizeCur++;
                }
            }
            while (lessModule(curStr, sizeCur, b.m_digits, b.m_length)) {
                free(curStr);
                curIsFree = true;
                right--;
                curStr = a.cut(left, right, b.m_length + 1);
                curIsFree = false;
                sizeCur++;
                resultStr[sizeResult++] = char(0);
            }
            LN cur(curStr, sizeCur, false, false);
            std::string curCheck = cur.toString();
            free(curStr);
            curIsFree = true;
            LN factor(1);
            while ((b * factor).lessEqualModule(cur)) {
                factor = factor + LN(1);
                std::string factorCheck = factor.toString();
            }
            factor = factor - LN(1);
            std::string factorCheck = factor.toString();
            LN mod(cur - (b * factor));
            std::string modCheck = mod.toString();
            resultStr[sizeResult++] = factor.m_digits[0];
            if (mod != LN(0ll)) {
                long long int pos = 0;
                for (long long int i = a.m_length - cur.m_length; i < a.m_length - cur.m_length + mod.m_length; i++) {
                    a.m_digits[i] = mod.m_digits[pos++];
                }
                a.m_length = a.m_length - sizeCur + mod.m_length;
                left = a.m_length - 1;
                right = a.m_length - mod.m_length;
                isLastZero = false;
            } else {
                a.m_length = a.m_length - sizeCur;
                while (a.m_length > 0 && a.m_digits[a.m_length - 1] == 0) {
                    a.m_length--;
                    resultStr[sizeResult++] = char(0);
                }
                left = a.m_length - 1;
                right = a.m_length - 1;
                isLastZero = true;
            }
        }
    } catch (int e) {
        free(resultStr);
        if (!curIsFree) {
            free(curStr);
        }
        throw;
    }
    if (isLastZero) {
        for (size_t i = 0; i < a.m_length; i++) {
            resultStr[sizeResult++] = char(0);
        }
    }
    LN result(resultStr, sizeResult, this->m_minus != second.m_minus, true);
    free(resultStr);
    return result;
}

LN LN::operator-() const {
    LN result = *this;
    if (this->m_length == 1 && this->m_digits[0] == 0) {
        result.m_minus = false;
    } else {
        result.m_minus = !result.m_minus;
    }
    return result;
}

std::string LN::toString() const {
    std::string result;
    if (m_isNaN) {
        result = "NaN";
    } else {
        if (m_minus) {
            result.push_back('-');
        }
        for (long long int i = m_length - 1; i >= 0; i--) {
            switch (m_digits[i]) {
                case 0: result.push_back('0'); break;
                case 1: result.push_back('1'); break;
                case 2: result.push_back('2'); break;
                case 3: result.push_back('3'); break;
                case 4: result.push_back('4'); break;
                case 5: result.push_back('5'); break;
                case 6: result.push_back('6'); break;
                case 7: result.push_back('7'); break;
                case 8: result.push_back('8'); break;
                case 9: result.push_back('9'); break;
            }
        }
    }
    return result;
}


LN LN::operator%(const LN &b) const {
    if (this->m_isNaN || b.m_isNaN) {
        return createNaN();
    }
    LN div(*this / b);
    return *this - (div * b);
}

LN::operator bool() const {
    if (this->m_isNaN) {
        return true;
    }
    if (this->m_length == 1 && this->m_digits[0] == 0) {
        return false;
    }
    return true;
}

LN::operator long long int() const {
    if (this->m_isNaN) {
        throw 4;
    }
    if (*this > LN(9223372036854775807) || *this < LN(-9223372036854775808ll)) {
        throw 3;
    }
    std::string number = this->toString();
    return stoll(number);
}

bool LN::lessEqualModule(const LN &b) const {
    if (this->m_length < b.m_length) {
        return true;
    } else if (this->m_length > b.m_length) {
        return false;
    } else {
        for (long long int i = this->m_length - 1; i >= 0; i--) {
            if (this->m_digits[i] < b.m_digits[i]) {
                return true;
            }
            if (this->m_digits[i] > b.m_digits[i]) {
                return false;
            }
        }
        return true;
    }
}

LN LN::operator~() const {
    if (this->m_isNaN || this->m_minus) {
        return createNaN();
    }
    long long int pos = (this->m_length + 1) / 2;
    LN cur;
    free(cur.m_digits);
    cur.m_digits = (char *) malloc(pos * sizeof(char));
    if (cur.m_digits == nullptr) {
        throw 2;
    }
    cur.m_length = pos;
    pos--;
    cur.m_minus = false;
    for (long long int i = 0; i < cur.m_length; i++) {
        cur.m_digits[i] = 0;
    }
    while (pos >= 0) {
        int l = 0, r = 10, curDigit = 0;
        while (l <= r) {
            int m = (l + r) >> 1;
            cur.m_digits[pos] = (char) m;
            if ((cur * cur) <= *this) {
                curDigit = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        cur.m_digits[pos] = (char) curDigit;
        pos--;
    }
    while (cur.m_length != 1 && cur.m_digits[cur.m_length - 1] == 0) {
        cur.m_length--;
    }
    return cur;
}

LN LN::operator+=(const LN &b) {
    return *this = *this + b;
}

LN LN::operator-=(const LN &b) {
    return *this = *this - b;
}

LN LN::operator*=(const LN &b) {
    return *this = *this * b;
}

LN LN::operator/=(const LN &b) {
    return *this = *this / b;
}

LN LN::operator%=(const LN &b) {
    return *this = *this % b;
}

LN LN::createNaN() {
    LN NaN(1);
    NaN.m_isNaN = true;
    return NaN;
}

LN operator ""_ln(const char *strNumber) {
    return LN(strNumber);
}