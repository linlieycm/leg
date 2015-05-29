package org.leg.siteweb.page.promoter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.json.JSONObject;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.ImageHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.GiftinfoDao;
import org.leg.siteweb.common.dao.UserinfoDao;
import org.leg.siteweb.common.dao.WorkinfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日志记录动作
 */
public class SalesMUpReportInsertAction extends ActionSupport {
	private static final long serialVersionUID = 5165050366615752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(SalesMUpReportInsertAction.class);

    private Integer stockStatus;
    private String giftJson;
    private File image;
    private String imageFileName;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        giftJson = giftJson.replace("[","").replace("]","");

        WorkinfoDao workinfoDao = new WorkinfoDao();
        GiftinfoDao giftinfoDao = new GiftinfoDao();
        UserinfoDao userinfoDao = new UserinfoDao();

        //保存图片
        String picIdList = "''";
        if(null != image && null !=imageFileName && imageFileName.length() > 0) {
            picIdList = ImageHelper.saveImage(image, imageFileName);
        }
        //插入workInfo表
        Record userRecord = Storage.executor().load(userinfoDao.getUserInfoById(userId));
        Integer storeId = Integer.parseInt(userRecord.get("StoreId").toString());
        Integer reportType = 1;
        Integer status = 1;
        Storage.executor().alter(workinfoDao.insertWorkinfoUp(storeId, userId, reportType, giftJson, picIdList, stockStatus, status));


        //查询赠品列表
        IList<Record> giftList = Storage.executor().select(giftinfoDao.getGiftInfoAll());

        //查询当前日期，如果今天已经提交了，则直接显示
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String reportDate = df.format(new Date());
        Record oldRecord = Storage.executor().load(workinfoDao.selWorkinfo(reportDate, userId, 1));
        if(null != oldRecord) {
            JSONObject j = JSONObject.convert(oldRecord.get("GiftInfo").toString());

            List<Record> giftResultList = new ArrayList<Record>();
            for(Record record : giftList) {
                String giftId = record.get("ID").toString();
                String giftName = record.get("GiftName").toString();
                if(null != j.get(giftId)) {
                    Record giftResult = new Record();
                    String num = j.get(giftId).toString();
                    giftResult.put("giftId", giftId);
                    giftResult.put("giftName", giftName);
                    giftResult.put("num", num);
                    giftResultList.add(giftResult);
                }
            }
            ServletActionContext.getContext().put("giftResultList", giftResultList);
            ServletActionContext.getContext().put("oldRecord", oldRecord);

            ServletActionContext.getContext().put("stockStatus", oldRecord.get("StockStatus"));
            ServletActionContext.getContext().put("status", oldRecord.get("Status"));
            return "SHOWHISTORY";
        }

        return "SUCCESS";
    }

	/**
	 * 输出控制台内容
	 * 
	 * @param content 控制台内容
	 */
	public void output(String content) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		}
		catch (IOException ex) {
			logger.error("call Response.getWriter() failed", ex);
			return;
		}
		writer.write(content);
		writer.close();
	}

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getGiftJson() {
        return giftJson;
    }

    public void setGiftJson(String giftJson) {
        this.giftJson = giftJson;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
