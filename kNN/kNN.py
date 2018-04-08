import operator
from collections import Counter
from math import sqrt


class StructNeighbour(object):
    def __init__(self, vector, name):
        super(StructNeighbour, self).__init__()
        self.vector = vector
        self.name = name


class kNN(object):
    def __init__(self, distance_function):
        """
        :param distance_function: function used to calculate vector distance. Use kNN.distance_... methods
        """
        self.distance_function = distance_function

    def assign(self, trainingValues, testValue, k):
        """
        Assign testValiue to the nearest neighbours
        :param trainingValues: array of StructNeighbour objects - neighbours
        :param testValue: StructNeighbour object - to be classified
        :param k: k nearest neighbours
        :return: name of StructNeighbour most frequent object
        """
        n = self._get_neighbours(trainingValues, testValue, k, self.distance_function)
        return max(Counter(n).items(), key=operator.itemgetter(1))[0]

    def _get_neighbours(self, trainingValues, testValue, k, distance_function):
        distances = []
        for x in trainingValues:
            dist = distance_function(testValue, x)
            distances += [[x.name, dist]]
        distances.sort(key=operator.itemgetter(1))
        neighbors = []
        for x in range(k):
            neighbors += [distances[x][0]]
        return neighbors

    @staticmethod
    def distance_euklides2(a, b):
        a_b_distance = 0
        for point_a, point_b in zip(a.vector, b.vector):
            a_b_distance += (point_a - point_b) ** 2
        return a_b_distance

    @staticmethod
    def distance_euklides(a, b):
        a_b_distance2 = 0
        for point_a, point_b in zip(a.vector, b.vector):
            a_b_distance2 += (point_a - point_b) ** 2
        return sqrt(a_b_distance2)

    @staticmethod
    def distance_chebyshev(a, b):
        max_distance = 0
        for point_a, point_b in zip(a.vector, b.vector):
            if abs(point_a - point_b) > max_distance:
                max_distance = abs(point_a - point_b)
        return max_distance

    @staticmethod
    def distance_street(a, b):
        # sum of every vector1 coordinate - sum of every vector2 coordinate
        a_b_distance = 0
        for point_a, point_b in zip(a.vector, b.vector):
            a_b_distance += abs(point_a - point_b)
        return a_b_distance


def main():
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
    whoami = knn.assign(training, test, 3)
    pass


if __name__ == "__main__":
    main()
