package MarcoShvy.com.encryptapi.repositories;

import MarcoShvy.com.encryptapi.domain.operation.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
