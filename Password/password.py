'''
Bo Mendez

This function checks whether the user's string is a valid password.
'''


def lowercase(user_in):
    '''
        Function -- lowercase
            This function checks whether the string contains a lowercase letter
        Parameters:
            user_in -- a string, expecting length of string > 0
        Returns:
            True -- if the string contains a lowercase letter
            False -- if the string does not contain a lowercase letter
    '''
    for i in range(len(user_in)):
        if user_in[i] in "abcdefghijklmnopqrstuvwxyz":
            return True
    return False


def uppercase(user_in):
    '''
        Function -- uppercase
            This function checks whether the string contains an uppercase
            letter
        Parameters:
            user_in -- a string, expecting length of string > 0
        Returns:
            True -- if the string contains an uppercase letter
            False -- if the string does not contain an uppercase letter
    '''
    for i in range(len(user_in)):
        if user_in[i] in "ABCDEFGHIJKLMNOPQRSTUVWXYZ":
            return True
    return False


def digit(user_in):
    '''
        Function -- digit
            This function checks whether the string contains a digit
        Parameters:
            user_in -- a string, expecting length of string > 0
        Returns:
            True -- if the string contains a digit
            False -- if the string does not contain a digit
    '''
    for i in range(len(user_in)):
        if user_in[i] in "0123456789":
            return True
    return False


def special_char(user_in):
    '''
        Function -- special_char
            This function checks whether the string contains a special
            character
        Parameters:
            user_in -- a string, expecting length of string > 0
        Returns:
            True -- if the string contains a special character
            False -- if the string does not contain a special character
    '''
    for i in range(len(user_in)):
        if user_in[i] in "$#@!":
            return True
    return False


def validate_char(user_in):
    '''
        Function -- validate_char
            This function checks whether the string contains invalid characters
        Parameters:
            user_in -- a string, expecting length of string > 0
        Returns:
            True -- if the strings only contains valid characters
            False -- if the strings contains any invalid characters
    '''
    for i in range(len(user_in)):
        if not lowercase(user_in[i]) and not uppercase(user_in[i]) and\
                not digit(user_in[i]) and not special_char(user_in[i]):
            return False
    return True


def valid_length(user_in):
    '''
        Function - valid_length
            This function checks whether the string is 9-12 characters.
        Paramters:
            user_in -- a string
        Returns:
            True -- if the string is 9-12 characters
            False -- if the string is not 9-12 characters
    '''
    if len(user_in) >= 9 and len(user_in) <= 12:
        return True
    else:
        return False


def secure_password(user_in):
    '''
        Function -- secure_password
            This function checks whether a string is a valid password.
        Parameters:
            user_in -- any string
        Returns:
            True -- if the string is a valid password
            False -- if the string is an invalid password
    '''
    if valid_length(user_in) and validate_char(user_in):
        if (lowercase(user_in) + uppercase(user_in) + digit(user_in) +
                special_char(user_in)) >= 3:
            return True
    return False
