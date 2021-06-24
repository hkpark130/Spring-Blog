package kr.pe.hkpark130.springblog.service.comments;

import kr.pe.hkpark130.springblog.domain.comment.Comments;
import kr.pe.hkpark130.springblog.domain.comment.CommentsRepository;
import kr.pe.hkpark130.springblog.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(CommentDto requestDto){
        return commentsRepository.save(requestDto.toEntity()).getComment_id();
    }

    @Transactional
    public Long update(Long id, CommentDto requestDto){
        Comments comments = commentsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));
        comments.update(requestDto.getComment());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));
        commentsRepository.delete(comments);
    }

    @Transactional
    public List<CommentDto> findAll(Long post_id) {
        return commentsRepository.findAll(post_id).stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    public CommentDto findById (Long id){
        Comments entity = commentsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 댓글 없음"));
        return new CommentDto(entity);
    }
}
