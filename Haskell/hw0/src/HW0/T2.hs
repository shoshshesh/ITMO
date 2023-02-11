module HW0.T2
  ( Not
  , doubleNeg
  , reduceTripleNeg
  ) where

import Data.Void (Void)

type Not a = a -> Void

-- equals a -> (a -> Void) -> Void. Let f = (a -> Void).
doubleNeg :: a -> Not (Not a)
doubleNeg a f = f a

-- equals (((a -> Void) -> Void) -> Void) -> a -> Void. Let f = (((a -> Void) -> Void) -> Void).
reduceTripleNeg :: Not (Not (Not a)) -> Not a
reduceTripleNeg f a = f (doubleNeg a)