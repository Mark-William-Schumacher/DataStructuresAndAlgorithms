# DataStructuresAndAlgorithms
Practice for technical interviews, done in preparation for onsite interviews during winter of 2015

These are technical interview prep questions taken from Elements of a Programming interview http://elementsofprogramminginterviews.com/pdf/epi-light-1.4.10.pdf and leetcode.com 


## Local and Global Alignment and Fitting Algorithms NEW*

<img src="https://github.com/Mark-William-Schumacher/DataStructuresAndAlgorithms/blob/master/Images/Local-GlobalAlignment.PNG" height=250px > 

GlobalAndLocalAlignment.java have some tester code written to solve some small scale dynamic programming local/global alignment problems. Algorithms are O(nm) space / time. 

Example: 
Solving Global alignment for s1= "TAGATA" , s2= "GTAGGCTTAAGGTTA"

Global Alignment Matrix:
    -    -    T    A    G    A    T    A \n
    -    0   -1   -2   -3   -4   -5   -6 \n
    G   -1   -1   -2   -1   -2   -3   -4 \n
    T   -2    0   -1   -2   -2   -1   -2 \n
    A   -3   -1    1    0   -1   -2    0 \n
    G   -4   -2    0    2    1    0   -1 \n
    G   -5   -3   -1    1    1    0   -1 \n
    C   -6   -4   -2    0    0    0   -1 \n
    T   -7   -5   -3   -1   -1    1    0 \n
    T   -8   -6   -4   -2   -2    0    0 \n
    A   -9   -7   -5   -3   -1   -1    1 \n
    A  -10   -8   -6   -4   -2   -2    0 \n
    G  -11   -9   -7   -5   -3   -3   -1 \n
    G  -12  -10   -8   -6   -4   -4   -2 \n
    T  -13  -11   -9   -7   -5   -3   -3 \n
    T  -14  -12  -10   -8   -6   -4   -4 \n
    A  -15  -13  -11   -9   -7   -5   -3 \n
    
Best Fitting:  O(n*m)
Fitting is the problem of fully considering s1 for any substring in s2. 
TAGA-T-A
TAGGCTTA

Global Alignment:  O(n*m)
Both strings must be fully considered .
GTAGGCTTAAGGTTA
-TAG----A---T-A

Local Alignment: O(n*m)
Considering all substrings of s1 and s2.
TAG
TAG

Can be done in O(n) space at not cost to time with a clever solution 
