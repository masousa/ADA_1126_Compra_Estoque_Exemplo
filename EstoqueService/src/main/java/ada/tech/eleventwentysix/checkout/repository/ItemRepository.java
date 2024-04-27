package ada.tech.eleventwentysix.checkout.repository;


import ada.tech.eleventwentysix.checkout.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findBySku(String s);
}
