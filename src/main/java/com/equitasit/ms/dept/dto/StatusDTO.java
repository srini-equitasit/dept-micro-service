package com.equitasit.ms.dept.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusDTO {
    private String statusMsg;

    public StatusDTO(String statusMsg) {
        this.statusMsg = statusMsg;
    }

}
