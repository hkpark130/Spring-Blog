package kr.p_e.hkpark130.springblog.service;

import kr.p_e.hkpark130.springblog.domain.Category;
import kr.p_e.hkpark130.springblog.domain.Post;
import kr.p_e.hkpark130.springblog.domain.Role;
import kr.p_e.hkpark130.springblog.domain.User;
import kr.p_e.hkpark130.springblog.dto.PostRequestDto;
import kr.p_e.hkpark130.springblog.dto.PostResponseDto;
import kr.p_e.hkpark130.springblog.dto.PostsResponseDto;
import kr.p_e.hkpark130.springblog.dto.PostSitemapDto;
import kr.p_e.hkpark130.springblog.exception.CustomException;
import kr.p_e.hkpark130.springblog.exception.code.ErrorCode;
import kr.p_e.hkpark130.springblog.repository.CategoryRepository;
import kr.p_e.hkpark130.springblog.repository.CommentRepository;
import kr.p_e.hkpark130.springblog.repository.PostRepository;
import kr.p_e.hkpark130.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
        private final PostRepository postRepository;
        private final UserRepository userRepository;
        private final CategoryRepository categoryRepository;
        private final CommentRepository commentRepository;

        @Transactional
        public Long createPost(PostRequestDto dto, String username) {
                User user = findUserByUsername(username);

                Category category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

                Post post = Post.builder()
                                .title(dto.getTitle())
                                .content(dto.getContent())
                                .category(category)
                                .author(user)
                                .build();

                return postRepository.save(post).getId();
        }

        @Cacheable(value = "posts", key = "#id")
        public PostResponseDto getPost(Long id) {
                Post post = postRepository.findById(id)
                                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

                return convertToDto(post);
        }

        public PostsResponseDto getPosts(int offset, int limit) {
                Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
                Page<Post> postPage = postRepository.findAll(pageable);

                List<PostResponseDto> postDtos = postPage.getContent().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());

                return new PostsResponseDto(postDtos, (int) postPage.getTotalElements(),
                                pageable.getPageNumber(), pageable.getPageSize());
        }

        @Transactional
        @CacheEvict(value = { "posts" }, key = "#postId")
        public void updatePost(Long postId, PostRequestDto dto, String username) {
                Post post = postRepository.findById(postId)
                                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
                Category category = categoryRepository.findById(dto.getCategoryId())
                                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
                User user = findUserByUsername(username);

                if (!post.getAuthor().getUsername().equals(username) &&
                                !user.getRole().equals(Role.ADMIN)) {
                        throw new CustomException(ErrorCode.UNAUTHORIZED_UPDATE);
                }

                post.setTitle(dto.getTitle());
                post.setContent(dto.getContent());
                post.setCategory(category);
        }

        @Transactional
        @CacheEvict(value = { "posts", "commentCounts" }, key = "#postId")
        public void deletePost(Long postId, String username) {
                Post post = postRepository.findById(postId)
                                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
                User user = findUserByUsername(username);

                if (!post.getAuthor().getUsername().equals(username) && !user.getRole().equals(Role.ADMIN)) {
                        throw new CustomException(ErrorCode.UNAUTHORIZED_DELETE);
                }

                postRepository.delete(post);
        }

        private User findUserByUsername(String username) {
                return userRepository.findByUsername(username)
                                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        }

        // 댓글 수를 별도 카운트 쿼리로 가져오는 캐시 메소드 추가
        @Cacheable(value = "commentCounts", key = "#postId")
        public int getCommentCount(Long postId) {
                return commentRepository.countByPostId(postId);
        }

        private PostResponseDto convertToDto(Post post) {
                return PostResponseDto.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .content(post.getContent())
                                .category(post.getCategory().getName())
                                .author(post.getAuthor().getUsername())
                                .createdAt(post.getCreatedAt())
                                .updatedAt(post.getUpdatedAt())
                                .commentCount(getCommentCount(post.getId()))
                                .build();
        }

        // PostService에 검색 메소드 추가
        public PostsResponseDto searchPosts(String keyword, int offset, int limit) {
                Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
                Page<Post> postPage = postRepository.findByTitleOrContentContaining(keyword, pageable);

                List<PostResponseDto> postDtos = postPage.getContent().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());

                return new PostsResponseDto(postDtos, (int) postPage.getTotalElements(),
                                pageable.getPageNumber(), pageable.getPageSize());
        }

        public PostsResponseDto searchPostsBySearchAndCategory(String keyword, Long categoryId, int offset, int limit) {
                Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
                Page<Post> postsPage = postRepository.findByTitleContainingOrContentContainingAndCategoryId(
                                keyword, categoryId, pageable);

                List<PostResponseDto> postDtos = postsPage.getContent().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());

                return new PostsResponseDto(postDtos, (int) postsPage.getTotalElements(),
                                pageable.getPageNumber(), pageable.getPageSize());
        }

        public PostsResponseDto getPostsByCategory(Long categoryId, int offset, int limit) {
                Pageable pageable = PageRequest.of(offset / limit, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
                Page<Post> postsPage = postRepository.findByCategoryId(categoryId, pageable);

                List<PostResponseDto> postDtos = postsPage.getContent().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());

                return new PostsResponseDto(postDtos, (int) postsPage.getTotalElements(),
                                pageable.getPageNumber(), pageable.getPageSize());
        }

        // 사이트맵용 전체 조회 (최소 필드만 반환). 내부적으로 페이지네이션 반복.
        public List<PostSitemapDto> findAllForSitemap() {
                int page = 0;
                int size = 500; // 한 번에 많이 읽어 네트워크 왕복 수 최소화
                Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
                List<PostSitemapDto> results = new ArrayList<>();

                while (true) {
                        Pageable pageable = PageRequest.of(page, size, sort);
                        Page<Post> postPage = postRepository.findAll(pageable);
                        if (!postPage.hasContent())
                                break;

                        for (Post p : postPage.getContent()) {
                                results.add(new PostSitemapDto(p.getId(), p.getCreatedAt(), p.getUpdatedAt()));
                        }

                        if (!postPage.hasNext())
                                break;
                        page++;
                }
                return results;
        }
}
