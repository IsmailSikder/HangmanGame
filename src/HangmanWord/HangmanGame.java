package HangmanWord;
/********
 * Purpose of this program is to make a hangman game
 * The program will ask a player to guess word from a random word generator
 * User will have six guesses(wrong or correct)
 * in the end the program will show correct word to the player.
 * Author: Mohammed Ismail Sikder
 * ********/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
	//Initializing all the necessary variable
	private static Scanner userInput = new Scanner(System.in);
	private static String[] words = null;
	private static String randWords="";
	private static String spaceBar="";
	private static int count;
	private static int countCorrect = 0;
	private static int countWrong=0;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Path to hangmanword file to read
		String inputFile="./HangmanWord.txt";
		readHangmanWord(inputFile);
		determineToPlay();
	}
	
	//read hangman word files and get the word to an array of String
	public static void readHangmanWord(String inputFile) throws FileNotFoundException{
		File file = new File(inputFile);
		Scanner fileScan =new Scanner(file);
	
		
		
		while(fileScan.hasNextLine()){
			String line = fileScan.nextLine();
			words = line.split(",");
		}	
	    
		
		fileScan.close();
	}
	
	//Asking user whether they wants to play game or not
	public static void determineToPlay(){
		
		String userAnswer;
		do{
			setToNull();
			System.out.print("Type Yes to play or No to exit the game: ");
			userAnswer = userInput.nextLine();
			userAnswer= userAnswer.toUpperCase();
			
			//Let the user determine whether they wants to play or not
			switch(userAnswer){
			case "YES":
				//System.out.println(words[0]);
				System.out.println("Yaaa let's do it");
				playGame();
				
				break;
			case "NO":
				System.out.println("Exiting game!");
				break;
			default:
				System.out.println("Wrong answer");
				break;
			}
			
		}while(!userAnswer.equals("NO"));
	}

	//If player says yes this method will implement.
	//This method will select a random string and show the spaces as many the char
	//The a loop for user guessing input char
	private static void playGame() {
		System.out.println("Your job is to guess the word");
		Random rand = new Random();
		
		//create randonm number (1-10)
		//String spaceBar="";
		int randIndex = rand.nextInt(10)+1;
		randWords = words[randIndex].toLowerCase();
		System.out.println(randWords);
	
		for(int i=0;i<randWords.length();i++){
			spaceBar=spaceBar+"-";
			
		}
		//Display space for the word
		System.out.println(spaceBar);
		
		//Convert Space bar to char Array of spaces
		char[] newSpaceBar =spaceBar.toCharArray();
		
		do{
			System.out.print("Guess a char: ");
			
			String guessChar = userInput.next();
			guessChar=guessChar.toLowerCase();
			
			//Random select string to char array
			char[] randWordArray = randWords.toCharArray();
			
			//User guessed string to char Array
			char[] guessCharArray =guessChar.toCharArray();
			
			//Call the method upon guessing
			onGuessing(newSpaceBar,guessCharArray,randWordArray);
		
		}while(count<6);

				
	}

	//This method implement whether the user guessed correct or not
	private static void onGuessing(char[] newSpaceBar,char[] guessCharArray,char[] randWordArray) {
		
		//this array will hold spaces for temporarily
		char[] tempSpaceBar = spaceBar.toCharArray();
		
		//If it is a correct guess this loop will add it the char array
		for(int i=0;i<randWordArray.length;i++){
			if(randWordArray[i]==guessCharArray[0]){
				newSpaceBar[i]=guessCharArray[0];
				
			}	
		}
		
		//if wrong guess or else
		if(Arrays.equals(newSpaceBar, tempSpaceBar)){
			System.out.println(spaceBar);
			countWrong++;
			hangmanImage(countWrong);
		}
		else{
			countCorrect++;
			System.out.println("Correct Guess");
			System.out.println(newSpaceBar);
			
			if(countCorrect==6){
				System.out.println("You have six correct guesses");
				System.out.print("The word is "+randWords+"\n");
				count = countCorrect;
			}
			else
				spaceBar = new String(newSpaceBar);
		}
			
	}
	
	//Shows hangman image as the user guess a wrong word or used already
	private static void hangmanImage(int countWrong) {
		if(countWrong==1){
			System.out.println("Wrong guess "+countWrong);
			System.out.println(" |---------");
			System.out.println("(0)");
		}
		else if(countWrong==2){
			System.out.println("Wrong guess "+countWrong);
			System.out.println(" |---------");
			System.out.println("(0)");
			System.out.println(" |");
		}
		else if(countWrong==3){
			System.out.println("Wrong guess "+countWrong);
			System.out.println(" |---------");
			System.out.println("(0)");
			System.out.println("/|");
		}
		else if(countWrong==4){
			System.out.println("Wrong guess "+countWrong);
			System.out.println(" |---------");
			System.out.println("(0)");
			System.out.println("/|\'");
		}
		else if(countWrong==5){
			System.out.println("Wrong guess "+countWrong);
			System.out.println(" |---------");
			System.out.println("(0)");
			System.out.println("/|\'");
			System.out.println("/");
		}
		else if(countWrong==6){
			System.out.println("Wrong guess "+countWrong);
			System.out.println(" |---------");
			System.out.println("(0)");
			System.out.println("/|\'");
			System.out.println("/\'");
			count=countWrong;
		}
		
	}
	
	//Reset all the variable to start the game from the beginning 
	private static void setToNull(){
		  randWords="";
		  spaceBar="";
		  count=0;
		countCorrect = 0;
		 countWrong=0;
	}
}
