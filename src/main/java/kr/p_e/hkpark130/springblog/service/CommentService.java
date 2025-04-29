package kr.p_e.hkpark130.springblog.service;

import kr.p_e.hkpark130.springblog.domain.Comment;
import kr.p_e.hkpark130.springblog.domain.Post;
import kr.p_e.hkpark130.springblog.domain.Role;
import kr.p_e.hkpark130.springblog.domain.User;
import kr.p_e.hkpark130.springblog.dto.CommentRequestDto;
import kr.p_e.hkpark130.springblog.dto.CommentResponseDto;
import kr.p_e.hkpark130.springblog.dto.CommentsResponseDto;
import kr.p_e.hkpark130.springblog.exception.CustomException;
import kr.p_e.hkpark130.springblog.exception.code.ErrorCode;
import kr.p_e.hkpark130.springblog.repository.CommentRepository;
import kr.p_e.hkpark130.springblog.repository.PostRepository;
import kr.p_e.hkpark130.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public CommentsResponseDto getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        List<CommentResponseDto> commentDtos = commentRepository.findByPostOrderByCreatedAtDesc(post)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new CommentsResponseDto(commentDtos);
    }

    @Transactional
    @CacheEvict(value = "commentCounts", key = "#postId")
    public Long createComment(Long postId, CommentRequestDto dto, String username) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Comment comment;

        // 로그인한 사용자인 경우
        if (username != null) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

            comment = Comment.builder()
                    .content(dto.getContent())
                    .post(post)
                    .author(user)
                    .isGuest(false)
                    .build();
        }
        // 게스트 사용자인 경우 - 비밀번호 선택적
        else {
            comment = Comment.builder()
                    .content(dto.getContent())
                    .post(post)
                    .guestName(dto.getGuestName() != null ? dto.getGuestName() : "익명")
                    .isGuest(true)
                    .build();

            // 비밀번호가 제공된 경우에만 설정
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                setCommentPassword(comment, dto.getPassword());
            }
        }

        commentRepository.save(comment);
        return comment.getId();
    }

    // 게스트 댓글 수정 (비밀번호 직접 검증)
    @Transactional
    public void updateGuestComment(Long commentId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        // 게스트 댓글이 아니면 예외 발생
        if (!comment.isGuest()) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_COMMENT_ACTION);
        }

        // 비밀번호 검증
        String password = dto.getPassword();
        if (!dto.isAdmin() && !validateCommentPassword(comment, password)) {
            throw new CustomException(ErrorCode.INVALID_COMMENT_PASSWORD);
        }

        // 내용 업데이트
        updateCommentContent(comment, dto.getContent());
    }

    // 로그인 사용자 댓글 수정 (권한 기반)
    @Transactional
    public void updateUserComment(Long commentId, CommentRequestDto dto, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        // 관리자 권한 확인
        boolean isAdmin = false;
        if (username != null) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
            isAdmin = Role.ADMIN.equals(user.getRole());
        }

        // 게스트 댓글이거나 권한이 없으면 예외 발생
        if (comment.isGuest()) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_COMMENT_ACTION);
        }

        // 작성자 또는 관리자 권한 확인
        if (!isAdmin && (username == null || !username.equals(comment.getAuthor().getUsername()))) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_COMMENT_ACTION);
        }

        // 내용 업데이트
        updateCommentContent(comment, dto.getContent());
    }

    // 게스트 댓글 삭제 (비밀번호 직접 검증)
    @Transactional
    @CacheEvict(value = "commentCounts", key = "#postId")
    public void deleteGuestComment(Long postId, Long commentId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        // 게스트 댓글이 아니면 예외 발생
        if (!comment.isGuest()) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_COMMENT_ACTION);
        }

        // 비밀번호 검증
        String password = dto.getPassword();
        if (!dto.isAdmin() && !validateCommentPassword(comment, password)) {
            throw new CustomException(ErrorCode.INVALID_COMMENT_PASSWORD);
        }

        // 댓글 삭제
        commentRepository.delete(comment);
    }

    // 로그인 사용자 댓글 삭제 (권한 기반)
    @Transactional
    @CacheEvict(value = "commentCounts", key = "#postId")
    public void deleteUserComment(Long postId, Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        // 관리자 권한 확인
        boolean isAdmin = false;
        if (username != null) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
            isAdmin = Role.ADMIN.equals(user.getRole());
        }

        // 게스트 댓글이거나 권한이 없으면 예외 발생
        if (comment.isGuest()) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_COMMENT_ACTION);
        }

        // 작성자 또는 관리자 권한 확인
        if (!isAdmin && (username == null || !username.equals(comment.getAuthor().getUsername()))) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_COMMENT_ACTION);
        }

        // 댓글 삭제
        commentRepository.delete(comment);
    }

    // 비즈니스 로직 - 댓글 DTO로 변환
    private CommentResponseDto convertToDto(Comment comment) {
        String authorName;
        if (comment.isGuest()) {
            authorName = comment.getGuestName() != null ? comment.getGuestName() : "익명";
        } else {
            authorName = comment.getAuthor() != null ? comment.getAuthor().getUsername() : "익명";
        }

        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(authorName)
                .createdAt(comment.getCreatedAt())
                .isGuest(comment.isGuest())
                .build();
    }

    // 비즈니스 로직 - 댓글 비밀번호 설정
    private void setCommentPassword(Comment comment, String rawPassword) {
        try {
            Field passwordField = Comment.class.getDeclaredField("password");
            passwordField.setAccessible(true);
            passwordField.set(comment, passwordEncoder.encode(rawPassword));
        } catch (Exception e) {
            throw new RuntimeException("댓글 비밀번호 설정 실패", e);
        }
    }

    // 비즈니스 로직 - 댓글 비밀번호 검증
    private boolean validateCommentPassword(Comment comment, String inputPassword) {
        if (comment.getPassword() == null) {
            return inputPassword == null || inputPassword.isEmpty();
        }
        return passwordEncoder.matches(inputPassword, comment.getPassword());
    }

    @Transactional(readOnly = true)
    public boolean validatePassword(Long commentId, String password) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
        return validateCommentPassword(comment, password);
    }

    // 비즈니스 로직 - 댓글 내용 업데이트
    private void updateCommentContent(Comment comment, String newContent) {
        try {
            Field contentField = Comment.class.getDeclaredField("content");
            contentField.setAccessible(true);
            contentField.set(comment, newContent);
        } catch (Exception e) {
            throw new RuntimeException("댓글 내용 업데이트 실패", e);
        }
    }
}