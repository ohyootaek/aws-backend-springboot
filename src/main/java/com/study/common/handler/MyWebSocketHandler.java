package com.study.common.handler;

import com.study.common.model.dto.ChatMessageDto;
import com.study.common.service.RedisService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

public class MyWebSocketHandler extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = new HashSet<>();
    private final RedisService redisService;

    public MyWebSocketHandler(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("WebSocket 연결이 성공적으로 이루어졌습니다.");
        session.sendMessage(new TextMessage("닉네임을 설정하세요."));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // 첫 번째 메시지가 닉네임인 경우
        if (!session.getAttributes().containsKey("nickname")) {
            session.getAttributes().put("nickname", payload);  // 닉네임 설정
            session.sendMessage(new TextMessage("닉네임이 설정되었습니다: " + payload));
        } else {
            String nickname = (String) session.getAttributes().get("nickname");
            System.out.println("받은 메시지: " + payload);
            // 받은 메시지를 ChatMessageDto에 담아서 Redis로 발행
            ChatMessageDto dto = new ChatMessageDto();
            dto.setMessage(payload);
            dto.setSender(nickname);  // 닉네임 사용
            redisService.publish(dto);  // Redis로 메시지 발행
        }
    }

    // WebSocket에 연결된 모든 클라이언트에게 메시지를 전송
    public static void sendToAllClients(String message) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(message));  // 모든 클라이언트에 메시지 전송
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
