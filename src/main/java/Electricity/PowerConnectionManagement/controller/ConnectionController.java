package Electricity.PowerConnectionManagement.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Electricity.PowerConnectionManagement.entity.Connection;
import Electricity.PowerConnectionManagement.exception.ResourceNotFoundException;
import Electricity.PowerConnectionManagement.payload.ConnectionPatchDto;
import Electricity.PowerConnectionManagement.repository.ConnectionRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class ConnectionController {
    
    @Autowired
    private ConnectionRepository connectionRepository;

    // get all connections
    @SuppressWarnings("unchecked")
    @GetMapping("/connections")
    public Page<Connection> getAllConnections(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam @DateTimeFormat(pattern = "dd/MM/yy") Optional<Date> startDate,
        @RequestParam @DateTimeFormat(pattern = "dd/MM/yy") Optional<Date> endDate
    ) {
        PageRequest pageable = PageRequest.of(page, pageSize);
        if (startDate.isPresent() && endDate.isPresent()){

            return  (Page<Connection>) connectionRepository.findByDateOfApplicationBetween(startDate.get(), endDate.get(),pageable);
        } else {
        return connectionRepository.findAll(pageable);
        }
    }

    // create connection rest api
    @SuppressWarnings("null")
    @PostMapping("/connections")
    public Connection createConnection(@RequestBody Connection connection) {
        return connectionRepository.save(connection);
    }

    // get connection by id rest api
    @GetMapping("/connections/{id}")
    public ResponseEntity <Connection> getConnectionById(@PathVariable Long id) {
        Connection connection = connectionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Connection not exist with id :" + id));
            System.out.println(connection.getOwnerShip());
        return ResponseEntity.ok(connection);
    }

    // update connection rest api
    //Todo add reviewer connection.
@PutMapping("/connections/{id}")
public ResponseEntity<Connection> updateConnection(@PathVariable Long id, @RequestBody Connection connectionDetails) {
    Optional<Connection> optionalConnection = connectionRepository.findById(id);
    
    if (!optionalConnection.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    
    Connection connection = optionalConnection.get();
     // Update connection details with the provided connectionDetails
     connection.setApplicantName(connectionDetails.getApplicantName());
     connection.setGender(connectionDetails.getGender());
     connection.setDistrict(connectionDetails.getDistrict());
     connection.setState(connectionDetails.getState());
     connection.setPincode(connectionDetails.getPincode());
     connection.setOwnerShip(connectionDetails.getOwnerShip());
     connection.setGovtIdType(connectionDetails.getGovtIdType());
     connection.setIdNumber(connectionDetails.getIdNumber());
     connection.setCategory(connectionDetails.getCategory());
     connection.setLoadApplied(connectionDetails.getLoadApplied());
     connection.setDateOfApproval(connectionDetails.getDateOfApproval());
     connection.setModifiedDate(connectionDetails.getModifiedDate());
     connection.setStatus(connectionDetails.getStatus());
     connection.setReviewerId(connectionDetails.getReviewerId());
     connection.setReviewerName(connectionDetails.getReviewerName());
     connection.setReviewerComments(connectionDetails.getReviewerComments());
    // Save the updated connection
    @SuppressWarnings("null")
    Connection updatedConnection = connectionRepository.save(connection);
    
    return ResponseEntity.ok(updatedConnection);
}

    // delete connection rest api
    @DeleteMapping("/connections/{id}")
    public ResponseEntity < Map < String, Boolean >> deleteConnection(@PathVariable Long id) {
        Connection connection = connectionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Connection not exist with id :" + id));

        connectionRepository.delete(connection);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
