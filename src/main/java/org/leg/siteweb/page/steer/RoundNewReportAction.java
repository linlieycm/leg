package org.leg.siteweb.page.steer;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.ImageHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.StoreinfoDao;
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
public class RoundNewReportAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(RoundNewReportAction.class);

    private Integer storeId;
    private String remark;
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

        StoreinfoDao storeinfoDao = new StoreinfoDao();

        //查询所辖分店
        IList<Record> storeinfoList = Storage.executor().select(storeinfoDao.getStoreInfoBySteerIdUndo(userId));
        if(null != storeinfoList && storeinfoList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record storeinfo : storeinfoList) {
                resultList.add(storeinfo);
            }
            ServletActionContext.getContext().put("storeinfoList", resultList);
        }

        ServletActionContext.getContext().put("userId", userId);
        return "SUCCESS";

    }

    public String doInsert() {

        int userId = User.getUserId();
        if(0 == userId) {
            ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }

        WorkinfoDao workinfoDao = new WorkinfoDao();
        StoreinfoDao storeinfoDao = new StoreinfoDao();

        //查询当天是否有报告，有直接显示
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String reportDate = df.format(new Date());
        Record workInfo = Storage.executor().load(workinfoDao.selWorkinfoStore(reportDate, userId, storeId, 3));
        if(null != workInfo) {
            ServletActionContext.getContext().put("workInfo", workInfo);
            Record storeInfo = Storage.executor().load(storeinfoDao.getStoreInfoById(workInfo.getInteger("StoreId")));
            ServletActionContext.getContext().put("storeInfo", storeInfo);
            return "SHOWHISTORY";
        }

        //保存图片
        String picIdList = "''";
        if(null != image && null !=imageFileName && imageFileName.length() > 0) {
            picIdList = ImageHelper.saveImage(image, imageFileName);
        }

        Storage.executor().alter(workinfoDao.insertWorkinfoThree(storeId, userId, 3, picIdList, remark, 2));

        String result = "";
        try {
            result =  this.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
