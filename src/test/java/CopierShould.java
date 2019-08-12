import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.*;

class CopierShould {
    private Destination destination;
    private Source source;
    private Copier copier;

    @BeforeEach
    void setUp() {
        destination = mock(Destination.class);
        source = mock(Source.class);
        copier = new LineCopier(source, destination);
    }

    @Test
    void get_newline_character_from_source() {
        when(source.getChar()).thenReturn('\n');

        copier.copy();

        verify(source, times(1)).getChar();
    }

    @Test
    void get_a_line_of_characters_from_source() {
        when(source.getChar()).thenReturn('A', 'B', 'C', '\n');

        copier.copy();

        verify(source, times(4)).getChar();
    }

    @Test
    void set_a_newline_character_into_destination() {
        when(source.getChar()).thenReturn('\n');

        copier.copy();

        verify(destination, times(1)).setChar('\n');
    }

    @Test
    void set_a_line_of_characters_into_destination() {
        when(source.getChar()).thenReturn('A', 'B', 'C', '\n');

        copier.copy();

        InOrder inOrder = inOrder(destination);
        inOrder.verify(destination).setChar('A');
        inOrder.verify(destination).setChar('B');
        inOrder.verify(destination).setChar('C');
        inOrder.verify(destination).setChar('\n');
    }

    @Test
    void copy_only_one_line() {
        when(source.getChar()).thenReturn('A', '\n', 'B');

        copier.copy();

        verify(destination, times(2)).setChar(anyChar());
    }
}
