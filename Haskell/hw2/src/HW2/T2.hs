{-# LANGUAGE TupleSections #-}

module HW2.T2
  ( distOption
  , distPair
  , distQuad
  , distAnnotated
  , distExcept
  , distPrioritised
  , distStream
  , distList
  , distFun
  , wrapOption
  , wrapPair
  , wrapQuad
  , wrapAnnotated
  , wrapExcept
  , wrapPrioritised
  , wrapStream
  , wrapList
  , wrapFun
  ) where

  import HW2.T1

  distOption      :: (Option a, Option b) -> Option (a, b)
  distOption tuple = case tuple of
    (None, None)     -> None
    (None, Some _)   -> None
    (Some _, None)   -> None
    (Some a, Some b) -> Some (a, b)

  distPair        :: (Pair a, Pair b) -> Pair (a, b)
  distPair (P a1 a2, P b1 b2) = P (a1, b1) (a2, b2)

  distQuad        :: (Quad a, Quad b) -> Quad (a, b)
  distQuad (Q a1 a2 a3 a4, Q b1 b2 b3 b4) = Q (a1, b1) (a2, b2) (a3, b3) (a4, b4)

  distAnnotated   :: Semigroup e => (Annotated e a, Annotated e b) -> Annotated e (a, b)
  distAnnotated (a :# e1, b :# e2) = (a, b) :# (e1 <> e2)

  distExcept      :: (Except e a, Except e b) -> Except e (a, b)
  distExcept tuple = case tuple of
    (Error e1, _)          -> Error e1
    (_, Error e2)          -> Error e2
    (Success a, Success b) -> Success (a, b)

  distPrioritised :: (Prioritised a, Prioritised b) -> Prioritised (a, b)
  distPrioritised tuple = case tuple of
    (Low a   , Low b)    -> Low    (a, b)
    (Low a   , Medium b) -> Medium (a, b)
    (Low a   , High b)   -> High   (a, b)
    (Medium a, Low b)    -> Medium (a, b)
    (Medium a, Medium b) -> Medium (a, b)
    (Medium a, High b)   -> High   (a, b)
    (High a  , Low b)    -> High   (a, b)
    (High a  , Medium b) -> High   (a, b)
    (High a  , High b)   -> High   (a, b)

  distStream      :: (Stream a, Stream b) -> Stream (a, b)
  distStream (a :> streamA, b :> streamB) = (a, b) :> distStream (streamA, streamB)

  distList        :: (List a, List b) -> List (a, b)
  distList tuple = case tuple of
    (Nil, _) -> Nil
    (a :. listA, listB) -> mapList (a,) listB <> distList (listA, listB)

  distFun         :: (Fun i a, Fun i b) -> Fun i (a, b)
  distFun (F f, F g) = F (\i -> (f i, g i))
  
  wrapOption      :: a -> Option a
  wrapOption = Some
  
  wrapPair        :: a -> Pair a
  wrapPair a = P a a
  
  wrapQuad        :: a -> Quad a
  wrapQuad a = Q a a a a
  
  wrapAnnotated   :: Monoid e => a -> Annotated e a
  wrapAnnotated a = a :# mempty
  
  wrapExcept      :: a -> Except e a
  wrapExcept = Success
  
  wrapPrioritised :: a -> Prioritised a
  wrapPrioritised = Low
  
  wrapStream      :: a -> Stream a
  wrapStream a = a :> wrapStream a
  
  wrapList        :: a -> List a
  wrapList a = a :. Nil
  
  wrapFun         :: a -> Fun i a
  wrapFun a = F (const a)