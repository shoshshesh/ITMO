module HW1.T1
  ( Day (..)
  , nextDay
  , afterDays
  , isWeekend
  , daysToParty
  ) where

import Numeric.Natural (Natural)
import Data.Function (fix)

data Day = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
  deriving (Show)

-- | Returns the day that follows the day of the week given as input.
nextDay :: Day -> Day
nextDay day = case day of
                   Monday    -> Tuesday
                   Tuesday   -> Wednesday
                   Wednesday -> Thursday
                   Thursday  -> Friday
                   Friday    -> Saturday
                   Saturday  -> Sunday
                   Sunday    -> Monday

-- | Returns the day of the week after a given number of days has passed.
afterDays :: Natural -> Day -> Day
afterDays = fix (\rec -> \n -> \day -> if n == 0 then day else rec (n - 1) (nextDay day))

-- | Checks if the day is on the weekend.
isWeekend :: Day -> Bool
isWeekend day = case day of
                     Saturday  -> True
                     Sunday    -> True
                     _         -> False

-- | Computes the number of days until Friday.
daysToParty :: Day -> Natural
daysToParty = fix (\rec -> \n -> \day ->
                    case day of
                         Friday    -> n
                         _         -> rec (n + 1) (nextDay day)) 0