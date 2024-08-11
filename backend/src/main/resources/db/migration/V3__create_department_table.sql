create table if not exists department
(
    id                       int auto_increment
    primary key,
    name                     varchar(10)   null,
    full_name                varchar(255)  null,
    manager                  varchar(50)   null,
    status                   int default 1 null,
    created_at               datetime      null,
    created_by               int           null,
    updated_at               datetime      null,
    updated_by               int           null
);

INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (1, 'A1', 'Khoa A1 cũ (Không sử dụng)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (2, 'A10', 'Khoa A10 (Y học cổ truyền)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (3, 'A11', 'Khoa A11 (Chăm sóc, bảo vệ sức khỏe cán bộ Trung ương )', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (4, 'A12-B', 'Khoa A12-B (Khoa Hồi sức Ngoại khoa và Ghép tạng)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (5, 'A12-A', 'Khoa A12-A ( Khoa Hồi sức Tích cực Nội và Chống độc )', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (6, 'A14.1', 'Khoa A14 cũ (KSD)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (7, 'A14', 'Khoa A14 (Nội tiết)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (8, 'A15.1', 'Khoa A15 cũ (KSD)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (9, 'A15', 'Khoa A15 (Khoa Nội thận và Lọc máu)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (10, 'A16', 'Khoa A16 (Quốc tế)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (11, 'A17', 'Khoa A17 (Khoa Nội Cơ, Xương, Khớp)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (12, 'A18', 'Khoa A18 (Huyết học lâm sàng)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (13, 'A1-A', 'Khoa A1-A (Nội tổng hợp)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (14, 'A1-C', 'Khoa A1-C (Bệnh cấp tính và cấp cứu)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (15, 'A20', 'Khoa A20 (Y học hạt nhân)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (16, 'A7-C', 'Khoa A7-C (Đột quỵ não)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (17, 'A2-A', 'Khoa A2-A (Nội tim mạch)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (18, 'A2-B', 'Khoa A2-B (Phẫu thuật tim mạch)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (19, 'A2-C', 'Khoa A2-C (Chẩn đoán và can thiệp tim mạch)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (20, 'A2-D', 'Khoa A2-D (Khoa Hồi Sức Tim Mạch)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (21, 'A3-A', 'Khoa A3-A (Khoa điều trị bệnh Ống tiêu hóa)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (22, 'A3-B', 'Khoa A3-B (Khoa điều trị Gan, Mật, Tụy )', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (23, 'A3-C', 'Khoa A3-C (Khoa Cấp cứu Tiêu hóa)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (24, 'A3-D', 'Khoa Nội soi tiêu hóa (A3-D)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (25, 'A4-A', 'Khoa A4-A(Lây truyền qua đường máu)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (26, 'A4-B', 'Khoa A4-B (Bệnh lây đường tiêu hóa)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (27, 'A4-C', 'Khoa A4-C (Bệnh lây đường hô hấp và hồi sức)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (28, 'A4-D', 'Khoa A4-D ( Hồi sức truyền nhiễm)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (29, 'A5', 'Khoa A5 (Nội Hô Hấp)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (30, 'A6', 'Khoa A6 cũ (KSD)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (31, 'A6-A', 'Khoa A6-A (Khoa chống đau và chăm sóc giảm nhẹ)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (32, 'A6-B', 'Khoa A6-B (Khoa Hóa trị)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (33, 'A6-C', 'Khoa A6-C (Xạ trị - Xạ phẫu)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (34, 'A6-D', 'Khoa A6-D (Khoa Ung thư tổng hợp)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (35, 'A7-A', 'Khoa A7-A (Nội thần kinh)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (36, 'A7-D', 'Khoa A7-D (Hồi Sức Thần Kinh)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (37, 'A8-A', 'Khoa A8- A (Da Liễu)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (38, 'A8-B', 'Khoa A8-B (Dị Ứng)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (39, 'A9', 'Khoa A9 (Nhi)', 'minh, van', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (40, 'AB', 'Khu khám bệnh A-B', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (41, 'B10', 'Khoa B10 (Răng)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (42, 'B11', 'Khoa B11 (Phụ sản)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (43, 'B15', 'Khoa B15 (Ngoại nhân dân)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (44, 'B1-A', 'Khoa B1-A (CT-CH tổng hợp)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (45, 'B1-B', 'Khoa B1-B (Chấn thương chi trên và vi phẫu thuật)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (46, 'B1-C', 'Khoa B1-C (Phẫu thuật khớp)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (47, 'B1-D', 'Khoa B1-D (CTCH cột sống)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (48, 'B2-B', 'Khoa B2-B (Tiết niệu dưới)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (49, 'B2-A', 'Khoa B2-A (Tiết niệu trên)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (50, 'B2-C', 'Khoa B2-C (Nam học)', 'giang', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (51, 'B3', '(B3 cũ - không sử dụng nữa)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (52, 'B3-A', 'Khoa B3-A (Phẫu thuật tiêu hóa)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (53, 'B3-B', 'Khoa B3-B (Phẫu thuật gan mật)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (54, 'B3-C', 'Khoa B3-C (Phẫu Thuật Hậu Môn,Trực Tràng và Sàn Chậu)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (55, 'B4', 'Khoa B4 (Ngoại Lồng ngực)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (56, 'B5', 'Khoa B5 (Gây mê hồi sức)', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (57, 'A7-B', 'Khoa A7-B (Ngoại thần kinh)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (58, 'B7', 'Khoa B7 (Mắt)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (59, 'B8-A', 'Khoa B8-A (Phẫu thuật sọ mặt)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (60, 'B8-B', 'Khoa B8-B (Phẫu thuật tạo hình và vi phẫu)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (61, 'B9', 'Khoa B9 (Tai - Mũi - Họng)', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (62, 'C1.1', 'Khoa Khám Bệnh Đa Khoa C1.1-A', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (63, 'C1.2', 'Khoa Khám Bệnh C1.2', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (64, 'C1.3', 'Khoa Cấp cứu C1.3', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (65, 'C1.3 NT', 'Khoa Cấp Cứu C1.3 Nội Trú', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (66, 'C19.CC', 'Khoa cấp cứu, phân loại bệnh nhân Covid', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (67, 'C6', 'Khoa C6 (Phục hồi chức năng)', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (68, 'C7', 'Khoa C7 (Chẩn đoán chức năng)', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (69, 'C8-C', 'Khoa C8-C (Siêu Âm chẩn đoán)', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (70, 'COVID.1', 'Khoa điều trị Covid 1- tầng 2', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (71, 'COVID.2', 'Khoa điều trị Covid 2- tầng 4', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (72, 'COVID.3', 'Khoa Điều Trị Covid 3 - Tầng 5', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (73, 'COVID.4', 'Khoa Điều Trị Covid 4 - Tầng 6', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (74, 'Covid.5', 'Khoa điều trị Covid 5 - tầng 7', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (75, 'Covid.6', 'Khoa điều trị Covid 6 - tầng 8', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (76, 'DTTN', 'Khu điều trị trong ngày và chống đau', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (77, 'KBTN', 'Khu điều trị trong ngày và chống đau', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (78, 'PTTYC', 'Khoa phẫu thuật và điều trị theo yêu cầu', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (79, 'TTDTHM', 'Trung tâm hỗ trợ sinh sản', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (80, 'TTHTSS', 'Trung Tâm Hỗ Trợ Sinh Sản', 'nga, phuong', 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (81, 'TTNCVD', 'Trung Tâm Nghiên Cứu Việt Đức', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (82, 'TTTVDT', 'Trung tâm tư vấn di truyền và sàng lọc ung thư', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (83, 'TTUPTH', 'Trung tâm ứng phó các tình huống khẩn cấp', NULL, 1);
INSERT INTO `department` (`id`, `name`, `full_name`, `manager`, `status`) VALUES (84, 'TYC', 'Khoa Khám Bệnh TYC C1.1-B', 'nga, phuong', 1);

