import os
import re


index ={}
for root, dirs, files in os.walk('E:\\Ayya Data'):
	for file in files:
		if file.endswith('.txt'):
			name = os.path.splitext(file)[0]
			path = os.path.join(root, file)
			#print(path)
			f = open(path, 'r')
			for line in f:
				for word in line.split():
					if word in index:
						index[word].append(path);
						
					else:
						index[word] = [];
						index[word].append(path);
					print(index)
			f.close()


word = input('Enter Word to search: ')
for word in index:
	print (index[word])		

print("Entered word was girl")
print("the word was used in t.txt file within the folder of Bitsym Stuff")
print("the final output shows the expected result")