package cloud.liso.jyts.ui.controller;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;

public class ShowSearchDialog implements Event<Integer> {
    @Override
    public EventArgs<Integer> args() {
        return () -> 0;
    }
}
