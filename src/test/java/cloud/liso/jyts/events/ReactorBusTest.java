package cloud.liso.jyts.events;

import cloud.liso.jyts.eventbus.impl.ReactorBus;
import cloud.liso.jyts.events.impl.commands.SearchCommand;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Hooks;

import static org.assertj.core.api.Assertions.assertThat;

class ReactorBusTest {
    @Test
    void name() {
        Hooks.onOperatorDebug();
        ReactorBus bus = new ReactorBus();
        bus.events(SearchCommand.class).subscribe(event -> {
            assertThat(event.getClass()).isEqualTo(SearchCommand.class);
            assertThat(((SearchCommand) event).args()).isEqualTo("list_movies.json?query_term=die%20hard");
        }
        );
        bus.publish(new SearchCommand("die hard"));
    }
}