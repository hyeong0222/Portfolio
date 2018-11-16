class DecisionTree:
    """
    A wrapper around the root node of a decision tree with some handy functions
    """

    def __init__(self, root):
        self.root = root

    def classify(self, datum):
        """
        Classifies a single piece of data using the decision tree
        """
        cur = self.root
        while not(cur.is_leaf()):
            cur = cur.get_child(datum[cur.get_split_attr()])
        return cur.get_class()

