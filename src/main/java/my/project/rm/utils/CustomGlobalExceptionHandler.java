package my.project.rm.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RestControllerAdvice
public class CustomGlobalExceptionHandler  {

    @ExceptionHandler(ResponseStatusException.class)
    public Map<String, Object> handle(ResponseStatusException t, WebRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", new Date());
        response.put("status", (t.getRawStatusCode()));
        response.put("status_message", (t.getStatus()));
        response.put("message", t.getReason());
        return response;
    }
}
