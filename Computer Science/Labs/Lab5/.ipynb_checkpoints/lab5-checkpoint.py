import pandas as pd
from openpyxl import load_workbook
# Загружаем файл
wb = load_workbook("./main.xlsx")
ws = wb["Sheet2"]

# Удаляем 7-й столбец (G)
ws.delete_cols(6)

# Сохраняем изменения в тот же файл
wb.save("./main.xlsx")

print("7-й столбец удалён без изменения формата")
