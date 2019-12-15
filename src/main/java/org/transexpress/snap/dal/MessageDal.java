package org.transexpress.snap.dal;

import org.transexpress.snap.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageDal {
    int insertMessage(Message message);

    List<Message> selectAllMessagesBetweenUsers(int firstUserId, int secondUserId);

    Optional<Message> selectMessageByID(int id);
}
