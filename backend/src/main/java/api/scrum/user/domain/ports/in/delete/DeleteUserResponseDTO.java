package api.scrum.user.domain.ports.in.delete;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserResponseDTO {
    private UUID id;
    private String nickname;
    private String email;
    private String name;
    private String profilePicture;
}
