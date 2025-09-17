package uniandes.dse.examen1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.entities.FactoryEntity;
import uniandes.dse.examen1.exceptions.RepeatedFactoryException;
import uniandes.dse.examen1.repositories.FactoryRepository;

@Slf4j
@Service
public class FactoryService {

    @Autowired
    FactoryRepository factoryRepository;

    public FactoryEntity createFactory(FactoryEntity newFactory) throws RepeatedFactoryException {
        // TODO
        log.info("Crear nueva fábrica. No puede tener el mismo nombre de una ya existente.");
        if (factoryRepository.findByName(newFactory.getName()) != null) {
            throw new RepeatedFactoryException("Ya existe una fábrica con ese nombre");
        }
        return factoryRepository.save(newFactory);
    }

}