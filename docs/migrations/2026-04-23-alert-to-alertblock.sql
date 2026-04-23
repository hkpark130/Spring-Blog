-- 2026-04-23: 기존 블로그 글의 <Alert ...>를 <AlertBlock ...>으로 일괄 치환.
-- MarkdownRenderer에서 <Alert> 지원이 제거됨에 따라 실제 콘텐츠를 마이그레이션.
-- 실행 전에 backup 필수.

START TRANSACTION;

UPDATE posts
SET content = REPLACE(content, '<Alert', '<AlertBlock')
WHERE content LIKE '%<Alert%';

UPDATE posts
SET content = REPLACE(content, '</Alert>', '</AlertBlock>')
WHERE content LIKE '%</Alert>%';

-- 실행 후 검증:
SELECT COUNT(*) AS with_alertblock FROM posts WHERE content LIKE '%<AlertBlock%';
SELECT COUNT(*) AS with_legacy_alert FROM posts WHERE content LIKE '%<Alert %' OR content LIKE '%<Alert>%';
-- with_legacy_alert는 0이어야 함.

-- 문제 없으면 COMMIT, 이상하면 ROLLBACK.
COMMIT;
