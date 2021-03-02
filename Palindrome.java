package palindromecalc;

public class Palindrome {

	static String input = ("PUT INPUT STRING HERE").replaceAll("[^A-Za-z]+", "").toLowerCase();
    static char Direction[][] = new char[input.length()][input.length()];
    
    static void LongestPalindrome(String str)
    {
    int len = str.length();
    int i, j, k;
    int LLPS[][] = new int[len][len];
    for (i = 0; i < len; i++)
        LLPS[i][i] = 1;

    for (k=2; k<=len; k++)
    {
        for (i=0; i<len-k+1; i++)
        {
            j = i+k-1;
            if (str.charAt(i) == str.charAt(j) && k == 2) {
            LLPS[i][j] = 2;
            Direction[i][j] = 'D';
            }
            else if (str.charAt(i) == str.charAt(j)) {
            	
            LLPS[i][j] = LLPS[i+1][j-1] + 2;
            Direction[i][j] = 'D';
            }
            else if (LLPS[i+1][j] >= LLPS[i][j-1])
            {
            LLPS[i][j] =  LLPS[i+1][j];
            Direction[i][j] = 'B';
            }
            else
            {
            LLPS[i][j] =  LLPS[i][j-1];
            Direction[i][j] = 'L';
            }
        }
    }

    }
    static String GenerateLPS(int i,int j,String str)
    {
        if (i>j)
            return "";
        else if (i == j){
            return Character.toString(str.charAt(i));
        }
        else if (Direction[i][j] == 'D')
            return (Character.toString(str.charAt(i)) + GenerateLPS(i+1,j-1,str) + Character.toString(str.charAt(i)));
        else if (Direction[i][j] == 'B')
            return GenerateLPS(i+1,j,str);
        else
            return GenerateLPS(i,j-1,str);

    }




	public static void main(String args[]){
    int length = input.length();
    LongestPalindrome(input);
    System.out.println("LPS = "+GenerateLPS(0,length-1, input));




	}

}
