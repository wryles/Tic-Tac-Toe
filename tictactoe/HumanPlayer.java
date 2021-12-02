import java.util.Scanner;

/**
 * A class representing a human tic-tac-toe player.
 * @author Riley Olds
 */
public class HumanPlayer extends APlayer {
    /**
     * Constructor for a HumanPlayer
     * @param game the game the player is playing in.
     * @param symbol the player's symbol.
     */
    public HumanPlayer(Game game, char symbol) {
        super(game, symbol);
    }

    /**
     * Asks the player for a move. Checks if the player entered "quit." If so returns null.
     * If they did not enter quit, it checks to make sure it is a valid input. If so it checks
     * if the move is valid. If not it prints why and ask for a new move. If the move is valid then it returns.
     * @return Null if the player asked to quit. If not it returns the move selected.
     */
    public Move pickMove() {
        Scanner scan = new Scanner(System.in);
        Move m = new Move();
        String moveS;

        do {
            System.out.println("Please enter a move in the form: a1 or A1.\nEnter quit if you wish to exit the game.");

            if(scan.hasNext()) { // make sure there is a string input
                moveS = scan.next();
                moveS = moveS.toUpperCase();
                if(moveS.equals("QUIT"))
                    return null;
                else if(moveS.length() != 2) { // make sure input is just two characters
                    System.out.println("Invalid input.\nPlease input your move in the correct form.");
                    continue;
                }
            } else {
                System.out.println("Invalid input.\nPlease input your move in the correct form.");
                continue;
            }
            // take the two chars and assign them as row and col
            int r = (int) moveS.charAt(0);
            int c = moveS.charAt(1);

            m = new Move(r-65, c-49);
            if(game.isValidMove(m) == 'R') {
                System.out.println("Invalid row.");
                continue;
            } else if(game.isValidMove(m) == 'C') {
                System.out.println("Invalid column");
                continue;
            } else if(game.isValidMove(m) == 'N') {
                System.out.println("Space already occupied.");
                continue;
            }
        } while(game.isValidMove(m) != 'V');

        return m;
    }

}