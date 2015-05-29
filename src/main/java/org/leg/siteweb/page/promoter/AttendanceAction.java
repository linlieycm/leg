package org.leg.siteweb.page.promoter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.siteweb.common.ImageHelper;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;
import org.leg.siteweb.common.dao.AttendanceDao;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 日志记录动作
 */
public class AttendanceAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(AttendanceAction.class);

    private Integer storeId;
    private Integer form;
    private String address;
    private Integer type;
    private String picStr;
    private File image;
    private String imageFileName;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {
        int userId = User.getUserId();
        if(0 == userId) {
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI());
        	ActionContext.getContext().put("redirect", ServletActionContext.getRequest().getRequestURI()); return "NOLOGIN";
        }
        AttendanceDao attendanceDao = new AttendanceDao();
        String picIdList = "";
        if(null != image && null !=imageFileName && imageFileName.length() > 0) {
            picIdList = ImageHelper.saveImage(image, imageFileName);
        }

		int result = Storage.executor().alter(attendanceDao.insertIntoAttendance(userId, storeId, form, address, type, picIdList));

        if(result > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }

    public String init() {
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPicStr() {
        return picStr;
    }

    public void setPicStr(String picStr) {
        this.picStr = picStr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
