# Sentiment Analysis of Amazon Reviews
Based on the review text from Amazon, predict the how buyers feel about the things they bought.
Namely, classify the review text to 5 classes, from one-star to five-star.

0. before running this program, you need to install a tool, Mallet, on your C:\\ directory. You can download the Mallet here.
http://mallet.cs.umass.edu/download.php
1. before running this program, you need to configure the variable, inputJson, in mainclass.java. 
2. before running this program, you need to insert the weka.jar and json-simple-1.1.jar into this program. 
3. This program can work on Json data set as given, Musical_instruments_5.json. You can also download data like this from amazon website. 
4. you can choose either Tf-Idf model or LDA model to process this data set.
5. This program's entry in in MainClass.java.

Attention: Tf-Idf model may need up to 10 GB memories and over 30 minutes for the given json data set. You can just use part of the data to test this program. Using LDA model may be less time consuming. 
Attention: This code can run successfully on Windows 2012 R2 with JAVA 8. Because it will use windows batch files to manipulate the Mallet.
