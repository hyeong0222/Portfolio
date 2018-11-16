#collaboration between Seungmin Baek (seubaek) and Sang Hyeong Woo (sangwoo)
import State
import Board
import heapq
import tests


###############################
# Uninformed Search Functions #
###############################

# Function to expand the fringe (queue), adding children of the first element to the back of the fringe
# This function should not return any value but just update the contents of the fringe
def fringe_expansion(current_state, fringe):
    """
        Add all the possible successive children of the current State to the end of the fringe list.
        Successors are States with Boards that are the result of performing a valid move.
        The successors are appended to the end of the fringe.
        This function should not return any value but just update the contents of fringe in memory
     """
    parent_board = current_state.board
    movements = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    successors = []
    # TODO: Add the moves of the current State to the end of the fringe list.

    for i in range (len(movements)):
        move = parent_board.slide_blank(movements[i])
        if move is not None:
            successors.append(move)
    for succ in successors:
        if succ != parent_board:
            fringe.append(State.State(succ, parent_board, 0, current_state.depth + 1))


# Function that implements the BFS algorithm
def breadth_first_search(fringe, limit, goal_board):
    # TODO: Implement this search
    if not fringe:
        return True
    else:
        current_state = fringe.pop(0)
        if current_state.board == goal_board:
            return current_state
        elif limit == 0:
            return True
        else:
            fringe_expansion(current_state, fringe)
            return False


# Function that implements the DFS algorithm
def depth_first_search(fringe, limit, goal_board, visited):
    if not fringe:
        return True
    else:
        current_state = fringe.pop()
        while current_state in visited:
            current_state = fringe.pop()
        # TODO: Implement the visited call to ignore visited States
        visited.append(current_state)
        if current_state.board == goal_board:
            return current_state
        elif limit == 0:
            return True
        else:
            fringe_expansion(current_state, fringe)
            return False


# Wrapper function - loop instead of recursion to save memory space
def uninformed_solver(board, limit, goal_board, mode):
    """
        Looping function which calls depth_first_search (mode = FALSE) or breadth_first_search (mode = TRUE) until it
        finds a solution (a State object) or until the limit has been reached.
        The limit means the maximum number of times checking if a node contains a goalBoard, NOT the
        maximum depth a solution can be.
        If the goal is reached, this function should return the goal State, None otherwise.
    """
    found = False
    fringe = [State.State(board, None, None, 0)]
    visited = []
    while not found:
        limit -= 1
        if mode is True:
            found = breadth_first_search(fringe, limit, goal_board)
        elif mode is False:
            found = depth_first_search(fringe, limit, goal_board, visited)
        else:
            return None
    if type(found) is State.State:
        print("Found Goal!")
        return found
    print("Limit Reached")
    return None


#####################################
# Heuristics and improved searching #
#####################################

# Function that expands the fringe using uniform cost
# This function should not return any value but just update the contents of the fringe (HINT: Use heap)
def ucs_expansion(current_state, fringe, explored):
    """
        Here you are to expand the fringe using the uniform cost search algorithm.
        Note that the fValue attribute of a node should be exactly equal to its depth since the
        cost of a single move is just 1.
        You should not return the fringe, but rather just update its contents in memory.
    """
    # TODO: Implement this expansion
    parent_board = current_state.board
    movements = [(-1, 0), (1, 0), (0, 1), (0, -1)]
    successors = []
    candidate = []

    for i in range (len(movements)):
        move = parent_board.slide_blank(movements[i])
        if move is not None:
            successors.append(move)
    for succ in successors:
        if succ != parent_board:
            candidate.append(State.State(succ, parent_board, current_state.depth + 1, current_state.depth + 1))
    for can in candidate:
        if can not in explored:
            heapq.heappush(fringe, can)


# Expand the fringe using A*
# This function should not return any value but just update the contents of the fringe (HINT: Use heap)
def a_star_expansion(current_state, fringe, goal_board, explored):
    """
        Here you are to expand the fringe using the A* search algorithm.
        Note that the fValue attribute of a node should be calculated as the actual cost from the
        initial node to the current node + the heuristic value of the current board.
        We have given you a heuristic, number of missed tiles, which you can use to make sure your search is working.
        Again, you should not return the fringe but rather just update its contents in memory.
    """
    # TODO: Implement this expansion
    parent_board = current_state.board
    movements = [(-1, 0), (1, 0), (0, 1), (0, -1)]
    successors = []
    candidate = []

    if current_state in explored:
        for ex in explored:
            if ex.board == current_state.board:
                if ex.depth <= current_state.depth:
                    return

    for i in range (len(movements)):
        move = parent_board.slide_blank(movements[i])
        if move is not None:
            successors.append(move)
    for succ in successors:
        if succ != parent_board:
            new = State.State(succ, parent_board, current_state.depth + 1 + heuristic (succ, goal_board), current_state.depth + 1)
            heapq.heappush(fringe, new)




# Function to find the manhattan distance between a given state and the goal state
def findManhattanDist(current_board, goal_board):
    sum = 0
    matrix = goal_board.matrix
    for i in range(0, 3):
        for j in range(0, 3):
            if matrix[i][j] == 0:
                continue
            value = Board.Board.find_element(current_board, matrix[i][j])
            sum += abs(i - value[0]) + abs(j - value[1])
    return sum


def my_own_heuristic(current_board, goal_board):
    # TODO: create your own heuristic
    # This heuristic should pass the same tests as Manhattan Distance does for A*, but finish them faster
    sum = 0
    matrix = current_board.matrix
    for i in range (0, 3):
        for j in range (0, 3):
            if matrix[i][j] == 0:
                continue
            else:
                value = Board.Board.find_element(current_board, matrix[i][j])
                if value != Board.Board.find_element(goal_board, matrix[i][j]):
                    sum += 1
        return sum


def heuristic(current_board, goal_board):
    return findManhattanDist(current_board, goal_board)


def informed_searches(fringe, limit, goal_board, explored, mode):
    """
       Looping function which calls a_star_expansion (mode = True) or ucs_expansion (mode = False) which
       expands the fringe of game States to choose from.
       The limit means the maximum number of times checking if a node contains a goalBoard, NOT the
       maximum depth a solution can be.
       Should return True when the limit is reached or the goal State if the goal is found
   """
    if not fringe:
        return True
    else:
        current_state = heapq.heappop(fringe)

        if current_state.board == goal_board:
            print("Found Goal!")
            return current_state
        elif limit <= 0:
            print("Limit Reached")
            return True
        else:
            if mode:
                a_star_expansion(current_state, fringe, goal_board, explored)
            else:
                ucs_expansion(current_state, fringe, explored)
            explored.add(current_state)
            return False


def informed_solver(current_board, limit, goal_board, mode):
    found = False
    fringe = [State.State(current_board, None, heuristic(current_board, goal_board), 0)]
    explored = set()
    while not found:
        limit -= 1
        found = informed_searches(fringe, limit, goal_board, explored, mode)
    if type(found) is State.State:
        return found
    return None


#####################################
# BONUS: Iterative-Deepening Search #
#####################################
def ids_solver(board, limit, goal_board):
    pass


def ids(fringe, limit, goal_board, horizon):
    pass


###########################
# Main method for testing #
###########################

def main():
    tests.tests()


if __name__ == "__main__":
    main()
