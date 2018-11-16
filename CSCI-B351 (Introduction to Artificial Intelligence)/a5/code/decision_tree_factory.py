from node import DTreeNode
from decision_tree import DecisionTree
import numpy as np

class DecisionTreeFactory:
    """
    A decision tree making class

    Args:
        data (np.matrix): training data from which to build tree
        labels (np.matrix): labels for training data
    """

    def __init__(self, data, labels):
        self.data = data
        self.labels = labels

    def build_tree(self):
        """
        Builds a tree from data and labels
        """

        root = DTreeNode(range(self.data.shape[0]))

        self._build_tree_rec(root)

        return DecisionTree(root)

    def _build_tree_rec(self, node):
        """
        Recursively builds tree out from input node

        Args:
            node (DTreeNode): the node from which to continue building out our tree.
        """

        # If we have pure data at this node, we can stop
        if node.should_stop(self.data):
            node.leaf = True
            return node
        # Otherwise it's time to find where to split
        splits = []

        # generate a split at each attribute (if possible)
        for attr in range(self.data.shape[1]):
            attr_pos_idxs = []
            attr_neg_idxs = []

            # idx are really jsut data points
            for idx in node.get_idxs():
                if self.data[idx, attr] == 1:
                    attr_pos_idxs.append(idx)
                else:
                    attr_neg_idxs.append(idx)

            try:
                splits.append((node, [DTreeNode(attr_pos_idxs),DTreeNode(attr_neg_idxs)],attr))
            except:
                pass

        # hopefully we could split somewhere
        assert splits != []

        ###########################
        # call _get_ranked_splits #
        ###########################
        ranked_splits = self._get_ranked_splits(splits)

        # get the DTreeNode for the best attr
        # split[1] is the [pos_child_node, neg_child_node] list
        best_pos_attr = ranked_splits[0][1][0]
        best_neg_attr = ranked_splits[0][1][1]

        # set class for each split by counting the frequency of each attr,
        # and choose the most frequent one
        # u: unique labels
        # c: counts of each label
        u,c = np.unique(self.labels[best_pos_attr.get_idxs()], return_counts=True)
        best_pos_attr.set_class(u,c)
        u,c = np.unique(self.labels[best_neg_attr.get_idxs()], return_counts=True)
        best_neg_attr.set_class(u,c)

        node.attr = ranked_splits[0][2]

        # Build out tree!
        # children is a dict with
        # the attribute values of the points as keys
        # and the child nodes (after being expaneded) as values
        node.children[1] = self._build_tree_rec(best_pos_attr)
        node.children[0] = self._build_tree_rec(best_neg_attr)

        return node

    ################
    ##### TODO #####
    ################
    def _get_ranked_splits(self, splits):
        """
        Sort the given list of possible splits in the order of information gain.
        argument 'splits' here is a list of tuples of the form "(parent_node, child_nodes_list, attr)"
        """
        splits = sorted(splits, key = self.helper, reverse = True)

        return splits


    def helper(self, var):

        return self._calc_information_gain(var[0], var[1])        
    

    ################
    ##### TODO #####
    ################
    def _calc_information_gain(self, parent_node, children_nodes):
        """
        Calculates the information gain going from parent node to some child nodes.
        Information gain can be calculated by finding the difference between the entropy
        at the parent node, and sum of the entropy of each child node normalized by the
        proportion of datapoints in that child node.

        Args:
            parent_node (DTreeNode): The node from which we are generating the split
            children_nodes (List<DTreeNode>): The child nodes for this potential split

        Returns:
            (float): the information gain from splitting with given attributes

        References:
            [1]: https://en.wikipedia.org/wiki/Information_gain_in_decision_trees
        """
        parentEntropy = parent_node.get_entropy(self.labels[parent_node.idxs])
        childrenEntropy = [0, 0]
        total = 0

        for i in range (len(children_nodes)):
            childrenEntropy[i] = children_nodes[i].get_entropy(self.labels[children_nodes[i].idxs])
            total += childrenEntropy[i] * ((len(self.labels[children_nodes[i].idxs])) / (len(self.labels[parent_node.idxs])))

        ig = parentEntropy - total

        return ig
        
