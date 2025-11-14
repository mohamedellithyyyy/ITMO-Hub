# Author = Эллити Мохамед Эмад Ахмед Авад
# Group = P3131
# Date = 05.10.2025
from tasks import Informatics_Lab3_Task1, Informatics_Lab3_Task2, Informatics_Lab3_Task3

def main():
    task = input("Please enter a task number (1, 2, or 3) or 0 for Exit: ").strip()

    if task == '0':
        print("Exiting program. Goodbye!")
    elif task == '1':
        print("Enter a text to extract surnames: ")
        lines = []
        while True:
            line = input()
            if line == "":
                break
            lines.append(line)
        text = "\n".join(lines)
        result = Informatics_Lab3_Task1.solve(text)
        print("Surnames found (sorted alphabetically):")
        for surname in result:
            print(surname)
        main()

    elif task == '2':
        letters = input("Enter three letters (without spaces, e.g., KRA): ").strip()
        distance = int(input("Enter the fixed distance between letters (integer): "))
        text = input("Enter a text to search for matching words: ")
        result = Informatics_Lab3_Task2.solve(text, letters, distance)
        print("Matching words:")
        for word in result:
            print(word)
        main()

    elif task == '3':
        case_number = int(input("Enter the word number for case matching: "))
        text = input("Enter the text for adjective case replacement: ")
        lines = []
        while True:
            line = input()
            if line == "":
                break
            lines.append(line)
        text = "\n".join(lines)
        result = Informatics_Lab3_Task3.solve(text, case_number)
        print("Modified text:")
        print(result)
        main()
    
    else:
        print("Invalid task number. Please enter 1, 2, or 3.")
        main()

if __name__ == "__main__":
    main()

