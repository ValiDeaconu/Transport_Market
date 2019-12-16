package org.transexpress.snap.dal;

import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDal {
    int insertMessage(Message message);

    Optional<Message> selectMessageByID(int id);

    List<Message> selectAllMessages();

    int deleteMessageByID(int id);

    int updateMessageByID(int id, Message message);
}
