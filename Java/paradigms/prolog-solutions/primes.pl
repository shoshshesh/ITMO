notDivide(_, 1) :- !.
notDivide(A, B) :- B1 is B - 1, mod(A, B) > 0, notDivide(A, B1).

prime(N) :- prime_table(N), !.
prime(N) :- N1 is N - 1, notDivide(N, N1), assert(prime_table(N)).

composite(N) :- not(prime(N)).

prime_divisors(N, R) :- nextPrimeDivisor(N, 1, P), myPrimeDivisors(N, P, R).
myPrimeDivisors(1, _, []) :- !.
myPrimeDivisors(N, CurP, [H | T]) :- CurP > 1, H is CurP, N1 is div(N, CurP), nextPrimeDivisor(N1, 1, NextP), myPrimeDivisors(N1, NextP, T), !.

square_divisors(N, R) :- N1 is N * N, prime_divisors(N1, R).

nextPrime(N, R) :- N1 is N + 1, composite(N1), nextPrime(N1, R), !.
nextPrime(N, R) :- R is N + 1, !.

nextPrimeDivisor(N, CurP, R) :- not(CurP > N), nextPrime(CurP, NextP), mod(N, NextP) > 0, nextPrimeDivisor(N, NextP, R), !.
nextPrimeDivisor(N, CurP, R) :- not(CurP > N), nextPrime(CurP, NextP), not(NextP > N), R is NextP.
nextPrimeDivisor(N, CurP, CurP).
nextPrimeDivisor(_, _, 0).