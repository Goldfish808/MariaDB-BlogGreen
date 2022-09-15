package site.metacoding.red.web;

import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.service.UsersService;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.dto.users.CMRespDto;
import site.metacoding.red.web.dto.users.request.JoinDto;
import site.metacoding.red.web.dto.users.request.LoginDto;
import site.metacoding.red.web.dto.users.request.SaveDto;

@RequiredArgsConstructor
@Controller
public class UsersController {

	private final UsersService usersService;
	private final HttpSession session;
	
	//ajax 로 아이디 중복체크 안하면 아래와 같이 컨트롤러 생성해서 해야함
	// 일단해보자구 
	// http://localhost:8000/users/usernameIsSame?username=입력값
	@GetMapping("/users/usernameIsSame")
	//@ResponseBody 파일이 아닌 데이터를 리턴할 수 있도록 함
	public @ResponseBody CMRespDto<Boolean> usernameIsSame(String username){
		boolean isSame = usersService.네임중복확인(username);
		
		return new CMRespDto<>(1, "성공", isSame);
	}
	 
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() { // 
		return "users/loginForm";
	}
	
	@PostMapping("/join")
	public @ResponseBody CMRespDto<?> join(@RequestBody JoinDto joinDto) {
		usersService.회원가입(joinDto);
		return new CMRespDto<>(1, "회원가입 완료", null);
	}
	
	@PostMapping("/login")
	public @ResponseBody CMRespDto<?> join(@RequestBody LoginDto loginDto) {
		Users principal = usersService.로그인(loginDto);
		if(principal == null) { //잘안된거를 튕겨내기 위한 조건문
			return new CMRespDto<>(-1, "로그인 실패", null);
		}
		session.setAttribute("principal", principal);
		
		return new CMRespDto<>(1, "로그인 성공", null);
	}
	
	@GetMapping("/users/{id}")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users usersPS =  usersService.회원정보보기(id);
		model.addAttribute("users", usersPS);
		return "users/updateForm";
	}
	
	@PutMapping("/users/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id, @RequestBody SaveDto updateDto) {
		Users usersPS = usersService.회원수정(id, updateDto);
		session.setAttribute("principal", usersPS); // 수정된 회원정보로 다시 세션을 덮어 씌움
		System.out.println("수정이 잘 되었나?"+usersPS.getEmail());
		return new CMRespDto<>(1, "수정이 잘 되었슴", null);
	}
	
	@DeleteMapping("/users/{id}")
	public @ResponseBody CMRespDto<?> delete( @PathVariable Integer id) {
		usersService.회원탈퇴(id);
		session.invalidate();
		return new CMRespDto<>(1, "수정이 잘 되었슴", null);
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/loginForm";
	}

}
