package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boardlikes.BoardLikes;
import site.metacoding.red.domain.boardlikes.BoardLikesDao;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.dto.boardlikes.request.LikesDto;
import site.metacoding.red.web.dto.boardlikes.response.LikesMainDto;

@RequiredArgsConstructor
@Service
public class BoardLikesService {
	private final BoardLikesDao boardLikesDao;

	public void insert(LikesDto id) {
		/*
		 * LikesMainDto like = boardLikesDao.findById(id); if((like == null)) {
		 * boardLikesDao.insertById(id); }
		 */
		boardLikesDao.insertById(id);
		System.out.println(id.getUsersId());
	}
	public void delete(LikesDto id) {
		BoardLikes likePS = boardLikesDao.findById(id);
		
		if(likePS == null) {
			Script.back("삭제할게 없습니다");
			System.out.println("삭제할게 읍따!!!");
		}
		
		boardLikesDao.deleteById(id);
	}

}
