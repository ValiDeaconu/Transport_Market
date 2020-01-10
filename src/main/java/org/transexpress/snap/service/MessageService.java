package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.MessageDal;
import org.transexpress.snap.dal.UserDal;
import org.transexpress.snap.misc.Tuple;
import org.transexpress.snap.model.Message;
import org.transexpress.snap.model.User;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageDal messageDal;
    private final UserDal userDal;

    @Autowired
    public MessageService(@Qualifier("mysql_messages") MessageDal messageDal,
                          @Qualifier("mysql_users") UserDal userDal) {
        this.messageDal = messageDal;
        this.userDal = userDal;
    }

    public int insertMessage(Message message) {
        return messageDal.insertMessage(message);
    }

    public Tuple<User, User, List<Message>> selectAllMessagesBetweenUsers(int firstUserId, int secondUserId) {
        List<Message> messages = messageDal.selectAllMessages();
        User firstUser = userDal.selectUserByID(firstUserId).orElse(null);
        User secondUser = userDal.selectUserByID(secondUserId).orElse(null);

        if (messages == null || firstUser == null || secondUser == null)
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

        total.sort((message1, message2) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date thisDate = null;
            Date otherDate = null;
            try {
                thisDate = sdf.parse(message1.getDate());
                otherDate = sdf.parse(message2.getDate());
            } catch (ParseException e) {
                System.err.println("ParseException caught: " + e.getMessage());
            }

            if (thisDate == null && otherDate == null)
                return 0;
            if (thisDate != null && otherDate == null)
                return -1;
            if (thisDate == null && otherDate != null)
                return 1;

            return thisDate.compareTo(otherDate);
        });

        return new Tuple<>(firstUser, secondUser, total);
    }

    public Optional<Message> selectMessageByID(int id) {
        return messageDal.selectMessageByID(id);
    }

    public List<User> getAllPersonsWhichTalkedToUserId(int userId) {
        List<Message> allMessages = messageDal.selectAllMessages();

        if (allMessages == null)
            return new ArrayList<>();

        List<Integer> indicesOfPartnersOfUser = allMessages.stream()
                .filter(message -> message.getSenderId() == userId || message.getReceiverId() == userId)
                .map(message -> (message.getSenderId() == userId) ? message.getReceiverId() : message.getSenderId())
                .distinct()
                .collect(Collectors.toList());

        return indicesOfPartnersOfUser.stream()
                .map(index -> userDal.selectUserByID(index).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
