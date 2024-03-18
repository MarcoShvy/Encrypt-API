package MarcoShvy.com.encryptapi.services;

import MarcoShvy.com.encryptapi.domain.operation.Operation;
import MarcoShvy.com.encryptapi.domain.operation.exceptions.OperationNotFoundException;
import MarcoShvy.com.encryptapi.dto.OperationDTO;
import MarcoShvy.com.encryptapi.dto.OperationResponseDTO;
import MarcoShvy.com.encryptapi.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationService {

    private OperationRepository repository;

    private EncryptService encryptService;

    public OperationService(OperationRepository repository, EncryptService encryptService) {
        this.repository = repository;
        this.encryptService = encryptService;
    }

    public Operation create(OperationDTO operationDTO) {
        Operation operation = new Operation();

        String userDocumentHashed = this.encryptService.encryptData(operationDTO.userDocument());
        String creditCardHashed = this.encryptService.encryptData(operationDTO.creditCardToken());

        operation.setCreditCardToken(creditCardHashed);
        operation.setUserDocument(userDocumentHashed);
        operation.setValue(operationDTO.operationValue());

        this.repository.save(operation);
        return operation;
    }

    public OperationResponseDTO read(Long id) throws OperationNotFoundException {
        Operation operation = this.repository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));

        String userDocumentHashed = this.encryptService.decryptData(operation.getUserDocument());
        String creditCardHashed = this.encryptService.decryptData(operation.getCreditCardToken());


        OperationResponseDTO dto = new OperationResponseDTO(operation.getId(), userDocumentHashed, creditCardHashed, operation.getValue());

        return dto;
    }

    public void delete(Long id) throws OperationNotFoundException {
        Operation operation = this.repository.findById(id).orElseThrow(() -> new OperationNotFoundException(id));

        repository.delete(operation);
    }
}
