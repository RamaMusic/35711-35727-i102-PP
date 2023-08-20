module Region ( Region, newR, foundR, linkR )
   where
--  linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR
import Point
import City
import Quality
import Link
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving Show
newR :: Region
newR = Reg [] [] []

-- canPlaceCity :: City -> [City] -> Bool
-- canPlaceCity (Cit _ point1) cities = not (True `elem` [point1 == point2 | (Cit _ point2) <- cities])
-- preguntar que hacer ya que no podemos sacar los puntos a no ser que importemos el constructor de City.
foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city = {-if canPlaceCity city cities then error "Ya está esa ciudad en la región" else -} Reg (cities ++ [city]) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tun) c1 c2 quality = (Reg cities newLinks tun) where
   newLinks = links ++ [newL c1 c2 quality]
-- Agregar verificación que no exista el mismo link pero invertido o de mejor/peor calidad.

-- Supongo que la lista de City es una lista ya ordenada de las ciudades por donde pasan los links.
-- tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
-- tunelR (Reg cities links tun) new_cities = (Reg cities links newTunels) where

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tun) c1 c2 = or [connectsT c1 c2 tunel | tunel <- tun]

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) c1 c2 = or [linksL c1 c2 link | link <- links]

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg _ _ tun) c1 c2 = delayT (head [tunel | tunel <- tun, connectsT c1 c2 tunel])
-- ¿Existe una forma más eficiente de hacerlo? ¿Quizás sin usar head?

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg _ _ tun) c1 c2 = minimum [capacityL link | link <- links, usesT link tunel] where
   tunel = head [tunel | tunel <- tun, connectsT c1 c2 tunel]
   links = [link | link <- links, usesT link tunel]
-- Hay que probarlo.

-- Falta ver tambien los links dentro del túnel y qué capacidad disponible tienen entre ellos.