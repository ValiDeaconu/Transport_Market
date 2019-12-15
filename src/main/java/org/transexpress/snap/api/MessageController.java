package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.model.Message;
import org.transexpress.snap.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/message")
@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {

        this.messageService = messageService;
    }

    @PostMapping
    public void addMessage(@Valid @NonNull @RequestBody Message message) {
        messageService.insertMessage(message);
    }

    @GetMapping(path = "{firstUserId}/{secondUserId}")
    public List<Message> getAllMessagesBetweenUsers(@PathVariable("firstUserId") int firstUserId,
                                                       @PathVariable("secondUserId") int secondUserId) {
        return messageService.selectAllMessagesBetweenUsers(firstUserId, secondUserId);
    }

    @GetMapping(path = "{id}")
    public Message getMessageByID(@PathVariable("id") int id) {
        return messageService.selectMessageByID(id).orElse(null);
    }
}
