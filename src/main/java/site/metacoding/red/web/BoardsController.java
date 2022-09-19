package site.metacoding.red.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.dto.boards.request.SaveDto;
import site.metacoding.red.web.dto.boards.request.WriteDto;
import site.metacoding.red.web.dto.boards.response.PagingDto;
import site.metacoding.red.web.dto.users.CMRespDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsService boardsService;
	private final HttpSession session;
	
	@PostMapping("/boardLikes")
	public @ResponseBody CMRespDto<?> boardLikes(){
		System.out.println("좋아요 REQUEST");
		return new CMRespDto<>(1," ",null);
	}
	
	
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page, String keyword) {
		PagingDto paging =  boardsService.게시글목록보기(page, keyword);
		model.addAttribute("paging",paging);
		
//		Map<String, Object> refferer = new HashMap<>();
//		refferer.put("page", paging.getCurrentPage());
//		refferer.put("keyword", paging.getKeyword());
		session.setAttribute("refferer", paging);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id, Model model) {
		model.addAttribute("boardsContent", boardsService.게시글상세보기(id));
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) { // userPS Is null to go main Index page, cause Not the login
			return "redirect:/";
		}
		return "boards/writeForm";
	}
	
	@PostMapping("/boards/write")
	public @ResponseBody CMRespDto<?> write(@RequestBody WriteDto writeDto) {
		Users userPS= (Users) session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, userPS);
		return new CMRespDto<>(1, "글쓰기성공", null);
	}
	
	@GetMapping("/boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPS = boardsService.게시글상세보기(id);
		if (principal.getId() != boardsPS.getUsersId()) { // userPS 가 null 이면 로그인 안된상태, 메인페이지로 감
			return "redirect:/";
		}
		model.addAttribute("boardsContent", boardsPS);
		return "boards/updateForm";
	}
	
	@PutMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> update(@PathVariable Integer id ,@RequestBody SaveDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return new CMRespDto<>(1, "글 수정 성공", null);
	}
	
	@DeleteMapping("/boards/{id}")
	public @ResponseBody CMRespDto<?> delete(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return new CMRespDto<>(1, "글삭제 성공", null);
	}
}
