import Board
import State
import updated_a3 as a3
import time


def tests():
    start_time1 = time.time()

    goal_board = Board.Board([[1, 2, 3],
                              [4, 5, 6],
                              [7, 8, 0]])

    easy_board = Board.Board([[1, 2, 0],
                              [4, 5, 3],
                              [7, 8, 6]])

    medium_board = Board.Board([[4, 1, 3],
                                [7, 2, 5],
                                [0, 8, 6]])

    hard_board = Board.Board([[2, 5, 0],
                              [1, 7, 3],
                              [4, 8, 6]])

    # test case for fringe_expansion
    fringe1 = []
    node1 = State.State(easy_board, None, 0, 0)
    a3.fringe_expansion(node1, fringe1)
    assert State.State(easy_board.slide_blank((-1, 0)), node1, 0, 1) not in fringe1
    assert State.State(easy_board.slide_blank((0, -1)), node1, 0, 1) in fringe1
    assert State.State(easy_board.slide_blank((1, 0)), node1, 0, 1) in fringe1
    assert State.State(easy_board.slide_blank((0, 1)), node1, 0, 1) not in fringe1

    # BFS Test
    print("BFS TESTS\n-------------")
    # Expected: Goal Found!
    bfs_fringe = []
    bfs_node_1 = State.State(medium_board, None, 0, 0)
    a3.fringe_expansion(bfs_node_1, bfs_fringe)
    bfs_result_1 = a3.uninformed_solver(medium_board, 5000, goal_board, True)
    assert bfs_result_1 is not None
    assert type(bfs_result_1) is State.State
    # Expected: Limit Reached
    bfs_fringe = []
    bfs_node_2 = State.State(hard_board, None, 0, 0)
    a3.fringe_expansion(bfs_node_2, bfs_fringe)
    bfs_result_2 = a3.uninformed_solver(hard_board, 5000, goal_board, True)
    assert bfs_result_2 is None
    assert type(bfs_result_2) is not State.State
    print("\n")

    # DFS Test
    print("DFS TESTS\n-------------")
    # Expected: Limit Reached
    dfs_fringe = []
    dfs_node_1 = State.State(medium_board, None, 0, 0)
    a3.fringe_expansion(dfs_node_1, dfs_fringe)
    dfs_result_1 = a3.uninformed_solver(medium_board, 1000, goal_board, False)
    assert dfs_result_1 is None
    assert type(dfs_result_1) is not State.State
    # Expected: Limit Reached
    dfs_fringe = []
    dfs_node_2 = State.State(hard_board, None, 0, 0)
    a3.fringe_expansion(dfs_node_2, dfs_fringe)
    dfs_result_2 = a3.uninformed_solver(hard_board, 1000, goal_board, False)
    assert dfs_result_2 is None
    assert type(dfs_result_2) is not State.State
    print("\n")

    # UCS Test
    print("UCS TESTS\n-------------")
    # Expected: Goal Found!
    ucs_result_1 = a3.informed_solver(medium_board, 5000, goal_board, False)
    assert ucs_result_1 is not None
    assert type(ucs_result_1) is State.State
    # Expected: Goal Found!
    ucs_result_2 = a3.informed_solver(hard_board, 5000, goal_board, False)
    assert ucs_result_2 is not None
    assert type(ucs_result_2) is State.State
    print("\n")

    # A* Test
    print("A* TESTS\n-------------")
    # Expected: Goal Found!
    astar_result_1 = a3.informed_solver(medium_board, 5000, goal_board, True)
    assert astar_result_1 is not None
    assert type(astar_result_1) is State.State
    # Expected: Goal Found!
    astar_result_2 = a3.informed_solver(hard_board, 5000, goal_board, True)
    assert astar_result_2 is not None
    assert type(astar_result_2) is State.State
    print("\n")

    # IDS Test
    print("IDS TESTS\n-------------")
    ids_fringe = []
    ids_node = State.State(hard_board, None, 0, 0)
    a3.fringe_expansion(ids_node, ids_fringe)
    ids_result = a3.ids_solver(hard_board, 5000, goal_board)

    print("\n--- %s seconds ---\n\n" % (time.time() - start_time1))
