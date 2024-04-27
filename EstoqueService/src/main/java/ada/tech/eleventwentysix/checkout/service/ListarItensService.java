package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.mapping.ConvertItemToItemResponse;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import ada.tech.eleventwentysix.checkout.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListarItensService {

    private final ItemRepository itemRepository;

    public List<ItemResponse> execute(){
        log.info("Listagem dos itens contidos no estoque");
        return itemRepository.findAll().stream().map(ConvertItemToItemResponse::convert).toList();
    }
}
