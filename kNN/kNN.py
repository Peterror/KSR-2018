import operator
from collections import Counter
from math import sqrt
import time
from random import randint


class StructNeighbour(object):
    """
    Structure of the neighbour.
    Pass objects of this class to kNN distance functions.
    """
    def __init__(self, vector, name):
        super(StructNeighbour, self).__init__()
        self.vector = vector
        self.name = name

    def __lt__(self, other):
        return self.vector < other.vector


class kNN(object):
    """
    Class used to classify elements by using k Nearest Neighbours
    """
    def __init__(self, distance_function):
        """
        :param distance_function: function used to calculate vector distance. Use kNN.distance_... methods
        """
        self.distance_function = distance_function

    def assign(self, training_values: list, test_value: StructNeighbour, k: int):
        """
        Assign testValiue to the nearest neighbours
        :param training_values: array of StructNeighbour objects - neighbours
        :param test_value: StructNeighbour object - to be classified
        :param k: k nearest neighbours
        :return: name of StructNeighbour most frequent object
        """
        n = self._get_neighbours(training_values, test_value, k, self.distance_function)
        return max(Counter(n).items(), key=operator.itemgetter(1))[0]

    def assign_array(self, training_values: list, test_value: StructNeighbour, kmin: int, kmax: int):
        """
        Assign testValue to the nearest neighbours array from kmin to kmax
        :param training_values: array of StructNeighbour objects - neighbours
        :param test_value: StructNeighbour object - to be classified
        :param kmin: k nearest neighbours minimum
        :param kmax: k nearest neighbours maximum
        :return: Array of names of StructNeighbour most frequent object in corresponding k
        """
        n = self._get_neighbours(training_values, test_value, kmax, self.distance_function)
        return [max(Counter(n[:i]).items(), key=operator.itemgetter(1))[0] for i in range(kmin, kmax+1)]

    def _get_neighbours(self, training_values, test_value, k, distance_function):
        """
        Calculate the nearest neighbours
        :return: list of neighbours names
        """
        distances = []
        for x in training_values:
            dist = distance_function(test_value, x)
            distances += [[x.name, dist]]
        neighbors = []
        for x in range(k):
            val = min(distances, key=operator.itemgetter(1))
            for v in val[0]:
                neighbors += [v]
            del distances[distances.index(val)]
        return neighbors

    @staticmethod
    def distance_euklides2(a: StructNeighbour, b: StructNeighbour):
        a_b_distance = 0
        for point_a, point_b in zip(a.vector, b.vector):
            a_b_distance += (point_a - point_b) ** 2
        return a_b_distance

    @staticmethod
    def distance_euklides(a: StructNeighbour, b: StructNeighbour):
        a_b_distance2 = 0
        for point_a, point_b in zip(a.vector, b.vector):
            a_b_distance2 += (point_a - point_b) ** 2
        return sqrt(a_b_distance2)

    @staticmethod
    def distance_chebyshev(a: StructNeighbour, b: StructNeighbour):
        max_distance = 0
        for point_a, point_b in zip(a.vector, b.vector):
            if abs(point_a - point_b) > max_distance:
                max_distance = abs(point_a - point_b)
        return max_distance

    @staticmethod
    def distance_street(a: StructNeighbour, b: StructNeighbour):
        # sum of every vector1 coordinate - sum of every vector2 coordinate
        a_b_distance = 0
        for point_a, point_b in zip(a.vector, b.vector):
            a_b_distance += abs(point_a - point_b)
        return a_b_distance


def measure_distance_function_performance():
    """
    Performance testing function. Results displayed on the terminal.
    """
    vector_size = 200
    test_repeats = 10000
    a = StructNeighbour([randint(0, 40) for i in range(vector_size)], "a")
    b = StructNeighbour([randint(0, 40) for i in range(vector_size)], "b")

    start = time.time()
    for i in range(test_repeats):
        kNN.distance_chebyshev(a, b)
    end = time.time()
    print("distance_chebyshev(s): %f" % (end - start))

    start = time.time()
    for i in range(test_repeats):
        kNN.distance_euklides(a, b)
    end = time.time()
    print("distance_euklides(s): %f" % (end - start))

    start = time.time()
    for i in range(test_repeats):
        kNN.distance_street(a, b)
    end = time.time()
    print("distance_street(s): %f" % (end - start))


def main():
    measure_distance_function_performance()  # Performance testing
    a = StructNeighbour([1, 2, 1], "a")
    b = StructNeighbour([9, 1, 5], "a")
    c = StructNeighbour([5, 8, 8], "a")
    d = StructNeighbour([1, 4, 1], "b")
    e = StructNeighbour([1, 1, 1], "b")
    f = StructNeighbour([4, 0, 3], "c")
    g = StructNeighbour([3, 4, 7], "d")
    training = [a, b, c, d, e, f, g]
    test = StructNeighbour([0, 0, 0], "test")
    knn = kNN(kNN.distance_euklides2)
    whoami = knn.assign(training, test, 3)  # DEBUGGING PURPOSES
    pass


if __name__ == "__main__":
    main()
