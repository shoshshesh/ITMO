module HW2.T3
  ( joinOption
  , joinExcept
  , joinAnnotated
  , joinList
  , joinFun
  ) where

  import HW2.T1
  
  joinOption    :: Option (Option a) -> Option a
  joinOption option = case option of
    None          -> None
    Some None     -> None
    Some (Some a) -> Some a
    
  joinExcept    :: Except e (Except e a) -> Except e a
  joinExcept except = case except of
    Error e             -> Error e
    Success (Error e)   -> Error e
    Success (Success a) -> Success a
  
  joinAnnotated :: Semigroup e => Annotated e (Annotated e a) -> Annotated e a
  joinAnnotated ((a :# e1) :# e2) = a :# (e1 <> e2)
  
  joinList      :: List (List a) -> List a
  joinList lists = case lists of
    Nil -> Nil
    list :. rest -> list <> joinList rest
  
  joinFun       :: Fun i (Fun i a) -> Fun i a
  joinFun (F f) = F (\i -> unpackFun (f i) i)
  
  unpackFun :: Fun i a -> (i -> a)
  unpackFun (F f) = f