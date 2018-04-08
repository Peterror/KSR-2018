from bs4 import BeautifulSoup

VALID_PLACES = ['west-germany', 'usa', 'france', 'uk', 'canada', 'japan']


def is_reuter_valid(reuter):
    if reuter.find('text').find('body') is None:
        return False
    try:
        for place in reuter.places.find_all('d'):
            if place.text in VALID_PLACES:
                return True
    except TypeError:  # NoneType
        pass
    return False


def valid_places(places):
    valid_places = new_soup.new_tag('places')
    for place in places.find_all('d'):
        if place.text in VALID_PLACES:
            valid_places.append(place)
    return valid_places


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
            new_tag_places = valid_places(reuter.places)
            new_tag_body = new_soup.new_tag("body")

            new_tag_body.append(reuter.find('text').find('body').text)

            new_tag_reuters.append(new_tag_places)
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

with open('reuters21578/reuters_test', 'w') as reuters_output:
    reuters_output.write(str(test_soup))

with open('reuters21578/reuters_train', 'w') as reuters_output:
    reuters_output.write(str(train_soup))
