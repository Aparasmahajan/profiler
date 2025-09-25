package com.apex_aura.profiler.builder;


import com.apex_aura.profiler.dto.ResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilderFactory {

    private ResponseBuilderFactory() {}

    /**
     * return Response to verify phone number
     *
     * @param message
     * @param responseCode
     * @return ResponseDTO
     */
    public static ResponseDTO getResponse(String message, String responseCode) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(responseCode) == 2000 ?  "SUCCESS" : "FAIL")
                .message(message)
                .responseCode(Integer.parseInt(responseCode))
                .data(null)
                .build();
    }

    /**
     * return Response to verify phone number
     *
     * @param message
     * @param messageCode
     * @param data
     * @return ResponseDTO
     */
    public static ResponseDTO getResponse(String message, String messageCode, Object data) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(messageCode) == 2000 ? "SUCCESS" : "FAIL")
                .message(message)
                .responseCode(Integer.parseInt(messageCode))
                .data(data)
                .build();
    }

    public static ResponseDTO getSuccessResponse(String message, String responseCode) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(responseCode) == 2000 ? "SUCCESS" : "FAIL")
                .message(message)
                .responseCode(Integer.parseInt(responseCode))
                .data(null)
                .build();
    }

    public static ResponseDTO getSuccessResponse(String message, String responseCode, Object data) {
        return ResponseDTO.builder()
                .status(Integer.parseInt(responseCode) == 2000 ? "SUCCESS" : "FAIL")
                .message(message)
                .responseCode(Integer.parseInt(responseCode))
                .data(data)
                .build();
    }


}
