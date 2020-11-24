'''
Bo Mendez

This program plays three round of hangman with the user.
'''


SECRET_WORDS = ["APPLE", "OBVIOUS", "XYLOPHONE"]


def is_letter(user_in):
    '''
        Function -- is_letter
            This function returns true is the user enters one letter.
        Parameters:
            user_in -- a string that the user enters
        Returns:
            True -- if the string is one letter
            False -- if the string is not one letter
    '''
    if len(user_in) == 1:
        return True
    else:
        return False


def in_word(user_in, string):
    '''
        Function -- in_word
            This function returns true if the user guesses characters in the
            secret word
        Parameters:
            user_in -- a string that the user enters
            string -- the secret word for the round
        Returns:
            True -- if the user enters characters that are in the secret word
            False -- if the user enters characters that are not in the
            secret word.
    '''
    if user_in in string:
        return True
    else:
        return False


def already_guessed(user_in, ALL_GUESSES):
    '''
        Function -- already_guessed
            This function returns true if the user enters a character that they
            have already guessed.
        Parameters:
            user_in -- a string that the user enters
            ALL_GUESSES -- the string of all the user's guesses so far
        Returns:
            True -- if the user enters a character in ALL_GUESSES
            False -- if the user enters a character not in ALL_GUESSES
    '''
    if user_in in ALL_GUESSES:
        return True
    else:
        return False


def insert_letters(string, ALL_GUESSES):
    '''
        Function -- insert_letters
            This function takes the length of the secret word, prints the same
            number of underscores, checks whether the characters are in the
            string, and replaces the appropriate underscore with the characters
            that belongs in that word
        Parameters:
            string -- the secret word for the round
            ALL_GUESSES -- the string of all the user's guesses so far
        Returns:
            A string with the underscores replaced with the letters guessed
    '''
    END_STRING = "_" * len(string)
    loop_count = 0
    while loop_count < len(string):
        for i in range(len(string)):
            if string[i] in ALL_GUESSES:
                END_STRING = END_STRING[:i] + string[i] + END_STRING[i + 1:]
                loop_count += 1
            loop_count += 1
    return END_STRING


def add_to_guessed(user_in, ALL_GUESSES):
    '''
        Function -- add_to_guessed
            This function adds the user's new guess to all previous guesses
        Parameters:
            user_in -- a string that the user enters
            ALL_GUESSES -- the string of all the user's guesses so far
        Returns:
            A string that has been updated with all the user's guesses so far
    '''
    ALL_GUESSES += user_in
    return ALL_GUESSES


def game_over(string, END_STRING):
    '''
        Function -- game_over
            This function checks whether the user has guessed the secret word
        Parameters:
            string -- the secret word for the round
            END_STRING -- the string of guesses so far
        Returns:
            True -- if the user has guessed the secret word
            False -- if the user has not guessed the secret word
    '''
    if string == END_STRING:
        return True
    else:
        return False


def end_string(secret_word):
    END_STRING = "_" * len(secret_word)
    return END_STRING


def main():
    ALL_GUESSES = ""
    COUNT = 0
    WINS = 0
    CURRENT_ROUND = 1
    WORD_ROUND = 0
    while CURRENT_ROUND <= 3:
        secret_word = SECRET_WORDS[WORD_ROUND]
        END_STRING = end_string(secret_word)
        print(end_string(secret_word))
        while True:
            user_in = input("Enter a letter or word: ").upper()
            if COUNT >= 5:
                print("You lose! The word was " + secret_word)
                break
            if len(user_in) == 1 and already_guessed(user_in, ALL_GUESSES):
                print("You've already guessed that letter!")
                print(insert_letters(secret_word, ALL_GUESSES))
                print("Your guesses so far:", ALL_GUESSES)
            else:
                if in_word(user_in, secret_word):
                    if is_letter(user_in):
                        COUNT += 1
                        ALL_GUESSES = add_to_guessed(user_in, ALL_GUESSES)
                        print(insert_letters(secret_word, ALL_GUESSES))
                        END_STRING = insert_letters(secret_word, ALL_GUESSES)
                        print("Your guesses so far:", ALL_GUESSES)
                    else:
                        if game_over(secret_word, user_in):
                            print("You win!")
                            WINS += 1
                            break
                        print(insert_letters(secret_word, ALL_GUESSES))
                        END_STRING = insert_letters(secret_word, ALL_GUESSES)
                        print("Your guesses so far:", ALL_GUESSES)
                else:
                    if is_letter(user_in):
                        COUNT += 1
                        string_guess = add_to_guessed(user_in, ALL_GUESSES)
                        print(insert_letters(secret_word, string_guess))
                        ALL_GUESSES = add_to_guessed(user_in, ALL_GUESSES)
                        END_STRING = insert_letters(secret_word, ALL_GUESSES)
                        print("Your guesses so far:", ALL_GUESSES)
                    else:
                        print(insert_letters(secret_word, ALL_GUESSES))
                        print("Your guesses so far:", ALL_GUESSES)
            if game_over(secret_word, END_STRING):
                print("You win!")
                WINS += 1
                break
        CURRENT_ROUND += 1
        WORD_ROUND += 1
        COUNT = 0
        ALL_GUESSES = ""
        END_STRING = ""
    print("You won {} out of 3".format(WINS))


if __name__ == "__main__":
    main()
