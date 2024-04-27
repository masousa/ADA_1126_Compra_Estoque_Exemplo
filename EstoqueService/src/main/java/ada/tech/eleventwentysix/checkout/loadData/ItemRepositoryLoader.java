package ada.tech.eleventwentysix.checkout.loadData;

import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ItemRepositoryLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        if(itemRepository.count()==0) {
            createItems();
        }
    }

    private void createItems() {
        IntStream.range(1, 51)
                .mapToObj(this::generateItem).forEach(itemRepository::save);
    }

    private Item generateItem(int i) {
        if(i==0){
            i = 1;
        }
        Random random = new Random();

        Item item = new Item();
        item.setIdentificador(UUID.randomUUID().toString());
        item.setValorUnitario(BigDecimal.valueOf(random.nextInt(i)* 100L));
        item.setSku(String.format("Z%04d", i));
        item.setQuantidadeEstoque(i*100);

        return item;
    }
}
