package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

public class ShowMenu implements Event<Integer> {
    @Override
    public EventArgs<Integer> args() {
        return () -> 1;
    }
}
