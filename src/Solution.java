import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TrieNode {
    public TrieNode[] children = new TrieNode[30];
    public String item = "";
    public boolean isLeaf;
    
    // Initialize your data structure here.
    public TrieNode() {
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
        	if(c == '-') c = 'z' + 1;
        	if(c < 'a') c+=32;
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.item = word;
        node.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public TrieNode search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
        	if(c == '-') c = 'z' + 1;
        	if(c < 'a') c+=32;
            if (node.children[c - 'a'] == null) return null;
            node = node.children[c - 'a'];
        }
        return node;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return true;
    }
}

public class Solution {
	
	public static void main(String[] args) {
		
		Solution s = new Solution();
		
		Trie trie = s.buildTrie("https://raw.githubusercontent.com/lad/words/master/words");
//		Trie trie = s.buildTrie("/usr/share/dict/words");
		
		List<Set<Character>> l = s.readCases("src/cases.txt");
		int testId = 1;
		for (Set<Character> set : l) {
			List<String> res = s.search(set , trie);
			System.out.println("Result for case"+testId+": "+res);
			testId++;
		}
	}

	private List<Set<Character>> readCases(String filePath) {
		// TODO Auto-generated method stub
		List<Set<Character>> res = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			String line = reader.readLine();
			while(line != null) {
//				System.out.println(line);
				Set<Character> set = new HashSet<>();
				String[] cs = line.split(":");
				if(cs.length == 1) {
					System.err.println(cs[0]+" : input can not be empty");
					return res;
				}
				String[] input = cs[1].split(" +");
				for (String s : input) {
					if(isValid(s)) set.add(s.charAt(0));
					else return res;
				}
				res.add(set);
				// read next line
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.err.println("File doesn't exsit");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Empty file");
			e.printStackTrace();
		}
		return res;
	}

	private boolean isValid(String s) {
		// TODO Auto-generated method stub
		if(s.length() > 1) {
			System.err.println(s + " is not a character!");
			return false;
		}
		if(s.charAt(0) <= 'z' && s.charAt(0) >= 'A' || s.charAt(0) == '-') return true;
		System.err.println("Your input "+ s + " is illegal!");
		return false;
	}

	public List<String> search(Set<Character> set, Trie trie){
		List<String> res = new ArrayList<>();
		Character[] input = set.toArray(new Character[set.size()]);
//		System.out.println(Arrays.toString(input));
		backtrack(res, new StringBuilder(), input, trie);
		return res;
	}
	
	private void backtrack(List<String> res, StringBuilder sb, Character[] input, Trie trie) {
		// TODO Auto-generated method stub
		TrieNode node = trie.search(sb.toString());
		if(node != null) {
			if(node.isLeaf) res.add(sb.toString().toLowerCase());
			for (int i = 0; i < input.length; i++) {
				sb.append(input[i]);
				backtrack(res, sb, input, trie);	
				sb.deleteCharAt(sb.length()-1);
			}
		}
	}

	private Trie buildTrie(String filePath){
		Trie trie = new Trie();
		BufferedReader reader;
		try {
//			reader = new BufferedReader(new FileReader(new File(filePath)));
			URL url = new URL(filePath);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line = reader.readLine();
			while(line != null) {
//				System.out.println(line);
				trie.insert(line);
				// read next line
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File doesn't exsit");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Empty file");
			e.printStackTrace();
		}
		return trie;
	}
	
}
