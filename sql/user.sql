use test;

/* user Table */
CREATE TABLE `user` (
  `id` int(11) AUTO_INCREMENT NOT NULL,
  `login_id` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `upd_cnt` int(11) NOT NULL,
  primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Unique Index for login_id */
ALTER TABLE user ADD UNIQUE(login_id);