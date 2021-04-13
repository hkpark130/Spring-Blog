package kr.pe.hkpark130.springblog.service.posts;


import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.posts.PostsRepository;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물 없음"));
        posts.update(requestDto.getTitle(), requestDto.getContents());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물 없음"));
        postsRepository.delete(posts);
    }

    @Transactional
    public List<PostDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    public PostDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물 없음"));
        return new PostDto(entity);
    }
}
