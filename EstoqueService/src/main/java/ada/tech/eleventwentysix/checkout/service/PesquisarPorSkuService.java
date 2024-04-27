package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.exceptions.ItemNotFoundException;
import ada.tech.eleventwentysix.checkout.mapping.ConvertItemToItemResponse;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import ada.tech.eleventwentysix.checkout.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PesquisarPorSkuService {
    private final ItemRepository itemRepository;

    public ItemResponse execute(String sku){
        return ConvertItemToItemResponse.convert(itemRepository.findBySku(sku)
                .orElseThrow(ItemNotFoundException::new));

    }
}
