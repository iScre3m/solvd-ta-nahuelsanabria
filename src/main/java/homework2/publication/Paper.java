package homework2.publication;


import homework2.interfaces.ICopyable;

public class Paper extends Publication implements ICopyable {

    private final String author;

    public Paper(String title, String author, int pages, boolean available, Genre genre, String content) {
        super(title, pages, available, genre, content);
        this.author = author;
    }


    @Override
    public double calculateCopyPrice(int amountOfCopies, PriceCopyColor priceCopyColor) {
        int countLetters = 0;
        for (int i = 0; i < this.content.length(); i++) {
            countLetters++;
        }
        return priceCopyColor.addPriceColor(pricePerPageCopy * this.pages * countLetters * 1.05);
    }

    @Override
    public String toString() {
        return "Paper '" + title + '\'' +
                "author='" + author + '\'' +
                ", pages=" + pages +
                ", " + available +
                ", genre=" + genre;
    }
}
