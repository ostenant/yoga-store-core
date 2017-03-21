package org.ostenant.yoga.store.core.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.ostenant.yoga.store.core.bean.product.Type;
import org.ostenant.yoga.store.core.dao.product.TypeMapper;
import org.ostenant.yoga.store.core.query.TypeQuery;
import org.ostenant.yoga.store.core.service.product.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {

	@Resource
	private TypeMapper typeMapper;

	@Transactional(readOnly = true)
	public List<Type> getTypeNameList() {
		List<Type> types = typeMapper.getTypeNameList();
		return types;
	}

	@Transactional(readOnly = true)
	public List<Type> getTypeList(TypeQuery typeQuery) {
		List<Type> typeList = typeMapper.getTypeList(typeQuery);
		return typeList;
	}

	@Transactional(readOnly = true)
	public Type getTypeById(Integer typeId) {
		Type type = typeMapper.selectByPrimaryKey(typeId);
		return type;
	}

}
