package org.leg.siteweb.common;

import jxl.Workbook;
import jxl.write.*;
import org.leg.library.time.Date;
import org.leg.library.type.Record;
import org.leg.library.type.core.ILink;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Boolean;
import java.lang.Number;
import java.util.List;

/**
 * Created by myj on 15/5/10.
 */
public class JxlHelper {

    @SuppressWarnings("unchecked")
    public static InputStream getExcel(List<Record> list) throws IOException,
            WriteException {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //Class clazz = list.get(0).getClass();
        // 文件流
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        WritableWorkbook book = Workbook.createWorkbook(ostream);
        WritableSheet ws = book.createSheet("sheet1", 0);
        Label lable = null;
        // 列标
        WritableFont wfColumn = new WritableFont(WritableFont.TIMES, 10,
                WritableFont.BOLD, false);
        WritableCellFormat wcfColumn = new WritableCellFormat(wfColumn);
        wcfColumn.setAlignment(Alignment.CENTRE);
        wcfColumn.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        int j = 0;
        // 标题
        for (ILink<String, Object> link : list.get(0)) {
            lable = new Label(j++, 0, nameChenge(link.getOrigin()), wcfColumn);
            ws.addCell(lable);
        }
//        Field[] fields = clazz.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            if (!fields[i].getName().equalsIgnoreCase("serialVersionUID")) {
//                lable = new Label(j++, 0, fields[i].getName(), wcfColumn);
//                ws.addCell(lable);
//            }
//        }

//        Method[] methods = clazz.getMethods();
//        List<Method> getMethods = new ArrayList<Method>();
//        for (j = 0; j < fields.length; j++) {
//            for (int i = 0; i < methods.length; i++) {
//                if (methods[i].getName().equalsIgnoreCase(
//                        "get" + fields[j].getName())) {
//                    getMethods.add(methods[i]);
//                }
//            }
//        }

        // 内容
        WritableCellFormat wcfCell = new WritableCellFormat();
        wcfCell.setAlignment(Alignment.CENTRE);
        wcfCell.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        int i = 0;
        for (Record record : list) {
            int r = 0;
            i++;
            for (ILink<String, Object> link : record) {
                try {
                    Object result;
                    if(null == link.getDestination()) {
                        result = null;
                    }
                    else if(link.getDestination() instanceof Boolean) {
                        result = link.getDestination();
                    }
                    else if(link.getDestination() instanceof Number) {
                        result = link.getDestination();
                    }
                    else if(link.getDestination() instanceof String) {
                        result = link.getDestination();
                    }
                    else if(link.getDestination() instanceof Date) {
                        result = ((Date) link.getDestination()).toString();
                    }
                    else {
                        result = link.getDestination();
                    }
                    lable = new Label(r++, i, result == null ? "" : result
                            .toString(), wcfCell);
                    ws.addCell(lable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        book.write();
        book.close();
        InputStream istream = new ByteArrayInputStream(ostream.toByteArray());
        return istream;
    }

    private static String nameChenge(String iLink) {
        String result = iLink;
        if(result.equals("userId")) {
            result = "用户ID";
        } else if(result.equals("city")) {
            result = "城市";
        } else if(result.equals("storeName")) {
            result = "门店";
        } else if(result.equals("name")) {
            result = "促销员";
        } else if(result.equals("maxTime")) {
            result = "离岗时间";
        } else if(result.equals("minTime")) {
            result = "到岗时间";
        } else if(result.equals("usName")) {
            result = "所辖督导";
        } else if(result.equals("creaDay")) {
            result = "日期";
        } else if(result.equals("giftId")) {
            result = "礼品ID";
        } else if(result.equals("giftName")) {
            result = "礼品名";
        } else if(result.equals("num")) {
            result = "礼品数";
        }

        return result;
    }

}
