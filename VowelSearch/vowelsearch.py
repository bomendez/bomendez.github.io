'''
Bo Mendez

This program evaluates whether every word in a list contains a vowel.
'''


def check_vowel(item):
    '''
        Function -- check_vowel
            Checks whether the string contains a vowel
        Parameter:
            item -- expecting a string
        Returns:
            True -- if the string contains a vowel
            False -- if the string does not contain a vowel
    '''
    VOWELS = {"a", "e", "i", "o", "u", "A", "E", "I", "O", "U"}
    if len(item) == 0:
        return False
    else:
        if item[0] in VOWELS:
            return True
        else:
            return check_vowel(item[1:])


def contains_vowel(lst):
    '''
        Function -- contains_vowel
            Checks whether a list contains items with vowels
        Parameter:
            item -- expecting a list
        Returns:
            True -- if every item in the string contains a vowel
            False -- if every item in the string does not contain a vowel
    '''
    if len(lst) == 0:
        return False
    elif len(lst) == 1:
        if check_vowel(lst[0]):
            return True
        return False
    else:
        return check_vowel(lst[0]) and contains_vowel(lst[1:])
