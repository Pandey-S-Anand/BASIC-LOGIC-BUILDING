import java.util.HashMap;
import java.util.Map;

//Naive Approach

class SolutionNaive {
    public String capitalizeTitle(String title) {
      StringBuilder result=new StringBuilder();

      for(String str:title.toLowerCase().split(" ")){
        int n=str.length();
        if(n<=2){
          result.append(str);
        }else{
          result.append(Character.toUpperCase(str.charAt(0)));
          result.append(str.substring(1,n));
        }
        result.append(" ");
      }

      return result.toString().trim();
    }
}

//Optimal Approach

class SolutionOptimal {
    public String capitalizeTitle(String title) {
        char[]arr = title.toLowerCase().toCharArray();
        int start=0;

        for( int i = 0 ; i < arr.length ; i++){
            if(arr[i]==' '){
              if((i-start)>2){
                 arr[start]=Character.toUpperCase(arr[start]);
              }
              start=i+1;
            }
        }

        if((arr.length-start)>2){
           arr[start]=Character.toUpperCase(arr[start]);
        }

      return new String(arr);
    }
}


//Using Recursion

//Method 1

class SolutionRecursion1 {
    public String capitalizeTitle(String title) {
        return capitalizeTitleHelper(title.toLowerCase().toCharArray(), 0);
    }

    private String capitalizeTitleHelper(char[] arr, int index) {
        if (index >= arr.length) {
            return new String(arr);
        }

        int start = index;
        while (index < arr.length && arr[index] != ' ') {
            index++;
        }

        if (index - start > 2) {
            arr[start] = Character.toUpperCase(arr[start]);
        }

        return capitalizeTitleHelper(arr, index + 1);
    }
}


//Method 2

class SolutionRecursion2 {
    public String capitalizeTitle(String title) {
       return capitalizeTitle(title,0);
    }

    public String capitalizeTitle(String str,int index) {
        if(index==str.length()){
          return "";
        }

        int start=index;
        while(index<str.length() && str.charAt(index)!=' '){
           index++;
        }

        String word=str.substring(start,index);
        String transformedWord;

        if((index-start)<=2){
            transformedWord=word.toLowerCase();
        }else{
            transformedWord= Character.toUpperCase(word.charAt(0))+ word.substring(1).toLowerCase();
        }

        if(index==str.length()){
           return transformedWord;
        }else{
           return transformedWord+" "+capitalizeTitle(str,index+1);
        }
    }
}

// Optimized firstUniqChar method

public class _305CapitalizeTheTitle {
    public int firstUniqChar(String str) {
        Map<Character, Integer> charCount = new HashMap<>();

        // First pass: count the frequency of each character
        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Second pass: find the first unique character
        for (int i = 0; i < str.length(); i++) {
            if (charCount.get(str.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    // Main method for testing
    public static void main(String[] args) {
        _305CapitalizeTheTitle solution = new _305CapitalizeTheTitle();
        String testStr = "leetcode";
        int result = solution.firstUniqChar(testStr);
        System.out.println("The index of the first unique character is: " + result);
    }
}
