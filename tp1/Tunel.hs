module Tunel ( Tunel, newT, connectsT, usesT, delayT, isInTunel )
   where

import Link ( Link, connectsL, delayL )
import City ( City )

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

isInTunel :: City -> Tunel -> Bool
isInTunel c1 (Tun links) = not (null [link | link <- links, c1 `connectsL` link])

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c1 c2 tunel = c1 `isInTunel` tunel && c2 `isInTunel` tunel

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links)