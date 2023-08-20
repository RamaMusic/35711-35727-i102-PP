module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City ( City, distanceC )
import Quality ( Quality, capacityQ, delayQ )

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality | city1 == city2 = error "Ingresá dos ciudades distintas."
                         | city1 `distanceC` city2 == 0 = error "Las ciudades no pueden estar en el mismo lugar."
                         | otherwise = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin lCity1 lCity2 _)   | lCity1 == lCity2 = error "Ingresá dos ciudades distintas."
                                       | otherwise = city `elem` [lCity1, lCity2]

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link | city1 == city2 = error "Ingresá dos ciudades distintas."
                        | otherwise = city1 `connectsL` link && city2 `connectsL` link

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin c1 c2 quality) = c1 `distanceC` c2 * delayQ quality

{- Ejemplo:

l1 :: Link
l1 = newL c1 c2 q1

l2 :: Link
l2 = newL c2 c3 q2

links :: Bool
links = linksL c1 c2 l1
Output: True

links' :: Bool
links' = linksL c1 c3 l1
Output: False

connects :: Bool
connects = connectsL c1 l1
Output: True

connects' :: Bool
connects' = connectsL c3 l1
Output: False

capacity :: Int
capacity = capacityL l1
Output: 1

delay :: Float
delay = delayL l1
Output: 1.0

-- Error examples:
l3 :: Link
l3 = newL c1 c1 q1
Output: *** Exception: Ingresá dos ciudades distintas.

l4 :: Link
l4 = newL c1 c2 q3 where
   q3 = newQ "Good" 10 0.5
   c1 = newC "City 1" p1
   c2 = newC "City 2" p1

Output: *** Exception: Las ciudades no pueden estar en el mismo lugar.

-}