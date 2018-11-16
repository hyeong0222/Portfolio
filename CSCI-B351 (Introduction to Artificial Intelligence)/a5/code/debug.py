from driver import Driver
from node import DTreeNode
from decision_tree_factory import DecisionTreeFactory
import numpy as np

class debug:
    def __init__(self):
        self.labels = np.array([1,1,1,2,2,2,2,2])
        
    def debug_entropy(self):
        node = DTreeNode([1])
        node.idxs = [0,1,3]
        print(node.get_entropy(self.labels[node.idxs]))

        node.idxs = [2,4,5,6,7]
        print(node.get_entropy(self.labels[node.idxs]))

        node.idxs = list(range(8))
        print(node.get_entropy(self.labels[node.idxs]))

    def debug_informationGain(self):
        factory = DecisionTreeFactory( np.array(self.labels), np.array(self.labels) )
        parent = DTreeNode([1])
        child1 = DTreeNode([1])
        child2 = DTreeNode([1])
        parent.idxs = list(range(8))
        child1.idxs = [0,1,3]
        child2.idxs = [2,4,5,6,7]
        print(factory._calc_information_gain(parent, [child1, child2]))

        
if __name__ == '__main__':
    d = debug()
    print("Your entropy values: ")
    d.debug_entropy()
    print("\nExpected entropy values: \n0.9182958340544896\n0.7219280948873623\n0.954434002924965")
    print("\nYour information gain value: ")
    d.debug_informationGain()
    print("\nExpected information gain value: \n0.15886800584993")


