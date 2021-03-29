CREATE TABLE `gen_datasource_conf` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `password` varchar(50) NOT NULL COMMENT '用户ID',
                                       `create_date` datetime DEFAULT NULL,
                                       `update_date` datetime DEFAULT NULL,
                                       `del_flag` varchar(50) DEFAULT NULL COMMENT '用户ID',
                                       `name` varchar(50) NOT NULL COMMENT '用户ID',
                                       `url` varchar(255) NOT NULL COMMENT '用户ID',
                                       `username` varchar(50) NOT NULL COMMENT '用户ID',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;