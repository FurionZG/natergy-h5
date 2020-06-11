package com.natergy.natergyh5.utils;

import com.aspose.cells.License;
import com.aspose.cells.PdfSaveOptions;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import com.natergy.natergyh5.entity.Entry;
import com.natergy.natergyh5.entity.Offer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: 杨枕戈
 * @Date: 2019/12/5 14:52
 */
@Component
public class ExcelServer {
    // 经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 200;
    // 默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "png";
    @RequestMapping("/sheetToImage")
    @ResponseBody
    public void excelToImage(@RequestBody Offer offer) throws IOException {
        Integer entryRowCount = 9;
        ApplicationHome home = new ApplicationHome(getClass());
        InputStream in = new FileInputStream(home.toString()+"/static/priceList.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet1 = wb.getSheetAt(0);
        HSSFPatriarch pat = sheet1.getDrawingPatriarch();
        List children = pat.getChildren();
        Iterator it = children.iterator();
        while(it.hasNext()) {
            HSSFShape shape = (HSSFShape)it.next();
            if (shape instanceof HSSFTextbox){
                HSSFTextbox textbox = (HSSFTextbox)shape;
                HSSFRichTextString richString = textbox.getString();
                String str = richString.getString();
                if("$1".equals(str.trim())){
                    textbox.setString(new HSSFRichTextString(new SimpleDateFormat("yyyy年MM月dd日").format(new Date())));
                }
            }
        }
        Row rowName  = sheet1.getRow(2);
        rowName.getCell(0).setCellValue(offer.getCompanyName());
        for(Entry entry:offer.getEntryList()){
            Row rowTmp  = sheet1.getRow(entryRowCount);
            rowTmp.getCell(0).setCellValue("绿能3A分子筛");
            rowTmp.getCell(1).setCellValue(entry.getSize());
            rowTmp.getCell(2).setCellValue(entry.getWrapper());
            rowTmp.getCell(3).setCellValue(entry.getPrice());
            HSSFCellStyle cellStyle = getCellStyle(wb);
            for(int i =0;i<=3;i++){
                rowTmp.getCell(i).setCellStyle(cellStyle);
            }
            entryRowCount++;
        }
        FileOutputStream out = new FileOutputStream(home.toString()+"/static/priceListTmp.xls");
        wb.write(out);
        out.close();
        excel2pdf(home.toString()+"/static/priceListTmp.xls",home.toString()+"/static/priceList.pdf");
        pdfToImage(home.toString()+"/static/priceList.pdf", home.toString()+"/static/priceList.png");
    }

    private HSSFCellStyle  getCellStyle(HSSFWorkbook wb) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);
        return cellStyle;
    }

    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = ExcelServer.class.getClassLoader().getResourceAsStream("license.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void excel2pdf(String Address, String Address_out) {
        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
            return;
        }
        try {
            File pdfFile = new File(Address_out);// 输出路径
            Workbook wb = new Workbook(Address);// 原始excel路径
            PdfSaveOptions pdfSaveOptions = new PdfSaveOptions();
            pdfSaveOptions.setOnePagePerSheet(true);
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            wb.save(fileOS, SaveFormat.PDF);
            fileOS.close();
//			System.out.print("end");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * pdf转图片
     *
     * @param pdfPath PDF路径
     * @imgPath img路径
     * @page_end 要转换的页码，也可以定义开始页码和结束页码，我这里只需要一页，根据需求自行添加
     */
    public static void pdfToImage(String pdfPath, String imgPath) {
        try {
            // 图像合并使用参数
            // 总宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            int shiftHeight = 0;
            // 保存每张图片的像素值
            BufferedImage imageResult = null;
            // 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            // 循环每个页码
            for (int i = 0, len = pdDocument.getNumberOfPages(); i < len; i++) {
//      if (i==page_end) {
                BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
                int imageHeight = image.getHeight();
                int imageWidth = image.getWidth();
                // 计算高度和偏移量
                // 使用第一张图片宽度;
                width = imageWidth;
                // 保存每页图片的像素值
                imageResult = new BufferedImage(width, imageHeight, BufferedImage.TYPE_INT_RGB);
                // 这里有高度，可以将imageHeight*len，我这里值提取一页所以不需要
                singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                // 写入流中
                imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
//      }else if(i>page_end) {
//        continue;
//      }
                break;
            }
            pdDocument.close();
            // 写图片
            ImageIO.write(imageResult, DEFAULT_FORMAT, new File(imgPath));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        // OVER
    }




}
