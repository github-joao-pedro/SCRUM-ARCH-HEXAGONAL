package api.scrum.exceptions.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private LocalDateTime timestamp;
    private String status;
    private int statusCode;
    private String error;
    private String message;
    private String path;
    private String details;
}
