module HW1.T4
  ( tfoldr
  , treeToList
  ) where

import HW1.T3

tfoldr :: (a -> b -> b) -> b -> Tree a -> b
tfoldr f zero Leaf = zero
tfoldr f zero (Branch size l a r) = tfoldr f (f a (tfoldr f zero r)) l

treeToList :: Tree a -> [a]    -- output list is sorted
treeToList = tfoldr (:) []