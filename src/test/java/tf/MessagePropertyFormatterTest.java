package tf;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import tf.tools.i18n.formatter.MessagePropertyFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author Tobias Fritz (tfr@itscope.de)
 */
public class MessagePropertyFormatterTest {
	private static final String RESSOURCES_PATH = "src/test/ressources/";
	private static final String MESSAGES_PROPERTIES_FILE = RESSOURCES_PATH+"messages.properties";
	private static final String MESSAGES_FORMATTED_PROPERTIES_FILE = RESSOURCES_PATH+"messages_formatted.properties";


	@Test
	public void messagePropertyFormatterTest() throws FileNotFoundException {

		List<String> lines = readLines(MESSAGES_PROPERTIES_FILE);
		List<String> sorted = new MessagePropertyFormatter(lines).sort();
		List<String> formatted = readLines(MESSAGES_FORMATTED_PROPERTIES_FILE);

		sorted.forEach(System.out::println);
		assertEquals(formatted, sorted);
	}

	@NotNull
	private static List<String> readLines(String filePath) throws FileNotFoundException {
		List<String> lines = new LinkedList<>();
		Scanner scanner = new Scanner(new File(filePath));
		while (scanner.hasNext()) {
			lines.add(scanner.nextLine());
		}
		return lines;
	}
}