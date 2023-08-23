-- Telco

-- Es una compañia que se dedica a comunicar las ciudades que se susbcriben a su servicio.
-- Primero las ingresa al mapa de la región. 
-- Luego establece vínculos entre ellas de cierta calidad y capacidad.
-- Finalmente establece canales que conectan distintas ciudades ocupando una unidad de 
-- capacidad por cada enlace recorrido.

-- Para sostener este modelo se cuenta con las siguientes entidades:

module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving Eq

instance Show Point where
    show :: Point -> String
    show (Poi x y) = "Point: (" ++ show x ++ "," ++ show y ++ ")"

newP :: Int -> Int -> Point
newP x y = Poi x y

difP :: Point -> Point -> Float  -- distancia absoluta
difP (Poi ax ay) (Poi bx by) = result where
    result = sqrt (fromIntegral (dx * dx + dy * dy))
    dx = ax - bx
    dy = ay - by