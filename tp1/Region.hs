module Region ( Region, newR, foundR, linkR, connectedR, linkedR, delayR, availableCapacityForR )
   where -- agregar tunelR,
import Point
import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel]

instance Show Region where
   show (Reg cities links tunels) = "Cities: " ++ show cities ++ "\n\nLinks : " ++ show links ++ "\n\nTunels: " ++ show tunels

newR :: Region
newR = Reg [] [] []

canPlaceCity :: City -> [City] -> Bool
canPlaceCity city1 cities = not (or [city2 `distanceC` city1 == 0| city2 <- cities])

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city  | isValid = Reg (cities ++ [city]) links tunels
                                       | otherwise = error "Ya existe una ciudad en ese punto."
                                       where isValid = canPlaceCity city cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tun) c1 c2 quality   | isValid = Reg cities newLinks tun
                                             | otherwise = error "Ya existe un link entre esas ciudades."
                                             where isValid = not (or [linksL c1 c2 link | link <- links])
                                                   newLinks = links ++ [newL c1 c2 quality]

-- Supongo que la lista de City es una lista ya ordenada de las ciudades por donde pasan los links.

-- Verificar que las ciudades que agreguemos en link y en tunelr esten dentro de la lista de region

-- tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
-- tunelR (Reg cities links tunnels) new_cities = (Reg cities links newTunels) where newtunnels = tunnels
   

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tun) c1 c2 = or [connectsT c1 c2 tunel | tunel <- tun]

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) c1 c2 = or [linksL c1 c2 link | link <- links]

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region@(Reg _ _ tun) c1 c2   | isValid = delayT (head [tunel | tunel <- tun, connectsT c1 c2 tunel])
                                    | otherwise = error "No existe un tunel que conecte ambas ciudades."
                                    where isValid = connectedR region c1 c2

-- availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
-- availableCapacityForR region@(Reg cities links tunnels) = 2

-- Falta ver tambien los links dentro del túnel y qué capacidad disponible tienen entre ellos.