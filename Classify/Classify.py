from kNN.kNN import StructNeighbour, kNN


class Classifier(object):
    def __init__(self, struct_neighbourhood_list):
        self.neighbourhood = struct_neighbourhood_list

    def classify_euklides(self, test_value, k):
        knn = kNN(kNN.distance_euklides)
        return knn.assign(self.neighbourhood, test_value, k)

    def classify_chebyshev(self, test_value, k):
        knn = kNN(kNN.distance_chebyshev)
        return knn.assign(self.neighbourhood, test_value, k)

    def classify_street(self, test_value, k):
        knn = kNN(kNN.distance_street)
        return knn.assign(self.neighbourhood, test_value, k)

    def a_classify_euklides(self, test_value, kmin, kmax):
        knn = kNN(kNN.distance_euklides)
        return knn.assign_array(self.neighbourhood, test_value, kmin, kmax)

    def a_classify_chebyshev(self, test_value, kmin, kmax):
        knn = kNN(kNN.distance_chebyshev)
        return knn.assign_array(self.neighbourhood, test_value, kmin, kmax)

    def a_classify_street(self, test_value, kmin, kmax):
        knn = kNN(kNN.distance_street)
        return knn.assign_array(self.neighbourhood, test_value, kmin, kmax)

