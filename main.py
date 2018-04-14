from Vectorize.ReutersVectorize import ReutersVectorize
from bs4 import BeautifulSoup
from Classify.Classify import Classifier
import threading


def load_nbhd(entries_soup_name, dict_soup_name):
    with open("Scripts/reuters21578/%s" % entries_soup_name) as fp:
        print("Opened Scripts/reuters21578/%s" % entries_soup_name)
        reuters_soup = BeautifulSoup(fp, "html.parser")
    with open("Scripts/reuters21578/%s" % dict_soup_name) as fp:
        print("Opened Scripts/reuters21578/%s" % dict_soup_name)
        reuters_dictionary_soup = BeautifulSoup(fp, "html.parser")

    return ReutersVectorize.generate_neighbourhood(reuters_dictionary_soup=reuters_dictionary_soup,
                                                   reuters_source_soup=reuters_soup)


def thread_classifier(name, clasification, kmin, kmax, test_neighbourhood):
    correct_classification_array = [0 for i in range(kmin, kmax+1)]
    incorrect_classification_array = [0 for i in range(kmin, kmax+1)]

    for test in test_neighbourhood:
        classification_array = clasification(test, kmin, kmax)
        for i in range(len(classification_array)):
            if classification_array[i] in test.name:
                correct_classification_array[i] += 1
            else:
                incorrect_classification_array[i] += 1
                # print(test.name)
                # print(classification_array[i])
            if (correct_classification_array[i] + incorrect_classification_array[i]) % 25 == 0:
                print("%s k=%i correct: %i incorrect: %i" % (name,
                                                             i+kmin,
                                                             correct_classification_array[i],
                                                             incorrect_classification_array[i]))
    file = open(name, "w")
    for i in range(kmax-kmin+1):
        file.write("k=%i correct: %i incorrect: %i" % (i+kmin,
                                                       correct_classification_array[i],
                                                       incorrect_classification_array[i]))
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
    names = ["chebyshev", "euklides", "street"]

    threads = []
    i = 0
    kmin = 1
    kmax = 10
    reuters1 = ["reuters_test", "reuters_train", "reuters_dictionary_max2000", "reuters_dictionary_min2000"]
    reuters2 = ["reuters2_test", "reuters2_train", "reuters2_dictionary_max2000", "reuters2_dictionary_min2000"]
    reuters3 = ["reuters3_test", "reuters3_train", "reuters3_dictionary_max2000", "reuters3_dictionary_min2000"]

    used_reuters = reuters2
    test_name = used_reuters[0]
    train_name = used_reuters[1]
    reuters_dictionary_name_TF = used_reuters[2]
    reuters_dictionary_name_bool = used_reuters[3]

    neighbourhood = load_nbhd(entries_soup_name=train_name, dict_soup_name=reuters_dictionary_name_bool)
    test_neighbourhood = load_nbhd(entries_soup_name=test_name, dict_soup_name=reuters_dictionary_name_bool)
    classifier = Classifier(neighbourhood)
    for clasification in [classifier.a_classify_chebyshev,
                          classifier.a_classify_euklides,
                          classifier.a_classify_street]:
        threads += [myThread(names[i], clasification, kmin, kmax, test_neighbourhood)]
        i += 1

    for thread in threads:
        thread.start()
    for thread in threads:
        thread.join()
    print("exiting")


if __name__ == "__main__":
    main()
