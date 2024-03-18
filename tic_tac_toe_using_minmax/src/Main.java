import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[][] board = {{"1", "2", "3"},
                            {"4", "5", "6"},
                            {"7", "8", "9"}};
        System.out.println("********** TIC-TAC-TOE **********");
        System.out.println("Welcome to Tic-Tac-Toe!");
        printBoard(board);
        String playerSymbol = null;
        String compSymbol = null;
        while(compSymbol == null){
            System.out.print("Would you like to be X or O: ");
            playerSymbol = scan.nextLine();
            if(Objects.equals(playerSymbol, "X")){
                compSymbol = "0";
            }else if(Objects.equals(playerSymbol, "O")){
                compSymbol = "X";
            }else{
                System.out.println("ERROR; NOT VALID SYMBOL; MUST BE X OR O");
            }
        }

        playGame(board, playerSymbol, compSymbol, scan);

    }

    public static void playGame(String[][] board, String playerSymbol, String compSymbol, Scanner scan){
        while(true){
            playerTurn(board, scan, playerSymbol);
            printBoard(board);
            if(isWinner(board, playerSymbol)){ System.out.println("PLAYER WINS"); break;}
            compTurn(board, compSymbol);
            printBoard(board);
            if(isWinner(board, compSymbol)) { System.out.println("WEAK AI WINS"); break;}
        }
    }

    public static void compTurn(String[][] board, String symbol){
        Random r = new Random();
        boolean goodLoc = false;
        int compLoc = 0;
        while(!goodLoc){
            compLoc = r.nextInt(8)+1;
            goodLoc = validMove(board, String.valueOf(compLoc));
        }
        placeSymbol(board, String.valueOf(compLoc), symbol);
        System.out.println("Computer played at location: " + compLoc);
    }

    public static void playerTurn(String[][] board, Scanner scan, String symbol){
        String loc = null;
        boolean goodLoc = false;
        while(!goodLoc){
            System.out.println("Please select a location to play between 1-9: ");
            loc = scan.nextLine();
            goodLoc = (validMove(board, loc));
            if(!goodLoc){
                System.out.println(loc + " is not valid, please try again!");
            }
        }
        placeSymbol(board, loc, symbol);
    }

    public static void placeSymbol(String[][] board, String loc, String symbol){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(Objects.equals(board[i][j], loc)){
                    board[i][j] = symbol;
                }
            }
        }
    }

    public static boolean validMove(String[][] board, String loc){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(Objects.equals(board[i][j], loc)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isWinner(String[][] board, String symbol){
        //diagonal win
        if(Objects.equals(board[0][0], symbol) && Objects.equals(board[1][1], symbol) && Objects.equals(board[2][2], symbol) ||
            Objects.equals(board[0][2], board[1][1]) && Objects.equals(board[1][1], symbol) && Objects.equals(board[2][0], symbol)){
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if(Objects.equals(board[i][0], symbol) && Objects.equals(board[i][1], symbol) && Objects.equals(board[i][2], symbol) ||
                Objects.equals(board[0][i], symbol) && Objects.equals(board[1][i], symbol) && Objects.equals(board[2][i], symbol)){
                return true;
            }
        }
        return false;
    }

    public static void printBoard(String[][] board){
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2] + "\n" +
                            board[1][0] + "|" + board[1][1] + "|" + board[1][2] + "\n" +
                            board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}