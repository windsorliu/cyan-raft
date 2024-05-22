package com.windsor.cyanraft.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
  private List<String> receivers;
  private String subject;
  private String content;
}
