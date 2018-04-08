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


def thread_classifier(name, clasification, k, test_neighbourhood):
    correct_classification = 0
    incorrect_classification = 0
    for test in test_neighbourhood:
        if test.name == clasification(test, k):
            correct_classification += 1
        else:
            incorrect_classification += 1
        if (correct_classification + incorrect_classification) % 10 == 0:
            print("correct: %i incorrect: %i" % (correct_classification, incorrect_classification))
    file = open(name, "w")
    file.write("correct: %i incorrect: %i" % (correct_classification, incorrect_classification))
    file.write('\n')
    file.close()


class myThread (threading.Thread):
   def __init__(self, name, clasification, k, test_neighbourhood):
        threading.Thread.__init__(self)
        self.name = name
        self.clasification = clasification
        self.k = k
        self.test_neighbourhood = test_neighbourhood

   def run(self):
      print ("Starting " + self.name)
      thread_classifier(self.name, self.clasification, self.k, self.test_neighbourhood)
      print ("Exiting " + self.name)

def main():
    neighbourhood = load_train_nbhd(desc_sort=True)
    test_neighbourhood = load_test_nbhd(desc_sort=True)
    classifier = Classifier(neighbourhood)
    names = ["chebyshev", "euklides", "street"]

    threads = []
    for k in [5, 10]:
        i = 0
        for clasification in [classifier.classify_chebyshev, classifier.classify_euklides, classifier.classify_street]:
            threads += [myThread(names[i]+str(k), clasification, k, test_neighbourhood)]
            i += 1
    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    print("exiting")


if __name__ == "__main__":
    main()
