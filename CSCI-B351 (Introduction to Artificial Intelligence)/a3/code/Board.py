###############
# Board Class #
###############


class Board:
    """
        This class represents the actual Board of the game

        matrix - a double sub-scripted list containing the description of the current game State
                 with 0 indicating the blank
        blankPos - a tuple containing the (row, column) position of the blank (denoted as 0)
    """

    # The 8-puzzle board representation
    def __init__(self, matrix):
        self.matrix = matrix
        for i in range(len(matrix)):
            if 0 in matrix[i]:
                self.blankPos = (i, matrix[i].index(0))
                return
        raise ValueError("Invalid Matrix!")

    # A function to provide a string description of the board
    def __str__(self):
        s = ""
        for i in range(len(self.matrix)):
            s += str(self.matrix[i]) + "\n"
        return s + "\n"

    # A function to checks if two Boards are equal
    def __eq__(self, other):
        if type(other) is not Board:
            return False
        other_matrix = other.matrix
        this_matrix = self.matrix
        if len(this_matrix) != len(other_matrix):
            return False
        for i in range(len(this_matrix)):
            if len(this_matrix[i]) != len(other_matrix[i]):
                return False
            for j in range(len(this_matrix[i])):
                if this_matrix[i][j] != other_matrix[i][j]:
                    return False
        return True

    # A function to create a copy of the Board object itself
    def duplicate(self):
        new_matrix = []
        for i in range(len(self.matrix)):
            new_matrix.append([])
            for j in range(len(self.matrix[i])):
                new_matrix[i].append(self.matrix[i][j])
        return Board(new_matrix)

    # A function that returns a tuple containing the (row, col) position of the given element in the board
    def find_element(self, elem):
        for i in range(len(self.matrix)):
            for j in range(len(self.matrix[i])):
                if self.matrix[i][j] == elem:
                    return (i, j)
        return None

    # A function that puts the four sliding functions together, and takes direction as input
    # move is a tuple representing (delta Y, delta X)
    def slide_blank(self, move):
        if move not in [(0, 1), (0, -1), (-1, 0), (1, 0)]:
            raise ValueError("Invalid move")
        new_blank_position = (self.blankPos[0] + move[0], self.blankPos[1] + move[1])
        if new_blank_position[0] < 0 or new_blank_position[0] > len(self.matrix) - 1:
            return None
        elif new_blank_position[1] < 0 or new_blank_position[1] > len(self.matrix[0]) - 1:
            return None
        else:
            new_board = self.duplicate()
            save_value = new_board.matrix[self.blankPos[0] + move[0]][self.blankPos[1] + move[1]]
            new_board.matrix[self.blankPos[0] + move[0]][self.blankPos[1] + move[1]] = 0
            new_board.matrix[self.blankPos[0]][self.blankPos[1]] = save_value
            new_board.blankPos = (self.blankPos[0] + move[0], self.blankPos[1] + move[1])
            return new_board

    def __hash__(self):
        s = 0
        for i in range(len(self.matrix)):
            for j in range(len(self.matrix[i])):
                s *= 10
                s += self.matrix[i][j]
        return s
