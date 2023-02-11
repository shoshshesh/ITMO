module HW1.T2
  ( N (..)
  , nplus
  , nmult
  , nsub
  , ncmp
  , nFromNatural
  , nToNum
  , nEven
  , nOdd
  , ndiv
  , nmod
  ) where

import Numeric.Natural (Natural)
import Data.Function (fix)

data N = Z | S N
  deriving (Show)

nplus :: N -> N -> N        -- addition
nplus a b = case b of
                 Z   -> a
                 S c -> S $ nplus a c

nmult :: N -> N -> N        -- multiplication
nmult a b = case b of
                 Z   -> Z
                 S c -> nplus (nmult a c) a

nsub :: N -> N -> Maybe N   -- subtraction     (Nothing if result is negative)
nsub a b = case (a, b) of
                (S c, Z)   -> Just a
                (S c, S d) -> nsub c d
                (Z, Z)     -> Just Z
                (Z, S c)   -> Nothing

ncmp :: N -> N -> Ordering  -- comparison      (Do not derive Ord)
ncmp a b = case (nsub a b) of
                Just Z     -> EQ
                Just (S c) -> GT
                Nothing    -> LT

nFromNatural :: Natural -> N
nFromNatural = fix (\rec -> \n -> \num -> if num == 0 then n else rec (S n) (num - 1)) Z

nToNum :: Num a => N -> a
nToNum = fix (\rec -> \num -> \n ->
               case n of
                    Z   -> num
                    S c -> rec (num + 1) c) 0

nEven, nOdd :: N -> Bool    -- parity checking
nEven n = case (nmod n (S (S Z))) of
               Z         -> True
               _         -> False

nOdd n = not $ nEven n

ndiv :: N -> N -> N         -- integer division
ndiv _ Z = fix id           -- infinite loop if division by 0
ndiv a b = case (ncmp a b) of
                EQ -> S Z
                GT -> S (ndiv (unpack  (nsub a b)) b)
                LT -> Z

nmod :: N -> N -> N         -- modulo operation
nmod a b = unpack (nsub a (nmult b (ndiv a b)))

unpack :: Maybe N -> N
unpack (Just n) = n
unpack Nothing = fix id -- (unpack Nothing) is impossible case