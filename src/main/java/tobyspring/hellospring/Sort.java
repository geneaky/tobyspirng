package tobyspring.hellospring;

import java.util.List;

public class Sort {

    public List<String> sortByLength(List<String> list) {
        list.sort((s1, s2) -> s1.length() - s2.length());
        return list;
    }
}
