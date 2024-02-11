package Electricity.PowerConnectionManagement.payload;

import lombok.Data;

@Data
public class ConnectionPatchDto {
    long id;
    String status;
    String comments;

}
