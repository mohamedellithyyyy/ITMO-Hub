import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

def plot_tetrahedron(ax, A, B, C, D):
    vertices = np.array([A, B, C, D])
    
    edges = [
        [0, 1], [0, 2], [0, 3],
        [1, 2], [1, 3], [2, 3]
    ]
    
    for edge in edges:
        ax.plot3D(*zip(vertices[edge[0]], vertices[edge[1]]), 'b-', linewidth=2)
    
    ax.scatter(*zip(A, B, C, D), c='red', s=100, marker='o')
    
    labels = ['A', 'B', 'C', 'D']
    for i, label in enumerate(labels):
        ax.text(vertices[i][0], vertices[i][1], vertices[i][2], label, fontsize=12)

def plot_test_points(ax, points, colors, markers):
    for point, color, marker in zip(points, colors, markers):
        ax.scatter(*point, c=color, s=150, marker=marker, alpha=0.8)

A = np.array([0, 0, 0])
B = np.array([1, 0, 0])
C = np.array([0, 1, 0])
D = np.array([0, 0, 1])

test_points = [
    np.array([0.25, 0.25, 0.25]),  # Внутри - зеленый круг
    np.array([1/3, 1/3, 1/3]),     # На грани - желтый квадрат
    np.array([0.5, 0, 0]),         # На ребре - оранжевый треугольник
    np.array([0, 0, 0]),           # Вершина - красная звезда
    np.array([1, 1, 1]),           # Снаружи - черный крест
]

colors = ['green', 'yellow', 'orange', 'red', 'black']
markers = ['o', 's', '^', '*', 'x']

fig = plt.figure(figsize=(12, 10))
ax = fig.add_subplot(111, projection='3d')

plot_tetrahedron(ax, A, B, C, D)
plot_test_points(ax, test_points, colors, markers)

ax.set_xlabel('X')
ax.set_ylabel('Y')
ax.set_zlabel('Z')
ax.set_title('Визуализация тетраэдра и тестовых точек')
ax.view_init(elev=20, azim=45)

plt.legend(['Тетраэдр', 'Внутри', 'На грани', 'На ребре', 'На вершине', 'Снаружи'],
           loc='upper right')
plt.tight_layout()
plt.show()
