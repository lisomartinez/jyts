package cloud.liso.jyts.eventbus;

import cloud.liso.jyts.events.Event;
import reactor.core.publisher.EmitterProcessor;

public interface EventBus {
    <T> void publish(Event<T> event);
    <T extends Event> EmitterProcessor<Event<?>> events(Class<T> event);
}
