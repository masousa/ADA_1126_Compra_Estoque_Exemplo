package ada.tech.eleventwentysix.checkout.repository;

import ada.tech.eleventwentysix.checkout.model.ItemVendido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendidoRepository extends JpaRepository<ItemVendido, Long> {
    List<ItemVendido> findBySku(String s);
    List<ItemVendido> findByCompraId(String compraId);
}
