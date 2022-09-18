package site.metacoding.red.domain.boardlikes;

import java.util.List;

import site.metacoding.red.web.dto.boardlikes.request.LikesDto;
import site.metacoding.red.web.dto.boardlikes.response.LikesMainDto;

public interface BoardLikesDao {
		public List<BoardLikes> findAll(LikesDto id);
		public BoardLikes findById(LikesDto id);
		public void insertById(LikesDto id);
		public void deleteById(LikesDto id);
		public void update();
		
		public Integer countLikesByid(BoardLikes id);
}
