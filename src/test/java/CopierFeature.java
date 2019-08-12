import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CopierFeature {
    @Test
    void copies_a_line_from_source_to_the_destination() {
        var source = new Source() {
            char[] content = {'A', 'B', 'C', '\n'};
            int pointer = 0;

            @Override
            public char getChar() {
                return content[pointer++];
            }
        };
        var destination = new Destination() {
            public String getCharacters() {
                return stringBuilder.toString();
            }

            StringBuilder stringBuilder = new StringBuilder();

            @Override
            public void setChar(char character) {
                stringBuilder.append(character);
            }
        };
        var copier = new LineCopier(source, destination);

        copier.copy();

        assertEquals("ABC\n", destination.getCharacters());
    }

}
