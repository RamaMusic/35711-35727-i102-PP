module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link ( Link, connectsL, delayL )
import City ( City )
import Data.List

data Tunel = Tun [Link] deriving (Eq)

instance Show Tunel where
   show (Tun links) = "Tunel: " ++ show links

newT :: [Link] -> Tunel
newT links = tunel where
   tunel | null links = error "No has ingresado ningún link."
         | otherwise = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c1 c2 (Tun links) = c1 `connectsL` h && c2 `connectsL` l || c1 `connectsL` l && c2 `connectsL` h where
   h = head links
   l = last links

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links)

{- Ejemplo:

t1 :: Tunel
t1 = newT [l1, l2]

t2 :: Tunel
t2 = newT [l1, l3]

connects :: Bool
connects = connectsT c1 c2 t1
Output: True

connects' :: Bool
connects' = connectsT c1 c3 t1
Output: False

uses :: Bool
uses = usesT l1 t1
Output: True

uses' :: Bool
uses' = usesT l2 t3
Output: False

delay :: Float
delay = delayT t1
Output: 2.0

-- Error examples:
t3 :: Tunel
t3 = newT []
Output: *** Exception: No has ingresado ningún link.

-}
