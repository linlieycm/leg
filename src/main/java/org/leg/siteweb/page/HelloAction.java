package org.leg.siteweb.page;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.json.JSONObject;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.User;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 日志记录动作
 */
public class HelloAction extends ActionSupport {
	private static final long serialVersionUID = 5165050349215752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(HelloAction.class);
	/**
	 * 上传的文件
	 */
	private File image;
	/**
	 * 上传的文件
	 */
	private String imageFileName;


	public void setImage(File image) {
		this.image = image;
	}
	public File getImage() {
		return image;
	}

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {
    	User.setUserId(1);
    	System.out.println(User.getUserId());

    	System.out.println(image);
    	JSONObject j = JSONObject.convert("{\"SHUIBEI\":\"a\",\"KATAO\":\"b\",\"TTTTTT\":\"c\"}");
    	j.toString();
    	IList<Record> record = Storage.executor().select("select DATE_FORMAT(Createtime,'%Y-%m-%d') as attTime, Type as type from t_caption where UserId = 1 and DATE_FORMAT(Createtime,'%Y-%m-%d') = '2015-05-03'");//("SELECT * FROM T_Caption WHERE ID = 2");
		// output("Caption = " + record.get("Caption"));
		ServletActionContext.getContext().put("message", record);
		return "ftl";
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
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageFileName() {
		return imageFileName;
	}
}
