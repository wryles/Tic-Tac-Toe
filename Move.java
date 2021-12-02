/**
 * A class representing a move in a game.
 * @author Riley Olds
 */
public class Move {
    
    // instantiate the fields
    int row; /** the row of the move.*/
    int col; /** the column of the move.*/
    
    /**
     * Empty Move constructor.
     */
    public Move() {};
    
    /**
     * Constructor for a Move.
     * @param row the row of the move.
     * @param col the column of the move.
     */
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}