class LineCopier implements Copier {

    private final Source source;
    private final Destination destination;

    public LineCopier(Source source, Destination destination) {

        this.source = source;
        this.destination = destination;
    }

    @Override
    public void copy() {
        char c;
        do {
            c = source.getChar();
            destination.setChar(c);
        } while (c != '\n');
    }
}
