from loader import Loader
from decision_tree_factory import DecisionTreeFactory
import numpy as np

# PATH VARS
ROOT_REL = '../'
DATA_REL = ROOT_REL + 'datasets/'

class Driver:
    """
    Driver class runs and reports details of running decision tree algorithm on datasets

    Args:
        dataset_names (List of String): list of the names of datasets to be loaded
    """

    def __init__(self):
        self.loader = Loader()

        self.datasets = {}

    def run(self, name, path=DATA_REL):
        """
        Builds decision tree a dataset and returns error
        """

        # load given dataset
        self._load(name, path)

        data = self.datasets[path+name]

        # initialize our decision tree builder
        factory = DecisionTreeFactory(data[0],data[2])

        # make the tree
        tree = factory.build_tree()

        # run on test set
        results = np.zeros(data[3].shape)
        for i in range(data[1].shape[0]):
            results[i] = tree.classify(data[1][i,:])
        count = 0

        # evaluate performance on test set
        for i in range(data[1].shape[0]):
            if not (results[i] == data[3][i]):
                count +=1

        # the percentage of labels which were incorrect
        return count/data[3].shape[0]

    def _load(self, name, path):
        """
        Add a dataset to the driver
        """
        dataset = self.loader.load_data(name, path)

        assert dataset is not None

        self.datasets[path+name] = dataset

if __name__ == '__main__':
    driver = Driver()
    voting_error = driver.run("voting_transformed.csv")
    scale_error = driver.run("scale_transformed.csv")
    print("Voting dataset error: " + str(voting_error))
    print("Scale dataset error: " + str(scale_error))
