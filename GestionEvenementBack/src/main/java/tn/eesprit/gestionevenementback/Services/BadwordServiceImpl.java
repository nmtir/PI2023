package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Badword;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.BadwordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BadwordServiceImpl implements IBadwordService {
    private final BadwordRepository BadwordRepo;
    @Override
    public List<Badword> retrieveAllBadwords(){return BadwordRepo.findAll();}
    @Override
    public Badword addOrUpdateBadword(Badword badword){return BadwordRepo.save(badword);}
    @Override
    public List<Badword> addOrUpdateBadwords(String badwords){
        List<Badword> allBw= this.BadwordRepo.findAll();
        List<Badword> addedBws= new ArrayList<>();
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
            if (!wordExists) {
                Badword badword=new Badword();
                badword.setWord(word);
                BadwordRepo.save(badword);
                addedBws.add(badword);
            } else {
                System.out.println(word + " is not a new word.");
        }
        }
        return addedBws;
    }
    @Override
    public Badword retrieveBadword(Integer id){return BadwordRepo.findById(id).orElse(null);}
    @Override
    public void removeBadword(Integer id){BadwordRepo.deleteById(id);}
}
