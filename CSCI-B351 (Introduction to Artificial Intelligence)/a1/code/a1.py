# Assignment 1
import math

#################################
# Problem 1
#################################
# Objectives:
# (1) Write a recursive function to compute the nth fibonacci number

def fib(n):
    if n < 0:
        print ("Invalid")
    elif n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fib(n-1) + fib(n-2)




#################################
# Problem 2
#################################
# Objectives:
# (1) Write a function which returns a list of the first and last items in a given list

def firstLast(n):
    
    if len(n) <= 1:
        return [n[0]]
    else:
        return [n[0], n[len(n)-1]]




#################################
# Problem 3
#################################
# Objectives:
# (1) Write a function which takes a matrix and returns the transpose of that matrix
# Note: A matrix is represented as a 2-d array: [[1,2,3],[4,5,6],[7,8,9]]


def transpose(matrix):
    
    result = []        
    for row in range (len(matrix[0])):
        newRow = []
        for col in range (len(matrix)):
            newRow.append(matrix[col][row])
        result.append(newRow)
    matrix = result
    return matrix

        


#################################
# Problem 4
#################################
# Objectives:
# (1) Write a function which takes two points of the same dimension, and finds the euclidean distance between them
# Note: A point is represented as a tuple: (0,0)
p = [1,2,3]
q = [4,5,6]
def euclidean(p1,p2):
    euc = 0
    for i in range(len(p1)):
        euc = euc + (p2[i] - p1[i])**2

    return math.sqrt(euc)






#################################
# Problem 5
#################################

# A Node is an object
# - value : Number
# - children : List of Nodes
class Node:
    def __init__(self, value, children):
        self.value = value
        self.children = children


exTree = Node(1,[Node(2,[]),Node(3,[Node(4,[Node(5,[]),Node(6,[Node(7,[])])])])])
                    


# Objectives:
# (1) Write a function to calculate the sum of every node in a tree (iteratively)
def sumNodes(root):
    lists = []
    lists.append(root)
    result = 0
    while(lists != []):
        node = lists.pop()
        result = result + node.value
        lists += node.children
    return result
    
    
    

# (2) Write a function to calculate the sum of every node in a tree (recursively)

def sumNodesRec(root):
    result = 0
    if root.children == []:
        result = result + root.value
        return result
    else:
        result = result + root.value
        for child in root.children:
            result = result + sumNodesRec(child)
        return result





#################################
# Problem 6
#################################
# Objectives:
# (1) Write a function compose, which takes an inner and outer function
# and returns a new function applying the inner then the outer function to a value

def compose(f_outer, f_inner):
    return lambda x: f_outer(f_inner(x))

    






#################################
# Bonus
#################################
# Objectives:
# (1) Create a string which has each level of the tree on a new line

def treeToString(root):

    string = ""
    if root.children == []:
        string += str(root.value)
        return string
    else:
        string = string + str(root.value)
        string = string + '\n'
        for child in root.children:
            string = string + str(treeToString(child))
    return string

    
