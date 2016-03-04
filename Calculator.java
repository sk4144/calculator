
/**This class represents the Calculator that calls on the
 * ExpressionTools class to perform the operations
 *
 * @author Suebin Kim
 * @version 11/18/15
 */



import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			throw new IOException("Error: Missing name of the input file");
		}
		else if (args.length < 2) {
			throw new IOException("Error: Missing name of the output file");
		}
		File source = new java.io.File(args[0]);

		//checking to make sure source file exists
		if(!source.exists()) {
			throw new IOException("Error: Source file does not exist or cannot be opened");
		}

		//checking to make sure output file can be created
		String results = args[1];
		File outputFile = new File(results);
		outputFile.createNewFile();
		if(!outputFile.exists()) {
			throw new IOException("Error: output file " + results + " could not be created");
		}


		PrintWriter output = new PrintWriter(outputFile);

		//create a scanner for the file
		Scanner reader = new Scanner(source);

		while (reader.hasNextLine()) {
			String ex = reader.nextLine();
			try {
				String postfix = ExpressionTools.infixToPostfix(ex);
				output.println(ExpressionTools.postfixEval(postfix));
			} catch (Exception e) {
				if(e instanceof PostFixException) {
					output.println("INVALID");
					System.out.println(e);
				}
				else if(e instanceof ArithmeticException) {
					output.println("UNDEF");
					System.out.println(e);
				}
			}
		}

		reader.close();
		output.close();


	}

}
