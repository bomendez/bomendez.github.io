from moves import Moves


def test_constructor():
    some_move = Moves([0, 0], [1, 1], False, [None])
    assert(some_move.start_location == [0,0])
    assert(some_move.end_location == [1,1])
    assert(some_move.capture_move == False)
    assert(some_move.capture_location == [None])


def test_get_start_end():
    some_move = Moves([0, 0], [1, 1], False, [None])
    assert(some_move.get_start_end == [0, 0], [1, 1])
    assert(not some_move.get_start_end == [0, 0], [0, 0])