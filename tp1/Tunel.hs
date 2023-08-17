module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link ( Link, connectsL, delayL )
import City ( City )
import Data.List

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

-- isInTunel :: City -> Tunel -> Bool
-- isInTunel c1 (Tun links) = not (null [link | link <- links, c1 `connectsL` link])

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c1 c2 (Tun links) = connectsL c1 (head links) && connectsL c2 (last links) || connectsL c1 (last links) && connectsL c2 (head links)
-- MEJORAR

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link 
usesT link (Tun links) = link `elem` links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = sum (map delayL links)