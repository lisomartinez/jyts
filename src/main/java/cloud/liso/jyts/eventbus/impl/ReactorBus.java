package cloud.liso.jyts.eventbus.impl;

import cloud.liso.jyts.eventbus.EventBus;
import cloud.liso.jyts.events.Event;
import org.springframework.stereotype.Component;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.FluxSink;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReactorBus implements EventBus {
    private Map<Class<? extends Event>, EmitterProcessor<Event<?>>> emitters;
    private Map<Class<? extends Event>, FluxSink<Event<?>>> sinks;

    public ReactorBus() {
        emitters = new HashMap<>();
        sinks = new HashMap<>();
    }

    @Override
    public <T> void publish(Event<T> event) {
        FluxSink<Event<?>> sink = sinks.getOrDefault(event.getClass(), null);
//        if (sink == null) {
//            EmitterProcessor<Event<?>> emitter = createEmitter(event.getClass());
//            sink = createSink(event.getClass(), emitter);
//        }
        sink.next(event);

    }

    @Override
    public synchronized <T extends Event> EmitterProcessor<Event<?>> events(Class<T> event) {
        EmitterProcessor<Event<?>> eventEmitterProcessor = emitters.get(event);
        if (eventEmitterProcessor == null) {
            eventEmitterProcessor = EmitterProcessor.create();
            emitters.put(event, eventEmitterProcessor);
            sinks.put(event, eventEmitterProcessor.sink());
        }
        return eventEmitterProcessor;
    }
}
