package homework2.publication;


import homework2.Library;
import homework2.exceptions.DuplicatedPublicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Publication {

    private Logger logger = LogManager.getLogger(Library.class.getName());
    protected int id;
    protected String title;
    protected int pages;
    protected boolean available;
    protected Genre genre;
    protected String content;
    private static int counterId;

    public Publication(String title, int pages, boolean available, Genre genre, String content) {
        this.title = title;
        this.pages = pages;
        this.available = available;
        this.genre = genre;
        this.content = content;
        this.id = ++counterId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String available = (this.available) ? "Available" : "Not Available";
        return "Publication '" + title + '\'' +
                ", pages: " + pages +
                ", " + available +
                ", genre: " + genre;
    }

    public final void isSamePublication(Publication publication) {

        if (this.equals(publication)) {
            logger.warn(this.title + " and " + publication.getTitle() + " are the same publication");
            throw new DuplicatedPublicationException(this.title + " and " + publication.getTitle() + " are the same publication");
        } else
            logger.warn(this.title + " and " + publication.getTitle() + " are different publications");
    }

}
