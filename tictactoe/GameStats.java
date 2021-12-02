/**
 * A class to store the stats of consecutive games.
 * @author Riley Olds
 */
public class GameStats {
    
    // instantiate variables
    int nwins;
    int nties;
    int nlosses;
    
    /**
     * Constructor for the GameStats class.
     */
    public GameStats() {
        nwins = 0;
        nties = 0;
        nlosses = 0;
    }
    
    /**
     * Records a win.
     */
    public void recordWin() {
        nwins += 1;
    }
    
    /**
     * Records a tie.
     */
    public void recordTie() {
        nties += 1;
    }
    
    /**
     * Records a loss.
     */
    public void recordLoss() {
        nlosses += 1;
    }
    
    /**
     * Prints out the statistics of the play session.
     * @return A sentence which expresses the number of wins, ties, and losses the player had.
     */
    public String toString() {
        return "You finished with: " + nwins + " wins; " + nties + " ties; " + nlosses + " losses.";
    }
}