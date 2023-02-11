{-# LANGUAGE DerivingStrategies #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}

module HW2.T6
  ( ParseError (..)
  , Parser (..)
  , runP
  , parseExpr
  ) where

  import HW2.T1 (Except (..), Annotated (..))
  import HW2.T4
  import HW2.T5
  import GHC.Natural (Natural)
  import Control.Applicative (Alternative (..), optional, many, some)
  import Control.Monad (MonadPlus, mfilter)
  import Data.Char
  import Data.Function (fix)

  data ParseError = ErrorAtPos Natural

  newtype Parser a = P (ExceptState ParseError (Natural, String) a)
    deriving newtype (Functor, Applicative, Monad)

  runP :: Parser a -> String -> Except ParseError a
  runP (P (ES f)) input = case f (0, input) of
                               (Error er) -> Error er
                               (Success (a :# _)) -> Success a

-- 1) What happens when the string is empty?
--    As we expected a char but found an empty string,
--    the result of parser's function transforms to
--    Error that contains ErrorAtPos with position where we expected the char.

-- 2) How does the parser state change when a character is consumed?
--    State changes to a new state with incremented position and old string without first char.

-- Parser of char.
  pChar :: Parser Char
  pChar = P $ ES $ \(pos, s) ->
    case s of
      []     -> Error (ErrorAtPos pos)
      (c:cs) -> Success (c :# (pos + 1, cs))

-- Parser that always fails.
  parseError :: Parser a
  parseError = P $ ES $ \_ -> Error $ ErrorAtPos 0

  instance Alternative Parser where
    empty = parseError
    (P (ES f)) <|> (P (ES g)) = P $ ES $ \state ->
      case f state of
           (Error _)   -> g state
           (Success a) -> Success a

  instance MonadPlus Parser   -- No methods.

-- Parser of end of input.
  pEof :: Parser ()
  pEof = P $ ES $ \state@(pos, s) ->
    case s of
         [] -> Success (() :# state)
         _  -> Error (ErrorAtPos pos)

-- Parser of numbers.
  pNum :: Parser Expr
  pNum = do
    skipWhitespaces
    pDouble <|> pInteger

-- Parser of integer numbers only.
  pInteger :: Parser Expr
  pInteger = parseNum >>= (return . Val)

-- Parser of double numbers only.
  pDouble :: Parser Expr
  pDouble = do
    number <- parseNumDotNum <|> parseNum
    maybe_exp <- optional parseExp
    case maybe_exp of
         Nothing   -> return (Val $ fromRational number)
         Just eValue  -> if eValue >= 0
                         then return (Val $ fromRational (number * 10^eValue))
                         else return (Val $ fromRational (number / 10^(-1 * eValue)))

-- Parser of such numbers: <digits>.<digits>
  parseNumDotNum :: Parser Rational
  parseNumDotNum = do
    integer <- parseNum
    _ <- parseChar ( == '.')
    ratio <- parseFracNum
    return (integer + ratio)

-- Parser of such numbers: <digits>
  parseNum :: Num a => Parser a
  parseNum = do
    numStr <- some $ parseChar Data.Char.isDigit
    return $ fromStrToNum numStr


-- Parser of exponent: e<number>
  parseExp :: Parser Int
  parseExp = do
    _ <- parseChar ( == 'e')
    sign <- optional $ parseChar ( == '-')
    expStr <- parseNum
    case sign of
         Nothing -> return (expStr :: Int)
         Just _  -> return (-1 * expStr :: Int)


-- Parser of fractional part of a number (after '.').
  parseFracNum :: Parser Rational
  parseFracNum = do
    numStr <- some $ parseChar Data.Char.isDigit
    return $ fromStrFracToRational numStr


-- Convert string to number.
  fromStrToNum :: Num a => String -> a
  fromStrToNum str = fromIntegral $ foldl (\b -> \a -> b * 10 + (digitToInt a)) 0 str

-- Convert string of fractional part to Rational (00123 -> 0.00123).
  fromStrFracToRational :: String -> Rational
  fromStrFracToRational str = let number = fromStrToNum str :: Rational
                                  counter = length str
                               in  fix (\rec -> \n -> \count ->
                                         if count == 0 then n else rec (n * 0.1) (count - 1)) number  counter

-- Skips whitespaces.
  skipWhitespaces :: Parser ()
  skipWhitespaces = do
    _ <- many $ parseChar (\c -> c `elem` [' ', '\n', '\r', '\t'])
    return ()

-- Parser of char which satisfies the condition.
  parseChar :: (Char -> Bool) -> Parser Char
  parseChar f = mfilter f pChar

-- Parser of expressions.
-- Grammar:
-- E  -> T E'
-- E' -> + T E' | - T E' | eps
-- T  -> F T'
-- T' -> * F T' | / F T' | eps
-- F  -> n | ( E )
  parseExpr :: String -> Except ParseError Expr
  parseExpr = runP (e <* pEof)

-- Parses E -> T E'
  e :: Parser Expr
  e = do
    skipWhitespaces
    term <- t
    ePrime term

-- Parser of Add and Sub.
  parseAddSub :: Parser (a -> a -> Prim a)
  parseAddSub = do
    skipWhitespaces
    op <- parseChar (\c -> c `elem` ['+', '-'])
    case op of 
         '+' -> return Add
         '-' -> return Sub
         _   -> undefined   -- unreachable


-- Parser of Mul and Div.
  parseMulDiv :: Parser (a -> a -> Prim a)
  parseMulDiv = do
    skipWhitespaces
    op <- parseChar (\c -> c `elem` ['*', '/'])
    case op of 
         '*' -> return Mul
         '/' -> return Div
         _   -> undefined   -- unreachable

-- Parses T -> F T'
  t :: Parser Expr
  t = do
    skipWhitespaces
    factor <- fact
    tPrime factor

-- Parses E' -> + T E' | - T E' | eps
  ePrime :: Expr -> Parser Expr
  ePrime termL = do
    skipWhitespaces
    maybe_op <- optional parseAddSub
    case maybe_op of
         Nothing -> return termL
         Just op -> t >>= (\termR -> ePrime (Op (termL `op` termR)))

-- Parses T' -> * F T' | / F T' | eps
  tPrime :: Expr -> Parser Expr
  tPrime factorL = do
    skipWhitespaces
    maybe_op <- optional parseMulDiv
    case maybe_op of
         Nothing -> return factorL
         Just op -> fact >>= (\factorR -> tPrime (Op (factorL `op` factorR)))

-- Parses F -> n | ( E )
  fact :: Parser Expr
  fact = pNum <|> pBrackets

-- Parser of (E)
  pBrackets :: Parser Expr
  pBrackets = do
    skipWhitespaces
    _ <- parseChar ( == '(')
    expr <- e
    _ <- parseChar ( == ')')
    skipWhitespaces
    return expr