import operator
from collections import Counter
from bs4 import BeautifulSoup

with open("reuters21578/reuters") as fp:
    print("Opened reuters21578/reuters")
    soup = BeautifulSoup(fp, "html.parser")

word_list = []

for text in soup.find("document").find_all("body"):
    word_list += text.text.split()

word_count = sorted(Counter(word_list).items(), key=operator.itemgetter(1), reverse=True)[:2000]
del word_list

new_soup = BeautifulSoup("<dict></dict>", "html.parser")

for word, frequency in word_count:
    new_tag = new_soup.new_tag("word")
    new_tag['text'] = word
    new_tag['frequency'] = frequency
    new_soup.dict.append(new_tag)

with open('reuters21578/dictionary', 'w') as dictionary_output:
    dictionary_output.write(new_soup.prettify())
