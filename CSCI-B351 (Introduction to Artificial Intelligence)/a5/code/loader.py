import numpy as np


class Loader:
    """
    Loader class provides utilities for loading datasets
    """


    def load_data(self, filename, path, split=0.75):
        """
        Import train and test data and labels from given file with train test split pct indicated

        Args:
            filename (str): name of the csv file to be loaded
            path (str|None): path to the file (excluding the file itself)
            split (float|None): pct of data in train set

        Returns:
            (np.matrix, np.matrix, np.matrix, np.matrix): the train, test data and the train, test labels
        """
        X, y = self._import_data(filename, path)
        X_train, X_test, y_train, y_test = self._train_test_split(X, y, split)
        return X_train, X_test, y_train, y_test

    def _import_data(self, filename, path):
        """
        Import Data from a csv file

        Args:
            filename (str): name of the csv file to be loaded
            path (str|None): path to the file (excluding the file itself)

        Returns:
            (np.matrix, np.matrix): the data and label matrices
        """
        with open(path + filename,'r',newline='') as csvfile:
            x = np.loadtxt(csvfile,delimiter=',')
            y = x[:,x.shape[1]-1]
            x = np.delete(x,x.shape[1]-1,1)
            return x,y

    def _train_test_split(self, data, labels, split):
        """
        Acquire training data and labels from a full dataset

        Args:
            data (np.matrix): the dataset
            labels (np.matrix): the labels
            split(float): the percentage of data points in the train set

        Returns:
            (np.matrix, np.matrix, np.matrix, np.matrix): the train, test data and the train, test labels
        """
        split_index = int(data.shape[0] * split)

        X_train = data[0:split_index, :]
        X_test = data[split_index:data.shape[0]-1, :]

        y_train = labels[0:split_index]
        y_test = labels[split_index:data.shape[0]-1]

        return X_train, X_test, y_train, y_test

    
# Rudimentary Unit Tests
if __name__ == '__main__':
    loader = Loader()
    scale_X, scale_y = loader._import_data('scale_transformed.csv')
    voting_X, voting_y = loader._import_data('voting_transformed.csv')
    assert scale_X is not None
    assert scale_y is not None
    assert voting_X is not None
    assert voting_y is not None

    X_train, X_test, y_train, y_test = loader._train_test_split(scale_X, scale_y, 0.75)
    assert X_train is not None
    assert X_test is not None
    assert y_train is not None
    assert y_test is not None

    test = loader.load_data('scale_transformed.csv')

    assert test[0] is not None
    assert test[1] is not None
    assert test[2] is not None
    assert test[3] is not None
