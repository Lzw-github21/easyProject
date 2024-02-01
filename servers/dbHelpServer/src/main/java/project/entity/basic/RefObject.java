package project.entity.basic;

/**
 * 相当于C#的 ref 参数
 * @param <T>
 */
public class RefObject<T>
{
    public T argvalue;
    public RefObject(T refarg)
    {
        argvalue = refarg;
    }
}


//    String receiverUerId = "";
//    String receiverUserName = "";
//    RefObject<String> tempRef_receiverUerId = new RefObject<String>(receiverUerId);
//    RefObject<String> tempRef_receiverUserName = new RefObject<String>(receiverUserName);
//	  this.GetMatterAcceptUser(matterRowGuid, orgCode, tempRef_receiverUerId, tempRef_receiverUserName);
//    receiverUerId = tempRef_receiverUerId.argvalue;
//    receiverUserName = tempRef_receiverUserName.argvalue;