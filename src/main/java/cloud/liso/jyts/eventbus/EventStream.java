package cloud.liso.jyts.eventbus;

import cloud.liso.jyts.events.Event;
import reactor.core.publisher.Flux;

public interface EventStream {
    Flux<? extends Event> stream();
}
