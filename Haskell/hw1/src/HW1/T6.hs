module HW1.T6
  ( mcat
  , epart
  ) where

import Data.Foldable

mcat :: Monoid a => [Maybe a] -> a
mcat list = foldr (\maybe ->
                   \value -> 
                    case (maybe, value) of 
                         (Just a, b)   -> a <> b
                         (Nothing, b)  -> b) (mempty) list

epart :: (Monoid a, Monoid b) => [Either a b] -> (a, b)
epart list = epartSplitted (split list [] [])

epartSplitted :: (Monoid a, Monoid b) => ([a], [b]) -> (a, b)
epartSplitted (l, r) = (foldMap id l, foldMap id r)

split :: [Either a b] -> [a] -> [b] -> ([a], [b])
split [] [] [] = ([], [])
split (x:xs) l r = let res = (split xs l r) in 
                     case x of
                          (Left c)  -> (c : (first res), second res)
                          (Right c) -> (first res, c : (second res))

first :: (a, b) -> a
first (a, b) = a

second :: (a, b) -> b
second (a, b) = b