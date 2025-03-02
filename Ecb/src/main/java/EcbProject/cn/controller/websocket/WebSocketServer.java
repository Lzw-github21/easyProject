//package EcbProject.cn.controller.websocket;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//@ServerEndpoint("/websocket")
//public class WebSocketServer {
//
//    // 保存所有连接的客户端
//    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
//
//    @OnOpen
//    public void onOpen(Session session) {
//        sessions.add(session);
//        System.out.println("New connection: " + session.getId());
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        sessions.remove(session);
//        System.out.println("Connection closed: " + session.getId());
//    }
//
//    @OnMessage
//    public void onMessage(String message, Session session) throws IOException {
//        System.out.println("Message received from " + session.getId() + ": " + message);
//        // 广播消息给所有客户端
//        for (Session s : sessions) {
//            s.getBasicRemote().sendText(message);
//        }
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        System.err.println("Error occurred in session " + session.getId());
//        throwable.printStackTrace();
//    }
//}