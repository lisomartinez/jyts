package cloud.liso.jyts.events;

import cloud.liso.jyts.ui.view.main.MenuItem;
import lombok.Data;

@Data
public class ShowDetails implements Event<MenuItem>{
    private final MenuItem mi;
    private final int row;
    private final int column;

    @Override
    public EventArgs<MenuItem> args() {
        return () -> mi;
    }
}
