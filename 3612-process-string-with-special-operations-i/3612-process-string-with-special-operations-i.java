class Solution {
    public String processStr(String s) {
        String str= "";
        for(char ch : s.toCharArray()){
            if(ch=='*'){
                if (!str.isEmpty()) {
                    str = remove(str);
                }
            }else if(ch=='#'){
                str=str+str;
            }else if(ch=='%'){
                str= new StringBuilder(str).reverse().toString();
            }
        else{
            str=str+ch;
        }
        }
        return str;
    }
    public String remove(String s){
        String r=s.substring(0,s.length()-1);
        return r;
    }
}