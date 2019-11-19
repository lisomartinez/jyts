package cloud.liso.jyts;

import cloud.liso.jyts.ui.Extensions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExtensionsTest {

    @Test
    void urlify() {
        assertThat(Extensions.urlify("die hard")).isEqualTo("die%20hard");
    }
}