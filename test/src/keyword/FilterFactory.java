package keyword;

import java.util.HashMap;
import java.util.Map;

public abstract class FilterFactory {
	protected static Map<String, Filter> filterManage = new HashMap<String, Filter>();
	
	public Filter getFilter(String filterName) {
		return filterManage.get(filterName);
	}
}
