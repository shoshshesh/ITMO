module HW2.T4
  ( State (..)
  , mapState
  , wrapState
  , joinState
  , modifyState
  , Prim (..)
  , Expr (..)
  , eval
  ) where

  import Control.Monad
  import HW2.T1

  data State s a = S { runS :: s -> Annotated s a }

  mapState :: (a -> b) -> State s a -> State s b
  mapState f S { runS = run } = S { runS = \s -> let (a :# state) = run s in f a :# state }

  wrapState :: a -> State s a
  wrapState a = S { runS = (a :#) }

  joinState :: State s (State s a) -> State s a
  joinState S { runS = run } = S { runS = \s -> let (inner :# state) = run s in runS inner state }

  modifyState :: (s -> s) -> State s ()
  modifyState f = S { runS = \s -> () :# f s }

  instance Functor (State s) where
    fmap = mapState

  instance Applicative (State s) where
    pure = wrapState
    p <*> q = Control.Monad.ap p q

  instance Monad (State s) where
    m >>= f = joinState (fmap f m)

  data Prim a =
      Add a a      -- (+)
    | Sub a a      -- (-)
    | Mul a a      -- (*)
    | Div a a      -- (/)
    | Abs a        -- abs
    | Sgn a        -- signum

  data Expr = Val Double | Op (Prim Expr)

  instance Num Expr where
    x + y         = Op (Add x y)
    x * y         = Op (Mul x y)
    x - y         = Op (Sub x y)
    abs x         = Op (Abs x)
    signum x      = Op (Sgn x)
    fromInteger x = Val (fromInteger x)

  instance Fractional Expr where
    x / y          = Op (Div x y)
    fromRational x = Val (fromRational x)
    
  eval :: Expr -> State [Prim Double] Double
  eval expr = case expr of
    (Val value)    -> pure value
    (Op (Add a b)) -> do resA <- eval a; resB <- eval b; modifyState (Add resA resB :); return (resA + resB)
    (Op (Sub a b)) -> do resA <- eval a; resB <- eval b; modifyState (Sub resA resB :); return (resA - resB)
    (Op (Mul a b)) -> do resA <- eval a; resB <- eval b; modifyState (Mul resA resB :); return (resA * resB)
    (Op (Div a b)) -> do resA <- eval a; resB <- eval b; modifyState (Div resA resB :); return (resA / resB)
    (Op (Abs a))   -> do resA <- eval a; modifyState (Abs resA :); return (abs resA)
    (Op (Sgn a))   -> do resA <- eval a; modifyState (Sgn resA :); return (signum resA)