import operator


class struct_neighbour(object):
    def __init__(self, vector, text):
        super(struct_neighbour, self).__init__()
        self.v = vector
        self.t = text


def getNeighbours(trainingValues, testValue, k, distanceFunction):
    distances = []
    for x in range(len(trainingValues)):
        dist = distanceFunction(testValue, trainingValues[x], len(testValue)-1)
        distances += [[trainingValues[x], dist]]
    distances.sort(key=operator.itemgetter(1))
    neighbors = []
    for x in range(k):
        neighbors.append(distances[x][0])
    return neighbors

def kNN(unknown_xy, neighbours):
    """
        name, x, y
    """
    odleglosci = []
    najblizsze = dict()
    for neighbour in neighbours:
        odleglosci += [[neighbour.name, (neighbour.x-unknown_xy.x)**2 + (neighbour.y-unknown_xy.y)**2]]
    najblizsze += sorted(odleglosci.items(), key=operator.itemgetter(1))

