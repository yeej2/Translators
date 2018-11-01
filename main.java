import java.util.*;
import java.io.*;
import java.text.*;
import java.lang.Object;
import java.nio.file.*;

public class main {

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);                           // declaring Scanner
	System.out.print("Please enter in file for translating: ");
	String infile = scan.next();                                     // takes user input and sets it to infile

	String[] name = infile.split("\\.");                             // splits infile into two parts at the "."

	String infilename = name[0];                                     // the first part of the split
	String infileext = name[1];                                      // the second part of the split

        //System.out.println(infilename + "." + infileext);

	String outfilename;                                              // declaring outfilename

/*
if else statement that checks if the second part of the split is java
or if it is cpp and adds at end accordingly
*/
	if (infileext.equals("java")) {
	    outfilename = infilename + ".cpp";
	} else if (infileext.equals("cpp")) {
	    outfilename = infilename + ".java";
	} else {
	    System.out.println("Program not designed to translate other languages!");
	    return;
	}//if-else


	File outfile = new File(outfilename);                            // allows us to create a new file called outfile htat is given the name of the file

  /*
  if there is a file that has the same name and not a directory, this will
  delete the file to allow a new one to be written over it.
  */
	if (outfile.exists() && !outfile.isDirectory()) {
	    outfile.delete();
	}

  /*
  Declaring variables
  */

	String line = "";
	String prevLine = "";
	String tempLine = "";
	String temp = "";
	/*try (Stream<String> lines = Files.lines(Paths.get(infile))) {
	    line = lines.skip(1).findFirst.get();
	    }*/

	try {
	    FileInputStream fs = new FileInputStream(infile);                    // Allows us to get into the file input
	    BufferedReader br = new BufferedReader(new InputStreamReader(fs));   // allows us to read the lines
	    JTranslator jtrans = new JTranslator();                               // Declares that we will be using a method in another class
	    CTranslator ctrans = new CTranslator();
	    //for (int i = 0; i < 5; i++) {
	    //while (br.readLine() != null) {

      /*
      if the end of the file name reads java add header to a new file with a cpp ending
      */
	    if (infileext.equals("java")) {
		//System.out.println("Adding header to .cpp file");                       // debugging code
		jtrans.addCPPHeader(outfile);
		//System.out.println("Header added to .cpp file");                        // debugging code

		line = br.readLine();                                                   // starts the program to start reading lines of code and declares it in line
		/*
		  Until the code reaches the start of the main method, this will read the code
		*/
		do {
		    line = br.readLine();
		    line = line.replaceAll("\\s","");
		} while (!line.equals("publicstaticvoidmain(String[]args){"));

		line = br.readLine();


		do {
		    prevLine = line;                                                    // sets preLine equal to line before line reads the next line of code
		    line = br.readLine();                                               // reads the next line of code
		    System.out.println(line);
        if(prevLine.equals("}")){
          System.out.println("HELLO?");
        }
        if(line == null){
          System.out.println("CHECK");
        }
		    if (prevLine.equals("}") && line == null) {                         // if prevLine ends with a } and the last line in null, break out of this operation
            prevLine="";
            break;
		    }

		    // Entering section that causes issues
		    //System.out.println("Starting Split!");                              // debugging code (does not get this far)
		    String[] tempStArr = prevLine.split("\\(");                           // splits the prevLine
		    //System.out.println("Line has been split");
		    //System.out.println("Length: " + tempStArr.length);

		    tempLine = tempStArr[0];                                            // first part stored as tempLine
		    /*
		      int i = 1;
		      while (i < tempStArr.length) {
		      //System.out.println("Separating into two parts");
		      System.out.println(tempStArr[i]);

		      temp = tempStArr[i];                                                // second part stored as temp
		      //System.out.println("Temp line: " + temp);
		      i++;
		      }//while */

		    tempLine = tempLine.replaceAll("\\s","");                           // code to remove white space from code
		    // Exiting section that causes issues


		    //System.out.println("Determining if print statement!");              // debugging code

		    /*
		      if tempLine says System.out.println or System.out.print, the code will call
		      a method to translate print statements otherwise it will keep translating.
		    */
		    if (tempLine.equals("System.out.println") || tempLine.equals("System.out.print")) {
			//System.out.println("Translating print statement");
			jtrans.JstringTrans(prevLine, outfile);
		    } else {
			//System.out.println("Translated a not print statement");
			jtrans.JTranslate(prevLine, outfile);
		    }


		    //System.out.println("Line read: " + prevLine);                       // lets us see what lines are being read and printed
		    //line = br.readLine();

		} while (line != null);                                                 // as long as there is code to read, the do statement above will run.
		//System.out.println("File for translating: " + infile);                  // states the name of file translating
		//System.out.println("Last line: " + line);                               // prints the last line of the code

	    } else {                                                                  // not present yet.
		System.out.println("Translating cpp -> java");
		ctrans.addJavaHeader(infilename, outfile);

		line = br.readLine();                                                   // starts the program to start reading lines of code and declares it in line
		/*
		  Until the code reaches the start of the main method, this will read the code
		*/
		do {
		    line = br.readLine();
		    line = line.replaceAll("\\s","");
		} while (!line.equals("intmain(){"));

		line = br.readLine();


		do {
		    prevLine = line;                                                    // sets preLine equal to line before line reads the next line of code
		    line = br.readLine();                                               // reads the next line of code
		    System.out.println(line);
		    if (prevLine.equals("}") && line == null) {                         // if prevLine ends with a } and the last line in null, break out of this operation
			break;
		    }

		    String[] tempStArr = prevLine.split("\\<<");
		    tempLine = tempStArr[0];
		    tempLine = tempLine.replaceAll("\\s","");

		    if (tempLine.equals("cout")) {
			//System.out.println("Translating print statement");
			ctrans.CstringTrans(prevLine, outfile);
		    } else {
			//System.out.println("Translated a not print statement");
			ctrans.CTranslate(prevLine, outfile);
		    }//if-else

		} while (line != null);                                                 // as long as there is code to read, the do statement above will run.
	    }//if-else

	    ctrans.CTranslate("}",outfile);

	    /*
	      catches exception of a file inputed by user is not found.
	    */
	} catch (Exception FileNotFoundException) {
	    System.out.println("This file doesn't exist, Lame-O!");
	    return;
	}

    }//main

}//finalProject
