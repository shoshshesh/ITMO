module HW0.T3
  ( i
  ) where

s :: (a -> b -> c) -> (a -> b) -> (a -> c)
s f g x = f x (g x)

k :: a -> b -> a
k x y = x

i :: a -> a
i = s k k

-- It's too hard :(