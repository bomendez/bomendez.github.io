'''
Bo Mendez

This class handles possible moves in the game.
'''


class Moves:
    '''
        Class -- Moves
            Responsible for maintaining the pieces.
        Attributes:
            start_location -- the location of a piece before a move
            end_location -- the location of a piece after a move
            capture_move -- whether a move is a capture move or not
            capture_location -- a list containing the location of a piece
            that can be captured
        Methods:
            
    '''


    def __init__(
        self, start_location, end_location, capture_move, capture_location):
        '''
            Constructor -- creates a new instance of Moves
            Parameters:
                start_location -- the location of a piece before a move, as a [row, col]
                end_location -- the location of a piece after a move, as a [row, col]
                capture_move -- whether a move is a capture move or not, a list of [row, col]
                capture_location -- the [row, col] of the capture location
        '''
        self.start_location = start_location
        self.end_location = end_location
        self.capture_move = capture_move
        self.capture_location = capture_location


    def __repr__(self):
        '''
            Method -- __repr__
                Creates a string representation of the Move
            Parameter:
                self -- The current Move object
            Returns:
                A string representation of the Move.
        '''
        return "start: {}, end: {}, capture: {}".format(
            self.start_location, self.end_location, self.capture_move)
    

    def get_start_end(self):
        '''
            Method -- get_start_end
                Will get the start and end location of a Move
            Parameter:
                self -- The current Move object
            Returns:
                A list containing the start and end location of the Move
        '''
        return [self.start_location, self.end_location]