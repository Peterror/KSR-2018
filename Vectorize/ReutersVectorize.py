from kNN.kNN import StructNeighbour
from bs4 import BeautifulSoup
import re
from collections import Counter


class ReutersVectorize(object):
    """
    Class used to generate Neighbourhood (StructNeighbour) from BeautifulSoup parsable file.
    """
    @staticmethod
    def generate_neighbourhood(reuters_source_soup: BeautifulSoup, reuters_dictionary_soup: BeautifulSoup):
        """
        Generate vectorized Neighbourhood from specified files
        :param reuters_source_soup: BeautifulSoup of texts which will be vectorized
        :param reuters_dictionary_soup: BeautifulSoup of words which will be used to vectorize texts
        :return: list of StructNeighbour's
        """
        bump = 0
        r = re.compile(r"[^a-zA-Z' ']")  # REGEX used to extracting unwanted characters(+ numbers)
        neighbourhood = []
        reuters_dictionary_words = []
        for word in reuters_dictionary_soup.find_all("word"):
            reuters_dictionary_words += [word.text]
        for reuter in reuters_source_soup.document.find_all("reuters"):
            wordlist = []
            bump += 1
            if bump % 1000 == 0:
                print(bump)

            place_name = reuter.find("places").find_all("d")
            place_names = []
            for name in place_name:
                place_names += [name.text]
            wordlist += r.sub('', reuter.body.text.lower()).split()
            word_count_dict = dict(Counter(wordlist))
            neighbourhood += [StructNeighbour(vector=ReutersVectorize._generate_vector(word_count_dict,
                                                                                       reuters_dictionary_words),
                                              name=place_names)]
        return neighbourhood

    @staticmethod
    def _generate_vector(word_count_dict, reuters_dictionary_words):
        vector = []
        for word in reuters_dictionary_words:
            try:
                vector += [word_count_dict[word]]
            except KeyError:  # the word hasn't appeared
                vector += [0]
        return vector
