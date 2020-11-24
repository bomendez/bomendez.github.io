'''
This class is responsible for drawing the board and pieces.
It will also listen for clicks.
'''
import turtle
from gamestate import GameState


class DrawGame:
    '''
    '''
    NUM_SQUARES = 8 # The number of squares on each row.
    SQUARE = 50 # The size of each square in the checkerboard.
    PADDING = 1 # Number of pixels desired for padding (used around game pieces)
    BOARD_SIZE = NUM_SQUARES * SQUARE # Total number of squares on board
    WINDOW_SIZE = BOARD_SIZE + SQUARE # The extra + SQUARE is the margin
    CORNER = -(BOARD_SIZE) / 2 # Gives position of the bottom left corner square
    SQUARE_COLORS = ("light gray", "white")
    PIECES_COLORS = ("black", "red")
    gs = GameState()
    def __init__(self):
        turtle.setup(self.WINDOW_SIZE, self.WINDOW_SIZE)
        turtle.screensize(self.BOARD_SIZE, self.BOARD_SIZE)
        turtle.bgcolor("white") # The window's background color
        turtle.tracer(0, 0) # makes the drawing appear immediately
        self.pen = turtle.Turtle() # This variable does the drawing.
        self.pen.penup() # This allows the pen to be moved.
        self.pen.hideturtle() # This gets rid of the triangle cursor.
        self.pen.color("black", "white") # The first parameter is the outline color, the second is the fill
        self.draw_game_state(self.pen)
        screen = turtle.Screen()
        screen.onclick(self.click_handler)
        turtle.done() # Stops the window from closing.


    def click_handler(self, x, y):
        '''
            Function -- click_handler
                Called when a click occurs.
            Parameters:
                x -- X coordinate of the click. Automatically provided by Turtle.
                y -- Y coordinate of the click. Automatically provided by Turtle.
            Returns:
                Nothing. Listens for clicks. Moves pieces and checks for
                allowed moves.
        '''
        if self.out_of_bounds(x,y):
            print("Out of bounds")
        else:
            row = self.get_square_pos(y)
            col = self.get_square_pos(x)
            if [row, col] in self.gs.next_move: # User clicks in a valid next move
                # Set pos of pen to first click and draw square
                x_coor_first = self.get_coor(self.gs.both_clicks[0][1])
                y_coor_first = self.get_coor(self.gs.both_clicks[0][0])
                self.pen.setposition(x_coor_first, y_coor_first)
                self.draw_square(self.pen, self.SQUARE, self.PIECES_COLORS[0], self.SQUARE_COLORS[0])
                # Update gs.board with "--"
                self.gs.board[self.gs.both_clicks[0][0]][self.gs.both_clicks[0][1]] = "--"
                # Draw piece at most recent click
                x_coor = self.get_coor(row)
                y_coor = self.get_coor(col)
                self.pen.setposition(x_coor, y_coor)
                self.pieces_by_position(self.pen, row, col, self.gs.current_player)
                # Update gs.board with new color
                self.gs.board[row][col] = self.gs.current_player
                # Call gs.current_player
                self.gs.set_player()
                print(self.gs.current_player)
                # Reset [-1,-1]
                self.gs.both_clicks = [[-1,-1], [-1,-1]]
                self.gs.next_move = []
            elif self.valid_click(self.gs.board[row][col]): # First click
                self.gs.both_clicks[0] = [row, col]
                self.gs.next_move = self.valid_move(row, col)
            print(self.gs.board)


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
        return self.gs.current_player == piece


    def valid_move(self, row, col):
        '''
        Function -- valid_move
            Checks the next valid move for a piece
        Parameters:
            row -- the row of the click. Expecting an integer.
            col -- the column of the click. Expecting an integer.
        Returns:
            moves -- a list of the possible next move or moves.
        '''
        moves = []
        print("current row is", row, "current col is", col)
        if self.gs.current_player == self.gs.BLACK:
            row += 1
        else:
            row -= 1
        try:
            if self.gs.board[row][col + 1] == "--":
                print("new move is ", row, col + 1)
                moves.append([row, col + 1])
        except IndexError:
            print("Cannot move to that square")
        try:
            if self.gs.board[row][col - 1] == "--":
                print("new move is", row, col - 1)
                moves.append([row, col - 1])
        except IndexError:
            print("Cannot move to that square")
        return moves


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
        HALF_SQUARES = self.NUM_SQUARES / 2
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
        for i in range(4):
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


    def inner_squares(self, pen): # Rename
        '''
            Function -- inner_squares
                Draws squares inside the board and sets each the appropriate color.
            Parameters:
                pen -- the variable turtle
            Returns:
                Nothing. Draws squares inside the board and colors each.
        '''
        pen.setposition(self.CORNER, self.CORNER)
        self.draw_square(pen, self.BOARD_SIZE, self.PIECES_COLORS[0], self.SQUARE_COLORS[1])
        for row in range(self.NUM_SQUARES):
            for col in range(self.NUM_SQUARES):
                pen.setposition(self.CORNER + self.SQUARE * col, self.CORNER + self.SQUARE * row)
                if col % 2 != row % 2:
                    self.draw_square(pen, self.SQUARE, self.PIECES_COLORS[0], self.SQUARE_COLORS[0])
                    piece = self.gs.board[row][col]
                    if piece != "--":
                        self.pieces_by_position(pen, row, col, piece)


    def pieces_by_position(self, pen, row, column, color):
        '''
            Function -- pieces_by_position
                Draws 1 piece on the board.
            Parameters:
                pen -- the variable turtle            
                row -- the row, expecting an integer from 0 to 7.
                column -- the column, expecting an integer from 0 to 7.
                color -- the color of the piece. Expecting a string.
            Returns:
                Nothing. Draws the appropriate game piece.
        '''
        RADIUS = (self.SQUARE / 2) - self.PADDING
        CENTER = (self.CORNER + (self.SQUARE / 2))
        pen.color("white", color)
        pen.setposition(CENTER + self.SQUARE * column, self.CORNER + self.SQUARE * row)
        self.draw_circle(pen, RADIUS)


    def draw_game_state(self, pen):
        '''
        Function -- draw_game_state
            Calls the function to draw the board and pieces
        Parameters:
            pen -- the variable turtle            
        Returns:
            Nothing. Calls a function.
        '''
        self.inner_squares(pen)
