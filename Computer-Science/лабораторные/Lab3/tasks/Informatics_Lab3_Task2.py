# # Author = Эллити Мохамед Эмад Ахмед Авад
# # Group = P3131
# # Date = 05.10.2025


import re
text = "КоРмА КоРкА КоРчмА КаРоА КорА КоРмАн КРА"
l1,l2,l3 = ('К','Р','А')
distance = 1
pattern = rf"\b{l1}[^{l1}{l1.lower()}{l2}{l2.lower()}{l3}{l3.lower()}]{{{distance}}}{l2}[^{l1}{l1.lower()}{l2}{l2.lower()}{l3}{l3.lower()}]{{{distance}}}{l3}\b"
matches = re.findall(pattern, text, re.IGNORECASE)
print("Найденные слова:", matches)









































# import re
# def find_words(words, letters, distance):
#     b1, b2, b3 = letters
#     # \bК[^КкРрАа]Р[^КкРрАа]А\b
#     exclude = f"{b1}{b1.lower()}{b2}{b2.lower()}{b3}{b3.lower()}"
#     pattern = rf"\b{b1}[^{exclude}]{{{distance}}}{b2}[^{exclude}]{{{distance}}}{b3}\b"
#     regex = re.compile(pattern)
#     result = [word for word in words if regex.search(word)]
#     return result
# test_words = [
#     "КоРмА",
#     "КоРкА",
#     "КоРчмА",
#     "КxxРyyА",
#     "кАР",
#     "К1Р2А"
# ]
# print("Найденные слова:",find_words(test_words, ('К','Р','А'), 1))
# print("Найденные слова:",find_words(test_words, ('К','Р','А'), 2))
# print("Найденные слова:",find_words(test_words, ('к','А','Р'), 0))
# print("Найденные слова:",find_words(test_words, ('К','Р','А'), 0))
# print("Найденные слова:",find_words(test_words, ('К','Р','А'), 2))

# # words = input("Enter words separated by space: ").split()
# # letters_input = input("Enter three letters like КРА (no spaces): ")
# # letters = tuple(letters_input) 
# # distance = int(input("Number of distance: "))
# # print(find_words(words, letters, distance))
