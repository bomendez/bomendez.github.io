from vowelsearch import contains_vowel, check_vowel


def test_contains_vowel():
    assert(contains_vowel(["dog", "cat"]) is True)
    assert(contains_vowel(["dog", "l"]) is False)
    assert(contains_vowel(['A1']) is True)
    assert(contains_vowel(["A1", "dog"]) is True)
    assert(contains_vowel(["dog", "l", "cat"]) is False)


def test_check_vowel():
    assert(check_vowel("dog") is True)
    assert(check_vowel("A") is True)
    assert(check_vowel("l") is False)
    assert(check_vowel("A1") is True)
