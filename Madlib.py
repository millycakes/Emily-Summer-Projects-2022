from bs4 import BeautifulSoup
import requests
import random

def Madlib():
    search = input("What would you like your Blanks game to be about?")
    word = "_".join(search.split())
    link = 'http://en.wikipedia.org/wiki/'+word
    output = requests.get(link).text
    soup = BeautifulSoup(output,'html.parser')
    paragraph = soup.find_all('p')[1].text
    words = paragraph.split(' ')
    my_dict ={}
    for i in range(10):
        index = random.randint(0,len(words)-1)
        while index in list(my_dict.keys()) or words[index][-1] == "]":
            index = random.randint(0,len(words)-1)
        my_dict[index] = input("Choose a category for the word: "+words[index]+"\n****************************\nPossible categories include:\nperson name, organization name, place name, other name, singular noun,\nplural noun, past tense verb, -ing verb, -s verb, simple verb,\nadjective, adverb, Your Choice:")        
    replaced_words={}
    for k,v in my_dict.items():
        replaced_words[k]=input("Give me a word belonging to the category: "+v+" ")
    for k,v in replaced_words.items():
        words[k] = v
    new_para = " ".join(words)
    print(new_para)
    file_output=open('blank.tsv','w')
    file_output.write(new_para)
    file_output.close()
Madlib()
    
