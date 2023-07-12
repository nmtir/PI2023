package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.Post;

import java.util.List;

public interface IMessageService {
    List<Message> retrieveAllMessages();
    Message addOrUpdateMessage(Message Message);
    Message retrieveMessage(Integer id);
    void removeMessage(Integer id);
    Message addAndAssignMessage(Message message, Integer id, Long UserId,Integer Target);
    Message UpdateSignal(Message message,Integer id);
    Message checkLikeAndUpdateMessage(Message message,Long id);
    Message removeLike(Message message,Long id);
    Message removeSignal(Message message,Long id);
    Message checkSignalAndUpdateMessage(Message message,Long id);
    List<Message> retrieveAllBlockedMessages();
}

