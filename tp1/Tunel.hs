module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link ( Link, connectsL, delayL )
import City ( City )

data Tunel = Tun [Link] deriving Eq

instance Show Tunel where
   show :: Tunel -> String
   show (Tun links) = "Tunel: " ++ show links

newT :: [Link] -> Tunel
newT links = tunel where
   tunel | null links = error "No has ingresado ningún link."
         | otherwise = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c1 c2 (Tun links) = areConnected && areEndPoints where
   areConnected = (c1 `connectsL` h && c2 `connectsL` l) || (c1 `connectsL` l && c2 `connectsL` h)
   areEndPoints = (links `isEndPoint` c1) && (links `isEndPoint` c2)
   h = head links
   l = last links

isEndPoint :: [Link] -> City -> Bool
isEndPoint links city = length [link | link <- links, connectsL city link] == 1

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links)