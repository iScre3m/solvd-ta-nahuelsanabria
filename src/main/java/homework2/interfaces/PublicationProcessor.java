package homework2.interfaces;

import homework2.publication.Publication;

@FunctionalInterface
public interface PublicationProcessor {

    boolean processPublication(Publication publication);
}
