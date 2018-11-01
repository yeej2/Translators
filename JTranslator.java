import java.io.*;

public class JTranslator {

    public JTranslator() {

    }

    public void JTranslate(String line, File outfile) {

	try {
	    PrintWriter writer = new PrintWriter(new FileOutputStream(outfile, true));//, "UTF-8"); // allows us to write into a new file
	    writer.println(line);                                                                   // prints the line of code into the new file

	    writer.close();                                                                         // closes new file
	} catch (Exception IOException) {
	    System.out.println("Some sort of IO error here");
	}

    }

// Adds a C++ header to the new file
    public void addCPPHeader(File outfile) {

	try {
	    PrintWriter writer = new PrintWriter(new FileOutputStream(outfile, true));             // declares writer as a new PrintWriter, allows us to write into new file

      /*
      Adds C++ headers
      */
	    writer.println("#include <iostream>");
	    writer.println("#include <string>");
	    writer.println("using namespace std;");
	    writer.println("int main() {");

	    writer.close();
	} catch (Exception IOException) {
	    System.out.println("Some sort of IO error here");
	}

    }

    //method that translates print statments from java to C++
    public void JstringTrans(String line, File outfile) {

	try {
	    PrintWriter writer = new PrintWriter(new FileOutputStream(outfile, true));

	    String[] printStatement = line.split("\\(");                                             // splits the printStatement after (
	    char[] systemSt = printStatement[0].toCharArray();                                     // sets the first part of split to systemSt
	    char[] printSt = printStatement[1].toCharArray();                                      // sets second part of split to printSt
	    int sysLength = systemSt.length;                                                       // creates sysLength to get obtain the length of the first part of split
	    boolean endl = false;                                                                  // sets endl = to false

       // if the length of systemSt -2 is l and systemSt -1 is n it declares endl as true
	    if (systemSt[sysLength - 2] == 'l' && systemSt[sysLength - 1] == 'n') {
		endl = true;
	    }

	    writer.print("cout <<");                                                               // writes cout << into new file

	    // loops as long as i is less than the length of printSt -2 and reads through each part of the character array
	    for (int i = 0; i < (printSt.length - 2); i++) {

		if (printSt[i] == '+') {                                                                //if code finds a +, it will replace it with <<
		    writer.print("<<");
		} else {                                                                                // otherwise it will continue
		    writer.print(printSt[i]);
		}
	    }

	    if (endl) {                                                                          // if endl is true, code will add << endl; to the end of the line.
		writer.print("<< endl");
	    }

	    writer.println(";");


	    writer.close();                                                                      // finishes with writting in new file
	} catch (Exception IOException) {                                                        
	    System.out.println("Some sort of IO error here");
	}
    }
}
