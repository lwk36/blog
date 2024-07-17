-- 用户表
CREATE TABLE `users` (
    `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，需要加密存储',
    `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '邮箱',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 文章表
CREATE TABLE `post` (
    `post_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标题',
    `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
    `user_id` int NOT NULL COMMENT '作者ID，关联到用户表',
    `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`post_id`),
    KEY `fk_post_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 操作日志表

CREATE TABLE `operation_logs` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `timestamp` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `user_id` int DEFAULT NULL COMMENT '操作用户ID',
    `description` varchar(255) DEFAULT NULL COMMENT '操作描述',
    `request_url` varchar(512) NOT NULL COMMENT '请求URL',
    `request_method` varchar(10) NOT NULL COMMENT '请求方法',
    `request_params` text COMMENT '请求参数',
    `response_result` text COMMENT '响应结果',
    `execution_time` int DEFAULT NULL COMMENT '执行时间(毫秒)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';

-- 角色认证表
CREATE TABLE `authorities` (
    `authority_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `authority` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色',
    `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
    PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色认证表';