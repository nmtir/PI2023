package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Transport;

import java.util.List;

public interface ITransportService {
    List<Transport> retrieveAllTransports();
    Transport addOrUpdateTransport(Transport transport);
    Transport retrieveTransport(Integer id);
    void removeTransport(Integer id);
    Transport addTransportAndAssignToEvent(Long eventId,Transport transport);
}
