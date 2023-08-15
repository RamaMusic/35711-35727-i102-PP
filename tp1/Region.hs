module Region ( Region, newR, foundR, linkR )
   where
--  linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR
import Point
import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving Show
newR :: [City] -> [Link] -> [Tunel] -> Region
newR cities links tunels = Reg cities links tunels

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city = newR (cities ++ [city]) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tun) c1 c2 quality = newR cities newLinks tun where
   newLinks = (links ++ [newL c1 c2 quality])

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR = 2

-- connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
-- connectedR = 2

-- linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
-- linkedR = 4

-- delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
-- delayR = 3

-- availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
-- availableCapacityForR = 2