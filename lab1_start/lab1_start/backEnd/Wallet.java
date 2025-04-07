package backEnd;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Wallet {
    /**
     * The RandomAccessFile of the wallet file
     */  
    private RandomAccessFile file;

    /**
     * Creates a Wallet object
     *
     * A Wallet object interfaces with the wallet RandomAccessFile
     */
    public Wallet () throws Exception {
	this.file = new RandomAccessFile(new File("backEnd/wallet.txt"), "rw");
    }

    /**
     * Gets the wallet balance. 
     *
     * @return                   The content of the wallet file as an integer
     */
    public int getBalance() throws IOException {
	this.file.seek(0);
	return Integer.parseInt(this.file.readLine());
    }

    /**
     * Sets a new balance in the wallet
     *
     * @param  newBalance          new balance to write in the wallet
     */
    public void setBalance(int newBalance) throws Exception {
	this.file.setLength(0);
	String str = Integer.valueOf(newBalance).toString()+'\n'; 
	this.file.writeBytes(str); 
    }

    public boolean safeWithdraw(int valueToWithdraw) throws Exception{

        if (valueToWithdraw > getBalance()){
            return false;
        }
        int new_balance = getBalance() - valueToWithdraw;
        setBalance(new_balance);
        return true;
    }

    /**
     * Closes the RandomAccessFile in this.file
     */
    public void close() throws Exception {
	this.file.close();
    }
}
