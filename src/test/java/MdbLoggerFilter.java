import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import com.bfw.flume.plugin.mdb.filter.SinkFilter;

/**
 * @author Louis(LiXiang)
 * @description 自定义日志过滤器
 */
public class MdbLoggerFilter implements SinkFilter{
	/**
	 * 字段列表
	 */
	private String[] fields;
	
	/**
	 * 登录MongoDB用户名
	 */
	private static String userName;
	
	/**
	 * 登录MongoDB密码
	 */
	private static String passWord;
	
	/**
	 * 文档索引类型
	 */
	private String collectionName;
	
	/**
	 * 文档索引名称
	 */
	private String dataBaseName;
	
	/**
	 * 日志记录字段分隔符
	 */
	private String fieldSeparator;
	
	/**
	 * 逗号正则式
	 */
	private static Pattern commaRegex;
	
	@Override
	public String getDocId() {
		return fields[0]; 
	}
	
	@Override
	public String getPassword() {
		return passWord;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public String getCollectionName() {
		return collectionName; 
	}

	@Override
	public String getDataBaseName() {
		return dataBaseName; 
	}

	@Override
	public Map<String, Object> doFilter(String record) { 
		String[] fieldValues=commaRegex.split(record);
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put(fields[0], fieldValues[0].trim());
		map.put(fields[1], fieldValues[1].trim());
		map.put(fields[2], fieldValues[2].trim());
		return map;
	}

	@Override
	public void filterConfig(Properties properties) {
		commaRegex=Pattern.compile(fieldSeparator);
	}
}
