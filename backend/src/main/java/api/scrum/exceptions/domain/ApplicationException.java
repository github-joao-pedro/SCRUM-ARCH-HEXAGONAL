package api.scrum.exceptions.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplicationException extends RuntimeException {
    private final int statusCode;
    private final String message;
    private final String details;

    public ApplicationException(int statusCode, String message, String details) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }
}
