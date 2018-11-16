import math

class Player:

    def __init__(self, depthLimit, isPlayerOne):

        self.isPlayerOne = isPlayerOne
        self.depthLimit = depthLimit

    # TODO
    # Returns a heuristic for the board position
    # Good positions for 0 pieces should be positive and good positions for 1 pieces
    # should be negative
    # this is really bad but whatever
    def heuristic(self, board):
        for i in range(board.HEIGHT):
            for j in range(board.WIDTH):
                if self.isPlayerOne:
                    p1Heuristic = 0

                    try:
                        if (board[i - 1][j] == None):
                            if (board[i][j - 1] == None):
                                p1Heuristic += 10
                            elif (board[i + 1][j - 1] == None):
                                p1Heuristic += 10
                            elif (board[i + 1][j] == None):
                                p1Heuristic += 10
                            elif (board[i][j + 1] == None):
                                p1Heuristic += 10
                            elif (board[i + 1][j + 1] == None):
                                p1Heuristic += 10
                            else: p1Heuristic += 0
                    except: pass
                    
                    try:
                        if (board[i - 1][j] != None):
                            if (board[i - 2][j - 1] != None):
                                if (board[i - 1][j - 1] == None):
                                    p1Heuristic += 10
                                elif (board[i][j - 1] == None):
                                    p1Heuristic += 10
                                elif (board[i + 1][j - 1] == None):
                                    p1Heuristic += 10
                                else: p1Heuristic += 0
                            elif (board[i - 2][j + 1] != None):
                                if (board[i - 1][j + 1] == None):
                                    p1Heuristic += 10
                                elif (board[i][j + 1] == None):
                                    p1Heuristic += 10
                                elif (board[i + 1][j + 1] == None):
                                    p1Heuristic += 10
                                else: p1Heuristic += 0
                            elif (board[i + 1][j] == None):
                                p1Heuristic += 10
                            else: p1Heuristic += 0
                    except: pass
                    return p1Heuristic

                else:
                    p2Heuristic = 0

                    try:
                        if (board[i - 1][j] == None):
                            if (board[i][j - 1] == None):
                                p2Heuristic -= 10
                            elif (board[i + 1][j - 1] == None):
                                p2Heuristic -= 10
                            elif (board[i + 1][j] == None):
                                p2Heuristic -= 10
                            elif (board[i][j + 1] == None):
                                p2Heuristic -= 10
                            elif (board[i + 1][j + 1] == None):
                                p2Heuristic -= 10
                            else: p2Heuristic -= 0
                    except: pass
                    
                    try:
                        if (board[i - 1][j] != None):
                            if (board[i - 2][j - 1] != None):
                                if (board[i - 1][j - 1] == None):
                                    p2Heuristic -= 10
                                elif (board[i][j - 1] == None):
                                    p2Heuristic -= 10
                                elif (board[i + 1][j - 1] == None):
                                    p2Heuristic -= 10
                                else: p2Heuristic -= 0
                            elif (board[i - 2][j + 1] != None):
                                if (board[i - 1][j + 1] == None):
                                    p2Heuristic -= 10
                                elif (board[i][j + 1] == None):
                                    p2Heuristic -= 10
                                elif (board[i + 1][j + 1] == None):
                                    p2Heuristic -= 10
                                else: p2Heuristic -= 0
                            elif (board[i + 1][j] == None):
                                p2Heuristic -= 10
                            else: p2Heuristic -= 0
                    except: pass
                    return p2Heuristic

class PlayerMM(Player):
    def __init__(self, depthLimit, isPlayerOne):
        super().__init__(depthLimit, isPlayerOne)

    #TODO
    #returns the optimal column to move in by implementing the Minimax algorithm
    def findMove(self, board):   
        def minimax(board, depth, isMax):
            terminal = board.isTerminal()
            # 1. Checks wheter position is terminal
            if terminal == 0:
                return 0, -1
            if terminal == 1:
                return 1000000, -1
            if terminal == 2:
                return -1000000, -1
            # 2. If depth is zero, returns heuristic value
            if depth == 0:
                return self.heuristic(board), -1
            # 3. Iterates over possible moves for the current player
            best_move = -1
            if isMax:
                best_score = -math.inf
            else:
                best_score = math.inf
            for move in board.children():
                # 4. Uses recursion with minimax to score each move
                score = minimax(move[1], depth-1, not isMax)[0]
                # 5. For player 0, finds the max-score move; for player 1, finds the min score move
                if (isMax and (score > best_score)) or (not isMax and score < best_score):
                    best_score = score
                    best_move = move[0]
            # 6. Returns the best move for the current player, and its score.
            return best_score, best_move

        minmaxmove = minimax(board, self.depthLimit, self.isPlayerOne)[1]
        return minmaxmove

class PlayerAB(Player):
    
    def __init__(self, depthLimit, isPlayerOne):
        super().__init__(depthLimit, isPlayerOne)
        
    #TODO
    #returns the optimal column to move in by implementing the Alpha-Beta algorithm
    def findMove(self, board):
        def alphaBeta(board, depth, alpha, beta, isMax):
            # 1. Checks whether position is terminal.
            terminal = board.isTerminal()
            if terminal == 0:
                return 0, -1
            if terminal == 1:
                return math.inf, -1
            if terminal == 2:
                return -math.inf, -1
            # 2. If depth is zero, returns heursitic
            if depth == 0:
                return self.heuristic(board), -1
            # 3. Uses recursion, passing alpha and beta, to score each move
            best_move= board.children()[0][0]
            if isMax:
                best_score = -10000000 # added extra zero
            else:
                best_score = 10000000 # added zero; your code has to prefer the other player winning to making no move at all
            for move in board.children():
                score = alphaBeta(move[1], depth-1, alpha, beta, not isMax)[0]
                if (isMax and score > best_score) or (not isMax and score < best_score):
                    best_score = score
                    best_move = move[0]
                if isMax:
                    if best_score > alpha:
                        alpha = best_score
                if not isMax:
                    if best_score < beta:
                        beta = best_score
                if alpha >= beta:
                    break
            # 5. Returns the best move for the current player, and its score
            return best_score, best_move
        abMove = alphaBeta(board, self.depthLimit, -math.inf, math.inf, self.isPlayerOne)[1]
        return abMove

class PlayerABDP(Player):

    def __init__(self, depthLimit, isPlayerOne):
        super().__init__(depthLimit, isPlayerOne)

        self.resolved = {}

    #TODO
    #returns the optimal column to move in by implementing the Alpha-Beta algorithm with dynamic programming
    def findMove(self, board):
        pass



#######################################################
###########Example Subclass for Testing
#######################################################

#This will inherit your findMove from above, but will override the heuristic function with
#a new one; you can swap out the type of player by changing X in "class TestPlayer(X):"
class TestPlayer(PlayerMM):

    def __init__(self, depthLimit, isPlayerOne):
        super().__init__(depthLimit, isPlayerOne)

    #define your new heuristic here
    def heurisitic(self):
        pass
