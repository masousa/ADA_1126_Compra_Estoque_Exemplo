package ada.tech.eleventwentysix.checkout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "compra")
@NamedEntityGraph(name = "Compra.items",
        attributeNodes = @NamedAttributeNode("itemList")
)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime dataCompra;

    private BigDecimal valorTotal;

    private StatusCompra status;

    private String identificador;

    @OneToMany(mappedBy = "compra")
    private List<Item> itemList;
}
