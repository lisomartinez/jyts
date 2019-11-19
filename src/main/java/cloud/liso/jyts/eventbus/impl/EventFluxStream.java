package cloud.liso.jyts.eventbus.impl;

import cloud.liso.jyts.eventbus.EventStream;
import cloud.liso.jyts.events.Event;
import lombok.AllArgsConstructor;
import reactor.core.publisher.EmitterProcessor;

@AllArgsConstructor(staticName = "of")
public class EventFluxStream implements EventStream {
    private EmitterProcessor<Event<?>> flux;
    @Override
    public EmitterProcessor<Event<?>> stream() {
        return flux;
    }
}
