from bs4 import BeautifulSoup

VALID_TOPICS = ['grain', 'acq', 'corn', 'money-fx']


def is_reuter_valid(reuter):
    valid_data_counter = 0
    if reuter.find('text').find('body') is None:
        return False
    try:
        places = reuter.topics.find_all('d')
        if len(places) < 2:
            return False
        for place in places:
            if place.text in VALID_TOPICS:
                valid_data_counter += 1
            if valid_data_counter >= 2:
                return True
    except TypeError:  # NoneType
        pass
    return False


def valid_topics(topics):
    valid_topics = new_soup.new_tag('places')
    for topic in topics.find_all('d'):
        if topic.text in VALID_TOPICS:
            valid_topics.append(topic)
    return valid_topics


new_soup = BeautifulSoup('<document set="training"></document>', "html.parser")
train_soup = BeautifulSoup('<document set="training"></document>', "html.parser")
test_soup = BeautifulSoup('<document set="testing"></document>', "html.parser")

for reuters_number_str in [str(i).zfill(3) for i in range(22)]:
    with open("reuters21578/reut2-%s.sgm" % reuters_number_str) as fp:
        print("Opened reuters21578/reut2-%s.sgm" % reuters_number_str)
        soup = BeautifulSoup(fp, "html.parser")
    reuters = soup.find_all('reuters')
    for reuter in reuters:
        if is_reuter_valid(reuter):
            new_tag_reuters = new_soup.new_tag("reuters")
            new_tag_topics = valid_topics(reuter.topics)
            new_tag_body = new_soup.new_tag("body")

            new_tag_body.append(reuter.find('text').find('body').text)

            new_tag_reuters.append(new_tag_topics)
            new_tag_reuters.append(new_tag_body)
            new_soup.document.append(new_tag_reuters)

soup_items = new_soup.document.find_all("reuters")
soup_counter = 0
for soup_item in soup_items:
    if soup_counter < (len(soup_items) * 0.6):
        train_soup.document.append(soup_item)
    else:
        test_soup.document.append(soup_item)
    soup_counter += 1

with open('reuters21578/reuters2_test', 'w') as reuters_output:
    reuters_output.write(str(test_soup))

with open('reuters21578/reuters2_train', 'w') as reuters_output:
    reuters_output.write(str(train_soup))
