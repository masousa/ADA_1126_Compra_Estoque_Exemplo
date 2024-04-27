package ada.tech.eleventwentysix.checkout.client;

import ada.tech.eleventwentysix.checkout.client.payload.EstoqueResponse;
import ada.tech.eleventwentysix.checkout.client.payload.ItemEstoqueRequest;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface EstoqueClient {
    @PostExchange(value = "/")
    EstoqueResponse removerItens(@RequestBody List<ItemEstoqueRequest> itens);
    @GetExchange(value = "/{sku}")
    ItemResponse pesquisarItem(@PathVariable("sku") String sku);
}
