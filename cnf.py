import random
clauses = []


def generate_cnf(k, x, y):
    global clauses
    for i in range(0, x + 1):
        if i != 0:
            numbers = []
            for j in range(0, k):
                rand = random.randint(*random.choice([(-y, -1), (1, y)]))
                while rand in numbers:
                    rand = random.randint(*random.choice([(-y, -1), (1, y)]))
                numbers.append(rand)
            literals = ""
            for z in range(0, len(numbers)):
                literals = literals + str(numbers[z]) + " "
            _clause = "\n"
            _clause = _clause + literals + "0"
            clauses.append(_clause)
        else:
            clauses.append("p cnf " + str(x) + " " + str(y))
    return


def make_file(file_name):
    file = open(file_name + ".txt.", "w")
    for i in range(0, len(clauses)):
        file.write(clauses[i])
    file.close()


def run():
    print("\nWelcome to my random CNF generator. Follow the instructions as you go further.\nPlease press Enter to continue:")
    input()
    print("Enter the file name you want for your CNF txt file:", end='')
    file_name = str(input())
    print("Enter the number of k-literals you want:", end='')
    k_literals = int(input())
    print("Enter the number of clauses you want:", end='')
    x_clauses = int(input())
    print("Enter the number of variables you want:", end='')
    y_variables = int(input())
    while y_variables < k_literals:
        print("\nLiterals(k) cannot be greater than variables(y).")
        print("Please provide new values for k and y.\n")
        print("Enter the number of k-literals you want")
        k_literals = int(input())
        print("Enter the number of variables you want")
        y_variables = int(input())

    generate_cnf(k_literals, x_clauses, y_variables)
    make_file(file_name)

    print("\nYou're all set! The file is ready.")
    return


run()
