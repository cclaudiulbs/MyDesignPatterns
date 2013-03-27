package com.cc.java.designpatterns;

import java.io.*;

/**
 * @author claudiu <br>
 *         When? When two or more implementations of a similar algorithm exist Its a behavioral pattern, as it's used to
 *         manage algorithms, relantionships, and responsabilities between objects
 * 
 *         From GoF: Defines the skeleton of an algorithm in a method, making subclasses implement their specific
 *         behavior based on the skeleton, hence the subclasses can redefine some steps in the algorithm, without
 *         chaning the algorithm structure
 * 
 *         Template Pattern core components: <li>basically works with Abstract Classes -- abstract methods for
 *         delegating/deffering the specific behavior to be Overriden by subclasses <li>a final non-overridable
 *         templateMethod which uses the abstract methods to control the structure of the algorithm, hence the algorithm
 *         is not changed, but deffered to be specifically implemented by every concrete Implementer</li>
 * 
 *         Through polymorphism each method is called at runtime, and its speficic algorithm is invoked, based on the
 *         same structure define by the templateMethod Pattern method
 * 
 *         One more important thing: the Template Method makes use of the Hollywood Principle:
 *         "don't call us! we'll call you" This principle says, that low level components should NOT call/depend
 *         directly on the high-level components, but high-level components should call low-level components to get the
 *         job done; in such way, our Abstract class, holding the Template method will control the overall process by
 *         calling subclasses methods when necessary
 * 
 *         The general algorithm is saved in one place but the concrete steps may be changed by the subclasses.
 * 
 *         let subclasses implement (through method overriding) behavior that can vary avoid duplication in the code:
 *         the general workflow structure is implemented once in the abstract class's algorithm, and necessary
 *         variations are implemented in each of the subclasses. control at what point(s) subclassing is allowed. As
 *         opposed to a simple polymorphic override, where the base method would be entirely rewritten allowing radical
 *         change to the workflow, only the specific details of the workflow are allowed to change.
 */
// -- The Printer is the entry-point for the Template Method, where the final templateMethod defines the algorithm
// -- Structure
abstract class Printer {

	abstract boolean isReadable();

	abstract InputStream getStream();

	/**
	 * Template Method, used to "set" the Pattern Algorithm based on deffered methods implemented by the specific
	 * classes on runtime
	 */
	final OutputStream templateWriteDataStream() {
		OutputStream outputStream = new ByteArrayOutputStream();
		InputStream inputStream = getStream();

		if(isReadable()) {
			try {
				int eachByte;
				while((eachByte = inputStream.read()) != -1) {
					outputStream.write(eachByte);
				}
			} catch(IOException ioException) {
			}
		}
		return outputStream;
	}
}

// -- Using the concrete FilePrinter, the overridable methods are overriden and the abstract methods are called from the
// -- template Parent
class FilePrinter extends Printer {

	private final File file;

	FilePrinter(File file) {
		this.file = file;
	}

	@Override
	public boolean isReadable() {
		return file.canRead();
	}

	@Override
	public InputStream getStream() {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch(IOException ioException) {
			ioException.printStackTrace();
		}

		assert inputStream != null: "Input Stream cannot be null";
		return inputStream;
	}
}

// -- Can be applied to other Data Types than File
class DataPrinter extends Printer {

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public InputStream getStream() {
		return null;
	}
}

public class TemplateMethodPatternClientDemo {

	public static void main(String[] args) {
		Printer filePrinter = new FilePrinter(new File("/home/cclaudiu/Likewise_script.sh"));
		Printer dataPrinter = new DataPrinter();

		System.out.println(filePrinter.templateWriteDataStream());
		// System.out.println(dataPrinter.templateWriteDataStream()); // not implemented yet
	}
}
