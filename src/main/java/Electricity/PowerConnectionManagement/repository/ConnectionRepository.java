package Electricity.PowerConnectionManagement.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import Electricity.PowerConnectionManagement.entity.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
   @SuppressWarnings("null")
Optional<Connection> findById(Long id);

Page<Connection> findByDateOfApplicationBetween(Date startDate, Date endDate, PageRequest pageable);

}
