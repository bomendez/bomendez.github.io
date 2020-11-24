from password import (lowercase, uppercase, digit, special_char,
                      validate_char, valid_length, secure_password)


def test_lowercase():
    assert(lowercase("a") is True)
    assert(lowercase("A") is False)
    assert(lowercase("aA") is True)
    assert(lowercase("A1$") is False)
    assert(lowercase("A1$a") is True)


def test_uppercase():
    assert(uppercase("a") is False)
    assert(uppercase("A") is True)
    assert(uppercase("aA") is True)
    assert(uppercase("a1$") is False)
    assert(uppercase("a1$A") is True)


def test_digit():
    assert(digit("a") is False)
    assert(digit("0") is True)
    assert(digit("1234567890") is True)
    assert(digit("a$A") is False)
    assert(digit("a1$A") is True)


def test_special_char():
    assert(special_char("a") is False)
    assert(special_char("!") is True)
    assert(special_char("$!@#") is True)
    assert(special_char("a7A") is False)
    assert(special_char("a7A@") is True)


def test_validate_char():
    assert(validate_char("a") is True)
    assert(validate_char("B") is True)
    assert(validate_char("1") is True)
    assert(validate_char("#") is True)
    assert(validate_char("(") is False)
    assert(validate_char("1B#1") is True)
    assert(validate_char("1B#1%") is False)


def test_valid_length():
    assert(valid_length("0") is False)
    assert(valid_length("12345678") is False)
    assert(valid_length("123456789") is True)
    assert(valid_length("1234567899") is True)
    assert(valid_length("12345678999") is True)
    assert(valid_length("123456789999") is True)
    assert(valid_length("1234567899999") is False)


def test_secure_password():
    assert(secure_password("Aa!") is False)
    assert(secure_password("aaaaaaaaa") is False)
    assert(secure_password("aaaaaaaaaaaaa") is False)
    assert(secure_password("AAAAAAAAA") is False)
    assert(secure_password("111111111") is False)
    assert(secure_password("!!!!!!!!!") is False)
    assert(secure_password("*********") is False)
    assert(secure_password("Aa!!!!!!!") is True)
    assert(secure_password("Aa1111111") is True)
    assert(secure_password("0123456789ABC") is False)
    assert(secure_password("01234567ABC!") is True)
