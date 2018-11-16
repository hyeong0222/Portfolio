from player import PlayerMM, PlayerAB
from board import Board

##################
# This is the heuristic that we use in the following tests:
# test_AB
# test_MM
##################
# This heuristic is NOT used in the competency test.
# We use a more advanced heuristic in the cometency tests.
# You may not use this heuristic as your own. Even if you
# do, then you will fail the competency test. More points
# will be deducted from your score if you attempt to submit 
# this exact heuristic. 
##################

# this is really bad but whatever
def heuristic(self, board):
    p1Count = 0
    p2Count = 0
    for col in board.board:
        shouldDouble = False
        lastElSeen = -1
        for el in col:
            if el == lastElSeen:
                if el == 0:
                    p1Count += 2 if shouldDouble else 1
                else:
                    p2Count += 2 if shouldDouble else 1
                shouldDouble = True
            else:
                shouldDouble = False
                lastElSeen = el
    return p1Count - p2Count

def override_heuristic(PlayerClass):
    class NewClass(PlayerClass): pass
    NewClass.heuristic = heuristic
    return NewClass

PlayerMM = override_heuristic(PlayerMM)
PlayerAB = override_heuristic(PlayerAB)

if __name__ == "__main__":

    ##################
    # Now for the good stuff
    ##################


    board_hash = 19677 #### INSERT BOARD HASH FROM ERROR MESSAGE HERE ####


    # The code below is simply initializing a board based on the given hash
    # This will create a board that starts at a certain game state. This
    # has many uses, including debugging! :)  
    board = Board(hash=board_hash)
    board.print()

    # Now we're initializing our player with a certain depth limit
    depth = 7 # Play around with this number and see if it changes your result!
    isPlayerOne = not board.numMoves % 2 # Don't modify this! This determines the isPlayerOne property

    playerMM = PlayerMM(depth, isPlayerOne)
    playerAB = PlayerAB(depth, isPlayerOne)

    #### Now that we've set up our board to replicate the situation where we fail the 
    #### test in autograder, we can debug:

    # In the autograder error mrssage provide the move that you made, plus a list of 
    # acceptable moves. So you should run this until you return an item in that list.
    # Note that it's VERY important you copy the hash from the error message! 
    # Otherwise, the results will mean nothing!
    nextMoveMM = playerMM.findMove(board)
    nextMoveAB = playerAB.findMove(board)
    print("MM Move:", nextMoveMM)
    print("AB Move:", nextMoveAB)
    # Note that this doesn't actually make the move! You will have to call board.makeMove to do that,
    # but this shouldn't be too important for debugging using the info we provide. 
