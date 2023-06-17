package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.*;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Repository.MessageRepository;
import tn.eesprit.gestionevenementback.Repository.PostRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService{
    private final MessageRepository MessageRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    @Override
    public List<Message> retrieveAllMessages(){return MessageRepo.findAll();}
    @Override
    public Message addOrUpdateMessage(Message message){return MessageRepo.save(message);}
    @Override
    public Message UpdateSignal(Message message,Integer id){
       /* User user=userRepo.findById(id).orElse(null);
        message.getSignalUsers().add(user);
        message.setCountSignal(message.getCountSignal()+1);
        if (message.getCountSignal()>10) {
            message.setSignal(1);
        }
        else message.setSignal(0);*/
        return MessageRepo.save(message);
    }
    @Override
    public Message checkLikeAndUpdateMessage(Message message,Integer id){
        Message m=MessageRepo.findById(message.getMessageId()).orElse(null);
        User user=userRepo.findById(id).orElse(null);
        for (User u:m.getLikes())
        {
            if (u==user)
            {
                return m;
            }
        }
        m.getLikes().add(user);
        return MessageRepo.save(m);

    }
    @Override
    public Message removeLike(Message message,Integer id){
        Message m=MessageRepo.findById(message.getMessageId()).orElse(null);
        User user=userRepo.findById(id).orElse(null);
        for (User u:m.getLikes())
        {
            if (u==user)
            {
                m.getLikes().remove(u);
                return MessageRepo.save(m);
            }
        }
        return m;

    }
    @Override
    public Message addAndAssignMessageToForum(Message message, Integer id, Integer UserId){
        Post post=postRepo.findById(id).orElse(null);
        User user=userRepo.findById(UserId).orElse(null);
        message.setUser(user);
        Date date = new Date();
        message.setIsBlocked(0);
        message.setDatePub(date);
        message.setPost(post);
        return MessageRepo.save(message);
    }
    @Override
    public Message retrieveMessage(Integer id){return MessageRepo.findById(id).orElse(null);}
    @Override
    public void removeMessage(Integer id){MessageRepo.deleteById(id);}

}
