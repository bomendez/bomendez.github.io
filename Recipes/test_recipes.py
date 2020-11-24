from recipes import split_ingredients, validate_time, convert_filename,\
    generate_lines
from pytest import raises


def test_split_ingredients():
    assert(split_ingredients("") == [''])
    assert(split_ingredients("A") == ['A'])
    assert(split_ingredients("A; B, C") == ['A; B', 'C'])
    assert(split_ingredients("1 egg, 2 c, salt") == ['1 egg', '2 c', 'salt'])


def test_validate_time():
    assert(validate_time("") is False)
    assert(validate_time("A") is False)
    assert(validate_time("A1C") is False)
    assert(validate_time("A!b5") is False)
    assert(validate_time("-10") is False)
    assert(validate_time("0") == 0)
    assert(validate_time("1001") == 1001)


def test_convert_filename():
    assert(convert_filename("") is False)
    assert(convert_filename("123!&") is False)
    assert(convert_filename("A") == "a.txt")
    assert(convert_filename("A1") == "a.txt")
    assert(convert_filename("1my rec9ipE   ") == "my_recipe.txt")
    assert(convert_filename("Egg and Soliders") == "egg_and_soliders.txt")


def test_generate_lines():
    assert(generate_lines("my", ["1 e", "1 c"], 10, "bake") ==
           ["my", "", "Ingredients:", "", "1 e", "1 c", "",
            "Time: 10 minutes", "", "Directions:", "bake"])
