from drawGame import DrawGame
from gamestate import GameState
from piece import Piece
from moves import Moves


def test_constructor():
    some_object = DrawGame(True)
    gs = GameState()
    gs.board = [
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
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"]
    ]
    assert(some_object.test_mode == True)


def test_is_second_click():
    some_object = DrawGame(True)
    assert(not some_object.is_second_click())
    some_object.gs.both_clicks = [[4, -1], [-1,-1]]
    assert(some_object.is_second_click())


def test_valid_click():
    some_object = DrawGame(True)
    some_piece = Piece("black", [[1, -1], [1, 1]], False, [0,0])
    some_move = Moves([0, 0], [1, 1], False, [None])
    some_object.gs.next_moves = [some_move]
    some_object.gs.board = [
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
    some_object.gs.current_player = "black"
    assert(some_object.valid_click(some_piece))
    some_piece = Piece("black", [[1, -1], [1, 1]], False, [1,1])
    assert(not some_object.valid_click(some_piece))


def test_is_first_click():
    some_object = DrawGame(True)
    some_piece = Piece("black", [[1, -1], [1, 1]], False, [0,0])
    some_object.gs.board = [
        [some_piece, "empty", "empty", "empty", "empty", "empty", "empty",\
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
    some_move = Moves([0, 0], [1, 1], False, [None])
    some_object.gs.next_moves = [some_move]
    some_object.gs.current_player = "black"
    assert(some_object.is_first_click(0, 0))
    some_object.gs.board = [
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
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"]
    ]
    assert(not some_object.is_first_click(0, 0))


def test_valid_move():
    some_object = DrawGame(True)
    some_move = Moves([-1, -1], [1, 1], False, [None])
    assert(some_object.valid_move(some_move, 1, 1))
    some_move = Moves([1, -1], [1, 1], False, [None])
    assert(not some_object.valid_move(some_move, 1, 1))
    some_object.gs.both_clicks[0] = [0, 0]
    assert(not some_object.valid_move(some_move, 1, 1))


def test_update_location_attribute():
    some_object = DrawGame(True)
    some_move = Moves([0, 0], [1, 0], False, [None])
    some_piece = Piece("black", [[1, -1], [1, 1]], False, [0,0])
    some_object.gs.board = [
        [some_piece, "empty", "empty", "empty", "empty", "empty", "empty",\
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
    some_object.update_location_attribute(some_move)
    updated_location = [1, 0]
    assert(some_piece.location == updated_location)



def test_set_king():
    some_object = DrawGame(True)
    some_move = Moves([6, 1], [7, 0], False, [None])
    some_piece = Piece("red", [[1, -1], [1, 1]], False, [0,0])
    some_object.gs.board = [
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
            "empty"],
        [some_piece, "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"]
    ]
    assert(not some_object.set_king(some_move))


def test_update_board():
    some_object = DrawGame(True)
    some_move = Moves([0, 0], [1, 1], True, [0,0])
    some_object.gs.board = [
        ["filled", "empty", "empty", "empty", "empty", "empty", "empty",\
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
    some_object.update_board(some_move)
    updated_board = [
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
            "empty"],
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"]
        ]
    assert(some_object.gs.board == updated_board)
    some_object.gs.both_clicks = [[0,0], [1,1]]
    some_move = Moves([0, 0], [1, 0], False, [None])
    some_object.gs.board = [
        ["moved piece", "empty", "empty", "empty", "empty", "empty", "empty",\
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
    some_object.update_board(some_move)
    updated_board = [
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "moved piece", "empty", "empty", "empty", "empty", "empty",\
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
    assert(some_object.gs.board == updated_board)
    second_move = Moves([0, 0], [1, 1], False, [None])
    some_object.gs.board = [
        ["moved piece", "empty", "empty", "empty", "empty", "empty", "empty",\
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
    some_object.update_board(second_move)
    updated_board = [
        ["empty", "empty", "empty", "empty", "empty", "empty", "empty",\
            "empty"],
        ["empty", "moved piece", "empty", "empty", "empty", "empty", "empty",\
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
    assert(some_object.gs.board == updated_board)



def test_is_capture():
    some_object = DrawGame(True)
    some_move = Moves([6, 1], [7, 0], False, [None])
    assert(not some_object.is_capture(some_move))
    some_move = Moves([6, 1], [7, 0], True, [None])
    assert(some_object.is_capture(some_move))


def test_out_of_bounds():
    some_object = DrawGame(True)
    assert(not some_object.out_of_bounds(100, 100))
    assert(some_object.out_of_bounds(400, 400))


def test_get_square_pos():
    some_object = DrawGame(True)
    assert(some_object.get_square_pos(-199) == 0)
    assert(some_object.get_square_pos(1) == 4)


def test_get_coor():
    some_object = DrawGame(True)
    assert(some_object.get_coor(0) == -200)
    assert(some_object.get_coor(4) == 0)


def test_get_capture_moves():
    some_object = DrawGame(True)
    some_piece = Piece("black", [[1, -1], [1, 1]], False, [0,0])
    some_object.get_capture_moves(some_piece)
    assert(some_object.gs.next_moves == [])


def test_check_game_over():
    some_object = DrawGame(True)
    some_object.gs.next_moves = []
    assert(some_object.check_game_over())
    some_object.gs.next_moves = [0]
    assert(not some_object.check_game_over())
