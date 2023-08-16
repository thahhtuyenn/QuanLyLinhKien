package utils;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;
import service.CustomerService;
import service.EmployeeService;
import service.ProductService;
import service.PromotionService;
import service.PurchaseOrderDetailService;
import service.PurchasingOrderService;
import service.SalesOrderDetailService;
import service.SalesOrderService;
import service.VendorService;

import java.awt.FileDialog;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class WritePDF {

    Document document;
    FileOutputStream file;
    Font fontData;
    Font fontTitle;
    Font fontHeader;

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    public WritePDF() {
        try {
            fontData = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL);
            fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 26, Font.NORMAL);
            fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 13, Font.NORMAL);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(WritePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Khong tim thay duong dan file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontTitle));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    public void writeObject(String[] data) {
        Paragraph pdfData = new Paragraph();
        for (int i = 0; i < data.length; i++) {
            pdfData.add(data[i] + "  ");
        }
        try {
            document.add(pdfData);
        } catch (DocumentException ex) {
            Logger.getLogger(WritePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeTable(JTable _table) {
        PdfPTable pdfTable = new PdfPTable(_table.getColumnCount());
        for (int i = 0; i < _table.getRowCount(); i++) {
            for (int j = 0; j < _table.getColumnCount(); j++) {
                String data = String.valueOf(_table.getValueAt(i, j));
                PdfPCell cell = new PdfPCell(new Phrase(data, fontData));
                pdfTable.addCell(cell);
            }
        }
        try {
            document.add(pdfTable);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }

    private String getFile() {
    	// default
        fd.setFile("untitled.pdf");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }
    

    public void writeHoaDon(int id) {
        String url = "";
        try {
            fd.setTitle("Hóa đơn"); 
            url = getFile(); 
            if (url == null) { 
                return;
            } 
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            
            setTitle("Thông tin hóa đơn");
            // Hien thong tin cua hoa don hien tai
            SalesOrderService salesOrderService = new SalesOrderService();
            CustomerService customerService = new CustomerService();
            EmployeeService employeeService = new EmployeeService();
            PromotionService promotionService = new PromotionService();
            ProductService productService = new ProductService();
            SalesOrderDetailService salesOrderDetailService = new SalesOrderDetailService();

            SalesOrderEntity salesOrderEntity = salesOrderService.findOne(id);

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Hóa đơn: " + String.valueOf(salesOrderEntity.getSalesOrderID()), fontData));

            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Khách hàng: " + customerService.findById(salesOrderEntity.getCustomerId()).getFullName()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(salesOrderEntity.getOrderDate()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + employeeService.findById(salesOrderEntity.getEmployeeId()).getFullName()));
            para2.add(glue);
            para2.add("Giờ lập: " + String.valueOf(salesOrderEntity.getOrderTime()));

            Paragraph paraKhuyenMai = new Paragraph();
            paraKhuyenMai.setPaddingTop(30);
            paraKhuyenMai.setFont(fontData);
            paraKhuyenMai.add("Khuyến mãi: " + promotionService.findById(salesOrderEntity.getPromotionId()).getPromotionName());

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(paraKhuyenMai);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(5);
            double tongKhuyenMai = 0;
            double tongThanhTien = 0;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                pdfTable.addCell(new PdfPCell(new Phrase("")));
            }

            //Truyen thong tin tung chi tiet vao table
            for (SalesOrderDetailEntity salesOrderDetailEntity : salesOrderDetailService.findByOrderId(id)) {

                int ma = salesOrderDetailEntity.getProductId();
                String ten = productService.findById(ma).getName();
                String gia = PriceFormatter.format(productService.findById(ma).getPrice());
                String soluong = String.valueOf(salesOrderDetailEntity.getOrderQuantity());
                String thanhtien = PriceFormatter.format(salesOrderDetailEntity.getSubTotal());

                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(ma), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(gia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soluong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhtien, fontData)));

                tongThanhTien += salesOrderDetailEntity.getSubTotal();
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            tongKhuyenMai = salesOrderEntity.getTotalDue() - tongThanhTien;
            
            Paragraph paraTongThanhTien = new Paragraph(new Phrase("Tổng thành tiền: " + PriceFormatter.format(tongThanhTien), fontData));
            paraTongThanhTien.setIndentationLeft(300);
            document.add(paraTongThanhTien);
            Paragraph paraTongKhuyenMai = new Paragraph(new Phrase("Tổng khuyến mãi: " + PriceFormatter.format(tongKhuyenMai), fontData));
            paraTongKhuyenMai.setIndentationLeft(300);
            document.add(paraTongKhuyenMai);
            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format(salesOrderEntity.getTotalDue()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }
    }
    

    public void writePhieuNhap(int id) {
        String url = "";
        try {
            fd.setTitle("Phiếu nhập");
            url = getFile();
            if (url == null) {
                return;
            }
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            
            setTitle("Thông tin phiếu nhập");

            PurchasingOrderService purchasingOrderService = new PurchasingOrderService();
            PurchaseOrderDetailService purchaseOrderDetailService = new PurchaseOrderDetailService();
            ProductService productService = new ProductService();
            VendorService vendorService = new VendorService(); 
            EmployeeService employeeService = new EmployeeService();

            PuschaseOrderEntity puschaseOrderEntity = purchasingOrderService.findOne(id);

            Chunk glue = new Chunk(new VerticalPositionMark());// Khoang trong giua hang
            Paragraph paraMaHoaDon = new Paragraph(new Phrase("Phiếu nhập: " + String.valueOf(puschaseOrderEntity.getPuschaseOrderId()), fontData));
            Paragraph para1 = new Paragraph();
            para1.setFont(fontData);
            para1.add(String.valueOf("Nhà cung cấp: " + vendorService.findById(puschaseOrderEntity.getVendorId()).getVendorName()));
            para1.add(glue);
            para1.add("Ngày lập: " + String.valueOf(puschaseOrderEntity.getOrderDate()));

            Paragraph para2 = new Paragraph();
            para2.setPaddingTop(30);
            para2.setFont(fontData);
            para2.add(String.valueOf("Nhân viên: " + employeeService.findById(puschaseOrderEntity.getEmployeeId()).getFullName()));
            para2.add(glue);
            para2.add("Giờ lập: " + String.valueOf(puschaseOrderEntity.getOrderTime()));

            document.add(paraMaHoaDon);
            document.add(para1);
            document.add(para2);
            document.add(Chunk.NEWLINE);//add hang trong de tao khoang cach

            //Tao table cho cac chi tiet cua hoa don
            PdfPTable pdfTable = new PdfPTable(5);
            PdfPCell cell;

            //Set headers cho table chi tiet
            pdfTable.addCell(new PdfPCell(new Phrase("Mã sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Đơn giá", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Số lượng", fontHeader)));
            pdfTable.addCell(new PdfPCell(new Phrase("Tổng tiền", fontHeader)));

            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                pdfTable.addCell(cell);
            }

            //Truyen thong tin tung chi tiet vao table
            for (PuschaseOrderDetailEntity puschaseOrderDetailEntity : purchaseOrderDetailService.findByOrderId(id)) {
            	int ma = puschaseOrderDetailEntity.getProductId();
                String ten = productService.findById(ma).getName();
                String gia = PriceFormatter.format(productService.findById(ma).getPrice());
                String soluong = String.valueOf(puschaseOrderDetailEntity.getOrderQuantity());
                String thanhtien = PriceFormatter.format(puschaseOrderDetailEntity.getSubTotal());

                pdfTable.addCell(new PdfPCell(new Phrase(String.valueOf(ma), fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(ten, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(gia, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(soluong, fontData)));
                pdfTable.addCell(new PdfPCell(new Phrase(thanhtien, fontData)));
            }

            document.add(pdfTable);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thanh toán: " + PriceFormatter.format(puschaseOrderEntity.getTotalDue()), fontData));
            paraTongThanhToan.setIndentationLeft(300);
            document.add(paraTongThanhToan);
            document.close();
            
            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }

    public void closeFile() {
        document.close();
    }
    public static void main(String[] args) {
		new WritePDF().writePhieuNhap(2);
	}
}
