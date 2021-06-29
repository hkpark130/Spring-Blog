package kr.pe.hkpark130.springblog.service.posts;


import kr.pe.hkpark130.springblog.domain.posts.Posts;
import kr.pe.hkpark130.springblog.domain.posts.PostsRepository;
import kr.pe.hkpark130.springblog.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
        posts.update(requestDto.getTitle(), requestDto.getContents(), requestDto.getCategory());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물 없음"));
        postsRepository.delete(posts);
    }

    @Transactional
    public List<PostDto> findAllDesc(Integer offsetPage, Integer perPage) {
//        return postsRepository.findAllDesc(offsetPage, perPage).stream()
//                .map(PostDto::new)
//                .collect(Collectors.toList());
        List<Object[]> postList = postsRepository.findAllDesc(offsetPage, perPage);
        List<PostDto> postDtoList = new ArrayList<>();

        for (Object[] object : postList) {
            postDtoList.add(
                    new PostDto(((BigInteger) object[0]).longValue(), (String) object[1],
                            (String) object[2], ((Timestamp) object[3]).toLocalDateTime(), ((BigInteger) object[4]).longValue() )
            );
        }

        return postDtoList;
    }

    @Transactional
    public List<PostDto> findCategoryDesc(String category, Integer page, Integer perPage) {
//        return postsRepository.findCategoryDesc(category, page, perPage).stream()
//                .map(PostDto::new)
//                .collect(Collectors.toList());
        List<Object[]> postList = postsRepository.findCategoryDesc(category, page, perPage);
        List<PostDto> postDtoList = new ArrayList<>();

        for (Object[] object : postList) {
            postDtoList.add(
                    new PostDto(((BigInteger) object[0]).longValue(), (String) object[1],
                            (String) object[2], ((Timestamp) object[3]).toLocalDateTime(), ((BigInteger) object[4]).longValue() )
            );
        }

        return postDtoList;
    }

    public PostDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시물 없음"));
        return new PostDto(entity);
    }

    @Transactional
    public TreeMap<Integer, String> getPageAllList(String category, Integer perPage, Integer pageBlock, Integer pageNum) {
        pageNum = (pageNum<1)? 1:pageNum; // 1보다 작은 페이지는 1로 처리
        Integer pageCnt; // 총 페이지 수
        if(category.length() == 0){
            pageCnt = (int)( Math.ceil((double)postsRepository.getPageAllList()/perPage) );
        } else {
            pageCnt = (int)( Math.ceil((double)postsRepository.getPageCategoryList(category)/perPage) );
        }
        pageNum = (pageNum>pageCnt)? pageCnt:pageNum; // 최대 페이지 넘지 않도록
        Boolean next = ((int)(Math.ceil((double)pageNum/pageBlock))*pageBlock < pageCnt)? true:false; // 다음 페이지 유무
        Boolean prev = (pageNum > pageBlock)? true:false; // 이전 페이지 유무
        Integer pageArrCnt = (pageCnt <= pageBlock)? pageCnt:(next)? pageBlock:pageCnt - (((pageNum-1)/pageBlock)*pageBlock); // 페이지 담을 배열 길이가 pageBlock 넘지 않도록
        TreeMap<Integer, String> pageArr = new TreeMap<Integer, String>();; // 페이지리스트 맵
        Integer startPage = ((pageNum-1)/pageBlock)*pageBlock +1 ; // 시작 페이지

        for (int i=0; i < pageArrCnt ;i++){
            if (prev){
                pageArr.put(((pageNum-1)/pageBlock)*pageBlock, "<<"); // 이전 페이지
                pageArrCnt++;
                prev = false;
                continue;
            }
            pageArr.put(startPage, Integer.toString(startPage));
            startPage++;
        }
        if (next){
            pageArr.put((int)(Math.ceil((double)pageNum/pageBlock))*pageBlock +1, ">>"); // 다음 페이지
        }

        return pageArr;
    }

}
