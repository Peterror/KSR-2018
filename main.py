from ReutersVectorize import ReutersVectorize
from bs4 import BeautifulSoup
from Classify.Classify import Classifier
import threading

def load_train_nbhd(desc_sort=True):
    with open("Scripts/reuters21578/reuters_train") as fp:
        print("Opened Scripts/reuters21578/reuters_train")
        reuters_train_soup = BeautifulSoup(fp, "html.parser")
    if desc_sort:
        with open("Scripts/reuters21578/reuters_dictionary_max2000") as fp:
            print("Opened Scripts/reuters21578/reuters_dictionary_max2000")
            reuters_dictionary_soup = BeautifulSoup(fp, "html.parser")
    else:
        with open("Scripts/reuters21578/reuters_dictionary_min2000") as fp:
            print("Opened Scripts/reuters21578/reuters_dictionary_min2000")
            reuters_dictionary_soup = BeautifulSoup(fp, "html.parser")

    return ReutersVectorize.generate_neighbourhood(reuters_dictionary_soup=reuters_dictionary_soup,
                                                   reuters_train_soup=reuters_train_soup)

def load_test_nbhd(desc_sort=True):
    with open("Scripts/reuters21578/reuters_test") as fp:
        print("Opened Scripts/reuters21578/reuters_test")
        reuters_train_soup = BeautifulSoup(fp, "html.parser")
    if desc_sort:
        with open("Scripts/reuters21578/reuters_dictionary_max2000") as fp:
            print("Opened Scripts/reuters21578/reuters_dictionary_max2000")
            reuters_dictionary_soup = BeautifulSoup(fp, "html.parser")
    else:
        with open("Scripts/reuters21578/reuters_dictionary_min2000") as fp:
            print("Opened Scripts/reuters21578/reuters_dictionary_min2000")
            reuters_dictionary_soup = BeautifulSoup(fp, "html.parser")

    return ReutersVectorize.generate_neighbourhood(reuters_dictionary_soup=reuters_dictionary_soup,
                                                   reuters_train_soup=reuters_train_soup)


def thread_classifier(name, clasification, kmin, kmax, test_neighbourhood):
    correct_classification_array = [0 for i in range(kmin, kmax+1)]
    incorrect_classification_array = [0 for i in range(kmin, kmax+1)]

    for test in test_neighbourhood:
        classification_array = clasification(test, kmin, kmax)
        for i in range(len(classification_array)):
            if test.name == classification_array[i]:
                correct_classification_array[i] += 1
            else:
                incorrect_classification_array[i] += 1
                # print(test.name)
                # print(classification_array[i])
            if (correct_classification_array[i] + incorrect_classification_array[i]) % 25 == 0:
                print("%s k=%i correct: %i incorrect: %i" % (name, i+kmin, correct_classification_array[i], incorrect_classification_array[i]))
    file = open(name, "w")
    for i in range(kmax-kmin+1):
        file.write("k=%i correct: %i incorrect: %i" % (i+kmin, correct_classification_array[i], incorrect_classification_array[i]))
        file.write('\n')
    file.close()


class myThread(threading.Thread):
    def __init__(self, name, clasification, kmin, kmax, test_neighbourhood):
        threading.Thread.__init__(self)
        self.name = name
        self.clasification = clasification
        self.kmin = kmin
        self.kmax = kmax
        self.test_neighbourhood = test_neighbourhood

    def run(self):
        print("Starting " + self.name)
        thread_classifier(self.name, self.clasification, self.kmin, self.kmax, self.test_neighbourhood)
        print("Exiting " + self.name)


def main():
    neighbourhood = load_train_nbhd(desc_sort=True)
    test_neighbourhood = load_test_nbhd(desc_sort=True)
    classifier = Classifier(neighbourhood)
    names = ["chebyshev", "euklides", "street"]

    threads = []
    i = 0
    kmin = 1
    kmax = 10
    for clasification in [classifier.a_classify_chebyshev, classifier.a_classify_euklides, classifier.a_classify_street]:
        threads += [myThread(names[i], clasification, kmin, kmax, test_neighbourhood)]
        i += 1

    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    print("exiting")


if __name__ == "__main__":
    main()
