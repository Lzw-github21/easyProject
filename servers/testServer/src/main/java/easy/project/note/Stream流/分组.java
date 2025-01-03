package easy.project.note.Stream流;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 分组 {

    public static void main(String[] args) {
        // 根据账号对数据进行分组
        Map<String, List<Person>> map1 = Stream.of(
                new Person("张三", 18, 175)
                , new Person("李四", 22, 177)
                , new Person("张三", 14, 165)
                , new Person("李四", 15, 166)
                , new Person("张三", 19, 182)
        ).collect(Collectors.groupingBy(Person::getName));
        map1.forEach((k,v)-> System.out.println("k=" + k +"\t"+ "v=" + v));
        System.out.println("-----------");
        // 根据年龄分组 如果大于等于18 成年否则未成年
        Map<String, List<Person>> map2 = Stream.of(
                new Person("张三", 18, 175)
                , new Person("李四", 22, 177)
                , new Person("张三", 14, 165)
                , new Person("李四", 15, 166)
                , new Person("张三", 19, 182)
        ).collect(Collectors.groupingBy(p -> p.getAge() >= 18 ? "成年" : "未成年"));
        map2.forEach((k,v)-> System.out.println("k=" + k +"\t"+ "v=" + v));
    }
}
