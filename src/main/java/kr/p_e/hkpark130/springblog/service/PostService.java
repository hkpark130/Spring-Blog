package kr.p_e.hkpark130.springblog.service;

import kr.p_e.hkpark130.springblog.domain.Category;
import kr.p_e.hkpark130.springblog.domain.Post;
import kr.p_e.hkpark130.springblog.domain.Role;
import kr.p_e.hkpark130.springblog.domain.User;
import kr.p_e.hkpark130.springblog.dto.PostRequestDto;
import kr.p_e.hkpark130.springblog.dto.PostResponseDto;
import kr.p_e.hkpark130.springblog.dto.PostsResponseDto;
import kr.p_e.hkpark130.springblog.exception.CustomException;
import kr.p_e.hkpark130.springblog.exception.code.ErrorCode;
import kr.p_e.hkpark130.springblog.repository.CategoryRepository;
import kr.p_e.hkpark130.springblog.repository.PostRepository;
import kr.p_e.hkpark130.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

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

    private PostResponseDto convertToDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory().getName(),
                post.getAuthor().getUsername(),
                post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                post.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}