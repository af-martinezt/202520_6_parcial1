package uniandes.dse.examen1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.entities.ContractEntity;
import uniandes.dse.examen1.exceptions.InvalidContractException;
import uniandes.dse.examen1.repositories.SupplierRepository;
import uniandes.dse.examen1.repositories.FactoryRepository;
import uniandes.dse.examen1.repositories.ContractRepository;

@Slf4j
@Service
public class ContractService {

    @Autowired
    FactoryRepository factoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ContractRepository contractRepository;

    public ContractEntity createContract(String factoryName, String supplierCode, Double contractValue)
            throws InvalidContractException {
        // TODO
        if (contractValue <= 0) {
            throw new InvalidContractException("El valor del contrato debe ser un número positivo");
        }
        if (factoryRepository.findByName(factoryName) == null) {
            throw new InvalidContractException("No existe una fábrica con ese nombre");
        }
        if (supplierRepository.findBySupplierCode(supplierCode) == null) {
            throw new InvalidContractException("No existe un proveedor con ese código");
        }


        return contractRepository();
    }
}
