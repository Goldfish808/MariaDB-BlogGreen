package site.metacoding.red.web.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CMRespDto<T> { //공통적인 응답을 위한 DTO : body 에 데이터를 담아 통신할 때는 json으로 통일이기 때문
	private Integer code; 
	private String msg; // 실패 사유, 
	private T data;
}
