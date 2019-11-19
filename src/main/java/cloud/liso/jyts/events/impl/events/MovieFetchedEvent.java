package cloud.liso.jyts.events.impl.events;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

public final class MovieFetchedEvent implements Event<String> {

    private final String content;

    public MovieFetchedEvent(String content) {
        this.content = content;
    }

    @Override
    public EventArgs<String> args() {
        return (() -> content);
    }
}
