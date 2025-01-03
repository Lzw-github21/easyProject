package easy.project.note.Stream流;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 聚合 {
    public static void main(String[] args) {
        // 获取年龄的最大值
        Optional<Person> maxAge = Stream.of(
                new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)
        ).collect(Collectors.maxBy((p1, p2) -> p1.getAge() - p2.getAge()));
        System.out.println("最大年龄：" + maxAge.get());
        // 获取年龄的最小值
        Optional<Person> minAge = Stream.of(
                new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)
        ).collect(Collectors.minBy((p1, p2) -> p1.getAge() - p2.getAge()));
        System.out.println("最新年龄:" + minAge.get());
        // 求所有人的年龄之和
        Integer sumAge = Stream.of(
                        new Person("张三", 18)
                        , new Person("李四", 22)
                        , new Person("张三", 13)
                        , new Person("王五", 15)
                        , new Person("张三", 19)
                )
                //.collect(Collectors.summingInt(s -> s.getAge()))
                .collect(Collectors.summingInt(Person::getAge))
                ;
        System.out.println("年龄总和：" + sumAge);
        // 年龄的平均值
        Double avgAge = Stream.of(
                new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)
        ).collect(Collectors.averagingInt(Person::getAge));
        System.out.println("年龄的平均值：" + avgAge);
        // 统计数量
        Long count = Stream.of(
                        new Person("张三", 18)
                        , new Person("李四", 22)
                        , new Person("张三", 13)
                        , new Person("王五", 15)
                        , new Person("张三", 19)
                ).filter(p->p.getAge() > 18)
                .collect(Collectors.counting());
        System.out.println("满足条件的记录数:" + count);

    }
}
