package ada.tech.eleventwentysix.checkout.repository;

import ada.tech.eleventwentysix.checkout.model.Compra;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
    @EntityGraph(value = "Compra.items")
    Compra findByIdentificador(String identificadorCompra);
}
