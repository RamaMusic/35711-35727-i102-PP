import Data.List
import Point
import City
import Quality
import Link
import Tunel
import Region


-- POINT --
p1 = newP 1 1 -- Output Poi 1.0 1.0
p2 = newP 2 2
p3 = newP 3 3
p4 = newP 4 4
p5 = newP 5 5
p1' = newP 1 1

testP = [difP p1 p2 == sqrt 2, 
        p1 /= p2, 
        not (p1 == p3),
        p1 == p1']

-- CITY -- 
bsas = newC "Buenos Aires" p1 -- Output Cit "Buenos Aires" (Poi 1.0 1.0)
capital = newC "Caba" p1
chaco = newC "Chaco" p2
cacho = newC "Chaco" p2
sn = newC "Santiago" p3
salta = newC "Salta" p4
snn = newC "SanNicolas" p5

errorCity = newC "" p1 -- Output *** Exception: No has ingresado un nombre a la ciudad.

testC = [nameC bsas == "Buenos Aires",
        distanceC bsas capital == 0,
        distanceC bsas chaco == sqrt 2
        ]

-- QUALITY -- 
low = newQ "low" 2 10 -- Output: "Qua "low" 2 10.0"
medium = newQ "medium" 4 20
high = newQ "high" 6 30
errorQuality = newQ "" 0 0.0 -- Output: *** Exception: No has ingresado una capacidad, delay o nombre válido.

testQ = [capacityQ low == 2,
        delayQ low == 10,
        medium /= low
        ]

-- LINK --
l1 = newL bsas chaco low
l1' = newL bsas chaco high
l1'' = newL bsas chaco low
l2 = newL chaco sn medium
l3 = newL sn salta high
l4 = newL snn sn high

errorLink1 = newL bsas bsas low -- Output: *** Exception: No puedes crear un link entre la misma ciudad.
errorLink2 = newL bsas capital low -- Output: *** Exception: Las ciudades no pueden estar en el mismo lugar.

testL = [l1 /= l1', 
        l1 == l1'', 
        connectsL chaco l2,
        not (connectsL bsas l2),
        linksL salta sn l3,
        not (linksL salta sn l4),
        capacityL l1 == 2,
        delayL l1 == sqrt 2 * delayQ low]

-- TUNEL --
t1 = newT [l1, l2, l3]
t2 = newT [l2, l3, l4]
t3 = newT [l1, l2]
errorTunel = newT [] -- Output: *** Exception: No has ingresado ningún link.

testT = [connectsT bsas salta t1,
        not (connectsT bsas chaco t1),
        not (usesT l4 t1),
        usesT l3 t1,
        delayT t3 == sqrt 2 * delayQ low + sqrt 2 * delayQ medium]

-- REGION --
r1 = newR 
r2 = foundR r1 bsas -- Output: Reg [Cit "Buenos Aires" (Poi 1 1)] [] []
r3 = foundR r2 chaco
r4 = foundR r3 sn
r5 = foundR r4 salta
r6 = foundR r5 snn
errorRegion = foundR r6 capital -- Output: *** Exception: Ya existe una ciudad en ese punto.

r7 = linkR r5 bsas chaco low -- Output: Reg [Cit ...] [Lin (Cit "Buenos ...) (Cit "Chaco ...) (Qua "low" 2 10.0)] []
r8 = linkR r7 chaco sn medium
r8' = linkR r7 chaco bsas medium -- Output: *** Exception: Ya existe un link entre esas ciudades.

r9 = tunelR r8 [bsas, chaco, sn]
r9' = tunelR r8 [bsas, chaco, sn, salta] -- Output: *** Exception: No existe un enlacee entre esas ciudades.

r10 = tunelR (linkR (foundR r9' snn) salta snn high) [salta, snn]


testR = []

-- Global tests
testAll = all and [testP, testC, testQ, testL, testT, testR]
testAll' = map and [testP, testC, testQ, testL, testT, testR]

-- Verifica que todos los tests sean verdaderos. Si al menos uno es falso, devuelve False.


