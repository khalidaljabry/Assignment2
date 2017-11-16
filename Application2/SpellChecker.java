
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.objects.NativeArray;
import jdk.nashorn.internal.runtime.JSType;

public class SpellChecker {

   /**
    * This method takes in a filename and spells check it	
    * @param filename an input file stream (i.e. bonk.txt)	
    * @return ArrayList<String> list of words not found in dictionary 
    */
    public ArrayList<String> spell(String filename) {
        //Arraylist object to hold the list of words not found in dictionary
        ArrayList<String> misspelledWordList=new ArrayList<String>();
        List<String> dictionaryWordList = new ArrayList<String>();
        
	//try catch block to handle exception if something gone wrong with file opening
        try	
        {
            //file reader to read file 
            FileReader _filereader=new FileReader(filename);
            //file reader to read dictionary file
            FileReader _dicFileReader=new FileReader("dictionary.txt");
            
            //buffered reader to reader stream of data from file open with file reader
            BufferedReader bufferedreader=new BufferedReader(_filereader);
            //buffered reader to reader stream of data from dictionary file open with file reader
            BufferedReader bufferedreaderDic=new BufferedReader(_dicFileReader);
            
            //Read all the list of words from the dictionary and store to the string array
            String single_word=null;
                    
            while((single_word=bufferedreaderDic.readLine())!=null)
            {
                dictionaryWordList.add(single_word);
            }
            bufferedreaderDic.close();
            
            String Single_Line=null;	//to store individual single Line from the text file
	
            while((Single_Line=bufferedreader.readLine())!=null)	//loops executes until line exists in the file
            {
               // Single_Line.replaceAll("\\D", "");
                Single_Line = Single_Line.replace("."," ").replace(",", " ").replace("("," ").replace(")", " ")
                        .replace("-"," ").replace("\"", " ").replace("?"," ").replace(":"," ").replaceAll("[0-9]","");
                
                String wordlist[]=Single_Line.split(" ");
                
                for(String word : wordlist)
                {
                    //check if the word is not in the dictionary 
                    if(!inDictionary(word.trim(),dictionaryWordList.toArray(new String[0])))
                    {
                        misspelledWordList.add(word);
                    }
                }
            }
            
            //closing files
            bufferedreader.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        
        return misspelledWordList;
    }

   /**
    * This method takes the list of words not found in the dictionary	
    * and prints a list of non-duplicate words
    * @param ArrayList<String> List of words not found in dictionary	
    */
    public void printNonDuplicates(ArrayList<String> misspelledword) {
	
        //flag variable 
        boolean isFound=false;
        
        //List to store the unique words
        List<String> uniqueWordList=new ArrayList<String>();
        
        for(String word : misspelledword)
        {
            //reset the flag
            isFound=false;
            //check if the word is already present in the unique word list
            for(String uniqueword : uniqueWordList)
            {
                //check if word matches
                if(uniqueword.equals(word))
                {
                    isFound=true;
                    break;
                }
            }
            
            //check if word was not found then add to list of unique words
            if(!isFound)
            {
                uniqueWordList.add(word);
            }
        }
        
        //displaying the list of unique non duplicate missspelled words
        for(String words : uniqueWordList)
        {
            System.out.println("\t"+words);
        }
    }
   
   /**
    * This method returns true if a word in input file stream	
    * (i.e. bonk.txt) is in the dictionary, false otherwise.	
    * @param word a string that contains a word to be checked	
    * @return the boolean status of the word (i.e. true or false)	
    */
    public boolean inDictionary(String word, String[] dictionary) {
	
        //looping through every word in the dictionary to check if the word is correct
        for(String dicWord : dictionary)
        {
            //chec if a word 
            if(word.length()>1 )
            {
                //check if word is present
                if(word.toLowerCase().equals(dicWord.toLowerCase()))
                {
                    return true;
                }
            }
            else if(word.length()<=1 )
            {
                return true;
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
      
      // Prompt the user to enter a file to be spell checked
      SpellChecker sc = new SpellChecker();
            
      //displaying the message to the screen
      System.out.println("Reading input file: "+args[0]);
      
      //ArrayList class object to hold the list of the words that are misspelled
      ArrayList<String> misspelledWordList=sc.spell(args[0]);
      
      //check if the list is not empty
      if(misspelledWordList.size()>0)
      {
          //calling printmethod to display the non-duplicate words
          sc.printNonDuplicates(misspelledWordList);
      }
      
    }
}
