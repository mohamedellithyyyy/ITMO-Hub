import numpy as np

def point_in_tetrahedron(P, A, B, C, D):
    vAB = B - A
    vAC = C - A
    vAP = P - A
    vAD = D - A
    M_P_ABC = np.linalg.det([vAB, vAC, vAP])
    M_D_ABC = np.linalg.det([vAB, vAC, vAD])
    M_P_ABD = np.linalg.det([vAB, vAD, vAP])
    M_C_ABD = np.linalg.det([vAB, vAD, vAC])
    M_P_ACD = np.linalg.det([vAC, vAD, vAP])
    M_B_ACD = np.linalg.det([vAC, vAD, vAB])
    vCB = B - C
    vCD = D - C
    vCP = P - C
    vCA = A - C
    M_P_BCD = np.linalg.det([vCB, vCD, vCP])
    M_A_BCD = np.linalg.det([vCB, vCD, vCA])
    epsilon = 1e-9

    def check_sign(Mp, Mopp):
        if Mopp == 0:
            return False, "Вырожденный тетраэдр"
        if Mp * Mopp < -epsilon:
            return False, "Снаружи"
        return True, "Условие выполнено"
    
    results = [
        check_sign(M_P_ABC, M_D_ABC),
        check_sign(M_P_ABD, M_C_ABD),
        check_sign(M_P_ACD, M_B_ACD),
        check_sign(M_P_BCD, M_A_BCD)
    ]

    if not all(res[0] for res in results):
        return "Снаружи"

    zero_counts = 0
    if abs(M_P_ABC) < epsilon: zero_counts += 1
    if abs(M_P_ABD) < epsilon: zero_counts += 1
    if abs(M_P_ACD) < epsilon: zero_counts += 1
    if abs(M_P_BCD) < epsilon: zero_counts += 1

    if zero_counts == 0: return "Внутри"
    elif zero_counts == 1: return "На грани"
    elif zero_counts == 2: return "На ребре"
    elif zero_counts >= 3: return "Совпадение с вершиной" 

A = np.array([0, 0, 0])
B = np.array([1, 0, 0])
C = np.array([0, 1, 0])
D = np.array([0, 0, 1])

P_inside = np.array([1/4,1/4,1/4])
P_on_face = np.array([1/3, 1/3, 1/3])
P_on_edge = np.array([0.5, 0, 0])
P_on_vertex = np.array([0, 0, 0])
P_outside = np.array([1, 1, 1])

print(f"P_inside: {point_in_tetrahedron(P_inside, A, B, C, D)}")
print(f"P_on_face: {point_in_tetrahedron(P_on_face, A, B, C, D)}")
print(f"P_on_edge: {point_in_tetrahedron(P_on_edge, A, B, C, D)}")
print(f"P_on_vertex: {point_in_tetrahedron(P_on_vertex, A, B, C, D)}")
print(f"P_outside: {point_in_tetrahedron(P_outside, A, B, C, D)}")
