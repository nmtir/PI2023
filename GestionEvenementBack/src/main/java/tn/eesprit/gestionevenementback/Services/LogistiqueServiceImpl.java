package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Logistique;
import tn.eesprit.gestionevenementback.Entities.Ordre;
import tn.eesprit.gestionevenementback.Repository.LogistiqueRepository;
import tn.eesprit.gestionevenementback.Repository.OrdreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogistiqueServiceImpl implements ILogistiqueService{
    private final LogistiqueRepository logistiqueRepo;
    private final OrdreRepository ordreRepo;
    @Override
    public List<Logistique> retrieveAllLogistiques(){return logistiqueRepo.findAll();}
    @Override
    public Logistique addOrUpdateLogistique(Logistique logistique){return logistiqueRepo.save(logistique);}

    @Override
    public Logistique calculDepensesMaterielLogistique(Logistique logistique){
               Logistique logistique1 =logistiqueRepo.findById(logistique.getLogistiqueId()).orElse(null);
                List<Ordre> ordres=ordreRepo.findByLogistiqueLogistiqueId(logistique.getLogistiqueId());
                Float D = (float) 0;
                for (Ordre i:ordres) {
                    D=D+(i.getProduct().getPrice()*i.getQuantity());}
                logistique=logistique1;
                logistique.setDepenses(D);
        return logistiqueRepo.save(logistique);
    }
    @Override
    public Logistique retrieveLogistique(Integer id){return logistiqueRepo.findById(id).orElse(null);}
    @Override
    public Logistique retrieveLogistiqueByEventId(Long id){return logistiqueRepo.findByEventId(id);}


    @Override
    public void removeLogistique(Integer id){logistiqueRepo.deleteById(id);}

}
