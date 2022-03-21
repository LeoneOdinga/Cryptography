import java.util.Scanner;

public class CaesarCipher
{
  static String text_to_encode = null;
  static String text_to_decode = null;
  
  final static  char [] all_small_letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
		  'o','p','q','r','s','t','u','v','w','x','y','z'};
  
  final static char [] all_capital_letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
		  'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
  
  static Scanner input_integer = new Scanner(System.in);
  static Scanner input_string = new Scanner(System.in);
  
  public static void main(String[] args)
  {
	  System.out.println("\nCaesar Cipher Algorithm: "
	  		+ "\nSelect From Below Options: "
	  		+ "\n1. Encrypt Text Using Known Key"
	  		+ "\n2. Decrypt Text Using Known Key"
	  		+ "\n3. Decrypt Text Without Key(Brute Force)");
	  System.out.println("\nSelect an option{1-3}: ");
	  int user_option =0;
	  
	  user_option = input_integer.nextInt();
	  userSelectionArea(user_option);
  }
  
  public static String encryptText(String message, int key)
  { 
	 StringBuilder encrypted_text = new StringBuilder();
	 
	 for(int i=0; i<message.length(); i++)
	 {
		 char current_letter = message.charAt(i);
		 
		 if(isCapitalLetter(current_letter))
		 {
			encrypted_text.append(encryptCapitalLetter(current_letter, key));
		 }
		 else if(isSmallLetter(current_letter))
		 {
			 encrypted_text.append(encryptSmallLetter(current_letter, key));
		 }
		 else
		 {
			 encrypted_text.append(current_letter);
		 }
		 
	 }
	 return encrypted_text.toString();
  }
  
  public static String decryptText(String encrypted_message, int key)
  {
		 StringBuilder decrypted_text = new StringBuilder();
		 
		 for(int i=0; i<encrypted_message.length(); i++)
		 {
			 char current_letter = encrypted_message.charAt(i);
			 
			 if(isCapitalLetter(current_letter))
			 {
				decrypted_text.append(decryptCapitalLetter(current_letter, key));
			 }
			 else if(isSmallLetter(current_letter))
			 {
				 decrypted_text.append(decryptSmallLetter(current_letter, key));
			 }
			 else
			 {
				 decrypted_text.append(current_letter);
			 }
			 
		 }
		 return decrypted_text.toString();
  }
  
  public static boolean isCapitalLetter(char current_letter)
  {
	 return current_letter >= 'A' && current_letter<='Z';
  }
  
  public static boolean isSmallLetter(char current_letter)
  {
	 return current_letter >= 'a' && current_letter<='z';
  }
  
  public static char encryptCapitalLetter(char current_letter, int key)
  { 
	  char encrypted_capital_letter = 0;
	  if(isCapitalLetter(current_letter))
	  {
		  int current_letter_position = linearSearch(all_capital_letters, current_letter);
		  int encrypted_letter_position  = (current_letter_position + key) % 26;
		  encrypted_capital_letter = all_capital_letters[encrypted_letter_position];
	  }
	  return encrypted_capital_letter;
  }
  
  public static char encryptSmallLetter(char current_letter, int key)
  {
	  char [] all_small_letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
			  'o','p','q','r','s','t','u','v','w','x','y','z'};
	  
	  char encrypted_small_letter = 0;
	  if(isSmallLetter(current_letter))
	  {
		  int current_letter_position = linearSearch(all_small_letters, current_letter);
		  int encrypted_letter_position  = (current_letter_position + key) % 26;
		  encrypted_small_letter = all_small_letters[encrypted_letter_position];
	  }
	  return encrypted_small_letter;
  }
  
  public static char decryptCapitalLetter(char current_letter, int key)
  {
	  char [] all_capital_letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
			  'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	  
	  char decrypted_capital_letter = 0;
	  if(isCapitalLetter(current_letter))
	  {
		  int current_letter_position = linearSearch(all_capital_letters, current_letter);
		  int pos = current_letter_position - key;
		  if(pos < 0)
			  pos = 26 - Math.abs(pos);
		  int decrypted_letter_position  = (pos) % 26;
		  decrypted_capital_letter = all_capital_letters[decrypted_letter_position];
	  }
	  return decrypted_capital_letter;
  }
  
  public static char decryptSmallLetter(char current_letter, int key)
  {
	  char decrypted_small_letter = 0;
	  if(isSmallLetter(current_letter))
	  {
		  int current_letter_position = linearSearch(all_small_letters, current_letter);
		  int pos = current_letter_position - key;
		  if(pos < 0)
			  pos = 26 - Math.abs(pos);
		  int decrypted_letter_position  = (pos) % 26;
		  decrypted_small_letter = all_small_letters[decrypted_letter_position];
	  }
	  return decrypted_small_letter;
  }
  
  public static String [] bruteForceTheKey (String encoded_message)
  {
	  String [] all_possibilities = new String[26];
	  
	  for(int i=0; i<all_possibilities.length; ++i)
	  {
		  all_possibilities[i] = decryptText(encoded_message,i);
	  }
	  return all_possibilities;
  }
 
  public static void printAllPossibleSolutions(String encoded_message)
  {
	  String [] possible_combinations = bruteForceTheKey(encoded_message);
	  
	  for(int i =0; i<possible_combinations.length; i++)
	  {
		  System.out.println("\nKey = "+i+"\nDecrypted Text = "+possible_combinations[i]);
	  }
  }
  public static int linearSearch(char[] array, char target_value)
  {
	  for(int i =0; i<array.length; i++)
	  {
		  if(array[i] == target_value)
		  {
			  return i;
		  }
	  }
	  return -1;
  }
  public static void userSelectionArea(int user_option)
  {
	  switch(user_option)
	  {
	     case 1:
	    	 System.out.println("Enter The Plain Text To Encrypt: ");
	    	 text_to_encode = input_string.nextLine();
	    	 System.out.println("Enter The Key Value/ Shifting Value {0-25}: ");
	    	 int key = input_integer.nextInt();
	    	 System.out.println("Encrypted Plain Text(Ciphertext) Using Key "+key+" is:"
	    	 		+ " "+encryptText(text_to_encode,key));
	    	 break;
	    	 
	     case 2:
	    	 System.out.println("Enter The Cipher Text To Decrypt: ");
	    	 text_to_decode = input_string.nextLine();
	    	 System.out.println("Enter The Key Value/ Shifting Value {0-25}: ");
	    	 key = input_integer.nextInt();
	    	 System.out.println("The Plain Text From Decryption is: "+decryptText(text_to_decode,key));
	    	 break;
	    	 
	     case 3:
	    	 System.out.println("Brute Force All The Possible Keys{Hint: choose the one that makes sense}");
	    	 System.out.println("Enter the Cipher Text That You Want To Brute-Force: ");
	    	 text_to_decode = input_string.nextLine();
	    	 printAllPossibleSolutions(text_to_decode);
	    	 break;
	  }
  }
}
