# from openpyxl import load_workbook

# wb = load_workbook("./lab5.xlsx")
# ws = wb["Sheet2"]
# for row in ws.iter_rows(min_col=6, max_col=6, min_row=1, max_row=ws.max_row):
#     for cell in row:
#         cell.value = None 
# wb.save("./mainCleaned.xlsx")

# print("Column 6 (F) has been cleaned without deleting the column.")

import pandas as pd

# Read Excel without headers
df = pd.read_excel("./lab5.xlsx", sheet_name="Sheet2", header=None)

# Clean column 6 (index 5)
df[5] = None

# Function to add borders except B1:C2
def style_borders(x):
    styles = []
    for row_i in range(len(x)):
        row_styles = []
        for col_i in range(len(x.columns)):
            # Skip borders for B1:C2
            if row_i in [0, 1] and col_i in [1, 2]:
                row_styles.append("")
            else:
                row_styles.append("border: 1px solid black;")
        styles.append(row_styles)
    return styles

styled = df.style.apply(style_borders, axis=None)

# Save cleaned data (borders DO NOT save in Excel)
df.to_excel("./lab5Cleaned.xlsx", index=False, header=False)

styled
