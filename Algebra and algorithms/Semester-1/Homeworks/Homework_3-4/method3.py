import numpy as np

def plane_equation(v1, v2, v3):
    u = np.array(v2) - np.array(v1)
    w = np.array(v3) - np.array(v1)
    n = np.cross(u, w)
    A, B, C = n
    D = -np.dot(n, v1)
    return A, B, C, D

def check_point_in_tetrahedron(P, A, B, C, D, epsilon=1e-9):
    planes = [
        (plane_equation(A, B, C), D),
        (plane_equation(A, B, D), C),
        (plane_equation(A, C, D), B),
        (plane_equation(B, C, D), A)
    ]
    
    zero_count = 0
    outside = False
    
    for (A_coef, B_coef, C_coef, D_coef), opposite_vertex in planes:
        F_P = A_coef * P[0] + B_coef * P[1] + C_coef * P[2] + D_coef
        F_opp = (A_coef * opposite_vertex[0] + 
                 B_coef * opposite_vertex[1] + 
                 C_coef * opposite_vertex[2] + 
                 D_coef)
        
        if abs(F_P) < epsilon:
            zero_count += 1
        elif F_P * F_opp < -epsilon:
            outside = True
    
    if outside:
        return "Снаружи"
    
    if zero_count == 0:
        return "Внутри"
    elif zero_count == 1:
        return "На грани"
    elif zero_count == 2:
        return "На ребре"
    elif zero_count == 3:
        return "На вершине"
    else:
        return "Вырожденный случай"

A = (0, 0, 0)
B = (1, 0, 0)
C = (0, 1, 0)
D = (0, 0, 1)

test_points = [
    ((0.25, 0.25, 0.25), "Внутри"),
    ((1/3, 1/3, 1/3), "На грани BCD"),
    ((0.5, 0, 0), "На ребре AB"),
    ((0.5, 0.5, 0), "На грани ABC"),
    ((0, 0.5, 0.5), "На грани ACD"),
    ((0, 0, 0), "На вершине A"),
    ((1, 1, 1), "Снаружи"),
    ((0.1, 0.2, 0.3), "Внутри")
]

print("=" * 50)
print("Метод 3: Метод уравнений плоскостей")
print("=" * 50)

for point, expected in test_points:
    result = check_point_in_tetrahedron(point, A, B, C, D)
    print(f"Точка {point}: {result} (ожидалось: {expected})")

additional_points = [
    ((0, 0.5, 0), "На ребре AC"),
    ((0, 0, 0.5), "На ребре AD"),
    ((0.5, 0, 0.5), "На грани ABD"),
    ((2, 0, 0), "Снаружи"),
    ((0.25, 0, 0), "На ребре AB")
]
