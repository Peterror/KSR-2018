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


new_soup = BeautifulSoup("<document></document>", "html.parser")

for reuters_number_str in [str(i).zfill(3) for i in range(22)]:
    with open("reuters21578/reut2-%s.sgm" % reuters_number_str) as fp:
        print("Opened reuters21578/reut2-%s.sgm" % reuters_number_str)
        soup = BeautifulSoup(fp, "html.parser")
    reuters = soup.find_all('reuters')

    for reuter in reuters:
        if is_reuter_valid(reuter):
            new_tag_reuters = new_soup.new_tag("reuters")
            new_tag_places = new_soup.new_tag("places")
            new_tag_body = new_soup.new_tag("body")

            new_tag_places.append(reuter.places)
            new_tag_body.append(reuter.find('text').find('body').text)

            new_tag_reuters.append(new_tag_places)
            new_tag_reuters.append(new_tag_body)
            new_soup.document.append(new_tag_reuters)

with open('reuters21578/reuters', 'w') as reuters_output:
    reuters_output.write(new_soup.prettify())
