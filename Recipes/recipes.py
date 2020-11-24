'''
Bo Mendez

This program writes and reads recipes.
'''


def split_ingredients(ingredients):
    '''
        Function -- validate_ingredients
            Splits the string into a list separated by commas and removes any
            whitespace before or after each list item.
        Parameters:
            ingredients -- a string with items separated by commas
        Returns:
            a list with items separated by commas. If ingredients is empty,
            returns ['']
    '''
    just_split = ingredients.split(",")
    split_and_stripped = []
    for item in just_split:
        split_and_stripped.append(item.strip())
    return split_and_stripped


def validate_time(time):
    '''
        Function -- validate_time
            Validates whether the input is an integer greater or equal to 0.
        Parameters:
            time -- any string
        Returns:
            time_int -- if the string is an integer greater than or equal to 0
            False -- if the string is not an integer greater than or equal to 0
    '''
    try:
        time_int = int(time)
        if time_int >= 0:
            return time_int
        return False
    except ValueError:
        return False


def convert_filename(name):
    '''
        Function -- convert_filename
            Converts a name to an acceptable filename ending with .txt.
        Parameter:
            name -- any string
        Returns:
            filename -- a lowercase alphanumeric string with underscores ending
                in .txt
            False -- if the filename is empty after formatting
    '''
    name = name.lower()
    name = name.strip()
    name = name.replace(" ", "_")
    name_formatted = ""
    for i in name:
        if i.isalpha() or i == "_":
            name_formatted += i
    if len(name_formatted) == 0:
        return False
    filename = name_formatted + ".txt"
    return filename


def write_file(file_path, lines):
    '''
        Function -- write_file
            Writes the file based on user input and the file path.
        Parameter:
            file_path -- the path of the file
            lines -- a list
        Returns:
            Nothing.
    '''
    with open(file_path, "w") as file:
        for line in lines:
            file.write(line)


def generate_lines(name, ingredients_lst, time, directions):
    '''
        Function -- generate_lines
            Creates a list based on user input. Expecting non-empty input.
        Parameters:
            name -- a filename; an lowercase alphanumeric string ending in txt
            ingredients_lst -- a list with items separated by commas
            time -- an integer greater than 0
            directions -- a string
        Returns:
            A list containing strings of the recipe.
    '''
    lines = []
    lines.append(name)
    lines.append("\n" * 2)
    lines.append("Ingredients:")
    lines.append("\n")
    for i in ingredients_lst:
        lines.append(i + "\n")
    lines.append("\n")
    lines.append("Time: " + str(time) + " minutes")
    lines.append("\n" * 2)
    lines.append("Directions:" + "\n")
    lines.append(directions)
    return lines


def read_file(file_path):
    '''
        Function -- read_file
            Reads a file one line at a time.
        Parameter:
            file_path -- the path of the file
        Returns:
            The contents of the file (a list of strings)
    '''
    with open(file_path, "r") as file:
        lines = file.read()
    return lines


def main():
    option = None
    while option != "3":
        while option != "1" and option != "2" and option != "3":
            if option is not None:
                print("Invalid choice.")
            option = input("MENU: 1 - Save a new recipe, 2 - Read a recipe, "
                           "3 - Quit ")
        if option == "3":
            break
        elif option == "1":
            ingredients = input("Enter the ingredients on one line. Separate "
                                "each ingredient with a comma. ")
            ingredients_lst = split_ingredients(ingredients)
            while ingredients_lst == ['']:
                print("Recipe must have at least one ingredient.")
                ingredients = input("Enter the ingredients on one line. "
                                    "Separate each ingredient with a comma. ")
                ingredients_lst = split_ingredients(ingredients)
            directions = input("Enter the directions (1 paragraph only): ")
            time = input("Enter the time needed in minutes: ")
            while not validate_time(time):
                print("Invalid time. Must be an integer greater than or equal"
                      " to 0.")
                time = input("Enter the time needed in minutes: ")
            any_name = input("Enter a name for the recipe: ")
            filename = any_name
            while not convert_filename(filename):
                print("Unable to create the filename.")
                filename = input("Enter a string containing only letters, "
                                 "numbers, and spaces ")
            filename = convert_filename(filename)
            all_lines = generate_lines(any_name, ingredients_lst, time,
                                       directions)
            write_file(filename, all_lines)
            print(any_name, "recipe saved to", filename)
        else:
            name = input("Enter the name of the recipe: ")
            while not convert_filename(name):
                print("Unable to create the filename.")
                name = input("Enter the name of the recipe: ")
            filename = convert_filename(name)
            try:
                print(read_file(filename))
            except FileNotFoundError:
                print("Unable to read", filename)
        option = None


if __name__ == "__main__":
    main()
