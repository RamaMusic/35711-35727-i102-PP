module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving Eq

instance Show Quality where
    show (Qua name capacity delay) = "Quality: " ++ name ++ " " ++ show capacity ++ " " ++ show delay

-- ¿Crear una verificación para ver que el nombre no esté vacío, la capacidad sea mayor que 0 y el delay mayor que 0?
checkQ ::String -> Int -> Float -> Bool
checkQ name capacity delay = not (null name) && capacity > 0 && delay > 0

newQ :: String -> Int -> Float -> Quality
newQ name capacity delay
   | isValid = Qua name capacity delay
   | otherwise = error "No has ingresado una capacidad, delay o nombre válido."
   where isValid = checkQ name capacity delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay