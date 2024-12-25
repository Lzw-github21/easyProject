package ecay.project.note.Result;

public class 测试 {
    public static void main(String[] args) {
        System.out.println(R.ok());
        System.out.println(R.ok("123"));
        System.out.println(R.ok("123", "456"));
        System.out.println(R.failed());
        System.out.println(R.failed("123"));
        System.out.println(R.failed("123", ErrorCodeEnum.SERVICE_ERROR_C0001));
    }
}
