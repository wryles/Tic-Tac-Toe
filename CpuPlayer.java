/**
 * A class representing a computer tic-tac-toe player.
 * @author Riley Olds
 */
public class CpuPlayer extends APlayer {

    /**
     * Constructor for a CpuPlayer.
     * @param game the game the cpu is playing in.
     * @param symbol the cpu's symbol.
     */
    public CpuPlayer(Game game, char symbol) {
        super(game, symbol);
    }
    
    /**
     * Selects a random available move for the cpu. Does this by selecting a random number in the range [0,1)
     * which is multiplied by the board size before being cast to an int.
     * @return the cpu's move.
     */
    public Move pickMove() {
        Move m = new Move();
        
        do {
            int r = (int) (Math.random() * game.getBoardSize());
            int c = (int) (Math.random() * game.getBoardSize());
            m = new Move(r, c);
        } while(game.isValidMove(m) != 'V');
        return m;
    }
}