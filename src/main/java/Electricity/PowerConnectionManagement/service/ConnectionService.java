package Electricity.PowerConnectionManagement.service;
import java.util.List;
import org.springframework.data.domain.Page;

import Electricity.PowerConnectionManagement.entity.Connection;


public interface ConnectionService {
    Page<Connection> findPaginated(int pageNo, int pageSize); 
}
