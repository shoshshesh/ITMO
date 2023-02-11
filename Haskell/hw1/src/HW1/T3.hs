module HW1.T3
  ( Tree (..)
  , Meta
  , tsize
  , tdepth
  , tmember
  , tinsert
  , tFromList
  ) where

import Data.Function (fix)

data Tree a = Leaf | Branch Meta (Tree a) a (Tree a)
  deriving (Show)

type Meta = Int

-- | Size of the tree, O(1).
tsize :: Tree a -> Int
tsize Leaf = 0
tsize (Branch size l a r) = size

-- | Depth of the tree.
tdepth :: Tree a -> Int
tdepth Leaf = 0
tdepth (Branch size l a r) = max ((tdepth l) + 1) ((tdepth r) + 1)

-- | Check if the element is in the tree, O(log n)
tmember :: Ord a => a -> Tree a -> Bool
tmember element Leaf = False
tmember element (Branch size l a r) = if       element == a  then True
                                      else if  element <  a  then tmember element l
                                      else     tmember element r

-- | Insert an element into the tree, O(log n)
tinsert :: Ord a => a -> Tree a -> Tree a
tinsert element Leaf = mkBranch Leaf element Leaf
tinsert element (Branch size l a r) = if       element < a  then mkBranch (tinsert element l) a r 
                                      else if  element > a  then mkBranch l a (tinsert element r)
                                      else     (Branch size l a r)

-- | Build a tree from a list, O(n log n)
tFromList :: Ord a => [a] -> Tree a
tFromList = fix (\rec -> \tree -> \list ->
                  case list of
                       (x:xs) -> rec (tinsert x tree) xs
                       [] -> tree) Leaf

mkBranch :: Tree a -> a -> Tree a -> Tree a
mkBranch Leaf a Leaf = Branch 1 Leaf a Leaf
mkBranch Leaf a (Branch size l b r) = Branch (size + 1) Leaf a (Branch size l b r)
mkBranch (Branch size l b r) a Leaf = Branch (size + 1) (Branch size l b r) a Leaf
mkBranch (Branch sizeB lB b rB) a (Branch sizeC lC c rC) = Branch (sizeB + sizeC + 1) (Branch sizeB lB b rB) a (Branch sizeC lC c rC)