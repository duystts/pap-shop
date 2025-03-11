package org.example;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void exportToExcel(String filePath) {
        Workbook workbook = new XSSFWorkbook();

        createProductSheet(workbook);
        createOrderSheet(workbook);
        createRevenueSheet(workbook);
        createCustomerSheet(workbook);
        createImportReceiptSheet(workbook);

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file created!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Đóng workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createProductSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("San_pham");
        String[] headers = {"Mã sản phẩm", "Tên sản phẩm", "Loại", "Giá Nhập", "Giá Bán", "Số lượng tồn", "Nhà cung cấp"};
        List<Product> products = List.of(
                new Product("P001", "Áo ba lỗ", "Áo", 25000, 75000, 10, "Công ty A"),
                new Product("P002", "Quần tà lõn", "Quần", 20000, 60000, 20, "Công ty B")
        );
        writeData(sheet, headers, products);
    }

    private static void createOrderSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Don_hang");
        String[] headers = {"Mã đơn hàng", "Ngày đặt", "Khách hàng", "Sản phẩm", "Số lượng", "Tổng tiền", "Trạng thái"};
        List<Order> orders = List.of(
                new Order("O001", "10/03/2025", "Phạm Anh Duy", "Áo ba lỗ", 1, 150000, "Đã giao"),
                new Order("O002", "08/03/2025", "Quang Tấn Phát", "Quần tà lõn", 2, 120000, "Chờ xử lý")
        );
        writeData(sheet, headers, orders);
    }

    private static void createRevenueSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Doanh_thu");
        String[] headers = {"Ngày", "Mã đơn hàng", "Khách hàng", "Tổng tiền", "Phương thức thanh toán"};
        List<Revenue> revenues = List.of(
// bo data vao
        );
        writeData(sheet, headers, revenues);
    }

    private static void createCustomerSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Khach_hang");
        String[] headers = {"Mã khách hàng", "Họ tên", "Số điện thoại", "Email", "Địa chỉ"};
        List<Customer> customers = List.of(
// bo data vao
        );
        writeData(sheet, headers, customers);
    }

    private static void createImportReceiptSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet("Nhap_hang");
        String[] headers = {"Mã nhập hàng", "Ngày nhập hàng", "Nhà cung cấp", "Sản phẩm", "Số lượng", "Giá nhập", "Tổng tiền"};
        List<ImportReceipt> importReceipts = List.of(
//bo data vao
        );
        writeData(sheet, headers, importReceipts);
    }

    private static <T> void writeData(Sheet sheet, String[] headers, List<T> data) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        int rowNum = 1;
        for (T obj : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (var field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    row.createCell(colNum++).setCellValue(field.get(obj).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        exportToExcel("data.xlsx");
    }
}
