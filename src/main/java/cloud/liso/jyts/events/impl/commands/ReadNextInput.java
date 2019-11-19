package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

public class ReadNextInput implements Event<Integer> {
    private final int index;

    public ReadNextInput(int index) {
        this.index = index;
    }

    @Override
    public EventArgs<Integer> args() {
        return () -> index;
    }
}
