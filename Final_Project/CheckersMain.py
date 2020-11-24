'''
Bo Mendez
CS 5001, Fall 2020

This program runs a game of checkers.
'''
import turtle
from GameState import GameState


NUM_SQUARES = 8 # The number of squares on each row.
SQUARE = 50 # The size of each square in the checkerboard.
PADDING = 1 # Number of pixels desired for padding (used around game pieces)
BOARD_SIZE = NUM_SQUARES * SQUARE # Total number of squares on board
CORNER = -(BOARD_SIZE) / 2 # Gives position of the bottom left corner square
SQUARE_COLORS = ("light gray", "white")
PIECES_COLORS = ("black", "red")
both_clicks = [[-1,-1], [-1,-1]] # Initialized as out-of-bounds


def click_handler(x, y):
    '''
        Function -- click_handler
            Called when a click occurs.
        Parameters:
            x -- X coordinate of the click. Automatically provided by Turtle.
            y -- Y coordinate of the click. Automatically provided by Turtle.
        Returns:
            Nothing. Prints the coordinates of the location of the user's click
    '''
    pen = turtle.Turtle() # This variable does the drawing.
    pen.penup() # This allows the pen to be moved.
    pen.hideturtle() # This gets rid of the triangle cursor.
    pen.color("black", SQUARE_COLORS[0])
    gs = GameState() # How to call Gamestate.board??
    if out_of_bounds(x,y):
        return None
    else:
        row_col = get_row_col(x,y)
        row = row_col[0]
        col = row_col[1]
        if both_clicks[0][0] < 0:
            # No clicks yet
            # Draw square
            color = gs.board[row][col]
            if gs.board[row][col] != "--":
                draw_new_square(pen, row, col)
                    # print(gs.board)
                both_clicks[0] = [row, col]
                gs.board[row][col] = "--"
                print(both_clicks)
        else:
            # First list of both_clicks is filled, second is not
            # Draw piece
            # if forward_move(both_clicks[0][1], col, color):
            both_clicks[1] = [row, col]
            print(both_clicks)
            pieces_by_position(pen, row, col, color)
            gs.board[row][col] = color
                # print(gs.board)
            both_clicks = [[-1,-1], [-1,-1]]


def forward_move(last_col, curr_col, color):
    if color == "black":
        if curr_col - last_col > 0:
            return True
    else:
        if curr_col - last_col < 0:
            return True
    return False


def out_of_bounds(x, y):
    '''
        Function -- out_of_bounds
            Called when a click occurs.
        Parameters:
            x -- X coordinate of the click.
            y -- Y coordinate of the click.
        Returns:
            Nothing. Prints "Click out of bounds" if click is outside board.
    '''
    if abs(x) > 200 or abs(y) > 200:
        print("Click out of bounds")


def draw_square(a_turtle, size):
    '''
        Function -- draw_square
            Draw a square of a given size.
        Parameters:
            a_turtle -- an instance of Turtle
            size -- the length of each side of the square
        Returns:
            Nothing. Draws a square in the graphics window.
    '''
    RIGHT_ANGLE = 90
    a_turtle.begin_fill()
    a_turtle.pendown()
    for i in range(4):
        a_turtle.forward(size)
        a_turtle.left(RIGHT_ANGLE)
    a_turtle.end_fill()
    a_turtle.penup()


def draw_circle(a_turtle, size):
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


def draw_pieces(pen, color, rows):
    '''
        Function -- draw_pieces
            Draws 2 consecutive rows of filled circles that represent the game
            pieces.
        Parameters:
            pen -- the variable turtle
            color -- the color of the piece. Expecting a string.
            rows -- the lowest row on the board
        Returns:
            Nothing. Draws the game pieces.
    '''
    RADIUS = (SQUARE / 2) - PADDING
    CENTER = (CORNER + (SQUARE / 2)) # The intitial position of piece in bottom left corner
    pen.color("white", color) # White outline with color fill
    for col in range(NUM_SQUARES):
        for row in range(rows, rows + 3):
            if col % 2 != row % 2:
                # Create object piece 0,1,2,... here
                pen.setposition(CENTER + SQUARE * col, CORNER + SQUARE * row)
                draw_circle(pen, RADIUS)


def pieces_by_position(pen, row, column, color):
    '''
        Function -- pieces_by_position
            Draws 1 piece on the board.
        Parameters:
            pen -- the variable turtle            
            corner -- the position of the bottom left corner of the board
            row -- the row, expecting an integer from 0 to 7.
            column -- the column, expecting an integer from 0 to 7.
            color -- the color of the piece. Expecting the string "black" or
            "red.
        Returns:
            Nothing. Draws the appropriate game piece.
    '''
    RADIUS = (SQUARE / 2) - PADDING
    CENTER = (CORNER + (SQUARE / 2))
    pen.color("white", color)
    pen.setposition(CENTER + SQUARE * column, CORNER + SQUARE * row)
    draw_circle(pen, RADIUS)


def board_outline(pen):
    '''
        Function -- board_outline
            Draw a square that serves as the outline of the board.
        Parameters:
            pen -- the variable turtle
        Returns:
            Nothing. Draws a square in the graphics window.
    '''
    pen.setposition(CORNER, CORNER)
    draw_square(pen, BOARD_SIZE)


def inner_squares(pen):
    '''
        Function -- inner_squares
            Draws squares inside the board and sets each the appropriate color.
        Parameters:
            pen -- the variable turtle
        Returns:
            Nothing. Draws squares inside the board and colors each.
    '''
    pen.color("black", SQUARE_COLORS[0])
    for col in range(NUM_SQUARES):
        for row in range(NUM_SQUARES):
            if col % 2 != row % 2:
                pen.setposition(CORNER + SQUARE * col, CORNER + SQUARE * row)
                draw_square(pen, SQUARE)


def draw_new_square(pen, row, col):
    '''
    Function -- draw_new_square
        This function draws a square on the row and column provided.
    Parameters:
        pen --
        row --
        col --
    Returns:
        Nothing. Draws a square.
    '''
    pen.color("black", SQUARE_COLORS[0])
    for r in range(NUM_SQUARES):
        for c in range(NUM_SQUARES):
            if r == row and c == col:
                pen.setposition(CORNER + SQUARE * r, CORNER + SQUARE * c)
                draw_square(pen, SQUARE)


def draw_piece(pen, board):
    for row in range(NUM_SQUARES):
        for col in range(NUM_SQUARES):
            piece = board[row][col]
            if piece != "--":
                pieces_by_position(pen, row, col, piece)


def get_row_col(x,y):
    HALF_SQUARES = NUM_SQUARES / 2
    row = (x // SQUARE) + HALF_SQUARES
    col = (y // SQUARE) + HALF_SQUARES
    return int(row), int(col)


def draw_game_state(pen, GameState):
    board_outline(pen)
    inner_squares(pen)
    draw_piece(pen, GameState.board)


def main():
    '''
    Function -- gameDriver
        Handle user input and updating the pieces.
    '''
    # Create the UI window
    window_size = BOARD_SIZE + SQUARE # The extra + SQUARE is the margin
    turtle.setup(window_size, window_size)

    # Set the drawing canvas size. The should be actual board size
    turtle.screensize(BOARD_SIZE, BOARD_SIZE)
    turtle.bgcolor("white") # The window's background color
    turtle.tracer(0, 0) # makes the drawing appear immediately

    pen = turtle.Turtle() # This variable does the drawing.
    pen.penup() # This allows the pen to be moved.
    pen.hideturtle() # This gets rid of the triangle cursor.
    pen.color("black", "white") # The first parameter is the outline color, the second is the fill
    
    # Draws board and pieces
    draw_game_state(pen, GameState())

    # Click handling
    screen = turtle.Screen()
    screen.onclick(click_handler)
    
    turtle.done() # Stops the window from closing.


if __name__ == "__main__":
    main()
