package cloud.liso.jyts.yts;

import cloud.liso.jyts.events.impl.commands.Command;

public interface YtsClient {
    void execute(Command<?> command);
}
