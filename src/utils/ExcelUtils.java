package utils;

import java.awt.FileDialog;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.relation.Role;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import entity.CategoryEntity;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import entity.ProductEntity;
import entity.PromotionEntity;
import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;
import entity.RoleEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;
import entity.VendorEntity;
import service.CategoryProductService;
import service.CustomerService;
import service.EmployeeService;
import service.ProductService;
import service.PromotionService;
import service.PurchaseOrderDetailService;
import service.PurchasingOrderService;
import service.SalesOrderDetailService;
import service.VendorService;

public class ExcelUtils {
	static FileDialog mFileDialog = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

	private static String getFile() {
		mFileDialog.setFile("untitled.xls");
		mFileDialog.setVisible(true);

		String url = mFileDialog.getDirectory() + mFileDialog.getFile();
		if (url.equals("nullnull")) {
			return null;
		}
		return url;
	}

	// Xuất file Excel Nhà cung cấp
	public static void exportExcelVendor(List<VendorEntity> list) {
		mFileDialog.setTitle("Xuất Nhà Cung Cấp");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			try (HSSFWorkbook workbook = new HSSFWorkbook()) {
				HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");

				int rownum = 0;
				Row row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue("STT");
				row.createCell(1, CellType.STRING).setCellValue("Mã nhà cung cấp");
				row.createCell(2, CellType.STRING).setCellValue("Tên nhà cung cấp");
				row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
				row.createCell(4, CellType.STRING).setCellValue("Số điện thoại");
				row.createCell(5, CellType.STRING).setCellValue("Fax");

				for (VendorEntity v : list) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
					row.createCell(1, CellType.STRING).setCellValue(v.getVendorId());
					row.createCell(2, CellType.STRING).setCellValue(v.getVendorName());
					row.createCell(3, CellType.STRING).setCellValue(v.getAddress());
					row.createCell(4, CellType.STRING).setCellValue(v.getPhone());
					row.createCell(5, CellType.STRING).setCellValue(v.getFax());
				}
				for (int i = 0; i < rownum; i++) {
					sheet.autoSizeColumn(i);
				}

				File file = new File(url);
				file.getParentFile().mkdirs();
				outFile = new FileOutputStream(file);
				workbook.write(outFile);

				JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Nhân viên
	public static void exportExcelEmployee(List<EmployeeEntity> list) {
		mFileDialog.setTitle("Xuất Nhân Viên");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Nhân viên");

			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
			row.createCell(2, CellType.STRING).setCellValue("Tên nhân viên");
			row.createCell(3, CellType.STRING).setCellValue("Ngày sinh");
			row.createCell(4, CellType.STRING).setCellValue("Địa chỉ");
			row.createCell(5, CellType.STRING).setCellValue("Số điện thoại");
			row.createCell(6, CellType.STRING).setCellValue("Trạng thái");

			for (EmployeeEntity e : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.STRING).setCellValue(e.getEmployeeId());
				row.createCell(2, CellType.STRING).setCellValue(e.getFullName());
				row.createCell(3, CellType.STRING).setCellValue(String.valueOf(e.getBirthDate()));
				row.createCell(4, CellType.STRING).setCellValue(e.getAddress());
				row.createCell(5, CellType.STRING).setCellValue(e.getPhone());
				row.createCell(6, CellType.STRING).setCellValue((e.getStatus() == 1 ? "Đang làm" : "Đã nghỉ"));
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Khách hàng
	public static void exportExcelCustomer(List<CustomerEntity> list) {
		mFileDialog.setTitle("Xuất Khách Hàng");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Khách hàng");

			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã khách hàng");
			row.createCell(2, CellType.STRING).setCellValue("Tên khách hàng");
			row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
			row.createCell(4, CellType.STRING).setCellValue("Số điện thoại");
			row.createCell(5, CellType.STRING).setCellValue("Trạng thái");

			for (CustomerEntity cs : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.STRING).setCellValue(cs.getCustomerId());
				row.createCell(2, CellType.STRING).setCellValue(cs.getFullName());
				row.createCell(3, CellType.STRING).setCellValue(cs.getAddress());
				row.createCell(4, CellType.STRING).setCellValue(cs.getPhone());
				row.createCell(5, CellType.STRING).setCellValue((cs.getStatus() == 1 ? "Hiện" : "Ẩn"));
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Khuyến mãi
	public static void exportExcelPromotion(List<PromotionEntity> list) {
		mFileDialog.setTitle("Xuất Khuyến mãi");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Khuyến mãi");

			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã khuyến mãi");
			row.createCell(2, CellType.STRING).setCellValue("Tên khuyến mãi");
			row.createCell(3, CellType.NUMERIC).setCellValue("Phần trăm");
			row.createCell(4, CellType.STRING).setCellValue("Ngày bắt đầu");
			row.createCell(5, CellType.STRING).setCellValue("Ngày kết thúc");

			for (PromotionEntity p : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.STRING).setCellValue(p.getPromotionId());
				row.createCell(2, CellType.STRING).setCellValue(p.getPromotionName());
				row.createCell(4, CellType.NUMERIC).setCellValue(p.getPromotionPercent());
				row.createCell(5, CellType.STRING).setCellValue(String.valueOf(p.getStartDate()));
				row.createCell(6, CellType.STRING).setCellValue(String.valueOf(p.getEndDate()));
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Sản phẩm
	public static void exportExcelProduct(List<ProductEntity> list) {
		mFileDialog.setTitle("Xuất Sản Phẩm");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Sản phẩm");

			CategoryProductService categoryProductService = new CategoryProductService();

			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã sản phẩm");
			row.createCell(2, CellType.STRING).setCellValue("Loại sản phẩm");
			row.createCell(3, CellType.STRING).setCellValue("Tên sản phẩm");
			row.createCell(4, CellType.NUMERIC).setCellValue("Đơn giá");
			row.createCell(5, CellType.NUMERIC).setCellValue("Số lượng");
			row.createCell(6, CellType.STRING).setCellValue("Trạng thái");

			for (ProductEntity p : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.NUMERIC).setCellValue(p.getProductId());
				row.createCell(2, CellType.STRING)
						.setCellValue(categoryProductService.findById(p.getCategoryId()).getCategoryName());
				row.createCell(3, CellType.STRING).setCellValue(p.getName());
				row.createCell(4, CellType.NUMERIC).setCellValue(p.getPrice());
				row.createCell(5, CellType.NUMERIC).setCellValue(p.getQuantity());
				row.createCell(6, CellType.NUMERIC).setCellValue(p.getStatus());

			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Loại sản phẩm
	public static void exportExcelCategory(List<CategoryEntity> list) {
		mFileDialog.setTitle("Xuất Loại Sản Phẩm");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Loại sản phẩm");

			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã Loại");
			row.createCell(2, CellType.STRING).setCellValue("Tên loại");
			row.createCell(3, CellType.STRING).setCellValue("Mô tả");

			for (CategoryEntity c : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.STRING).setCellValue(c.getCategoryId());
				row.createCell(2, CellType.STRING).setCellValue(c.getCategoryName());
				row.createCell(3, CellType.STRING).setCellValue(c.getDescription());
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Quyền
	public static void exportExcelRole(List<RoleEntity> list) {
		mFileDialog.setTitle("Xuất Quyền");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Quyền");

			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã quyền");
			row.createCell(2, CellType.STRING).setCellValue("Tên quyền");
			row.createCell(3, CellType.STRING).setCellValue("Chi tiết quyền");

			for (RoleEntity r : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.STRING).setCellValue(r.getRoleId());
				row.createCell(2, CellType.STRING).setCellValue(r.getRoleName());
				row.createCell(3, CellType.STRING).setCellValue(r.getDescription());
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Hóa đơn
	public static void exportExcelSalesOrder(List<SalesOrderEntity> list) {
		mFileDialog.setTitle("Xuất Hóa Đơn");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Hóa đơn");

			EmployeeService employeeService = new EmployeeService();
			CustomerService customerService = new CustomerService();
			PromotionService promotionService = new PromotionService();
			SalesOrderDetailService salesOrderDetailService = new SalesOrderDetailService();
			ProductService productService = new ProductService();

			int rownum = 0;
			int sttHoaDon = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
			row.createCell(2, CellType.STRING).setCellValue("Nhân viên");
			row.createCell(3, CellType.STRING).setCellValue("Khách hàng");
			row.createCell(4, CellType.STRING).setCellValue("Khuyến mãi");
			row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
			row.createCell(6, CellType.STRING).setCellValue("Giờ lập");
			row.createCell(7, CellType.STRING).setCellValue("Tổng tiền");

			row.createCell(8, CellType.STRING).setCellValue("Sản phẩm");
			row.createCell(9, CellType.STRING).setCellValue("Số lượng");
			row.createCell(10, CellType.STRING).setCellValue("Đơn giá");
			row.createCell(11, CellType.STRING).setCellValue("Thành tiền");

			for (SalesOrderEntity soe : list) {
				rownum++;
				sttHoaDon++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(sttHoaDon);
				row.createCell(1, CellType.STRING).setCellValue(soe.getSalesOrderID());
				row.createCell(2, CellType.STRING)
						.setCellValue(employeeService.findById(soe.getEmployeeId()).getFullName());
				row.createCell(3, CellType.STRING)
						.setCellValue(customerService.findById(soe.getCustomerId()).getFullName());
				row.createCell(4, CellType.STRING)
						.setCellValue(promotionService.findById(soe.getPromotionId()).getPromotionName());
				row.createCell(5, CellType.STRING).setCellValue(String.valueOf(soe.getOrderDate()));
				row.createCell(6, CellType.STRING).setCellValue(String.valueOf(soe.getOrderTime()));
				row.createCell(7, CellType.NUMERIC).setCellValue(soe.getTotalDue());

				for (SalesOrderDetailEntity cthd : salesOrderDetailService.findByOrderId(soe.getSalesOrderID())) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(8, CellType.STRING)
							.setCellValue(productService.findById(cthd.getProductId()).getName());
					row.createCell(9, CellType.NUMERIC).setCellValue(cthd.getOrderQuantity());
					row.createCell(10, CellType.NUMERIC)
							.setCellValue(productService.findById(cthd.getProductId()).getPrice());
					row.createCell(11, CellType.NUMERIC).setCellValue(cthd.getSubTotal());
				}
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	// Xuất file Excel Phiếu nhập
	public static void exportExcelPurchaseOrder(List<PuschaseOrderEntity> list) {
		mFileDialog.setTitle("Xuất dữ liệu phiếu nhập ra excel");
		String url = getFile();
		if (url == null) {
			return;
		}

		FileOutputStream outFile = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Hóa đơn nhập");

			EmployeeService employeeService = new EmployeeService();
			PromotionService promotionService = new PromotionService();
			ProductService productService = new ProductService();
			VendorService vendorService = new VendorService();
			PurchaseOrderDetailService detailService = new PurchaseOrderDetailService();
			PurchasingOrderService purchasingOrderService = new PurchasingOrderService();
			int rownum = 0;
			Row row = sheet.createRow(rownum);

			row.createCell(0, CellType.NUMERIC).setCellValue("STT");
			row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
			row.createCell(2, CellType.STRING).setCellValue("Mã nhà cung cấp");
			row.createCell(3, CellType.STRING).setCellValue("Mã nhân viên");
			row.createCell(4, CellType.STRING).setCellValue("Ngày lập");
			row.createCell(5, CellType.STRING).setCellValue("Giờ lập");
			row.createCell(6, CellType.STRING).setCellValue("Tổng tiền");
			row.createCell(7, CellType.STRING).setCellValue("Sản phẩm");
			row.createCell(8, CellType.STRING).setCellValue("Số lượng");
			row.createCell(9, CellType.STRING).setCellValue("Đơn giá");
			row.createCell(10, CellType.STRING).setCellValue("Thành tiền");

			for (PuschaseOrderEntity poe : list) {
				rownum++;
				row = sheet.createRow(rownum);

				row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
				row.createCell(1, CellType.NUMERIC).setCellValue(poe.getPuschaseOrderId());
				row.createCell(2, CellType.STRING)
						.setCellValue(vendorService.findById(poe.getPuschaseOrderId()).getVendorName());
				row.createCell(3, CellType.STRING)
						.setCellValue(employeeService.findById(poe.getEmployeeId()).getFullName());
				row.createCell(4, CellType.STRING).setCellValue(String.valueOf(poe.getOrderDate()));
				row.createCell(5, CellType.STRING).setCellValue(String.valueOf(poe.getOrderTime()));
				row.createCell(6, CellType.NUMERIC).setCellValue(poe.getTotalDue());

				for (PuschaseOrderDetailEntity ctpn : detailService.findByOrderId(poe.getPuschaseOrderId())) {
					rownum++;
					row = sheet.createRow(rownum);

					row.createCell(7, CellType.STRING)
							.setCellValue(productService.findById(ctpn.getProductId()).getName());

					row.createCell(8, CellType.NUMERIC).setCellValue(ctpn.getOrderQuantity());
					row.createCell(9, CellType.NUMERIC)
							.setCellValue(productService.findById(ctpn.getProductId()).getPrice());
					row.createCell(10, CellType.NUMERIC).setCellValue(ctpn.getSubTotal());
				}
			}
			for (int i = 0; i < rownum; i++) {
				sheet.autoSizeColumn(i);
			}

			File file = new File(url);
			file.getParentFile().mkdirs();
			outFile = new FileOutputStream(file);
			workbook.write(outFile);

			JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (outFile != null) {
					outFile.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(ExcelUtils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
