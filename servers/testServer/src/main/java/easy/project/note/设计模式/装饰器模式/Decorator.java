package easy.project.note.设计模式.装饰器模式;

public class Decorator extends Component {
    //维持一个对抽象构件对象的引用
    private Component component;


    /**
     * 装饰器构造函数
     *
     * @param component 被装饰的组件对象
     */
    public Decorator(Component component) {
        this.component = component;
    }


    /**
     * 执行操作。
     *
     * <p>调用原有业务方法（这里并没有真正实施装饰，而是提供了一个统一的接口，将装饰过程交给子类完成）。</p>
     *
     * @return 无返回值
     */
    @Override
    public void operation() {
        //调用原有业务方法(这里并没有真正实施装饰,而是提供了一个统一的接口,将装饰过程交给子类完成)
        component.operation();
    }
}
