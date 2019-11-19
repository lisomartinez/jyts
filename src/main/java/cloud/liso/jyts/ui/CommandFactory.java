package cloud.liso.jyts.ui;

import cloud.liso.jyts.events.impl.commands.Command;
import cloud.liso.jyts.events.impl.commands.SearchCommand;
import org.springframework.stereotype.Component;

@Component
public class CommandFactory {
    public Command<?> createCommand(Parameters parameters) {
        switch (parameters.getType()) {
            case SEARCH:
                return new SearchCommand(parameters.getArgsAsString());
            default:
                throw new RuntimeException();
        }
    }
}
