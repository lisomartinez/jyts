package cloud.liso.jyts.ui;

import cloud.liso.jyts.eventbus.EventBus;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class StartMenu {
    private Screen screen;
    private EventBus bus;

    public StartMenu(Screen screen, EventBus bus) {
        this.screen = screen;
        this.bus = bus;
    }

   public void start() throws IOException {
       screen.startScreen();
       TextGraphics textGraphics = screen.newTextGraphics();
       textGraphics.putString(1, 1, "Searching");
       screen.refresh();
   }
}

