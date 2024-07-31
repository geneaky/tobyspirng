package tobyspring.hellospring;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTest {

    @Test
    void sort() {
        Sort sort = new Sort();

        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        assertThat(list).containsExactly("b", "aa");
    }
}
