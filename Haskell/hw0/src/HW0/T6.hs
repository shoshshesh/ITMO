module HW0.T6
  ( a
  , b
  , c
  , a_whnf
  , b_whnf
  , c_whnf
  ) where

import HW0.T1 (distrib)
import Data.Char (isSpace)

a = distrib (Left ("AB" ++ "CD" ++ "EF"))     -- distrib from HW0.T1
b = map isSpace "Hello, World"
c = if 1 > 0 || error "X" then "Y" else "Z"

a_whnf = (Left ("AB" ++ "CD" ++ "EF"), Left ("AB" ++ "CD" ++ "EF"))
b_whnf = False : (map isSpace "ello, World")
c_whnf = "Y"