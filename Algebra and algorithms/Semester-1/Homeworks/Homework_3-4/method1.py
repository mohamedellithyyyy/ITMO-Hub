import math

def getVector(v1, v2):
    return (v1[0]-v2[0], v1[1]-v2[1], v1[2]-v2[2])

def vector_product(v1, v2):
    return (v1[1]*v2[2]-v1[2]*v2[1],
            v1[2]*v2[0]-v1[0]*v2[2],
            v1[0]*v2[1]-v1[1]*v2[0])

def scalar_product(v1, v2):
    return v1[0]*v2[0] + v1[1]*v2[1] + v1[2]*v2[2]

def getVolume(w,x,y,z):
    wx = getVector(x, w)
    wy = getVector(y, w)
    wz = getVector(z, w)
    det = scalar_product(wx, vector_product(wy, wz))
    return abs(det) / 6.0

def classify_point_in_tetra(A,B,C,D,P, error_margin=10**(-9)):
    V = getVolume(A,B,C,D)
    if V <= error_margin:
        return "Тетраэдр некорректный"
    V1 = getVolume(P,B,C,D)  
    V2 = getVolume(A,P,C,D)
    V3 = getVolume(A,B,P,D)
    V4 = getVolume(A,B,C,P)
    S = V1 + V2 + V3 + V4
    
    zeros = sum(1 for v in (V1,V2,V3,V4) if v <= error_margin)
    outside = abs(S - V) > error_margin
    
    if outside:
        return "Точка снаружи"
    
    if zeros == 0:
        return "Точка внутри"
    elif zeros == 1:
        return "Точка на грани"
    elif zeros == 2:
        return "Точка на ребре"
    elif zeros == 3:
        return "Точка на вершине"
    else:
        return "Вырожденный случай"

A=(0,0,0); B=(1,0,0); C=(0,1,0); D=(0,0,1)
P=(0.25,0.25,0.25)
print(classify_point_in_tetra(A,B,C,D,P))
