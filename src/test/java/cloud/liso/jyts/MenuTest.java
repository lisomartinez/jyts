package cloud.liso.jyts;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.eventbus.impl.ReactorBus;
import cloud.liso.jyts.events.impl.commands.Command;
import cloud.liso.jyts.events.impl.commands.SearchCommand;
import cloud.liso.jyts.exceptions.ArgumentMissingException;
import cloud.liso.jyts.exceptions.InvalidOptionException;
import cloud.liso.jyts.exceptions.NoParametersSuppliedException;
import cloud.liso.jyts.ui.view.main.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MenuTest {
    private EventBus bus;
    private Menu menu;

    @BeforeEach
    void setUp() {
        bus = new ReactorBus();
//        menu = new Menu(bus, screen);
    }

    @Test
    void start_invaliddSearchFlags_shouldThrowInvalidOptionException() {
        String[] args = new String[3];
        args[0] = "-a";
        args[1] = "die";
        args[2] = "hard";
        assertThatExceptionOfType(InvalidOptionException.class)
                .as(args[0] + " option should be invalid")
                .isThrownBy(() -> menu.start(args))
                .withMessage("Invalid Option: -a");
    }

    @Test
    void start_argsLengthLessThanTwo_ThrowArgumentMissingException() {
        String[] args = new String[1];
        args[0] = "-s";
        assertThatExceptionOfType(ArgumentMissingException.class)
                .isThrownBy(() -> menu.start(args))
                .withMessage("Missing Argument for option -s");
    }

    @Test
    void start_argsLengthZero_ThrowNoParameterSuppliedException() {
        String[] args = new String[0];
                assertThatExceptionOfType(NoParametersSuppliedException.class)
                .isThrownBy(() -> menu.start(args))
                .withMessage("No arguments supplied");
    }

    @Test
    void start_validParams_publishSearchCommand() {
        bus.events(SearchCommand.class).cast(Command.class).map(Command::url).subscribe(cmd ->
            assertThat(cmd).isEqualTo("list_movies.json?query_term=die%20hard")
        );
        String[] args = new String[3];
        args[0] = "-s";
        args[1] = "die";
        args[2] = "hard";
        menu.start(args);
    }
}