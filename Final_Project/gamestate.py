'''
This class is responsible for keeping track of the state of the game.
It will also determine valid moves, and keep a move log.
'''


class GameState:
    '''
    '''
    BLACK = "black"
    RED = "red"
    def __init__(self):
        # Each piece is the the format ZXY
        # Z = color, Red or Black
        # X = the row the piece is initially in
        # Y = the column the piece is initially in
        # "--" represents and empty square
        self.board = [
            ["--", "black", "--", "black", "--", "black", "--", "black"],
            ["black", "--", "black", "--", "black", "--", "black", "--"],
            ["--", "black", "--", "black", "--", "black", "--", "black"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["--", "--", "--", "--", "--", "--", "--", "--"],
            ["red", "--", "red", "--", "red", "--", "red", "--"],
            ["--", "red", "--", "red", "--", "red", "--", "red"],
            ["red", "--", "red", "--", "red", "--", "red", "--"]
        ]
        self.both_clicks = [[-1,-1], [-1,-1]]
        self.next_move = []
        self.current_player = self.BLACK

    def set_player(self):
        '''
        Changes the player
        '''
        if self.current_player == self.BLACK:
            self.current_player = self.RED
        else:
            self.current_player = self.BLACK
