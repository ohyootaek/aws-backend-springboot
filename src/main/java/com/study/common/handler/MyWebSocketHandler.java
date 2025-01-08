package com.study.common.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 연결이 성공적으로 이루어졌을 때 호출되는 메서드
        System.out.println("WebSocket 연결이 성공적으로 이루어졌습니다.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메시지를 받았을 때 호출되는 메서드
        System.out.println("클라이언트로부터 받은 메시지: " + message.getPayload());

        // 클라이언트에게 응답 메시지 보내기
        session.sendMessage(new TextMessage("서버에서 받은 메시지: " + message.getPayload()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 에러가 발생했을 때 호출되는 메서드
        System.out.println("WebSocket 에러 발생: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // WebSocket 연결이 종료된 후 호출되는 메서드
        System.out.println("WebSocket 연결이 종료되었습니다.");
    }
}

