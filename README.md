# DiDi
## Requirement
Input: a set of input characters, ranging from a-z, case-insensitive. 

Output: all the possible English words from the dictionary that could be composed of these characters, case-insensitive.

The possible English words could be fetched from, for example,  https://raw.githubusercontent.com/lad/words/master/words which is a copy of /usr/share/dict/words.

Ensure the main program can be run in https://coderpad.io/

## Instructions
1. Source code is in Solution.java.
2. All test cases are in cases.txt, you can modify this file by adding or deleting the cases.
3. To run the project, use command "./runme.sh".
4. We can detect illeagal input, if user inputs dublicated character(case-insensitive), project will still run correctly.
5. You'd better not try to input too many characters, cause that might lead to OOM.
6. To increase the efficiency, I build a Trie tree for the dictionary. The maximum children node number for each parent node is 30.
Cause special character such as "-" exsits.
7. I provided two ways to read the dictionary. First is using the url. Second is using the local file path. The default way is using the URL.

## How to run
git clone https://github.com/incinerate/DiDi.git

cd DiDi && ./runme.sh

## Node

If permission denied while running shell script, please try to change the permission by "chmod 777 runme.sh".
