package hangman;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by tyler on 1/25/2017.
 */
public class Main{
    private int lengthOfWords;
    public static void main (String[] args){
        Main m = new Main();
        IEvilHangmanGame game = new EvilHangmanGame();
        Scanner scan = new Scanner(System.in);
        if(args.length > 3){System.out.println("Too many arguments, try again");return;}
        String dictionary = args[0];
        m.lengthOfWords = Integer.parseInt(args[1]);
        int numGuesses = Integer.parseInt(args[2]);
        File file = new File(dictionary);
        try {
            game.startGame(file, m.lengthOfWords);
        }catch (IllegalArgumentException e){return;}
        m.prompter(numGuesses, game,scan);
        scan.close();
    }

    void prompter(int numGuesses, IEvilHangmanGame game,Scanner scan){
        TreeSet<String> alreadyGuessed = new TreeSet<>();
        Set<String> wordSet = new TreeSet<>();
        while(numGuesses != 0) {
            String guessInput = userPrompter(wordSet,alreadyGuessed,numGuesses,scan);
            if (guessInput.length() == 1 && Character.isLetter(guessInput.charAt(0))) {
                alreadyGuessed.add(guessInput);
                    try {
                        wordSet = game.makeGuess(guessInput.charAt(0));
                        if(didWin(wordSet,alreadyGuessed)){
                            System.out.println("You Win! The Word was: " +  printWord(wordSet,alreadyGuessed));
                            return;
                        }
                        else{numGuesses += printHowMany(wordSet,guessInput.charAt(0));}
                    }
                    catch (IEvilHangmanGame.GuessAlreadyMadeException e) {
                        System.out.println("You have already guessed " + guessInput +"\n");
                        continue;//reprompt
                    }
            }
            else{System.out.println("Invalid Input\n"); continue;}//decrement here?
        }
        System.out.println("You Lose!\nThe word was: " + wordSet.iterator().next().toString());
    }

    //Lets us know if we have won the game yet or not
    boolean didWin(Set<String> wordSet,TreeSet<String> alreadyGuessed){
        if(wordSet.size() != 1){return false;}
        String compare = wordSet.iterator().next().toString();
        for(char c : compare.toCharArray()){
            if(!alreadyGuessed.contains(c+"")){return false;}
        }
        return true;
    }

    //The prompting messages that get sent to the user
    private String userPrompter(Set<String> wordSet, TreeSet<String> alreadyGuessed, int numGuesses,Scanner scan){
        System.out.println("You have " + numGuesses + " guesses left");
        System.out.print("Used Letters: " + getAlreadyUsed(alreadyGuessed) + "\n");
        System.out.println("Word: " + printWord(wordSet,alreadyGuessed));
        System.out.println("Enter Guess: ");
        String guessInput = scan.nextLine();
        return guessInput;
    }
    //returns a string which contains all the characters already used
    private String getAlreadyUsed(TreeSet<String> alreadyGuessed){
        String outStr = "";
        for(String str : alreadyGuessed){
         outStr += str + " ";
        }
        return outStr;
    }

    //prints our how many of the guess char is in any given string in the set
    //also returns a -1 if there were no guess chars, and 0 if there was at least one
    private int printHowMany(Set<String> wordSet,char guess){
        char[] str = wordSet.iterator().next().toString().toCharArray();
        int count = 0;
        for(char c : str){
            if(guess == c){count++;}
        }
        System.out.println("There was " + count + " occurance(s) of " + guess +"\n");
        if(count == 0){return -1;}
        return 0;
    }

    //used to print out the word being guessed
    private String printWord(Set<String> wordSet, TreeSet<String> alreadyGuessed){
        String output = "";
        if(!wordSet.isEmpty()) {
            char[] cArr = wordSet.iterator().next().toCharArray();
            for (char c : cArr) {
                if (alreadyGuessed.contains(c + "")) {
                    output += c;
                } else {
                    output += "-";
                }
            }
        }
        else{
            int count = 0;
            while(count < lengthOfWords){output+="-"; count++;}
        }
        return output;
    }

}
