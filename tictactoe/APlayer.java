/**
 * A class representing a generic tic-tac-toe player.
 * @author Riley Olds
 */
public class APlayer {
    
    // instantiation
    protected Game game; /** The game the player is in.*/
    protected char symbol; /** The player's symbol.*/
    
    /**
     * Constructor for APlayer for a given game and symbol.
     * @param game the game the player is partaking in.
     * @param symbol the symbol of the player.
     */
    protected APlayer(Game game, char symbol) {
        this.game = game;
        this.symbol = symbol;
    }
    
    /**
     * Returns the symbol of the player this is called upon.
     * @return the symbol of the player.
     */
    public char getSymbol() {
        return this.symbol;
    }
    
    /**
     * Empty pick move function
     * @return returns null.
     */
    public Move pickMove() {    
        return null;
    }
}