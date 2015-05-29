package org.leg.siteweb.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by myj on 15/5/12.
 */
public class Common {
    public static String getTxtWithoutNTSRElement(String str){
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("[\\s]|[\t]|[\r]|[\n]|[?]|[^\\p{ASCII}]");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
