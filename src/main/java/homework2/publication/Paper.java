package homework2.publication;


public class Paper extends Publication {

    private final String author;

    public Paper(String title, String author, int pages, boolean available, Genre genre, String content) {
        super(title, pages, available, genre, content);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

}
