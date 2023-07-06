package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Badword;
import tn.eesprit.gestionevenementback.Repository.BadwordRepository;

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
    public Badword retrieveBadword(Integer id){return BadwordRepo.findById(id).orElse(null);}
    @Override
    public void removeBadword(Integer id){BadwordRepo.deleteById(id);}
}
