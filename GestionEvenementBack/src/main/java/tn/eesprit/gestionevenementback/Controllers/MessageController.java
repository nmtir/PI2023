package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.Post;
import tn.eesprit.gestionevenementback.Services.IMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final IMessageService messageService;
    @PostMapping("/add")
    Message addMessage(@RequestBody Message message)
    {
        return messageService.addOrUpdateMessage(message);
    }
    @PostMapping("/add/{id}/{idUser}/{Target}")
    Message addMessageToPost(@RequestBody Message message, @PathVariable("id") Integer id,@PathVariable("idUser") Long idUser,@PathVariable("Target") Integer Targer)
    {
        return messageService.addAndAssignMessage(message,id,idUser,Targer);
    }
    @PutMapping("/update")
    Message updateMessage(@RequestBody Message message){
        return messageService.addOrUpdateMessage(message);
    }
    @PutMapping("/update/signal/{id}")
    Message updateMessageSignal(@RequestBody Message message,@PathVariable("id") Integer id){
        return messageService.UpdateSignal(message,id);
    }
    @PutMapping("/likes/update/{id}")
    Message checkLikesAndUpdateMessage(@RequestBody Message message,@PathVariable("id") Long id){
        return messageService.checkLikeAndUpdateMessage(message,id);
    }
    @PutMapping("/likes/remove/{id}")
    Message removeLike(@RequestBody Message message,@PathVariable("id") Long id){
        return messageService.removeLike(message,id);
    }
    @PutMapping("/signal/update/{id}")
    Message checkSignalAndUpdateMessage(@RequestBody Message message,@PathVariable("id") Long id){
        return messageService.checkSignalAndUpdateMessage(message,id);
    }
    @PutMapping("/signal/remove/{id}")
    Message removesignal(@RequestBody Message message,@PathVariable("id") Long id){
        return messageService.removeSignal(message,id);
    }
    @GetMapping("/get/{id}")
    Message getMessage(@PathVariable("id") Integer id){
        return messageService.retrieveMessage(id);
    }
    @GetMapping("/all")
    List<Message> getAllMessages(){return messageService.retrieveAllMessages();}
    @GetMapping("/all-blocked")
    List<Message> getAllBlockedMessages(){return messageService.retrieveAllBlockedMessages();}
    @DeleteMapping("/delete/{id}")
    void deleteMessage(@PathVariable("id") Integer id){
        messageService.removeMessage(id);
    }

}
