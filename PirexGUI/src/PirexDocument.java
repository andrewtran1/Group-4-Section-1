public class PirexDocument {
	private static final int MAX_LENGTH = 70;
	public final String author, title, fullText;
	public final int    docNumber;

	public PirexDocument(String author, String title, int docNumber, String fullText) {
		this.author    = author;
		this.title     = title;
		this.docNumber = docNumber;
		this.fullText  = fullText;
	}
	
 	public String toString() {
     	return String.format("%s  %s  %4d  %s", 
			author, title, docNumber, this.shortenedText());
 	}

	private String shortenedText() {
		String[] words = this.fullText.split(" ");
		String shortText = words.length != 0 ? words[0] : ""; 

		for (int i = 1; i < words.length && (shortText + words[i]).length() < MAX_LENGTH; i++) {
			shortText += " " + words[i];
		}

		return shortText;
	}
}
