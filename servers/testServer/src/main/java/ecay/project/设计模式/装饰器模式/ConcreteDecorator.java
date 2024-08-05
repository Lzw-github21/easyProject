package ecay.project.设计模式.装饰器模式;


public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation(); //调用原有业务方法
        addedBehavior(); //调用新增业务方法
    }

    //新增业务方法
    public void addedBehavior(){
        //......
    }
}
