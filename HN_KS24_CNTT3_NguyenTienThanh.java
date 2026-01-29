package Ex05;

import java.util.Scanner;
import java.util.regex.Pattern;

public class HN_KS24_CNTT3_NguyenTienThanh {
    public static void main(String[] args) {
        String[] studentIds = new String[100];
        int size = 0;
        Scanner sc = new Scanner(System.in);
        String regexPattern = "^B\\d{7}$";
        while (true) {
            System.out.println("=== MENU QUẢN LÝ MSSV ===");
            System.out.println("1. Hiển thị danh sách MSSV");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật theo vị trí");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập lại");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("--- DANH SÁCH MSSV ---");
                    if (size == 0) {
                        System.out.println("Danh sách hiện đang trống.");
                    } else {
                        for (int i = 0; i < size; i++) {
                            System.out.printf("Vị trí %d: %s\n", i, studentIds[i]);
                        }
                    }
                    break;
                case 2:
                    if (size == 100) {
                        System.out.println("Danh sách đầy, không thể thêm mới.");
                        break;
                    }
                    while (true) {
                        System.out.print("Nhập MSSV mới : ");
                        String newId = sc.nextLine().trim();
                        if (Pattern.matches(regexPattern, newId)) {
                            boolean isExist = false;
                            for (int i = 0; i < size; i++) {
                                if (studentIds[i].equals(newId)) {
                                    isExist = true;
                                    break;
                                }
                            }
                            if (isExist) {
                                System.err.println("Mã sinh viên này đã tồn tại!");
                            } else {
                                studentIds[size] = newId;
                                size++;
                                System.out.println("Thêm mới thành công!");
                                break;
                            }
                        } else {
                            System.err.println("MSSV phải bắt đầu bằng 'B' và 7 chữ số.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhập vị trí muốn sửa: ");
                    int updateIndex = -1;
                    try {
                        updateIndex = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.err.println("Vị trí không hợp lệ.");
                        break;
                    }
                    if (updateIndex >= 0 && updateIndex < size) {
                        while (true) {
                            System.out.print("Nhập MSSV mới để thay thế: ");
                            String updateId = sc.nextLine().trim();

                            if (Pattern.matches(regexPattern, updateId)) {
                                studentIds[updateIndex] = updateId;
                                System.out.println("Cập nhật thành công!");
                                break;
                            } else {
                                System.err.println("Vui lòng nhập đúng.");
                            }
                        }
                    } else {
                        System.err.println("Vị trí không tồn tại");
                    }
                    break;
                case 4:
                    System.out.print("Nhập MSSV cần xóa: ");
                    String deleteId = sc.nextLine().trim();
                    int foundIndex = -1;
                    for (int i = 0; i < size; i++) {
                        if (studentIds[i].equals(deleteId)) {
                            foundIndex = i;
                            break;
                        }
                    }
                    if (foundIndex != -1) {
                        for (int i = foundIndex; i < size - 1; i++) {
                            studentIds[i] = studentIds[i + 1];
                        }
                        studentIds[size - 1] = null;
                        size--;
                        System.out.println("Đã xóa mã sinh viên: " + deleteId);
                    } else {
                        System.err.println("Không tìm thấy MSSV này trong danh sách.");
                    }
                    break;
                case 5:
                    System.out.print("Nhập chuỗi ký tự cần tìm: ");
                    String keyword = sc.nextLine().toLowerCase();
                    System.out.println("Kết quả tìm thấy");
                    boolean hasResult = false;
                    for (int i = 0; i < size; i++) {
                        if (studentIds[i].toLowerCase().contains(keyword)) {
                            System.out.println("+ " + studentIds[i]);
                            hasResult = true;
                        }
                    }
                    if (!hasResult) {
                        System.err.println("Không tìm thấy kết quả nào.");
                    }
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
