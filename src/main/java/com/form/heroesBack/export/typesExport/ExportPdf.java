// package com.form.heroesBack.export.typesExport;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.lang.reflect.Field;
// import java.util.List;
// import java.util.Map;

// import com.form.heroesBack.export.interfaces.ExportStrategy;
// import com.itextpdf.kernel.colors.DeviceRgb;
// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfWriter;
// import com.itextpdf.layout.Document;
// import com.itextpdf.layout.Style;
// import com.itextpdf.layout.borders.Border;
// import com.itextpdf.layout.element.Cell;
// import com.itextpdf.layout.element.Paragraph;
// import com.itextpdf.layout.element.Table;
// import com.itextpdf.layout.element.Text;
// import com.itextpdf.layout.property.TextAlignment;
// import com.itextpdf.layout.property.UnitValue;

// import org.apache.commons.lang.WordUtils;
// import org.springframework.stereotype.Service;

// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Service
// public class ExportPdf implements ExportStrategy {

//     private List<Object> list;

//     public ByteArrayInputStream export(Object result, Map<String, Object> request) {
//         this.list = (List<Object>) result;
//         ByteArrayOutputStream out = new ByteArrayOutputStream();
//         PdfWriter pdfWriter = new PdfWriter(out);
//         PdfDocument pdf = new PdfDocument(new PdfWriter(out));
//         Document document = new Document(pdf);

//         // Titulo

//         Text textTitle = new Text(WordUtils.capitalize(request.get("entidad").toString()));
//         Style titleStyle = new Style();
//         titleStyle.setBold();
//         titleStyle.setFontSize(10);
//         titleStyle.setFontColor(new DeviceRgb(31, 106, 51));
//         titleStyle.setUnderline();
//         textTitle.addStyle(titleStyle);
//         Paragraph title = new Paragraph();
//         title.add(textTitle).setTextAlignment(TextAlignment.CENTER);

//         document.add(title);

//         // Subtitulo
//         StringBuilder sb = new StringBuilder("Filtro usado: ");

//         request.keySet().forEach(e -> {
//             if (!"Entidad".equals(e)) {
//                 sb.append(e);
//                 sb.append(": ");
//                 sb.append(request.get(e));
//             }
//         });

//         log.info(sb.toString());

//         Text subText = new Text(sb.toString());
//         Style subTitleStyle = new Style();
//         subTitleStyle.setFontSize(6);
//         subTitleStyle.setFontColor(new DeviceRgb(31, 106, 51));
//         subText.addStyle(subTitleStyle);
//         Paragraph subTitle = new Paragraph();
//         subTitle.add(subText);
//         document.add(subTitle);

//         // Tabla
//         // Headers
//         Table table = new Table(this.list.get(0).getClass().getDeclaredFields().length - 1);
//         table.setWidth(UnitValue.createPercentValue(100));

//         Style headerStyle = new Style();
//         headerStyle.setBorder(Border.NO_BORDER);
//         headerStyle.setFontSize(8);
//         headerStyle.setFontColor(new DeviceRgb(31, 106, 51));

//         for (int i = 1; i < this.list.get(0).getClass().getDeclaredFields().length; i++) {
//             if (!"id".equals(list.get(0).getClass().getDeclaredFields()[i].getName())) {
//                 table.addCell(new Cell()
//                         .add(new Paragraph(
//                                 WordUtils.capitalize(this.list.get(0).getClass().getDeclaredFields()[i].getName())))
//                         .addStyle(headerStyle));
//             }
//         }

//         // Cells
//         Style cellStyle = new Style();
//         cellStyle.setBorder(Border.NO_BORDER);
//         cellStyle.setFontSize(8);
//         this.list.forEach(c -> {
//             Field[] fields = c.getClass().getDeclaredFields();
//             for (Field field : fields) {
//                 if (!"id".equals(field.getName())) {
//                     field.setAccessible(true);
//                     insertCell(table, cellStyle, c, field);
//                 }
//             }
//         });
//         document.add(table);

//         document.close();

//         return new ByteArrayInputStream(out.toByteArray());
//     }

//     private void insertCell(Table table, Style cellStyle, Object c, Field field) {
//         try {
//             if (this.list.indexOf(c) % 2 == 0) {
//                 table.addCell(new Cell().add(new Paragraph(field.get(c).toString())).addStyle(cellStyle)
//                         .setBackgroundColor(new DeviceRgb(173, 208, 125)));
//             } else {
//                 table.addCell(new Cell().add(new Paragraph(field.get(c).toString())).addStyle(cellStyle)
//                         .setBackgroundColor(new DeviceRgb(236, 236, 236)));
//             }
//         } catch (IllegalArgumentException | IllegalAccessException e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     public ByteArrayInputStream export(Object result) {
//         // TODO Auto-generated method stub
//         return null;
//     }

// }
