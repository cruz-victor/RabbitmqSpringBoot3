//DTO

package com.ubicuosoft.sm.queue.publisherconsumer.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@ToString
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String message;
}
