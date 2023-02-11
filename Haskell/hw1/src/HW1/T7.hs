module HW1.T7
  ( ListPlus (..)
  , Inclusive (..)
  , DotString (..)
  , Fun (..)
  ) where

data ListPlus a = a :+ ListPlus a | Last a
  deriving (Show, Eq)
infixr 5 :+

instance Semigroup (ListPlus a) where
--  (<>) :: ListPlus a -> ListPlus a -> ListPlus a
    Last a <> list = a :+ list
    (a :+ listL) <> listR = a :+ (listL <> listR)

data Inclusive a b = This a | That b | Both a b
  deriving (Show, Eq)

instance (Semigroup a, Semigroup b) => Semigroup (Inclusive a b) where
--  (<>) :: Inclusive a b -> Inclusive a b -> Inclusive a b
    This i   <> This j   = This (i <> j)
    This i   <> That j   = Both i j
    This i   <> Both j k = Both (i <> j) k

    That i   <> This j   = Both j i
    That i   <> That j   = That (i <> j)
    That i   <> Both j k = Both j (i <> k)

    Both i j <> This k   = Both (i <> k) j
    Both i j <> That k   = Both i (j <> k)
    Both i j <> Both k t = Both (i <> k) (j <> t)

newtype DotString = DS String
  deriving (Show, Eq)

instance Semigroup DotString where
--  (<>) :: DotString -> DotString -> DotString
    (DS a)  <> (DS "") = DS a
    (DS "") <> (DS a)  = DS a
    (DS a)  <> (DS b)  = DS (a ++ ('.' : b))

instance Monoid DotString where
--  mempty :: DotString
    mempty = DS ""

newtype Fun a = F (a -> a)

instance Semigroup (Fun a) where
--  (<>) :: Fun a -> Fun a -> Fun a
    (F f) <> (F g) = F (f . g)

instance Monoid (Fun a) where
--  mempty :: Fun a
    mempty = F id