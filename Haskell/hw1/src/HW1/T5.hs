module HW1.T5
  ( splitOn
  , joinWith
  ) where

import Data.List.NonEmpty (NonEmpty(..))
import Data.Function (fix)

splitOn :: Eq a => a -> [a] -> NonEmpty [a]
splitOn sep string = fix (\rec -> \inputStr -> \curWord -> \words -> 
                           case inputStr of
                                (ch:rest) -> if   sep /= ch 
                                             then rec rest (ch : curWord) words
                                             else rec rest [] (curWord : words)
                                []        -> curWord :| words
                         ) (reverse string) [] []

joinWith :: a -> NonEmpty [a] -> [a]
joinWith sep words = fix (\processWords ->
                          \inputList    ->
                          \string       -> 
                           case inputList of
                                (word : rest) -> processWords rest (fix (\processWord ->
                                                                         \w           ->
                                                                         \str         ->
                                                                          case w of
                                                                               (ch : restW) -> processWord restW (ch : str)
                                                                               []           -> sep : str) (reverse word) string)
                                []            -> tail string -- string is not empty always because we always add sep in processWord
                         ) (reverse (unpack words)) []

unpack :: NonEmpty [a] -> [[a]]
unpack (element :| rest) = element : rest