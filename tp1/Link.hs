module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City ( City, distanceC, nameC )
import Quality ( Quality, capacityQ, delayQ )

data Link = Lin City City Quality deriving Eq

instance Show Link where
    show (Lin c1 c2 quality) = "\n    Link: \n    " ++ nameC c1 ++ " <-> " ++ nameC c2 ++ "\n    " ++ show quality

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality | city1 == city2 = error "No puedes crear un link entre la misma ciudad."
                         | city1 `distanceC` city2 == 0 = error "Las ciudades no pueden estar en el mismo lugar."
                         | otherwise = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin lCity1 lCity2 _) = city `elem` [lCity1, lCity2]

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link | city1 == city2 = error "Ingresá dos ciudades distintas."
                        | otherwise = city1 `connectsL` link && city2 `connectsL` link

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin c1 c2 quality) = c1 `distanceC` c2 * delayQ quality