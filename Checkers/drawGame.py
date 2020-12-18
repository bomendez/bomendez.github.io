'''
Bo Mendez
CS 5001, Fall 2020

This class is the driver of the game.
'''


import turtle
from gamestate import GameState
from piece import Piece


class DrawGame:
    '''
        Class -- DrawGame
            Responsible for drawing the board and pieces. 
            It will also listen for clicks.
        Attributes:
            pen -- an instance of Turtle to draw
            screen -- the instance of the turtle screen
            win -- an instance of Turtle to represent a win
            lose -- an instance of Turtle to represent a loss
        Methods:
            
    '''
    NUM_SQUARES = 8 # The number of squares on each row
    SQUARE = 50 # The pixel size of each square in the checkerboard
    PADDING = 1 # Number of pixels desired for padding (around game pieces)
    BOARD_SIZE = NUM_SQUARES * SQUARE # 400
    WINDOW_SIZE = BOARD_SIZE + SQUARE # 401
    CORNER = -(BOARD_SIZE) / 2 # -200
    SQUARE_COLORS = ("light gray", "white")
    PIECES_COLORS = ("black", "red")


    def __init__(self, test_mode):
        self.test_mode = test_mode
        self.gs = GameState()
        self.get_player_moves()
        if not self.test_mode:
            turtle.setup(self.WINDOW_SIZE, self.WINDOW_SIZE)
            turtle.screensize(self.BOARD_SIZE, self.BOARD_SIZE)
            turtle.bgcolor("white")
            turtle.tracer(0, 0)
            self.pen = turtle.Turtle()
            self.win = turtle.Turtle()
            self.lose = turtle.Turtle()
            self.pen.penup()
            self.pen.hideturtle()
            self.pen.color("black", "white")
            self.draw_board()            
            self.screen = turtle.Screen()
            self.screen.onclick(self.click_handler)
            turtle.done()


    def click_handler(self, x, y):
        '''
            Function -- click_handler
                Called when a click occurs.
            Parameters:
                x -- X coordinate of the click. Automatically provided by Turtle.
                y -- Y coordinate of the click. Automatically provided by Turtle.
            Returns:
                Nothing. Listens for clicks to activate moves.
        '''
        if self.out_of_bounds(x,y):
            print("Out of bounds")
        else:
            row = self.get_square_pos(y)
            col = self.get_square_pos(x)
            if self.is_first_click(row, col):
                self.gs.both_clicks[0] = [row, col]
                print("first click, available moves: ", self.gs.next_moves)
            elif self.is_second_click():
                print("Entered second click. Player =", self.gs.current_player)
                # Iterate through available moves
                for move in self.gs.next_moves:
                    if self.valid_move(move, row, col):
                        self.gs.both_clicks[1] = [row, col]
                        self.second_click(move)
                        break
                if self.is_capture(move): # last move was a capture
                    my_row = move.end_location[0]
                    my_col = move.end_location[1]
                    piece = self.gs.board[my_row][my_col]
                    self.get_capture_moves(piece)
                    print("second capture! Piece", piece, "at", my_row, my_col)
                    if self.gs.next_moves:
                        self.gs.both_clicks[0] = [row, col]
                        self.gs.both_clicks[1] = [-1, -1]
                    else:
                        self.switch_turns()
                else:
                    self.switch_turns()


    def ai_move(self):
        '''
            Method -- ai_move
                Moves the AI
            Parameter:
                None.
            Returns:
                Nothing. Moves the AI
        '''
        move = self.gs.next_moves[0]
        self.update_location_attribute(move)
        self.update_board(move)
        self.set_king(move)
        self.draw_board()
        if self.check_game_over():
            self.print_end_game()
        # setup for "if jump move avail"
        turn_continues = False
        if self.is_capture(move): # last move was a capture
            my_row = move.end_location[0]
            my_col = move.end_location[1]
            piece = self.gs.board[my_row][my_col]
            self.get_capture_moves(piece)
            print("second capture! Piece", piece, "at", my_row, my_col)
            if self.gs.next_moves:
                turn_continues = True
        if turn_continues:
            self.ai_move()
        else:
            self.gs.both_clicks = [[-1,-1], [-1,-1]]
            self.gs.next_moves = []
            self.gs.capture_moves_avail = []
            self.gs.set_player()
            self.get_player_moves()


    def is_second_click(self):
        '''
            Method -- is_second_click
                Returns whether the second click has happened
            Parameter:
                None.
            Returns:
                True -- if the first click has already occurred
                False -- if the first click has not yet occurred
        '''
        return self.gs.both_clicks[0] != [-1,-1]


    def is_first_click(self, row, col):
        '''
            Method -- is_first_click
                Returns whether a valid first click has happened
            Parameter:
                None.
            Returns:
                True -- if the first click is valid
                False -- if the first click is not valid
        '''
        return self.gs.board[row][col] != "empty" and\
            self.valid_click(self.gs.board[row][col])


    def second_click(self, move):
        '''
            Method -- second_click
                Executes the second click functions
            Parameter:
                move -- the current Move object     
            Returns:
                Nothing.
        '''
        self.update_location_attribute(move)
        self.update_board(move)
        self.set_king(move)
        self.draw_board()


    def valid_move(self, move, row, col):
        '''
            Method -- valid_move
                Returns whether a move is valid
            Parameter:
                move -- the current Move object
            Returns:
                True -- if the first click is the same as the end location
                of the move
                False -- if the first click is not equal to the end location
                of the move
        '''
        return self.gs.both_clicks[0] == move.start_location and\
            [row, col] == move.end_location


    def switch_turns(self):
        '''
            Method -- switch_turns
                Changes the player and resets global variables. Check for
                game over and ends the game if appropriate.
            Parameter:
                None.
            Returns:
                Nothing.
        '''
        self.gs.set_player()
        self.gs.both_clicks = [[-1,-1], [-1,-1]]
        self.gs.next_moves = []
        self.gs.capture_moves_avail = []
        self.get_player_moves()
        if not self.check_game_over():
            self.screen.ontimer(self.ai_move(), 500)
        else:
            self.print_end_game()


    def update_location_attribute(self, move):
        '''
        Function -- update_location_attr_capture
            Update the location attribute of moving piece
        Parameters:
            move -- an instance of the class Move
        Returns:
            Nothing.
        '''
        old_piece_row = move.start_location[0]
        old_piece_col = move.start_location[1]
        self.gs.board[old_piece_row][old_piece_col].location = [
            move.end_location[0], move.end_location[1]
            ]


    def set_king(self, move):
        '''
            Method -- set_king
                Checks whether a move has earned a king status and sets the
                attributes of the piece to king.
            Parameter:
                move -- the current Move object
            Returns:
                True -- if the king is a piece
                False -- if the king is not a piece
        '''
        piece = self.gs.board[move.end_location[0]][move.end_location[1]]
        # black piece
        if piece.color == self.PIECES_COLORS[0] and move.end_location[0] == 7:
            piece.king = True
            piece.directions = [[1, -1], [1, 1], [-1, -1], [-1, 1]]
         # red piece
        elif piece.color == self.PIECES_COLORS[1] and move.end_location[0]\
                == 0:
            piece.king = True
            piece.directions = [[1, -1], [1, 1], [-1, -1], [-1, 1]]
        return False


    def update_board(self, move):
        '''
        Function -- update_board
            Updates GameState attribute board with the empty piece and instance
            of class Piece is updated with new location
        Parameters:
            move -- an instance of the class Move
        Returns:
            Nothing.
        '''
        if move.capture_move == True:
            self.gs.board[move.capture_location[0]][move.capture_location[1]]\
                = "empty"
        if self.gs.current_player == self.PIECES_COLORS[0]:
            old_piece_row = self.gs.both_clicks[0][0]
            old_piece_col = self.gs.both_clicks[0][1]
            new_piece_row = self.gs.both_clicks[1][0]
            new_piece_col = self.gs.both_clicks[1][1]
        else:
            old_piece_row = move.start_location[0]
            old_piece_col = move.start_location[1]
            new_piece_row = move.end_location[0]
            new_piece_col = move.end_location[1]
        self.gs.board[new_piece_row][new_piece_col] = self.gs.board[
            old_piece_row][old_piece_col]
        self.gs.board[old_piece_row][old_piece_col] = "empty"


    def is_capture(self, move):
        '''
        Function -- is_capture
            Predicate that determines whether a move is a capture
        Parameters:
            move -- an instance of the class Move
        Returns:
            True -- if the move is a capture move
            False -- if a non-capture move
        '''
        return move.capture_move == True


    def valid_click(self, piece):
        '''
        Function -- valid_click
            Determines if a player clicked on their own piece
        Parameters:
            piece -- the color of the piece that was clicked
        Returns:
            True -- if the piece clicked matches the color of the
            current player
            False -- if the piece clicked does not match the color of
            the current player
        '''
        if piece != "empty" and self.gs.current_player == piece.color:
            for move in self.gs.next_moves: # Iterate through available moves
                if piece.location == move.start_location: # Valid move
                    return True
        return False


    def out_of_bounds(self, x, y):
        '''
            Function -- out_of_bounds
                Called when a click occurs.
            Parameters:
                x -- X coordinate of the click.
                y -- Y coordinate of the click.
            Returns:
                Nothing. Prints "Click out of bounds" if click is outside board.
        '''
        return abs(x) > 200 or abs(y) > 200
            

    def get_square_pos(self, coordinate):
        '''
        Function -- get_square_pos
            Converts from (x,y) position to (row, col)
        Parameters:
            coordinate -- the x or y coordinate. Expecting a float
        Returns:
            pos -- an integer value that represents the click as a row/col
        '''
        HALF_SQUARES = self.NUM_SQUARES / 2
        pos = (coordinate // self.SQUARE) + HALF_SQUARES
        return int(pos)


    def get_coor(self, pos):
        '''
        Function -- get_square_pos
            Converts from (row, col) to (x,y) coordinate
        Parameters:
            pos -- the row or column of the click. Expecting an integer.
        Returns:
            coor -- a coordinate (float) that represents the click
        '''
        HALF_SQUARES = self.NUM_SQUARES / 2 # 4
        coor = (pos - HALF_SQUARES) * self.SQUARE
        return int(coor)


    def draw_square(self, a_turtle, size, outline, fill):
        '''
            Function -- draw_square
                Draw a square of a given size.
            Parameters:
                a_turtle -- an instance of Turtle
                size -- the length of each side of the square. Expecting an int
                outline -- the color of the outline of the square as a string
                fill -- the color of the fill of the square. Expectng a string
            Returns:
                Nothing. Draws a square in the graphics window.
        '''
        RIGHT_ANGLE = 90
        a_turtle.color(outline, fill)
        a_turtle.begin_fill()
        a_turtle.pendown()
        for _ in range(4):
            a_turtle.forward(size)
            a_turtle.left(RIGHT_ANGLE)
        a_turtle.end_fill()
        a_turtle.penup()


    def draw_circle(self, a_turtle, size):
        '''
            Function -- draw_circle
                Draw a circle of a given size.
            Parameters:
                a_turtle -- an instance of Turtle
                size -- the length of each side of the square
            Returns:
                Nothing. Draws a circle in the graphics window.
        '''
        a_turtle.begin_fill()
        a_turtle.pendown()
        a_turtle.circle(size)
        a_turtle.end_fill()
        a_turtle.penup()
                

    def draw_board(self):
        '''
            Function -- init_board
                Draws squares inside the board and sets each the appropriate
                color.
            Parameters:
                None
            Returns:
                Nothing. Draws squares inside the board and colors each.
        '''
        self.pen.setposition(self.CORNER, self.CORNER)
        self.draw_square(
            self.pen, self.BOARD_SIZE, self.PIECES_COLORS[0],
            self.SQUARE_COLORS[1])
        for row in range(self.NUM_SQUARES):
            for col in range(self.NUM_SQUARES):
                self.pen.setposition(
                    self.CORNER + self.SQUARE * col, self.CORNER +
                    self.SQUARE * row)
                if col % 2 != row % 2:
                    self.draw_square(
                        self.pen, self.SQUARE, self.PIECES_COLORS[0],
                        self.SQUARE_COLORS[0])
                    if self.gs.board[row][col] != "empty":
                        # color = self.gs.board[row][col].color
                        piece = self.gs.board[row][col]
                        self.pieces_by_position(piece)


    def pieces_by_position(self, piece):
        '''
            Function -- pieces_by_position
                Draws 1 piece on the board.
            Parameters:         
                piece -- the current Piece object
            Returns:
                Nothing. Draws the appropriate game piece.
        '''
        RADIUS = (self.SQUARE / 2) - self.PADDING
        CENTER = self.CORNER + (self.SQUARE / 2)
        KING_CENTER = self.CORNER + self.SQUARE / 4
        HALF_RADIUS = RADIUS / 2
        row = piece.location[0]
        col = piece.location[1]
        self.pen.color("white", piece.color)
        self.pen.setposition(CENTER + self.SQUARE * col, self.CORNER + self.SQUARE * row)
        self.draw_circle(self.pen, RADIUS)
        if piece.king:
            self.pen.setposition(CENTER + self.SQUARE * col, KING_CENTER + self.SQUARE * row)
            self.pen.color("white", piece.color)
            self.draw_circle(self.pen, HALF_RADIUS)        


    def get_player_moves(self):
        '''
            Method -- get_player_moves
                Creates a list of all valid next moves for a player. Populates
                capture moves exclusively if available.
            Parameter:
                None
            Returns:
                Nothing.
        '''
        for row in self.gs.board:
            for piece in row:
                if piece != "empty" and piece.color == self.gs.current_player:
                    self.gs = piece.get_next_moves(self.gs)
        if self.gs.capture_moves_avail:
            self.gs.next_moves = self.gs.capture_moves_avail


    def get_capture_moves(self, piece):
        '''
            Method -- get_capture_moves
                Populates a list of capture moves for current piece.
            Parameter:
                piece -- the current Piece object
            Returns:
                Nothing.
        '''
        self.gs.next_moves = []
        self.gs.capture_moves_avail =[]
        piece.get_next_moves(self.gs)
        self.gs.next_moves = self.gs.capture_moves_avail


    def check_game_over(self):
        '''
            Method -- check_game_over
                Returns whether the game is over
            Parameter:
                None
            Returns:
                True -- if game is over
                False -- is game is not yet over
        '''
        count = 0
        for color in range(0):
            if self.gs.current_player == self.PIECES_COLORS[color]:
                for row in self.gs.board:
                    for piece in row:
                        if piece != "empty" and\
                                piece.color == self.PIECES_COLORS[color]:
                            count += 1
        if len(self.gs.next_moves) == 0 or count > 0:
            return True
        return False


    def print_end_game(self):
        '''
            Method -- print_end_game
                Prints the final screen when the game ends
            Parameter:
                None
            Returns:
                Nothing.
        '''
        if self.gs.current_player == self.PIECES_COLORS[0]:
            text = "Game Over! \n You lose!"
        else:
            text = "Game Over! \n You win!"
        style = ('Courier', 40, 'bold')
        self.pen.color("blue")
        self.pen.setposition(0, -50)
        self.pen.write(text, align="center", font=style)
