package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Badword;
import tn.eesprit.gestionevenementback.Entities.Message;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.BadwordRepository;
import tn.eesprit.gestionevenementback.Repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadwordServiceImpl implements IBadwordService {
    private final BadwordRepository BadwordRepo;
    private final MessageRepository MessageRepo;
    @Override
    public List<Badword> retrieveAllBadwords(){return BadwordRepo.findAll();}
    @Override
    public Badword addOrUpdateBadword(Badword badword){
        Boolean save=false;
        Badword bw=BadwordRepo.findById(badword.getBadwordId()).orElse(null);
        if(bw!=null && !(badword.getWord().isBlank()||badword.getWord().isEmpty()||badword.getWord().contains(","))){
            save=true;
            bw.setWord(badword.getWord());
            BadwordRepo.save(bw);
        } else if (!(badword.getWord().isBlank()||badword.getWord().isEmpty()||badword.getWord().contains(","))) {
            save=true;
            BadwordRepo.save(badword);
        }
        List<Message> allMsgs=this.MessageRepo.findAll();
        List<Badword> allBw=this.BadwordRepo.findAll();
        for (Message message : allMsgs) {
            message.setIsBlocked(0);
            boolean containsBadWord = false;
            for (Badword bword : allBw) {
                if (message.getContenu().toLowerCase().contains(bword.getWord().toLowerCase())) {
                    containsBadWord = true;
                    break;
                }
            }
            if (containsBadWord) {
                message.setIsBlocked(1);
                MessageRepo.save(message);
            }else MessageRepo.save(message);
        }
        if (save){return badword;}
        else return null;
    }
    @Override
    public List<Badword> addOrUpdateBadwords(String badwords){
        List<Badword> allBw= this.BadwordRepo.findAll();
        List<Message> allMsgs=this.MessageRepo.findAll();
        List<Badword> addedBws= new ArrayList<>();
        if(badwords.startsWith(",")){badwords=badwords.substring(1);}
        if(badwords.endsWith(",")){badwords=badwords.substring(0,badwords.length()-1);}
        String[] words = badwords.split(",");
        for (String word : words) {
            word = word.trim();
            boolean wordExists = false;
            for (Badword bw : allBw) {
                if (bw.getWord().equalsIgnoreCase(word)) {
                    wordExists = true;
                    break;

                }
            }
            if (!wordExists && !(word.isBlank()||word.isEmpty())) {
                Badword badword=new Badword();
                badword.setWord(word);
                BadwordRepo.save(badword);
                addedBws.add(badword);
            } else {
                System.out.println(word + " is not a new word.");
        }
        }
        for (Message message : allMsgs) {
            message.setIsBlocked(0);
            boolean containsBadWord = false;
            for (Badword badword : allBw) {
                if (message.getContenu().toLowerCase().contains(badword.getWord().toLowerCase())) {
                    containsBadWord = true;
                    break;
                }
            }
            if (containsBadWord) {
                message.setIsBlocked(1);
                MessageRepo.save(message);
            }else MessageRepo.save(message);
        }
        return addedBws;
    }
    @Override
    public Badword retrieveBadword(Integer id){return BadwordRepo.findById(id).orElse(null);}
    @Override
    public void removeBadword(Integer id){
        BadwordRepo.deleteById(id);
        List<Badword> allBw= this.BadwordRepo.findAll();
        List<Message> allMsgs=this.MessageRepo.findAll();
        for (Message message : allMsgs) {
            message.setIsBlocked(0);
            boolean containsBadWord = false;
            for (Badword badword : allBw) {
                if (message.getContenu().toLowerCase().contains(badword.getWord().toLowerCase())) {
                    containsBadWord = true;
                    break;
                }
            }
            if (containsBadWord) {
                message.setIsBlocked(1);
                MessageRepo.save(message);
            } else MessageRepo.save(message);
        }
    }
}
