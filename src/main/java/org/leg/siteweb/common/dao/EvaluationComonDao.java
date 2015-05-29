package org.leg.siteweb.common.dao;

/**
 * Created by myj on 15/5/9.
 */
public class EvaluationComonDao {
    public String getEvaluationComonAll() {
        String result = "select * from EvaluationComon where status = 1";
        return result;
    }
}
