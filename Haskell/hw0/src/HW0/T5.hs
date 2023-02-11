module HW0.T5
  ( Nat
  , nz
  , ns
  , nplus
  , nmult
  , nFromNatural
  , nToNum
  ) where

import Data.Function (fix)
import Numeric.Natural (Natural)

type Nat a = (a -> a) -> a -> a

nz :: Nat a
nz f a = a

-- ns :: ((a -> a) -> a -> a) -> ((a -> a) -> a -> a)
ns :: Nat a -> Nat a
ns nat f a = nat f (f a)

nplus, nmult :: Nat a -> Nat a -> Nat a
nplus a b f arg = a f $ b f arg
nmult a b f arg = a (b f) arg
 
nFromNatural :: Natural -> Nat a
nFromNatural = fix (\rec -> \n -> if n == 0 then nz else ns (rec (n - 1)))

nToNum :: Num a => Nat a -> a
nToNum nat = nat (+1) 0