package uniandes.dse.examen1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uniandes.dse.examen1.entities.FactoryEntity;
import uniandes.dse.examen1.entities.SupplierEntity;
import uniandes.dse.examen1.exceptions.RepeatedFactoryException;
import uniandes.dse.examen1.exceptions.RepeatedSupplierException;
import uniandes.dse.examen1.services.SupplierService;
import uniandes.dse.examen1.entities.SupplierEntity;

@DataJpaTest
@Transactional
@Import(SupplierService.class)
public class SupplierServiceTest {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateSupplier() throws RepeatedSupplierException {
        // TODO
        SupplierEntity newSupplier = factory.manufacturePojo(SupplierEntity.class);
        String name = newSupplier.getName();
        try {
            SupplierEntity storedEntity = SupplierService.createSupplier(newSupplier);
            SupplierEntity retrieved = entityManager.find(SupplierEntity.class, storedEntity.getId());
            assertEquals(name, retrieved.getName(), "The name is incorrect");
        } catch (RepeatedFactoryException e) {
            fail("No exception should be thrown: " + e.getMessage());
        }
    }

    @Test
    void testCreateRepeatedSupplier() {
        // TODO
        SupplierEntity firstEntity = factory.manufacturePojo(SupplierEntity.class);
        String name = firstEntity.getName();

        SupplierEntity repeatedEntity = new SupplierEntity();
        repeatedEntity.setName(name);

        try {
            supplierService.createSupplier(firstEntity);
            supplierService.createSupplier(repeatedEntity);
            fail("An exception must be thrown");
        } catch (Exception e) {
        }
    }
}
