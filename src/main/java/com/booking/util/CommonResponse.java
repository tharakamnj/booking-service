package com.booking.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private Object payload = null;
    private String errorMessage = "";
    private int status = 200;

}
