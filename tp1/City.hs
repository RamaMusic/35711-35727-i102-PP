module City ( City, newC, nameC, distanceC )
    where

import Point ( Point, difP )

data City = Cit String Point deriving Eq

instance Show City where
    show (Cit name point) = "\n    City: " ++ name ++ " | " ++ show point

newC :: String -> Point -> City
newC name point = if null name then error "No has ingresado un nombre a la ciudad." else Cit name point

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ point1) (Cit _ point2) = difP point1 point2