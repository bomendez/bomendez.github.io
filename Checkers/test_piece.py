from piece import Piece
from gamestate import GameState
from moves import Moves


def test_constructor():
    some_object = Piece("black", [[1, -1], [1, 1]], False, [0,0])
    assert(some_object.color == "black")
    assert(some_object.directions == [[1, -1], [1, 1]])
    assert(some_object.king == False)
    assert(some_object.location == [0,0])


def test_valid_capture():
    some_object = Piece("black", [[1, -1], [1, 1]], False, [0,0])
    some_gamestate = GameState()
    assert(some_object.valid_capture(some_gamestate, 0, 0))
    assert(not some_object.valid_capture(some_gamestate, 0, 9))
    some_gamestate.board = [
        ["black", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"]
    ]
    assert(not some_object.valid_capture(some_gamestate, 0, 0))