package org.leg.siteweb.page.admin;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.library.type.core.IList;
import org.leg.siteweb.common.Storage;
import org.leg.siteweb.common.dao.CourseInfoDao;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志记录动作
 */
public class InfoMCourseAction extends ActionSupport {
	private static final long serialVersionUID = 5165050377715752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(InfoMCourseAction.class);
    private Integer courseId;
    private String name;
    private String introduce;
    private String linkPla;

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {

        CourseInfoDao courseInfoDao = new CourseInfoDao();

        IList<Record> recordList = Storage.executor().select(courseInfoDao.getCourseInfoAll());
        if(null != recordList && recordList.size() > 0) {
            List<Record> resultList = new ArrayList<Record>();
            for (Record record : recordList) {
                resultList.add(record);
            }
            ServletActionContext.getContext().put("recordList", resultList);
        }

        return "SUCCESS";
    }

    public String manage() {
        String result;
        try {
            result = this.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "SUCCESS";
    }


    public String delMessage() {
        String result = "";
        CourseInfoDao courseInfoDao = new CourseInfoDao();
        Storage.executor().alter(courseInfoDao.delCourseById(courseId));

        try {
            result = execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public String addPre() {
        return "SUCCESS";
    }

    public String addMessage() {
        CourseInfoDao courseInfoDao = new CourseInfoDao();

        try {
            name = java.net.URLDecoder.decode(name, "UTF-8");
            introduce = java.net.URLDecoder.decode(introduce, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Storage.executor().alter(courseInfoDao.addCourse(name, introduce, linkPla));

        String result = "";
        try {
            result = execute();
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLinkPla() {
        return linkPla;
    }

    public void setLinkPla(String linkPla) {
        this.linkPla = linkPla;
    }
}
