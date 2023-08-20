module City ( City, newC, nameC, distanceC )
    where

import Point ( Point, difP )

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC name point = if null name then error "No has ingresado un nombre a la ciudad." else Cit name point

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ point1) (Cit _ point2) = difP point1 point2

{- Ejemplo:
c1 :: City
c1 = newC "City 1" p1

c2 :: City
c2 = newC "City 2" p2

name :: String
name = nameC c1
Output: "City 1"

distance :: Float
distance = distanceC c1 c2
Output: 1.4142135 or sqrt 2

-- Error examples:
c3 :: City
c3 = newC "" p1
Output: *** Exception: No has ingresado un nombre a la ciudad.
-}