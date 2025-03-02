package easy.project.note.WebSocket相关;


import javax.websocket.Session;

public class WebSocket {
    /**
     * session
     */
    private Session session;
    /**
     * 账号id
     */
    private String userId;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

