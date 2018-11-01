import java.io.*;

public class CTranslator {

    public CTranslator() {

    }//CTranslator constructor

    public void CTranslate(String line, File outfile) {

	try {
	    PrintWriter writer = new PrintWriter(new FileOutputStream(outfile, true));//, "UTF-8"); // allows us to write into a new file
	    writer.println(line);                                                                   // prints the line of code into the new file

	    writer.close();                                                                         // closes new file
	} catch (Exception IOException) {
	    System.out.println("Some sort of IO error here");
	}//try-catch

    }//CTranslate


    public void addJavaHeader(String name, File outfile) {

	try {
	    PrintWriter writer = new PrintWriter(new FileOutputStream(outfile, true));             // declares writer as a new PrintWriter, allows us to write into new file

	    /*
	      Adds java headers
	    */

	    writer.println("public class " + name + " {");
	    writer.println("    public static void main(String[] args) {");
	    writer.println();

	    writer.close();
	} catch (Exception IOException) {
	    System.out.println("Some sort of IO error here");
	}
	    
    }//addJavaHeader

    public void CstringTrans(String line, File outfile) {

	try {
	    PrintWriter writer = new PrintWriter(new FileOutputStream(outfile, true));

	    String[] printStatement = line.split("<<");	    
	    int lastentry = printStatement.length - 1;
	    if (printStatement[lastentry].equals(" endl;")) {
		writer.print("System.out.println(");
		lastentry--;
	    } else {
		writer.print("System.out.print(");
	    }//if
	    
	    for (int i = 1; i < lastentry - 1; i++) {
		writer.print(printStatement[i] + " + ");
	    }//for
	    if (lastentry > 0) { //remove ; from the final entry
		String newline = printStatement[lastentry].replaceAll("\\;","");
		writer.println(newline + ");");
	    } else {
		writer.println(");");
	    }//if-else

	    writer.close();
	} catch (Exception IOException) {                                                        
	    System.out.println("Some sort of IO error here");
	}
	
    }//CTranslator

}
