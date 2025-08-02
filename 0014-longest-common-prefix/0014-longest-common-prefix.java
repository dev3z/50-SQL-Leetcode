class Solution {
    public String longestCommonPrefix(String[] strs) {
        int length=strs.length;
        int min=201;
        int count=0;
        for(int i=0;i<length;i++){
            min=Math.min(min,strs[i].length());
        }
        StringBuilder s=new StringBuilder();
        for(int i=0;i<min;i++){
            for(int j=1;j<length;j++){
            if(strs[j].charAt(i)!=strs[j-1].charAt(i)) return s.toString();
            }
        s.append(strs[0].charAt(i));
        }
        return s.toString();
    }
}