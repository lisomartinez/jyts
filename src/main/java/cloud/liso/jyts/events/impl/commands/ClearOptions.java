package cloud.liso.jyts.events.impl.commands;

import cloud.liso.jyts.events.Event;
import cloud.liso.jyts.events.EventArgs;
import cloud.liso.jyts.ui.view.main.MenuItem;
import lombok.Data;

@Data
public class ClearOptions implements Event<Integer> {
    private final MenuItem menuItem;
    private final int id;
    private final int index;

   public int getId() {
        return id;
   }

    @Override
    public EventArgs<Integer> args() {
        return () -> index;
    }
}
