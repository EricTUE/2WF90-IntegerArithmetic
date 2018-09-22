
package io;

import arithmetic.Computation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Reads (and parses) the inputs from a given input stream (E.g. System.in)
 * 
 * @author E.M.A. Arts (1004076)
 * @author K. Degeling (1018025)
 * @author R.M. Jonker (1011291)
 * @author S. Jacobs (1005276)
 * @author M. Schotsman (0995661)
 * 
 * @since 6 SEPT 2018
 */
public class InputReader {
    
    //input directories
    protected static final File DEFAULT_INPUT_FILE_PATH = new File("res/");
    protected static final String DEFAULT_INPUT_FILE_NAME = "input.txt";
    protected static final File DEFAULT_INPUT_FILE = new File(DEFAULT_INPUT_FILE_PATH + "/" + DEFAULT_INPUT_FILE_NAME);
    
    //create neccesary folder structure
    static {
        DEFAULT_INPUT_FILE_PATH.mkdirs();
    }
    
    private final Scanner lineScanner;
    
    //constructor
    //to read from a file pass the following: new FileInputStream(FILE)
    public InputReader(InputStream streamIn) {
        this.lineScanner = new Scanner(streamIn);
    }
    
    public InputReader() throws FileNotFoundException {
        this(new FileInputStream(DEFAULT_INPUT_FILE));
    }
    
    /**
     * Reads the data from the input File
     *
     * @return the data as a 'Computation'
     */
    public Computation getNextComputation() {        
        String type = "[INVALID]";
        int radix = -1;
        String x = "0";
        String y = "0";
        String m = "[INVALID]";
        
        //keep reading lines until we encounter an empty one
        String line;
        while(lineScanner.hasNextLine() && !(line = lineScanner.nextLine()).isEmpty()) {
            if (line.charAt(0) == '#') {
                continue;
            }
            Scanner sc = new Scanner(line);
            if (!sc.hasNext()) {
                System.err.println("An empty line was read -> This should never happen;");
                break;
            }
            String next = sc.next();
            switch (next) {
                case "[radix]": radix = sc.nextInt(); break;
                case "[add]": 
                case "[subtract]": 
                case "[multiply]":
                case "[karatsuba]":
                case "[reduce]":
                case "[inverse]":
                case "[euclid]":
                    type = next;
                    break;
                case "[x]": x = sc.next(); break;
                case "[y]": y = sc.next(); break;
                case "[m]": m = sc.next(); break;
                default: System.err.println("Unexpected input read: " + next); break;
            }
        }
        //empty line was read (or EoF was reached) -> computation data read
        
        if (type.equals("[INVALID]")) {
            return null;
        }
        
        //create the Computation
        return new Computation(x, y, radix, type, m);
    }
}    

    
  
