package ada.tech.eleventwentysix.checkout.rest;

import ada.tech.eleventwentysix.checkout.payloads.request.ItemRequest;
import ada.tech.eleventwentysix.checkout.payloads.response.ItemResponse;
import ada.tech.eleventwentysix.checkout.payloads.response.EstoqueResponse;
import ada.tech.eleventwentysix.checkout.service.ListarItensService;
import ada.tech.eleventwentysix.checkout.service.PesquisarPorSkuService;
import ada.tech.eleventwentysix.checkout.service.RemoverItensService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(name = "/item")
@RequiredArgsConstructor
@Slf4j
public class EstoqueController {

    private final ListarItensService listarItensService;
    private final PesquisarPorSkuService pesquisarPorSkuService;
    private final RemoverItensService removerItensService;

    @Operation(summary = "Remover Itens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item Adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao tentar remover um item"),
    })
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueResponse removerItens(@RequestBody List<ItemRequest> itens){

        log.info("Requisicao recebida {}",itens);

        return removerItensService.execute(itens, false);
    }

    @Operation(summary = "Consultar Estoque Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item em estoque"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado"),
    })
    @GetMapping(value = "/query/{sku}")
    public ItemResponse pesquisarItem(@PathVariable("sku") String sku){
        return pesquisarPorSkuService.execute(sku);
    }

    @GetMapping(value = "/")
    public List<ItemResponse> listarItens(){
        return listarItensService.execute();
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Adicionar um item ao Estoque")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse adicionarItem(@RequestBody ItemRequest itemRequest){
        return null;
    }

    @PostMapping(value = "/add/{idCompra}")
    @Operation(summary = "Adicionar um item ao Estoque por estorno de compra")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse estornarItem(@PathVariable(name = "compraID") String compraId, @RequestBody ItemRequest itemRequest){
        return null;
    }
}
