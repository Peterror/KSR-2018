import operator
from collections import Counter
from bs4 import BeautifulSoup
import re

with open("reuters21578/reuters_train") as fp:
    print("Opened reuters21578/reuters_train")
    soup = BeautifulSoup(fp, "html.parser")

word_list = []

#  trans_table = str.maketrans("", "", string.punctuation+string.digits)
r = re.compile(r"[^a-zA-Z' ']")  # REGEX do wyciagania wszystkich znakow niechcianych(+ cyfr)

for text in soup.find("document").find_all("body"):
    word_list += r.sub('', text.text.lower()).split()  # lista slow wystepujacych w tekscie

word_count = sorted(Counter(word_list).items(), key=operator.itemgetter(1), reverse=True)[:2000]
del word_list

new_soup = BeautifulSoup("<dict></dict>", "html.parser")

frequency_sum = 0
for word, frequency in word_count:
    frequency_sum += frequency
    new_tag = new_soup.new_tag("word")
    new_tag.string = word
    new_tag['frequency'] = frequency
    new_soup.dict.append(new_tag)
new_soup.dict['frequency_sum'] = frequency_sum

with open('reuters21578/reuters_dictionary', 'w') as dictionary_output:
    print('saving reuters_dictionary')
    dictionary_output.write(new_soup.prettify())
