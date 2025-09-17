package uniandes.dse.examen1.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class SupplierEntity {

    @PodamExclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique code for the supplier
     */
    private String supplierCode;

    /**
     * The full name for the supplier
     */
    private String name;

    /**
     * The maximum number of contracts that the supplier can handle. If the number
     * is -1, it means that there is no limit.
     */
    private Integer capacity;

    // TODO
    @PodamExclude
    @ManyToOne
    private FactoryEntity factory; 

}
