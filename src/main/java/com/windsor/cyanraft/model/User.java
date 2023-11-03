package com.windsor.cyanraft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.Data;

@Data
public class User {

  private Integer userId;
  private String email;

  @JsonIgnore private String password;

  private Date createdDate;
  private Date lastModifiedDate;
}
