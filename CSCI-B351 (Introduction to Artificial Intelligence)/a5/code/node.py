import numpy as np
import math

class Node:
    """
    A node in a decision tree. Keeps track of attribute split for its children
    """

    def __init__(self):
        self.leaf = False
        self.children = {}
        self.attr = None
        self.predicted = 0

    def is_leaf(self):
        """
        Tells whether this node is terminal (has no children)
        """
        return self.leaf

    def get_child(self, identifier):
        """
        Returns the child node identified by identifier. For our purposes 0 is left node, 1 is right node

        Args:
            identifier (hashable): identifier of the child we want
        """

        return self.children[identifier]

    def get_class(self,):
        """
        Returns the most likely class of data points in the node. (can be run on any node but should only be used on leaves)
        """
        return self.predicted

    def get_split_attr(self):
        """
        Returns the attribute used for splitting the tree at this node. Error if is_leaf
        """
        assert self.is_leaf() == False

        return self.attr


class DTreeNode(Node):
    """
    A node used while building decision tree. Does all the things a node does + keep track of indexes and such
    """

    def __init__(self, idxs):
        super(DTreeNode, self).__init__()

        # There have to be some data points here!
        assert len(idxs) > 0

        self.idxs = idxs


    def get_idxs(self):
        """
        Returns indexes which come into this node
        """

        return self.idxs

    ################
    ##### TODO #####
    ################
    def get_entropy(self, labels):
        """
        Returns entropy within this node. Does not modify data or labels

        Args:
            labels (np.ndarray): the labels of the data points at this node, e.g. [1., 2., 0.]

        Returns:
            (float): the entropy of this node

        References:
            [1]: https://en.wikipedia.org/wiki/Decision_tree_learning#Information_gain
        """
        unique = np.unique (labels)
        countA = []
        
        for i in unique:
            count = 0
            for j in labels:
                if i == j:
                    count += 1
            countA.append(count)

        entrophy = 0

        for i in countA:
            chance = i/(len(labels))
            b = chance * (math.log(chance)/math.log(2))
            entrophy += b

        return -entrophy
                

    def should_stop(self, data):
        """
        Returns whether or not the decision tree building should stop here

        Returns:
            (bool): whether or not decision tree building should stop here
        """
        stop= True
        x = data[self.idxs,:]
        for i in range(1,x.shape[0]):
            if not np.equal(x[i-1,:],x[i,:]).all():
                stop = False
                break
        return stop

    def set_class(self,unique,counts):
        """
        Sets the most likely class of data points in the node. (can be run on any node but should only be used on leaves)
        """
        max_val = 0
        max_i = 0
        for i in range(counts.shape[0]):
            if counts[i] > max_val:
                max_i = i
        self.predicted = unique[max_i]
