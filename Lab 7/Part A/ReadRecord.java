/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: Harry Han, Yida Xu
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {
    
    private ObjectInputStream input;
    
    /**
     *  opens an ObjectInputStream using a FileInputStream
     */
    
    private void readObjectsFromFile(String name)
    {
        MusicRecord record ;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }
        
        /* The following loop is supposed to use readObject method of of
         * ObjectInputSteam to read a MusicRecord object from a binary file that
         * contains several reords.
         * Loop should terminate when an EOFException is thrown.
         */
        
        try
        {
            while (true)
            {             
				record = (MusicRecord) input.readObject();
				System.out.printf("%-10d%-30s%-20s%-10.2f\n", record.getYear(), record.getSongName(), record.getSingerName(), record.getPurchasePrice());
            } 
        } catch (EOFException e) {
			System.err.println("End of file reached");
		} catch (IOException e) {
            System.err.println( "Error opening file." );
			e.printStackTrace();
        } catch (ClassNotFoundException e) {
			System.err.println("Error...");
		} 

		try {
			input.close();
		} catch (IOException e){
			e.printStackTrace();
		}
    }
    
    
    public static void main(String [] args)
    {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("allSongs.ser");
    }
}