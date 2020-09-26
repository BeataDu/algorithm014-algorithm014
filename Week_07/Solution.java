package test20200926;

import java.util.ArrayList;
import java.util.List;
//前缀树得实现
class Trie {
    private boolean is_string=false;
    private Trie next[]=new Trie[26];

    public Trie(){}

    public void insert(String word){//插入单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)root.next[w[i]-'a']=new Trie();
            root=root.next[w[i]-'a'];
        }
        root.is_string=true;
    }

    public boolean search(String word){//查找单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)return false;
            root=root.next[w[i]-'a'];
        }
        return root.is_string;
    }

    public boolean startsWith(String prefix){//查找前缀
        Trie root=this;
        char p[]=prefix.toCharArray();
        for(int i=0;i<p.length;++i){
            if(root.next[p[i]-'a']==null)return false;
            root=root.next[p[i]-'a'];
        }
        return true;
    }
}
//上面是前缀树
class Solution {
    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();
    Trie root=new Trie();

    public List<String> findWords(char[][] board, String[] words) {

        // 第一步，构建前缀树
        for (String word : words) {
            root.insert(word);
        }

        this._board = board;
        // 对于矩阵中的每一个点进行回溯
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                StringBuilder sb=new StringBuilder();//每次到一个点，就先进建一个Stringbuilder类型
                boolean[][] is_visit=new boolean[board.length][board[0].length];
                backtracking(row,col,sb,is_visit);
            }
        }
        return this._result;
    }

    private void backtracking(int row, int col,StringBuilder sb,boolean[][] is_visit) {
        //退出回溯的条件，如果row和col是在边缘
        if(row>_board.length-1||row<0||col<0||col>_board[0].length-1) return;

        //如果当前节点访问过了就return
        if(is_visit[row][col]) return;
        //如果不是边缘的话，那么sb加入当前的字符
        sb.append(_board[row][col]);
        is_visit[row][col]=true;
        //前缀树包含这个单词的话

        if(root.search(sb.toString())) {
            if(!_result.contains(sb.toString())){
                _result.add(sb.toString());
            }
        }
        //如果前缀树搜索含有这个前缀，就继续上下左右搜索
        if(root.startsWith(sb.toString())){
            backtracking(row+1,col,sb,is_visit);


            backtracking(row-1,col,sb,is_visit);



            backtracking(row,col-1,sb,is_visit);



            backtracking(row,col+1,sb,is_visit);


        }
        sb.deleteCharAt(sb.length()-1);
        is_visit[row][col]=false;
        return;




    }

    public static void main(String[] args) {
        String[] words={
                "aaa"
        };
        char[][] baord={
                {'a','a'},
        };


        System.out.println(new Solution().findWords(baord,words));


    }
}