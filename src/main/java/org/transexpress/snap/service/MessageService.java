package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.MessageDal;
import org.transexpress.snap.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageDal messageDal;

    @Autowired
    public MessageService(@Qualifier("mysql_messages") MessageDal messageDal) {
        this.messageDal = messageDal;
    }

    public int insertMessage(Message message) {
        return messageDal.insertMessage(message);
    }

    public List<Message> selectAllMessagesBetweenUsers(int firstUserId, int secondUserId) {
        List<Message> messages = messageDal.selectAllMessages();

        if (messages == null)
            return null;

        List<Message> messagesFromUser1ToUser2 = messages.stream()
                .filter(m -> m.getSenderId() == firstUserId && m.getReceiverId() == secondUserId)
                .collect(Collectors.toList());
        List<Message> messagesFromUser2ToUser1 = messages.stream()
                .filter(m -> m.getSenderId() == secondUserId && m.getReceiverId() == firstUserId)
                .collect(Collectors.toList());

        List<Message> total = new ArrayList<>();
        total.addAll(messagesFromUser1ToUser2);
        total.addAll(messagesFromUser2ToUser1);

        return total;
    }

    public Optional<Message> selectMessageByID(int id) {
        return messageDal.selectMessageByID(id);
    }

}
