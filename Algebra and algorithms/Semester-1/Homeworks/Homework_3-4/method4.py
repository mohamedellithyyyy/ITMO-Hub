import numpy as np

def barycentric_coordinates(P, A, B, C, D, epsilon=1e-12):
    def tetra_volume(v1, v2, v3, v4):
        v21 = v2 - v1
        v31 = v3 - v1
        v41 = v4 - v1
        triple_product = np.dot(v21, np.cross(v31, v41))
        return abs(triple_product) / 6.0
    
    V_total = tetra_volume(A, B, C, D)
    
    if V_total < epsilon:
        return None, "Вырожденный тетраэдр"
    
    V_PBCD = tetra_volume(P, B, C, D)
    V_APCD = tetra_volume(A, P, C, D)
    V_ABPD = tetra_volume(A, B, P, D)
    V_ABCP = tetra_volume(A, B, C, P)
    
    alpha = V_PBCD / V_total
    beta = V_APCD / V_total
    gamma = V_ABPD / V_total
    delta = V_ABCP / V_total
    
    return [alpha, beta, gamma, delta], None

def classify_by_barycentric(P, A, B, C, D, epsilon=1e-12):
    coords, error = barycentric_coordinates(P, A, B, C, D, epsilon)
    
    if error:
        return error
    
    alpha, beta, gamma, delta = coords
    
    coords = [max(c, 0) if abs(c) < epsilon else c for c in coords]
    alpha, beta, gamma, delta = coords
    
    if any(c < -epsilon for c in coords):
        return "Снаружи"
    
    zero_count = sum(1 for c in coords if abs(c) < epsilon)
    
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

def point_location_barycentric(P, A, B, C, D, epsilon=1e-12):
    coords, error = barycentric_coordinates(P, A, B, C, D, epsilon)
    
    if error:
        return error, None
    
    alpha, beta, gamma, delta = coords
    
    location = classify_by_barycentric(P, A, B, C, D, epsilon)
    
    coords_corrected = [max(c, 0) if abs(c) < epsilon else c for c in coords]
    alpha_c, beta_c, gamma_c, delta_c = coords_corrected
    
    return location, {
        'alpha': alpha_c,
        'beta': beta_c,
        'gamma': gamma_c,
        'delta': delta_c,
        'sum': alpha_c + beta_c + gamma_c + delta_c
    }

A = np.array([0, 0, 0])
B = np.array([1, 0, 0])
C = np.array([0, 1, 0])
D = np.array([0, 0, 1])

test_points = [
    (np.array([0.25, 0.25, 0.25]), "Внутри"),
    (np.array([1/3, 1/3, 1/3]), "Внутри"),
    (np.array([0.5, 0, 0]), "На ребре"),
    (np.array([0, 0, 0]), "На вершине"),
    (np.array([1, 1, 1]), "Снаружи"),
    (np.array([0.1, 0.2, 0.3]), "Внутри"),
]

print("Метод барицентрических координат:")
print("-" * 50)

for P, expected in test_points:
    location, info = point_location_barycentric(P, A, B, C, D)
    
    if info:
        print(f"Точка {P}:")
        print(f"  Положение: {location} (ожидалось: {expected})")
        print(f"  Координаты: α={info['alpha']:.3f}, β={info['beta']:.3f}, γ={info['gamma']:.3f}, δ={info['delta']:.3f}")
        print(f"  Сумма: {info['sum']:.3f}")
        print()
    else:
        print(f"Точка {P}: {location}")
        print()