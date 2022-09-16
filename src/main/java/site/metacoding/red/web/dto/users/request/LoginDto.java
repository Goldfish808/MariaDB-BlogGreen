package site.metacoding.red.web.dto.users.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	private String username;
	private String password;
	private boolean remember;
}
