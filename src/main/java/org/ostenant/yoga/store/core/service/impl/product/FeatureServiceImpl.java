package org.ostenant.yoga.store.core.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.product.Feature;
import org.ostenant.yoga.store.core.dao.product.FeatureMapper;
import org.ostenant.yoga.store.core.query.FeatureQuery;
import org.ostenant.yoga.store.core.service.product.FeatureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {

	@Resource
	private FeatureMapper featureMapper;

	@Transactional(readOnly = true)
	public List<Feature> getFeatureList() {
		List<Feature> features= featureMapper.getFeatureList();
		return features;
	}

	@Transactional(readOnly = true)
	public List<Feature> getFeatureList2(FeatureQuery featureQuery) {
		List<Feature> featureList2 = featureMapper.getFeatureList2(featureQuery);
		return featureList2;
	}
}
