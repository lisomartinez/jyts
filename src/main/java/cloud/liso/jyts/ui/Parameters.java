package cloud.liso.jyts.ui;

import lombok.Data;

@Data
public class Parameters {
   private final CommandType type;
   private final CommandArguments args;

    public String getArgsAsString() {
        return args.get();
    }
}
