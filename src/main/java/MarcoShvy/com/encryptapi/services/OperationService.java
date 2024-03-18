package MarcoShvy.com.encryptapi.services;

import MarcoShvy.com.encryptapi.domain.operation.Operation;
import MarcoShvy.com.encryptapi.dto.OperationDTO;
import MarcoShvy.com.encryptapi.repositories.OperationRepository;

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
}
