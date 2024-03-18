package MarcoShvy.com.encryptapi.controllers;

import MarcoShvy.com.encryptapi.domain.operation.Operation;
import MarcoShvy.com.encryptapi.dto.OperationDTO;
import MarcoShvy.com.encryptapi.services.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    private OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<Operation> create(@RequestBody OperationDTO operationDTO){
        Operation newOperation = this.operationService.create(operationDTO);

        return ResponseEntity.ok(newOperation);
    }
}
