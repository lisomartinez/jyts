package cloud.liso.jyts.ui;

import org.springframework.stereotype.Component;

@Component
public class ArgsParser {
   public Parameters parse(String[] args) {
       return new Parameters(CommandType.SEARCH, CommandArguments.of("die hard"));
   }
}
