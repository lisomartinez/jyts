package cloud.liso.jyts.ui;

import lombok.Data;

@Data(staticConstructor = "of")
public class CommandArguments {
    private final String content;
    public String get() {
        return content;
    }
}
