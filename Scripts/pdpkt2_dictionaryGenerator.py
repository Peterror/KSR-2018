import operator
from collections import Counter
from bs4 import BeautifulSoup
import re

VALID_PLACES = {'grain': {}, 'acq': {}, 'corn': {}, 'money-fx': {}}

with open("reuters21578/reuters_train") as fp:
    print("Opened reuters21578/reuters_train")
    soup = BeautifulSoup(fp, "html.parser")

word_list = []

#  trans_table = str.maketrans("", "", string.punctuation+string.digits)
r = re.compile(r"[^a-zA-Z' ']")  # REGEX do wyciagania wszystkich znakow niechcianych(+ cyfr)

for text in soup.find("document").find_all("body"):
    word_list += r.sub('', text.text.lower()).split()  # lista slow wystepujacych w tekscie


for place_name, dictionary in VALID_PLACES.items():
    country_wordlist = []
    for reuter in soup.find("document").find_all("reuters"):
        for place in reuter.places.find_all('d'):
            if place.text == place_name:
                country_wordlist += r.sub('', reuter.body.text.lower()).split()  # lista slow wystepujacych dla kraju
    VALID_PLACES[place_name] = dict(Counter(country_wordlist))

new_soup = BeautifulSoup("<dict></dict>", "html.parser")

word_count = sorted(Counter(word_list).items(), key=operator.itemgetter(1), reverse=False)[:200]

frequency_sum = 0
for word, frequency in word_count:
    frequency_sum += frequency
    new_tag = new_soup.new_tag("word")
    new_tag.string = word
    new_tag['frequency'] = frequency
    for place_name, words in VALID_PLACES.items():
        try:
            new_tag[place_name] = words[word]
            new_tag[place_name] = 1  # BOOL extraction
        except:
            new_tag[place_name] = 0
    new_soup.dict.append(new_tag)
new_soup.dict['frequency_sum'] = frequency_sum


with open('reuters21578/reuters2_dictionary_min2000', 'w') as dictionary_output:
    print('saving reuters2_dictionary_min2000')
    dictionary_output.write(str(new_soup))

new_soup = BeautifulSoup("<dict></dict>", "html.parser")

word_count = sorted(Counter(word_list).items(), key=operator.itemgetter(1), reverse=True)[:200]

frequency_sum = 0
for word, frequency in word_count:
    frequency_sum += frequency
    new_tag = new_soup.new_tag("word")
    new_tag.string = word
    new_tag['frequency'] = frequency
    for place_name, words in VALID_PLACES.items():
        try:
            new_tag[place_name] = words[word]
        except:
            new_tag[place_name] = 0
    new_soup.dict.append(new_tag)
new_soup.dict['frequency_sum'] = frequency_sum


with open('reuters21578/reuters2_dictionary_max2000', 'w') as dictionary_output:
    print('saving reuters2_dictionary_max2000')
    dictionary_output.write(str(new_soup))
