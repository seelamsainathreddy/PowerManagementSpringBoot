package Electricity.PowerConnectionManagement.entity;


import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
@Entity
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String applicantName;
    private String gender;
    private String district;
    private String state;
    private Long pincode;
    private String ownerShip;
    private String govtIdType;

    private BigInteger idNumber;
    private String category;
    private Integer loadApplied;

    @JsonFormat(pattern="dd-MM-yy")
    private Date dateOfApplication;

    @JsonFormat(pattern="dd-MM-yy")
    private Date dateOfApproval;
    
    @JsonFormat(pattern="dd-MM-yy")
    private Date modifiedDate;
    private String status;

    private Long reviewerId;
    private String reviewerName;
    private String reviewerComments;

}


