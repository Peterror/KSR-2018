import operator
from collections import Counter


class StructNeighbour(object):
    def __init__(self, vector, text):
        super(StructNeighbour, self).__init__()
        self.vector = vector
        self.text = text


def distance_pitagoras2(a, b):
    a_b_distance = 0
    for point_a, point_b in zip(a.vector, b.vector):
        a_b_distance += (point_a - point_b)**2
    return a_b_distance


def get_neighbours(trainingValues, testValue, k, distance_function):
    distances = []
    for x in range(len(trainingValues)):
        dist = distance_function(testValue, trainingValues[x])
        distances += [[trainingValues[x].text, dist]]
    distances.sort(key=operator.itemgetter(1))
    neighbors = []
    for x in range(k):
        neighbors += [distances[x][0]]
    return neighbors


def kNN(trainingValues, testValue, k, distance_function):
    """
        name, x, y
    """
    n = get_neighbours(trainingValues, testValue, k, distance_function)
    return max(Counter(n).items(), key=operator.itemgetter(1))


a = StructNeighbour([1, 2, 1], "a")
b = StructNeighbour([9, 1, 5], "a")
c = StructNeighbour([5, 8, 8], "a")
d = StructNeighbour([1, 4, 1], "b")
e = StructNeighbour([1, 1, 1], "b")
f = StructNeighbour([4, 0, 3], "c")
g = StructNeighbour([3, 4, 7], "d")
training = [a,b,c,d,e,f,g]
test = StructNeighbour([0, 0, 0], "test")
whoami = kNN(training, test, 3, distance_pitagoras2)
pass
