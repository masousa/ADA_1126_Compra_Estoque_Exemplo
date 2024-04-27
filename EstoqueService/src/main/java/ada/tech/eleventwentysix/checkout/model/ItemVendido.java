package ada.tech.eleventwentysix.checkout.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "item_vendido")
public class ItemVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String sku;

    private BigDecimal valorUnitario;

    private Integer quantidade;


    private String compraId;

    private String identificador;

    public Integer getQuantidade() {
        return Objects.isNull(quantidade)?0:quantidade;
    }
}
