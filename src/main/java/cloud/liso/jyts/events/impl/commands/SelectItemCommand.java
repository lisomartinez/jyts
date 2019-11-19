package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

public class SelectItemCommand implements Event<Integer> {
    private int index;

    public SelectItemCommand(int index) {
        this.index = index;
    }

    @Override
    public EventArgs<Integer> args() {
        return () -> index;
    }
}
