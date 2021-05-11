import requests
from bs4 import BeautifulSoup

URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=394510&date=09-05-2021"
r = requests.get(URL)

soup = BeautifulSoup(r.content, 'html5lib') # If this line causes an error, run 'pip install html5lib' or install html5lib
print(soup.prettify())