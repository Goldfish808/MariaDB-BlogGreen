package site.metacoding.red.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.service.BoardsService;
import site.metacoding.red.web.dto.boards.request.SaveDto;
import site.metacoding.red.web.dto.boards.request.WriteDto;
import site.metacoding.red.web.dto.boards.response.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final BoardsService boardsService;
	private final HttpSession session;
	
	
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page, String keyword) {
		PagingDto paging =  boardsService.게시글목록보기(page, keyword);
		model.addAttribute("paging",paging);
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
		if (principal == null) { // userPS 가 null 이면 로그인 안된상태, 메인페이지로 감
			return "redirect:/";
		}
		return "boards/writeForm";
	}
	
	@PostMapping("/boards/write")
	public String write(WriteDto writeDto) {
		Users userPS= (Users) session.getAttribute("principal");
		boardsService.게시글쓰기(writeDto, userPS);
		return "redirect:/";
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
	
	@PutMapping("/boards/{id}/update")
	public String update(@PathVariable Integer id ,SaveDto updateDto) {
		boardsService.게시글수정하기(id, updateDto);
		return "게시글수정완료";
	}
	
	@DeleteMapping("/")
	public String delete(@PathVariable Integer id) {
		boardsService.게시글삭제하기(id);
		return "게시글삭제완료";
	}
}
