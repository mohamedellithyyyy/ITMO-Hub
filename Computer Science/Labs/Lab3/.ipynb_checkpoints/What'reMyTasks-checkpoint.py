def main():
    while True:
        isuNumber = input("Enter your ISU Number:\n")
        try:
            isuNumber = int(isuNumber)
            break  # exit loop if conversion succeeded
        except ValueError:
            print("❌ Please enter a valid number.\n")

    codeTask1 = isuNumber % 7
    codeTask2 = isuNumber % 5
    codeTask3 = isuNumber % 3

    print(f"The code for the first task is *{codeTask1}*")
    print(f"The code for the second task is *{codeTask2}*")
    print(f"The code for the third task is *{codeTask3}*")


if __name__ == "__main__":
    main()
