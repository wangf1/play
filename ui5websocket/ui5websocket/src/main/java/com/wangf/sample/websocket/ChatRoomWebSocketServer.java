package com.wangf.sample.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ChatRoomWebSocketServer {
	// The socket server is NOT application level, so must use static map to
	// store the sessions.
	private static final Set<Session> sessions = new HashSet<>();

	@OnOpen
	public void open(Session session) {
		sessions.add(session);
	}

	@OnClose
	public void close(Session session) {
		sessions.remove(session);
	}

	@OnError
	public void onError(Throwable error) {
	}

	@OnMessage
	public void handleMessage(String message, Session session) throws IOException {
		sendToAllConnectedSessions(message);
	}

	private void sendToAllConnectedSessions(String message) throws IOException {
		for (Session session : sessions) {
			sendToSession(session, message);
		}
	}

	private void sendToSession(Session session, String message) throws IOException {
		try {
			session.getBasicRemote().sendText(message.toString());
		} catch (IOException ex) {
			sessions.remove(session);
			throw ex;
		}
	}
}
