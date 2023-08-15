module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality 

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin lCity1 lCity2 _) = city `elem` [lCity1, lCity2]

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = city1 `connectsL` link && city2 `connectsL` link
--linksL city1 city2 (Lin lCity1 lCity2 _) = (lCity1, lCity2) `elem` [(city1, city2), (city2, city1)]

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ quality) = delayQ quality
