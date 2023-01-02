class TopSecretEncoder
{
  //Main method that holds the main menu and some variables that will be needed in other methods. This method allows for the code to be repeated until the user enters 0.
  public static void main(String[] args)
  {
    
    char[] nums = {'0','1','2'};
    char[] original = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    String a = " ";
    
    //while loop that runs until 0 is entered
    while(a.charAt(0) != nums[0])
    {
      
      //do loop that runs to ensure that an invalid input is not entered. Only when true input is entered, this loop will be escaped
      do
      {
        
        //main menu. store and output user's input
        System.out.println("Enter an option: \n\n"
                             + "(1) to encrypt a word \n\n"
                             + "(2) to decrypt a word \n\n"
                             + "(0) to exit \n");
        a = In.getString();
        System.out.println("Option: " + a);
        
        //if statement to make sure nothing happens if the user enters 0. if 0 is not inputed, run encrpytion / decryption method
        if (a.charAt(0) != nums[0])
        {
          
          if (a.charAt(0) == nums[1] || a.charAt(0) == nums[2])
          {
            //method call to start encryption or decryption process
            cryption(a, original, nums);
          }
          
          else
          {
            System.out.print("Invalid input. ");
          }
          
        }
        
      }
      //method call to ensure that valid input is entered
      while(menuValid(a, nums) == false);
      
    }
  }
  
  
  
  //Method that will encrpyt or decrpyt user's input. Many other method calls are made here to successfully return valid output to the user. Parameters are the 
  //main method input from the user, and two char arrays that will help in other methods that will be called from this method.
  public static void cryption(String a, char[] original, char[] nums)
  {
    
    //Initialize and declare variables that will be used in the loops
    String userIn = "";
    String key = "";
    
    //If user input was 1, then get encryption data from user
    if (a.charAt(0) == nums[1])
    {
      
      System.out.println("Enter an unencrypted word: ");
      userIn = In.getString();
      
      //Method call to authorize user input. Use while loop to keep going until valid input is given.
      while (stringValid(original,userIn) == false)
      {
        System.out.println("Invalid Input. Enter an unencrypted word: ");
        userIn = In.getString();
      }
      
      System.out.println("Enter your encryption key: ");
      key = In.getString();
      
      //Method call to authorize user input. Use while loop to keep going until valid input is given.
      while (stringValid(original,key) == false)
      {
        System.out.println("Invalid input. Enter your encryption key: ");
        key = In.getString();
      }
    }
    
    //If user input was 2, then get decryption data from user
    else if (a.charAt(0) == nums[2])
    {
      
      System.out.println("Enter an encrypted word: ");
      userIn = In.getString();
      
      //Method call to authorize user input. Use while loop to keep going until valid input is given.
      while (stringValid(original,userIn) == false)
      {
        System.out.println("Invalid Input. Enter an encrypted word: ");
        userIn = In.getString();
      }
      
      System.out.println("Enter your decryption key: ");
      key = In.getString();
      
      //Method call to authorize user input. Use while loop to keep going until valid input is given.
      while (stringValid(original,key) == false)
      {
        System.out.println("Invalid input. Enter your decryption key: ");
        key = In.getString();
      }
    }
    
    //use newArr for better organization (method call used here to save returning method data in char array
    //then if statement for proper output with method call to encrypt or decrypt word and output it to user.
    char[] newArr = shiftedArray(original, shift(key, original), a);
    
    if (a.charAt(0) == '1')
    {
      System.out.println("\nEncoded Word: " + cryptedName(newArr, userIn)+ "\n");
    }
    
    else if (a.charAt(0) == '2')
    {
      System.out.println("\nDecoded Word: " + cryptedName(newArr, userIn)+ "\n");
    }
    
  }
  
  
  //method to make sure that user's input is valid in the main menu (when entering 1, 2 or 0). 
  //If it is invalid, false is returned and the loop returns here to once again make sure the input is valid.
  //Parameters: String b is the user's input and nums is the char array that is used for comparing user's input to valid input
  public static boolean menuValid(String b, char[] nums)
  {
    
    //If the length of the input is more than 1, then it automatically should return false
    if (b.length() > 1)
    {
      return false;
    }
    
    //Input is only true if 1, 2, or 0 is entered
    else if (b.charAt(0) == nums[1] || b.charAt(0) == nums[2] || b.charAt(0) == nums[0])
    {
      return true;
    }
    
    //Everything else will return false
    else
    {
      return false;
    }
  }
  
  
  
  //method to make sure that user's input is valid in the encryption and decryption process. 
  //If they enter illegal words with anything other than letters, false will be returned and the user will once again be prompted for true input
  //Parameters: String input is the user's input, while original is the alphabet array
  public static boolean stringValid(char[] original, String input)
  {
    
    int count = 0;
    //nested for loops to compare every index of the string to every index of the array, making sure only letters are inputted
    for (int i = 0; i < input.length(); i++)
    {
      
      for (int j = 0; j < original.length; j++)
      {
        
        if (input.charAt(i) == original[j])
        {
          count++;
        }
        
      }
      
    }
    
    //if the count is equal to the input length (entire length consists of only letters), return truel. if not, invalid input
    if (count == input.length())
    {
      return true;
    }
    
    else
    {
      return false;
    }
    
  }
  
  //Method to get the number of shifts in the alphabet so that a new array can be generated in the future when we are actually returning the encrypted or decrypted word.
  //Parameters: z is the key and original is the alphabet
  //Returns s%26, meaning the amount of times to shift the index to the right by 1 for encryption.
  public static int shift(String z, char[] original)
  {
    
    int s = 0;
    
    //Use nested for loop to compare the index number in the alphabet to the letters. ex. abc = 1+2+3 = 6.
    for (int i = 0; i < z.length(); i++)
    {
      
      for (int j = 0; j < original.length; j++)
      {
        
        if (z.charAt(i) == original[j])
        {
          s+= j+1;
        }
        
      }
      
    }
    
    //Use modulo division to make the shifting process efficient
    return s%26;
  }
  
  
  
  
  //Method to shift the array by n times, which is given to us by the shift method. 
  //Parameters are the original, which is the alphabet array, s which is the shift amount and a which is the main menu selection. This is used for an if statement
  //Returns shifted array, which will be used in the future for encrypting and decrypting
  public static char[] shiftedArray(char[] original, int s, String a)
  {
    
    //Very important if statement which removes the need for 2 separate methods for shifting the array. 
    //Only have to use 1 and by minusing s by 26, we move the alphabet to the right enough to make it seem like it moved left
    if (a.charAt(0) == '2')
    {
      s = 26-s;
    }
    
    //Declare and initialize new array using for loop that will make every index the same between the original alphabet and the new array
    char[] shiftedOriginal = new char [original.length];
    
    for(int i = 0; i < original.length; i++) 
    {
      shiftedOriginal[i] = original[i];
    }
    
    //nested for loop that actually moves the new array to the right by using the end variable that will change the first index of the array 
    for (int i = 0; i < s; i++)
    {
      
      char end = shiftedOriginal[original.length-1];
      
      for (int j = original.length-1; j > 0; j--)
      {
        
        shiftedOriginal[j] = shiftedOriginal[j-1];
      }
      
      shiftedOriginal[0] = end;
    }
    
    return shiftedOriginal;
  }
  
  //Method for encrypting / decrypting the word that is inputted by the user. 
  //Parameters: shiftedArray, which is the new array that has been modified and b, which is the user's word
  //Returns the encrpyted or decrypting word that the user entered
  public static String cryptedName(char[] shiftedArray, String b)
  {
    
    //temporary variable allows us to give us an index on the shiftedArray, which is then saved in the varaible userEncrypted. Ascii is used to easily do this.
    int temp = 0;
    String userEncrypted = "";
    
    for (int i = 0; i < b.length(); i++)
    {
      temp = b.charAt(i) - 96;
      userEncrypted += shiftedArray[temp-1];
    }
    
    return userEncrypted;
  }
  
  
}





































