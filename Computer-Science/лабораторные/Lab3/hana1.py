 # Author = Ewida Hana Wael Ibrahim Khalil
# Group = P3115
# Date = 05.10.2025
import re

def replace_time_with_tbd(text):
    # Regex to match HH:MM or HH:MM:SS (24-hour format)
    pattern = r'\b([01]\d|2[0-3]):[0-5]\d(:[0-5]\d)?\b'
    return re.sub(pattern, '(TBD)', text)


# ---------- TEST CASES ----------

# Test 1
text1 = "Уважаемые студенты! В эту субботу в 15:00 планируется доп. занятие. То есть в 17:00:01 оно уже точно кончится."
print("Test 1:\n", replace_time_with_tbd(text1))
# Expected: "Уважаемые студенты! В эту субботу в (TBD) планируется доп. занятие. То есть в (TBD) оно уже точно кончится."

# Test 2
text2 = "Встреча начнется в 09:15 и закончится в 10:45."
print("Test 2:\n", replace_time_with_tbd(text2))
# Expected: "Встреча начнется в (TBD) и закончится в (TBD)."

# Test 3
text3 = "Запись началась в 22:59:59 и завершилась в 23:00:00."
print("Test 3:\n", replace_time_with_tbd(text3))
# Expected: "Запись началась в (TBD) и завершилась в (TBD)."

# Test 4
text4 = "Система запускается в 00:00 и выключается в 23:59."
print("Test 4:\n", replace_time_with_tbd(text4))
# Expected: "Система запускается в (TBD) и выключается в (TBD)."

# Test 5
text5 = "Ошибка в 25:00 и в 12:75, но 12:30 верно."
print("Test 5:\n", replace_time_with_tbd(text5))
# Expected: "Ошибка в 25:00 и в 12:75, но (TBD) верно."
