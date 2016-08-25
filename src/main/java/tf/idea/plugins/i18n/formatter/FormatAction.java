package tf.idea.plugins.i18n.formatter;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import tf.tools.i18n.formatter.MessagePropertyFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Format a message.properties file.
 *
 * @author Tobias Fritz (tfr@itscope.de)
 * @version $Id$
 */
public class FormatAction extends TextComponentEditorAction {
	protected FormatAction() {
		super(new Handler());
	}

	private static class Handler extends EditorWriteActionHandler {
		private static final String FORMATTER_OFF = "#@formatter:off";

		public void executeWriteAction(Editor editor, DataContext dataContext) {
			final Document doc = editor.getDocument();

			int startLine;
			int endLine;

			startLine = 0;
			endLine = doc.getLineCount() - 1;

			// Ignore last lines (usually one) which are only '\n'
			endLine = ignoreLastEmptyLines(doc, endLine);

			if (startLine >= endLine) {
				return;
			}

			// Extract text as a list of lines
			List<String> lines = extractLines(doc, startLine, endLine);

			// dumb sort
			lines = new MessagePropertyFormatter(lines).sort();

			StringBuilder sortedText = new StringBuilder();
			sortedText.append(FORMATTER_OFF + "\n");
			joinLines(sortedText, lines);

			// Remove last \n is sort has been applied on whole file and the file did not end with \n

			CharSequence charsSequence = doc.getCharsSequence();
			if (charsSequence.charAt(charsSequence.length() - 1) != '\n') {
				sortedText.deleteCharAt(sortedText.length() - 1);
			}

			// Replace text
			int startOffset = doc.getLineStartOffset(startLine);
			int endOffset = doc.getLineEndOffset(endLine) + doc.getLineSeparatorLength(endLine);

			editor.getDocument().replaceString(startOffset, endOffset, sortedText);
		}

		private int ignoreLastEmptyLines(Document doc, int endLine) {
			while (endLine >= 0) {
				if (doc.getLineEndOffset(endLine) > doc.getLineStartOffset(endLine)) {
					return endLine;
				}
				endLine--;
			}
			return -1;
		}

		private List<String> extractLines(Document doc, int startLine, int endLine) {
			List<String> lines = new ArrayList<>(endLine - startLine);
			for (int i = startLine; i <= endLine; i++) {
				String line = extractLine(doc, i);
				lines.add(line);
			}
			return lines;
		}

		private String extractLine(Document doc, int lineNumber) {
			int lineSeparatorLength = doc.getLineSeparatorLength(lineNumber);
			int startOffset = doc.getLineStartOffset(lineNumber);
			int endOffset = doc.getLineEndOffset(lineNumber) + lineSeparatorLength;
			return doc.getCharsSequence().subSequence(startOffset, endOffset).toString();
		}

		private void joinLines(StringBuilder builder , List<String> lines) {
			for (String line : lines) {
				builder.append(line);
				builder.append("\n");
			}
		}
	}
}