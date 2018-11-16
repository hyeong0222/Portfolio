###############
# State Class #
###############


class State:
    """
        This class represents the state of each board in the game

        board - the actual board that belongs to this state (See Board Class)
        parent - the State that the current State came from after applying a legal move
        fValue - the heuristic value of the board + the cost of the initial board to this board
        depth - the depth in the move tree from the original board that this board
                can be found in (the # of moves the puzzle has undergone)
    """

    # The representation of the current game state
    def __init__(self, board, parent, fValue, depth):
        self.board = board
        self.parent = parent
        self.fValue = fValue
        self.depth = depth

    # checks if two States are the same
    def __eq__(self, other):
        if type(other) is not State:
            return False
        return self.board == other.board

    # checks if the fValue of this board is less than the fValue of another board
    def __lt__(self, other):
        return self.fValue < other.fValue

    # converts this State into a string
    def __str__(self):
        return str(self.board) + "\nf: " + str(self.fValue) + "\nsteps: " + str(self.depth) + "\n"

    def __bool__(self):
        return True

    # Function to print a completed path from the initial state to the solution state #
    def printPath(self):
        print(self.board)
        if self.parent is not None:
            self.parent.printPath()

    def __hash__(self):
        return hash(self.board)
