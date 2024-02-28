package easyProject.枚举;

//@Getter
public enum Week {

    /**
     * 枚举Week注释
     */
    SPRING("SPRING_key", "SPRING_Name"),
    SUMMER("SUMMER_key", "SUMMER_Name"),
    AUTUMN("AUTUMN_key", "AUTUMN_Name"),
    WINTER("WINTER_key", "WINTER_Name"),
    ;
    /**
     * 字段注释
     */
    private String key;
    /**
     * 字段注释
     */
    private String name;

    Week(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Test {
    public static void main(String[] args) {
        //查询枚举值
        String week = "SUMMER";
        Week s = Week.valueOf(week);
        System.out.println(s);

        //枚举值不存在会抛出异常：IllegalArgumentException： No enum constant
        String week2 = "AAA";
        Week s2 = Week.valueOf(week2);
        System.out.println(s2);


        //对枚举进行条件判断
        switch (s) {
            case SUMMER:
                System.out.println(s);
                s = Week.SPRING;
                break;
            case SPRING:
                System.out.println(s);
                s = Week.SUMMER;
                break;
            case AUTUMN:
                System.out.println(s);
                s = Week.SPRING;
                break;
            default:
                System.out.println("未找到对应枚举类");
        }
        System.out.println(s.getName()+s.getKey());
        System.out.println("");
    }
}
