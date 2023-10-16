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