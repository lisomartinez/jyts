package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;

public interface Command<T> extends Event<T> {
    String url();
}
