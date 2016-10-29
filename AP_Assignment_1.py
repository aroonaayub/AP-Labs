import json
import string
import sys
import pprint 

words = {}
wordList = []
new = []
with open('dictionary.json','r', encoding='utf-8',errors='ignore') as data:   #reading file
 d=json.load(data)                        #loading dictionary in d
words = list(d.keys())                    #extacting keys/words from dictionary
#print(len(words))

num1 = input("Enter source word: " )      #getting user source input
num2 = input("Enter target word: " )      #getting user target input


if(len(num1) == len(num2)):               #check if the length of both words entered is same.

 for i in range(0,len(words)):            #extracting words of specific/same length
  if (len(words[i]) ==  len(num1)):
   wordList.append(words[i])              #appending the words in a list
 #print(wordList)


 num_1=[]
 num_2=[]
 num_1 = list(num1)                       #converting words to character array
 num_2 = list(num2)

 for i in range(0,len(num_1)):
  num_1=[]
  num_2=[]
  num_1 = list(num1)                       #converting words to character array
  num_2 = list(num2)            
  num_1[i] = num_2[i]                     #changing word's each character to target word's character
  #print(num_1)
  for j in range(0,len(words)):
   num_1 = ''.join(num_1)
   if(num_1 == words[j]):                 #checking each word with changed character in dictionary
    print("go on")
  print(''.join(num_1)) 

else:
 print("word length doesn't match!")
 sys.exit()

#hamming distance!                        #since everyone was talking about the hamming distance, here's one way of finding it!
#count = 0
#for i in range(0, len(num1)):
# if num1[i] != num2[i]:
#  count = count + 1


