package easy.project.note.Stream流;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class 中间操作Peak {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream()
                .peek(String::toUpperCase)  // 打印每个元素
                .collect(Collectors.toList()).forEach(System.out::println);
    }


}
