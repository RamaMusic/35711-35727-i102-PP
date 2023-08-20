import Point
import City
import Quality
import Link
import Tunel
import Region

p1 = newP 1 1
p2 = newP 2 2
p3 = newP 3 3
p4 = newP 4 4
p5 = newP 5 5

bsas = newC "" p1
capital = newC "Caba" p1
chaco = newC "Chaco" p2
cacho = newC "Chaco" p2
test = chaco == cacho
sn = newC "Santiago" p3
salta = newC "Salta" p4
snn = newC "SanNicolas" p5

low = newQ "low" 2 10
medium = newQ "medium" 4 20
high = newQ "high" 6 30

l1 = newL bsas chaco low
l2 = newL chaco sn medium
l3 = newL sn salta high
l4 = newL snn sn high

t1 = newT [l1, l2, l3]
t2 = newT [l2, l3, l4]

a = connectsT bsas salta t1
c = connectsT bsas chaco t1
d = usesT l4 t1
e = usesT l3 t1

-- let r1 = newR
-- r1 = foundR r1 bsas
-- r1 = foundR r1 capital

-- r1 = newR [bsas, chaco, sn, salta] [l1, l2, l3] [t1, t2]
-- r2 = foundR r1 snn

