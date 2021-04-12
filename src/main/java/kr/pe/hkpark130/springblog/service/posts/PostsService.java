package kr.pe.hkpark130.springblog.service.posts;


import kr.pe.hkpark130.springblog.domain.posts.PostsRepository;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
