package ada.tech.eleventwentysix.checkout.service;

import ada.tech.eleventwentysix.checkout.mapping.ConvertItemToItemResponse;
import ada.tech.eleventwentysix.checkout.model.Item;
import ada.tech.eleventwentysix.checkout.model.ItemVendido;
import ada.tech.eleventwentysix.checkout.payloads.request.ItemRequest;
import ada.tech.eleventwentysix.checkout.payloads.response.EstoqueResponse;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import ada.tech.eleventwentysix.checkout.queue.RemocaoItensPublisher;
import ada.tech.eleventwentysix.checkout.repository.ItemRepository;
import ada.tech.eleventwentysix.checkout.repository.ItemVendidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemoverItensService {
    private final ItemVendidoRepository itemVendidoRepository;
    private final ItemRepository itemRepository;
    private final RemocaoItensPublisher remocaoItensPublisher;

    public EstoqueResponse execute (List<ItemRequest> items, boolean enviarProvider){
      log.info("Remoção de itens invocado");
      EstoqueResponse estoqueResponse = new EstoqueResponse();
      estoqueResponse.setItemsRemovidos(new ArrayList<>());


      estoqueResponse.setIdRemocao(items.iterator().next().getCompraId());

      List<ItemVendido> itensVendidos = new ArrayList<>();
      List<Item> itensAlterados = new ArrayList<>();
      for (ItemRequest itemRequest : items){
          //Traz o item somente se ele existir no banco E tiver uma quantidade
          // igual ou maior que a requisitada
          Optional<Item> optionalItem = itemRepository.findBySku(itemRequest.getSkuId())
                  .filter(item -> item.getQuantidadeEstoque()>=itemRequest.getQuantidade())
                  .stream().findFirst();
          if(optionalItem.isPresent()){

              ItemVendido itemVendido = new ItemVendido();
              //Gerando um identificador aleatorio
              itemVendido.setIdentificador(UUID.randomUUID().toString());
              itemVendido.setValorUnitario(BigDecimal.ZERO);
              itemVendido.setQuantidade(itemRequest.getQuantidade());
              itemVendido.setSku(itemRequest.getSkuId());
              itemVendido.setCompraId(itemRequest.getCompraId());
              itensVendidos.add(itemVendido);
              Item item = optionalItem.get();
              item.setQuantidadeEstoque(item.getQuantidadeEstoque()-itemRequest.getQuantidade());
              itensAlterados.add(item);
          }
      }

      // Verifica se o número de itens vendidos é o mesmo do requisitado
        if(itensVendidos.size()==items.size()){
            log.info("todos os itens requisitados foram processados. {}", itensVendidos);
            itensVendidos.forEach(itemVendidoRepository::save);
            List<ItemResponse> itemResponses = itensAlterados.stream().map(itemRepository::save)
                    .map(ConvertItemToItemResponse::convert).toList();
            estoqueResponse
                    .setItemsRemovidos(itemResponses.stream().map(ItemResponse::getSkuId).toList());
        }

        // Somente para utilização do RabbitMQ ao invés do REST
        if(enviarProvider){
            remocaoItensPublisher.publish(estoqueResponse);
        }


      return estoqueResponse;
    }

}
