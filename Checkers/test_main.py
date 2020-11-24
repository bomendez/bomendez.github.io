from main import forward_move


def test_forward_move():
    assert(forward_move(0, 1, "black") is True)
    assert(forward_move(1, 0, "black") is False)
    assert(forward_move(0, 1, "red") is False)
    assert(forward_move(1, 0, "red") is True)