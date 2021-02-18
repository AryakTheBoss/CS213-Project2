package cs213lib;

/**
 * Passes commands to KioskTestVersion along with a message of its expected result
 * @author Aryak Pande (amp487), Mayank Singamreddy (mss390)
 */
public class Command {

    private String msg;
    private String command;

    /**
     * Creates a command with a message
     * @param command the command to pass
     * @param msg message to send as expected output
     */
    public Command(String command, String msg){
        this.msg = msg;
        this.command = command;
    }

    /**
     * Gets the command passed in
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    @Override
    public String toString(){
        return "Input: "+command+" Expected Output: "+msg;
    }
}
