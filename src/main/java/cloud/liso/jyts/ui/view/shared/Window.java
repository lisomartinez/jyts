package cloud.liso.jyts.ui.view.shared;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Window {
    private TerminalScreen screen;
    private Terminal terminal;

    public Window() {
        try {
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            this.terminal = factory.createTerminalEmulator();
            this.screen = new TerminalScreen(terminal);
            this.screen.startScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Screen screen() {
        return screen;
    }
}
