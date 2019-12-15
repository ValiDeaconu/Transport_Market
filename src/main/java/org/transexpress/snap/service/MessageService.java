package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.MessageDal;
import org.transexpress.snap.model.Message;

import java.util.List;
import java.util.Optional;

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
        return messageDal.selectAllMessagesBetweenUsers(firstUserId, secondUserId);
    }

    public Optional<Message> selectMessageByID(int id) {
        return messageDal.selectMessageByID(id);
    }

}
