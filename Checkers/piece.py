'''
Bo Mendez
CS 5001, Fall 2020

This class represents a piece.
'''

from moves import Moves


class Piece:
    '''
        Class -- Piece
            Responsible for maintaining the pieces.
        Attributes:
            color -- the color of the piece: "black" or "red" or "empty"
            directions -- the possible directions a piece can move
            king -- whether a piece is a king or not
            location -- the [row, col] indicating the location of the piece
        Methods:
            
    '''
    

    def __init__(self, color, directions, king, location):
        '''
            Constructor -- creates a new instance of Piece
            Parameters:
                color -- the color of the piece: "black" or "red" or "empty"
                directions -- the possible directions a piece can move
                king -- whether a piece is a king or not
                location -- the [row, col] of the piece
        '''
        self.color = color
        self.directions = directions
        self.king = king
        self.location = location
        self.valid_moves = []


    def __str__(self):
        '''
            Method -- __str__
                Creates a string representation of the Piece
            Parameter:
                self -- The current Piece object
            Returns:
                A string representation of the Piece.
        '''
        return self.color


    def __eq__(self, other):
        '''
            Method -- __eq__
                Checks if two objects are equal
            Parameters:
                self -- The current Piece object
                other -- An object to compare self to.
            Returns:
                True if the two objects are the same color, False otherwise.
        '''
        if type(self) != type(other):
            return False
        return self.color == other.color


    def get_next_moves(self, gs):
        '''
            Method -- get_next_moves
                Returns the possible next moves for a piece
            Parameter:
                gs -- an instance of the Gamestate class
            Returns:
                Nothing. 
                Creates an instance of a Moves object.
                Creates a global variable next_moves with all possible moves
                for that player's pieces.
        '''
        try:
            if self.color == gs.current_player:
                for direction in self.directions:
                    move_row = self.location[0] + direction[0]
                    move_col = self.location[1] + direction[1]
                    if move_row >= 0 and move_row < 8 and move_col >= 0 and\
                            move_col < 8:
                        if gs.board[move_row][move_col] == "empty":
                            gs.next_moves.append(Moves(self.location,
                                [move_row, move_col], False, [None]))
                        # If next square is opposite color
                        elif gs.board[move_row][move_col].color != self.color:
                            capture_row = move_row + direction[0]
                            capture_col = move_col + direction[1]
                            if self.valid_capture(
                                    gs, capture_row, capture_col):
                                gs.capture_moves_avail.append(Moves(
                                    self.location, [capture_row, capture_col],
                                    True, [move_row, move_col]))
            return gs
        except IndexError:
            print("1 move invalid")


    def valid_capture(self, gs, capture_row, capture_col):
        return capture_row >= 0 and capture_row < 8 and capture_col >= 0\
            and capture_col < 8 and\
            gs.board[capture_row][capture_col] == "empty"