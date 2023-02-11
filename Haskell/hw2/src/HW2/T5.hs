module HW2.T5 
  ( ExceptState (..)
  , mapExceptState
  , wrapExceptState
  , joinExceptState
  , modifyExceptState
  , throwExceptState
  , EvaluationError (..)
  , eval
  ) where

  import Control.Monad
  import HW2.T1
  import HW2.T4 (Prim(..), Expr (..))

  data ExceptState e s a = ES { runES :: s -> Except e (Annotated s a) }

  mapExceptState :: (a -> b) -> ExceptState e s a -> ExceptState e s b
  mapExceptState f ES { runES = run } = ES { runES = \s -> case run s of
                                                                Error e              -> Error e
                                                                Success (a :# state) -> Success (f a :# state) }

  wrapExceptState :: a -> ExceptState e s a
  wrapExceptState a = ES { runES = \s -> Success (a :# s) }

  joinExceptState :: ExceptState e s (ExceptState e s a) -> ExceptState e s a
  joinExceptState ES { runES = run } = ES { runES = \s -> case run s of
                                                               Error e -> Error e
                                                               Success (inner :# state) -> runES inner state }


  modifyExceptState :: (s -> s) -> ExceptState e s ()
  modifyExceptState f = ES { runES = \s -> Success (() :# f s) }

  throwExceptState :: e -> ExceptState e s a
  throwExceptState e = ES { runES = \_ -> Error e }

  instance Functor (ExceptState e s) where
    fmap = mapExceptState

  instance Applicative (ExceptState e s) where
    pure = wrapExceptState
    p <*> q = Control.Monad.ap p q

  instance Monad (ExceptState e s) where
    m >>= f = joinExceptState (fmap f m)

  data EvaluationError = DivideByZero

  eval :: Expr -> ExceptState EvaluationError [Prim Double] Double
  eval expr = case expr of
    (Val value)    -> return value
    (Op (Add a b)) -> do resA <- eval a; resB <- eval b; modifyExceptState (Add resA resB :); return (resA + resB)
    (Op (Sub a b)) -> do resA <- eval a; resB <- eval b; modifyExceptState (Sub resA resB :); return (resA - resB)
    (Op (Mul a b)) -> do resA <- eval a; resB <- eval b; modifyExceptState (Mul resA resB :); return (resA * resB)
    (Op (Div a b)) -> do resA <- eval a; resB <- eval b; if resB == 0 
                                                         then throwExceptState DivideByZero
                                                         else modifyExceptState (Div resA resB :);
                                                         return (resA / resB)
    (Op (Abs a))   -> do resA <- eval a; modifyExceptState (Abs resA :); return (abs resA)
    (Op (Sgn a))   -> do resA <- eval a; modifyExceptState (Sgn resA :); return (signum resA)