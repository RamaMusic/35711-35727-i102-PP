module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
   where
      
import City ( distanceC, City )
import Quality ( Quality )
import Link ( capacityL, linksL, newL, Link )
import Tunel ( connectsT, delayT, newT, usesT, Tunel )

data Region = Reg [City] [Link] [Tunel]

instance Show Region where
   show :: Region -> String
   show (Reg cities links tunels) = "Cities: " ++ show cities ++ "\n\nLinks : " ++ show links ++ "\n\nTunels: " ++ show tunels

newR :: Region
newR = Reg [] [] []

canPlaceCity :: City -> [City] -> Bool
canPlaceCity city1 cities = not (or [city2 `distanceC` city1 == 0| city2 <- cities])

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city
   | isValid = Reg (cities ++ [city]) links tunels
   | otherwise = error "Ya existe una ciudad en ese punto."
   where isValid = canPlaceCity city cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tun) c1 c2 quality
   | isValid = Reg cities newLinks tun
   | otherwise = error "Ya existe un link entre esas ciudades."
   where isValid = not (or [linksL c1 c2 link | link <- links])
         newLinks = links ++ [newL c1 c2 quality]

getLinkForR :: Region -> City -> City -> Link
getLinkForR (Reg _ links _) c1 c2
   | isValid = head link_list
   | otherwise = error "No existe un enlace entre esas ciudades."
   where link_list = [link | link <- links, linksL c1 c2 link]
         isValid = length link_list == 1

pathR :: Region -> [City] -> [Link]
pathR _ [_] = []
pathR region (city:cities) = [getLinkForR region city (head cities)] ++ pathR region cities

isInRegion :: Region -> [City] -> Bool
isInRegion (Reg cities _ _) list = and [or [city == city2 | city <- cities] | city2 <- list]

isRepeated :: [City] -> Bool
isRepeated [] = False
isRepeated (city:cities) = or [city == city2 | city2 <- cities]

isAlreadyConnected :: Region -> [City] -> Bool
isAlreadyConnected (Reg _ _ tunnels) cities = or [connectsT h l tunnel | tunnel <- tunnels] where
   h = head cities
   l = last cities

canCreateTunel :: Region -> [City] -> Bool
canCreateTunel region (city:cities) 
   | length cities == 1 = availableCapacityForR region city (head cities) >= 1
   | otherwise = availableCapacityForR region city (head cities) >= 1 && canCreateTunel region cities

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region@(Reg cities links tunnels) new_cities
   | not (isInRegion region new_cities) = error "Alguna de las ciudades no pertenece a la región."
   | isRepeated new_cities = error "La lista de ciudades contiene dos o más ciudades iguales."
   | length new_cities <= 1 = error "La lista de ciudades debe contener al menos dos ciudades."
   | isAlreadyConnected region new_cities = error "Las ciudades ya están conectadas."
   | not (canCreateTunel region new_cities) = error "No hay capacidad disponible para crear un tunel entre esas ciudades."
   | otherwise = Reg cities links newTunnels
   where newTunnels = tunnels ++ [newT path]
         path = pathR region new_cities

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tun) c1 c2 = or [connectsT c1 c2 tunel | tunel <- tun]

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) c1 c2 = or [linksL c1 c2 link | link <- links]

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region@(Reg _ _ tun) c1 c2
   | isValid = delayT (head [tunel | tunel <- tun, connectsT c1 c2 tunel])
   | otherwise = error "No existe un tunel que conecte ambas ciudades."
   where isValid = connectedR region c1 c2

tunnelCounter :: Region -> Link -> Int
tunnelCounter (Reg _ _ tunnels) link = length [tunel | tunel <- tunnels, link `usesT` tunel]

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region@(Reg _ links _) c1 c2 = capacityL target - (region `tunnelCounter` target) where
   target = getLinkForR region c1 c2