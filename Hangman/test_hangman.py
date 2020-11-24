from hangman import (is_letter, in_word, already_guessed, insert_letters,
                     add_to_guessed, game_over, end_string)


def test_end_string():
    assert(end_string("APPLE") == "_____")
    assert(end_string("OBVIOUS") == "_______")
    assert(end_string("XYLOPHONE") == "_________")


def test_game_over():
    assert(game_over("APPLE", "APPLE") is True)
    assert(game_over("OBVIOUS", "OBVIOUS") is True)
    assert(game_over("XYLOPHONE", "XYLOPHONE") is True)
    assert(game_over("APPLE", "_____") is False)
    assert(game_over("APPLE", "APPL_") is False)


def test_add_to_guessed():
    assert(add_to_guessed("A", "") == "A")
    assert(add_to_guessed("A", "E") == "EA")
    assert(add_to_guessed("A", "PEIA") == "PEIAA")
    assert(add_to_guessed("APPLE", "") == "APPLE")


def test_insert_letters():
    assert(insert_letters("APPLE", "A") == "A____")
    assert(insert_letters("APPLE", "AP") == "APP__")
    assert(insert_letters("APPLE", "APPLE") == "APPLE")
    assert(insert_letters("APPLE", "AL") == "A__L_")


def test_already_guessed():
    assert(already_guessed("A", "A") is True)
    assert(already_guessed("A", "APP") is True)
    assert(already_guessed("AP", "APPLE") is True)
    assert(already_guessed("A", "PAL") is True)


def test_in_word():
    assert(in_word("APPLE", "APPLE") is True)
    assert(in_word("OBVIOUS", "OBVIOUS") is True)
    assert(in_word("APPL", "APPLE") is True)
    assert(in_word("I", "APPLE") is False)


def test_is_letter():
    assert(is_letter("A") is True)
    assert(is_letter("AB") is False)
