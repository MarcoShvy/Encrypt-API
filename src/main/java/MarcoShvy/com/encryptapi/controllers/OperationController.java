package MarcoShvy.com.encryptapi.controllers;

import MarcoShvy.com.encryptapi.domain.operation.Operation;
import MarcoShvy.com.encryptapi.dto.OperationDTO;
import MarcoShvy.com.encryptapi.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.html.parser.Entity;
import java.net.URI;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<Operation> create(@RequestBody OperationDTO operationDTO, UriComponentsBuilder uriBuilder){
        Operation newOperation = this.operationService.create(operationDTO);

        var uri = uriBuilder.path("/api/operation/{id}").buildAndExpand(newOperation.getId()).toUri();


        return ResponseEntity.created(uri).body(newOperation);
    }

    @GetMapping
}
