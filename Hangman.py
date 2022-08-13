from bs4 import BeautifulSoup
import requests
import urllib.request
import random
import sys

#link = 'https://eslgrammar.org/list-of-nouns/'
#output = requests.get(link).text
#soup = BeautifulSoup(output,'html.parser')
#for row in soup.find('table').tbody.find_all('tr'):
 #  print(row.find_all('td').text)

def picture (num):
    if (num==0):
        print(' _____\n |\n |\n |\n_|_')
    elif (num==1):
        print(' _____\n |  |\n |\n |\n_|_')
    elif (num==2):
        print(' _____\n |  |\n |  O\n |\n_|_')
    elif (num==3):
        print(' _____\n |  |\n |  O\n |  |\n_|_')
    elif(num==4):
        print(' _____\n |  |\n |  O\n | /|\n_|_')
    elif(num==5):
        print(' _____\n |  |\n |  O\n | /|\\\n_|_')
    elif (num==6):
        print(' _____\n |  |\n |  O\n | /|\\\n_|_/')
    elif (num==7):
        print(' _____\n |  |\n |  O\n | /|\\\n_|_/ \\')

def guessline (temp):
    for i in range(len(temp)):
        if (temp[i]=='-'):
            print('_', end =" ")
        else:
            print(temp[i], end =" ")
    print()

def hangman ():
    print('Welcome to Hangman! We hope you enjoy your experience.')
    word = 'overwatch'
    temp = ''
    guesses = 0
    wrong = 0
    for i in range(len(word)):
        temp+='-'
    while (word!=temp):
        print('Please type a lowercase letter to guess.')
        x = input()
        while (not((ord(x)>=97)and(ord(x)<=122))):
            print('Please type a lowercase letter.')
            x = input()
        guesses+=1
        if (word.find(x)==-1):
            wrong+=1
            print('The letter is not part of the word!')
            picture(wrong)
            guessline(temp)
            if(guesses==7):
                print('You ran out of guesses and lost the game! Would you like to play again? Please type "Y" or "N"')
                ans = input()
                while (ans!='Y' and ans!='N'):
                    print('Please type "Y" or "N"')
                    ans = input()
                if (ans=='Y'):
                    hangman()
                else:
                    sys.exit()

        else:
            for i in range(len(word)):
                if(word[i]==x):
                    temp=temp[:i]+x+temp[i+1:]
            print('The letter is a part of the word!')
            picture(wrong)
            guessline(temp)
            if (word==temp):
                print('Congratulations! You guessed the word and saved the man! We hope you had a pleasant stay here at Hangman HQ')

hangman()
