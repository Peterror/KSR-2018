from kNN.kNN import StructNeighbour, kNN
from bs4 import BeautifulSoup
import re
from collections import Counter


class ReutersVectorize():
    @staticmethod
    def generate_neighbourhood(reuters_train_soup: BeautifulSoup, reuters_dictionary_soup: BeautifulSoup):
        bump = 0
        r = re.compile(r"[^a-zA-Z' ']")  # REGEX do wyciagania wszystkich znakow niechcianych(+ cyfr)
        neighbourhood = []
        reuters_dictionary_words = []
        for word in reuters_dictionary_soup.find_all("word"):
            reuters_dictionary_words += [word.text]
        for reuter in reuters_train_soup.document.find_all("reuters"):
            wordlist = []
            bump+=1
            if bump % 1000 == 0:
                print(bump)

            place_name = reuter.find("places").find("d").text
            wordlist += r.sub('', reuter.body.text.lower()).split()
            word_count_dict = dict(Counter(wordlist))
            neighbourhood += [StructNeighbour(vector=ReutersVectorize.generate_vector(word_count_dict,
                                                                                      reuters_dictionary_words),
                                              name=place_name)]
            pass
        return neighbourhood

    @staticmethod
    def generate_vector(word_count_dict, reuters_dictionary_words):
        vector = []
        for word in reuters_dictionary_words:
            try:
                vector += [word_count_dict[word]]
            except:
                vector += [0]
        return vector
