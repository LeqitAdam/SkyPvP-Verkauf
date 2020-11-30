package eu.playcen.skypvp.methods;

public enum ImageChar {

    //https://github.com/arxenix/ImageMessage

    BLOCK('\u2588'),
    DARK_SHADE('\u2593'),
    MEDIUM_SHADE('\u2592'),
    LIGHT_SHADE('\u2591');
    private char c;

    ImageChar(char c) {
        this.c = c;
    }

    public char getChar() {
        return c;
    }
}
