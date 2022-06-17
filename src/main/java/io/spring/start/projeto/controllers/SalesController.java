package io.spring.start.projeto.controllers;

import io.spring.start.projeto.dtos.SalesDto;
import io.spring.start.projeto.model.ClientModel;
import io.spring.start.projeto.model.ProductModel;
import io.spring.start.projeto.model.SalesModel;
import io.spring.start.projeto.services.ClientService;
import io.spring.start.projeto.services.ProductService;
import io.spring.start.projeto.services.SalesService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/venda")
public class SalesController {
    //cliente cadastra a compra
    //cliente cancela a compra
    final SalesService salesService;
    final ClientService clientService;
    final ProductService productService;

    public SalesController(SalesService salesService, ClientService clientService, ProductService productService) {
        this.salesService = salesService;
        this.clientService = clientService;
        this.productService = productService;
    }
    // No caso preciso verificar se existe tanto o cliente quanto o produto, não faz sentido vender o produto null
    @PostMapping
    public ResponseEntity<Object> saveSales(@RequestBody @Valid @NotNull SalesDto salesDto){
                    //verificação
        Optional<ProductModel> productModelOptional = productService.findById(salesDto.getProduct());
        if(!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Product not exists");
        }
        Optional<ClientModel> clientModelOptional = clientService.findById(salesDto.getClient());
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Client not exists");
        }
                    //instancia do objeto
        var salesModel = new SalesModel();
                    //deu certo nem mexo mais kkkkk
        salesModel.setClient(clientModelOptional.get());
        salesModel.setProduct(productModelOptional.get());
        salesModel.setAmount(salesDto.getAmount());

        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.save(salesModel));
    }

    @GetMapping
    public ResponseEntity<List<SalesModel>> getSales(){
        return ResponseEntity.status(HttpStatus.OK).body(salesService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> getOneSales(@PathVariable(value = "id") UUID id){
        Optional<SalesModel> salesModelOptional = salesService.findById(id);

        if(!salesModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: not found sales");
        }

        return ResponseEntity.status(HttpStatus.OK).body(salesModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSales(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid SalesDto salesDto){
        Optional<SalesModel> salesModelOptional = salesService.findById(id);
        if(!salesModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: not found and sales");
        }
        var salesModel = salesModelOptional.get();

        salesModel.setAmount(salesDto.getAmount());

        return ResponseEntity.status(HttpStatus.OK).body(salesService.update(salesModel));
    }
}
