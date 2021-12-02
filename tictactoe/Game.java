import java.util.Scanner;

/**
 * A class representing a tic-tac-toe game.
 * @author Riley Olds
 */
public class Game {

    char[][] board; /** represents the board as a 2d matrix of symbols.*/
    final int boardSize; /** represents board size, which will be the size of both dimensions of the board.*/
    final char SYMBOL_BLANK = ' '; /** symbol for blank spaces.*/
    final char SYMBOL_HUMAN = 'X'; /** symbol for the human player's spaces.*/
    final char SYMBOL_CPU = 'O'; /** symbol for the cpu's spaces.*/

    /** 
     * Constructor for Game objects.
     */
    public Game(int boardSize) {
        this.boardSize = boardSize;

        board = new char[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = SYMBOL_BLANK;
            }
        }
    }

    /** 
     * Returns the game board size.
     * @return the size of the board
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Checks the validity of given move.
     * @return 'V' if the move is valid, or a different character 
     * depending on why it is invalid.
     * @param move the move to be validated.
     */
    public char isValidMove(Move move) {
        char validity;

        if(move.row < 0 || move.row >= boardSize) 
            validity = 'R';
        else if(move.col < 0 || move.col >=boardSize)
            validity = 'C';
        else if(board[move.row][move.col] != SYMBOL_BLANK)
            validity = 'N';
        else 
            validity = 'V';

        return validity;
    }

    /**
     * Executes the move passed. If the move is invalid it returns false.
     * @param move the move to be executed.
     * @param symbol the symbol of the player making the move.
     * @return true if the move was successfully executed.
     */
    protected boolean executeMove(Move move, char symbol) {

        if(isValidMove(move) != 'V')
            return false;
        else {
            board[move.row][move.col] = symbol;
            return true;
        }
    }

    /**
     * Analyzes the board to determine the game state. That state is then returned as a character.
     * The game is over if either player has completed a row, column, diagonal,
     * or if the board is completely filled with no one acheiving victory.
     * @return A character indicating the game state: '?' if the game isn't over, 'T' if it is a tie,
     * or, if there is a victory, the winning player's symbol.
     */
    public char getGameStatus() {
        char status = '?';

        // rows
        for(int i = 0; i < boardSize; i++) {
            int h = 0;
            int c = 0;
            for(int j = 0; j < boardSize; j++) {
                if(board[i][j] == SYMBOL_HUMAN) 
                    h++;
                else if(board[i][j] == SYMBOL_CPU) 
                    c++;
            }
            if(h == boardSize) 
                status = SYMBOL_HUMAN;
            else if(c == boardSize)
                status = SYMBOL_CPU;
        }

        // columns
        for(int i = 0; i < boardSize; i++) {
            int h = 0;
            int c = 0;
            for(int j = 0; j < boardSize; j++) {
                if(board[j][i] == SYMBOL_HUMAN) 
                    h++;
                else if(board[j][i] == SYMBOL_CPU) 
                    c++;
            }
            if(h == boardSize) 
                status = SYMBOL_HUMAN;
            else if(c == boardSize)
                status = SYMBOL_CPU;
        }

        // left diagonal
        int h = 0;
        int c = 0;
        for(int i = 0; i < boardSize; i++) {
            if(board[i][i] == SYMBOL_HUMAN) 
                h++;
            else if(board[i][i] == SYMBOL_CPU) 
                c++;

            if(h == boardSize) 
                status = SYMBOL_HUMAN;
            else if(c == boardSize)
                status = SYMBOL_CPU;
        }

        // right diagonal
        h = 0;
        c = 0;
        for(int i = 0; i < boardSize; i++) {

            if(board[i][boardSize - 1 - i] == SYMBOL_HUMAN) 
                h++;
            else if(board[i][boardSize - 1 - i] == SYMBOL_CPU) 
                c++;

            if(h == boardSize) 
                status = SYMBOL_HUMAN;
            else if(c == boardSize)
                status = SYMBOL_CPU;
        }

        // tie
        int occupied = 0;
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(board[i][j] != ' ') 
                    occupied++;
                if(occupied == (boardSize * boardSize))
                    status = 'T';
            }
        }

        return status;
    }

    /**
     * Creates a textual represntation of the game board.
     * @return A string represnation of the board.
     */
    public String toString() {
        String bString = "";
        int row = 1;
        char col = 'A';

        for(int i = 0; i < boardSize; i++) {
            bString += "   " + row++;
            if(i == boardSize - 1)
                bString += "\n";
        }
        for(int i = 0; i < boardSize; i++) {
            bString = bString + col++ + " ";
            for(int j = 0; j < boardSize; j++) {
                bString += " " + board[i][j] + " ";
                if(j == boardSize - 1)
                    bString += "\n  ";
                else
                    bString += "|";
            }

            if(i != boardSize - 1) {
                for(int j = 0; j < boardSize; j++) {
                    if(j == boardSize - 1) 
                        bString += "---\n";
                    else
                        bString += "---|";
                }  
            }
        }
        System.out.println(bString);
        return bString;
    }

    /**
     * Plays a game of tic-tac-toe. The first player to play is chosen at random.
     * @return A character representing the result of the game: 'H' if the human won,
     * 'C' if the cpu won, 'T' if the game tied, and 'Q' if the human quit the game.
     */
    public char playSingleGame() {
        char turn;

        APlayer human = new HumanPlayer(this, SYMBOL_HUMAN);
        APlayer cpu = new CpuPlayer(this, SYMBOL_CPU);

        if(Math.random() < 0.5) {
            turn = SYMBOL_HUMAN;
            System.out.println("Player starts.");
        } else {
            turn = SYMBOL_CPU;
            System.out.println("Computer starts.");
        }

        while(getGameStatus() == '?') {
            // player turn
            if(turn == SYMBOL_HUMAN) {
                // player moves
                Move hMove = human.pickMove();
                if(hMove == null){ // check if user has quit
                    return 'Q';
                }
                executeMove(hMove, SYMBOL_HUMAN);
                // switch to cpu turn and print board
                turn = SYMBOL_CPU;
                toString();
                // check if player wins
                if(getGameStatus() == SYMBOL_HUMAN) {
                    System.out.println("You win!");
                    break;
                }
            }
            // cpu turn
            if(turn == SYMBOL_CPU) {
                // cpu moves
                Move cMove = cpu.pickMove();
                executeMove(cMove, SYMBOL_CPU);
                System.out.println("The computer has moved.");
                // switch to player turn and print board
                turn = SYMBOL_HUMAN;
                toString();
                // check if cpu wins
                if(getGameStatus() == SYMBOL_CPU) {
                    System.out.println("You lose!");
                    break;
                }
            }
        }
        // check if tied
        if(getGameStatus() == 'T')
            System.out.println("Cat's game!");

        switch(getGameStatus()) {
            case SYMBOL_HUMAN: {return 'H';}
            case SYMBOL_CPU: {return 'C';}
            case 'T': {return 'T';}
            default: return '?';
        }

    }

    /**
     * Runs tic-tac-toe games continuously until the user quits. Upon quitting, the statistics
     * of the session are printed.
     * @param args the arguments are not used.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to tictactoe.");

        GameStats stats = new GameStats();
        boolean keepPlaying = true;
        
        while(keepPlaying) {
            Scanner scan = new Scanner(System.in);

            Game game;
            int size = 3;

            System.out.println("Please enter a board size between 1 and 9.");
            if(scan.hasNextInt()) {
                size = scan.nextInt();
                if(size >= 1 && size <= 9) {
                    System.out.println("Starting game with board size " + size + ".");
                } else {
                    size = 3;
                    System.out.println("Invalid board size. Creating default sized board.");
                }
            } else {
                System.out.println("Invalid input. Creating default sized board.");
            }

            game = new Game(size);
            game.toString();

            switch(game.playSingleGame()) {
                case 'H': {
                    stats.recordWin();
                    continue;
                }
                case 'C': {
                    stats.recordLoss();
                    continue;
                }
                case 'T': {
                    stats.recordTie();
                    continue;
                }
                case 'Q': {
                    System.out.println("User has quit the game.\n" + stats.toString());
                    keepPlaying = false;
                }
            }

        }
    }
}