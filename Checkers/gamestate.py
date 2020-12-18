'''
Bo Mendez
CS 5001, Fall 2020

This class is used by drawGame to maintain the state of the game.
'''


from piece import Piece


class GameState:
    '''
        Class -- GameState
            Responsible for maintaining the location of each item on the board.
            It will also keep a log of clicks, valid moves, and current turn.
        Attributes:
            board -- A 2D list containing the location of the pieces.
            next_moves -- An empty list to be filled with the next moves
            capture_moves_avail -- An empty list to be filled with capture moves
            both_clicks -- A 2D list keeping a log of two clicks
            current_player -- a string that keeps a log of the current player
            game_over -- A boolean maintaining whether the game is over
        Methods:
            set_player -- changes the player
            fill_board -- creates empty pieces to fill the board
            init_pieces -- creates instances of each Piece on the board.
    '''
    ROWS = 8
    COLUMNS = 8
    EMPTY = "empty"
    BLACK = "black"
    RED = "red"
    def __init__(self):
        self.board = []
        self.next_moves = []
        self.capture_moves_avail = []
        self.fill_board()
        self.both_clicks = [[-1,-1], [-1,-1]] # format is [row, col]
        self.current_player = self.BLACK
        self.game_over = False


    def set_player(self):
        '''
            Function -- set_player
                Changes the player.
            Parameters:
                Nothing.
            Returns:
                Nothing. 
                Will change the current player between "red" and "black"
        '''
        if self.current_player == self.BLACK:
            self.current_player = self.RED
        else:
            self.current_player = self.BLACK


    def fill_board(self):
        '''
            Function -- fill_board
                Creates empty pieces to fill the board
            Parameters:
                Nothing.
            Returns:
                Nothing.
        '''
        self.board = [[self.EMPTY] * self.COLUMNS for i in range(self.ROWS)]
        self.init_pieces()
    

    def init_pieces(self):
        '''
            Function -- init_pieces
                Creates instances of each Piece on the board.
            Parameters:
                Nothing.
            Returns:
                Nothing.
        '''
        BLACK_ROWS = 3
        RED_ROWS = 4
        for row in range(self.COLUMNS):
            for col in range(self.ROWS):
                if row % 2 != col % 2:
                    if row < BLACK_ROWS:
                        self.board[row][col] = Piece(
                            "black", [[1, -1], [1, 1]], False, [row, col])
                    elif row > RED_ROWS:
                        self.board[row][col] = Piece(
                            "red", [[-1, -1], [-1, 1]], False, [row, col])
