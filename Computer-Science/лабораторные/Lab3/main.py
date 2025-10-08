# Author = Эллити Мохамед Эмад Ахмед Авад
# Group = P3131
# Date = 05.10.2025

from tasks import Informatics_Lab3_Task1,Informatics_Lab3_Task2,Informatics_Lab3_Task3

def main():
    task = input("Please enter a task number (1, 2, or 3): ").strip()

    if task == '1':
        text = input("Enter a text to extract surnames: ")
        result = task1.solve(text)
        print("Surnames found (sorted alphabetically):")
        for surname in result:
            print(surname)

    elif task == '2':
        letters = input("Enter three letters (without spaces, e.g., KRA): ").strip()
        distance = int(input("Enter the fixed distance between letters (integer): "))
        text = input("Enter a text to search for matching words: ")
        result = task2.solve(text, letters, distance)
        print("Matching words:")
        for word in result:
            print(word)

    elif task == '3':
        word_number = int(input("Enter the word number for case matching: "))
        text = input("Enter the text for adjective case replacement: ")
        result = task3.solve(text, word_number)
        print("Modified text:")
        print(result)

    else:
        print("Invalid task number. Please enter 1, 2, or 3.")
        main()

if __name__ == "__main__":
    main()
