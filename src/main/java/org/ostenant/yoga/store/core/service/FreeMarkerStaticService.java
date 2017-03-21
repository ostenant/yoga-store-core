package org.ostenant.yoga.store.core.service;

import java.util.Map;

public interface FreeMarkerStaticService {

	public void generateHtml(Map<String, Object> rootMap, Integer id);
}
