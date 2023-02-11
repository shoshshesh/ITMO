#include <cstdlib>
#include <iostream>
#include <string>

#ifndef LAB4_LN_H
#define LAB4_LN_H

class LN {
public:
    explicit LN(long long int = 0LL);
    explicit LN(const char *);
    explicit LN(std::string_view);
    LN(const LN &b);
    LN(LN &&b);
    ~LN() {
        free(m_digits);
    }
    LN & operator= (const LN &b);
    LN & operator= (LN &&b);
    LN operator+ (const LN &b) const;
    LN operator- (const LN &b) const;
    LN operator* (const LN &b) const;
    LN operator/ (const LN &b) const;
    LN operator% (const LN &b) const;
    LN operator~ () const;
    LN operator- () const;
    bool operator< (const LN &b) const;
    bool operator<= (const LN &b) const;
    bool operator> (const LN &b) const;
    bool operator>= (const LN &b) const;
    bool operator== (const LN &b) const;
    bool operator!= (const LN &b) const;
    LN operator+= (const LN &b);
    LN operator-= (const LN &b);
    LN operator*= (const LN &b);
    LN operator/= (const LN &b);
    LN operator%= (const LN &b);
    explicit operator long long int() const;
    explicit operator bool() const;
    std::string toString() const;

private:
    bool m_minus;
    char *m_digits;
    long long int m_length;
    bool m_isNaN = false;
    LN(const char *, const long long int, const bool, const bool);
    static char * add(const char *, size_t, const char *, size_t, size_t *);
    static char * subtract(const char *, size_t, const char *, size_t, size_t *);
    static char * multiply(const char *, size_t, int, int, size_t *);
    bool lessModule(const LN &b) const;
    static bool lessModule(const char *a, long long int sizeA, const char *b, long long int sizeB) ;
    bool lessEqualModule(const LN &b) const;
    char * cut(long long int, long long int, size_t) const;
    static LN createNaN();
};

LN operator ""_ln (const char *);

#endif