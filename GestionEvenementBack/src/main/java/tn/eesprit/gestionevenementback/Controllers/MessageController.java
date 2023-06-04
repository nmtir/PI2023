package tn.eesprit.gestionevenementback.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.Post;
import tn.eesprit.gestionevenementback.Services.IMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final IMessageService messageService;
    @PostMapping("/add")
    Message addMessage(@RequestBody Message message)
    {
        return messageService.addOrUpdateMessage(message);
    }
    @PostMapping("/add/{id}/{idUser}")
    Message addMessageToPost(@RequestBody Message message, @PathVariable("id") Integer id,@PathVariable("idUser") Integer idUser)
    {
        return messageService.addAndAssignMessageToForum(message,id,idUser);
    }
    @PutMapping("/update")
    Message updateMessage(@RequestBody Message message){
        return messageService.addOrUpdateMessage(message);
    }
    @PutMapping("/update/signal/{id}")
    Message updateMessageSignal(@RequestBody Message message,@PathVariable("id") Integer id){
        return messageService.UpdateSignal(message,id);
    }
    @GetMapping("/get/{id}")
    Message getMessage(@PathVariable("id") Integer id){
        return messageService.retrieveMessage(id);
    }
    @GetMapping("/all")
    List<Message> getAllMessages(){return messageService.retrieveAllMessages();}
    @DeleteMapping("/delete/{id}")
    void deleteMessage(@PathVariable("id") Integer id){
        messageService.removeMessage(id);
    }

}
