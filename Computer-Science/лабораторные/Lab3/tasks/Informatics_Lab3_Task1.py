# Author = Эллити Мохамед Эмад Ахмед Авад
# Group = P3131
# Date = 05.10.2025

import re
def solve(text):
    # Regex to match surnames followed by initials
    pattern = r'\b([A-ZА-ЯЁ][a-zа-яё]+(?:-[A-ZА-ЯЁ][a-zа-яё]+)?)\s+[A-ZА-ЯЁ]\.[A-ZА-ЯЁ]\.'
    # Find all surnames
    surnames = re.findall(pattern, text)
    # Remove duplicates, if any
    surnames = list(set(surnames))
    # Sort alphabetically
    surnames.sort()
    # if (surnames.isnull()):
    #     return "Can't find surnames"
    return surnames