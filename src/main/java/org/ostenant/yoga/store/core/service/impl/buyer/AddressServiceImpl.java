package org.ostenant.yoga.store.core.service.impl.buyer;

import java.util.List;

import org.ostenant.yoga.store.core.bean.user.Address;
import org.ostenant.yoga.store.core.bean.user.Buyer;
import org.ostenant.yoga.store.core.dao.user.AddressMapper;
import org.ostenant.yoga.store.core.service.buyer.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Transactional(readOnly = true)
	public List<Address> getOwnAddrList(Buyer buyer) {
		List<Address> addresses = addressMapper.getOwnAddrList(buyer
				.getUsername());
		return addresses;
	}

	public void modifyDefaultAddress(Integer addrId) {
		addressMapper.modifyDefaultAddress(addrId);
	}

	@Transactional(readOnly = true)
	public Integer getOwnAddrCount(String username) {
		Integer count = addressMapper.getOwnAddrCount(username);
		return count;
	}

	public void saveOwnAddr(Address address) {
		addressMapper.insertSelective(address);
	}

	public void deleteAddressById(Integer addrId) {
		addressMapper.deleteByPrimaryKey(addrId);
	}

	@Transactional(readOnly = true)
	public Address getDefaultAddress(String username) {
		Address address = addressMapper.getDefaultAddress(username);
		return address;
	}

	@Transactional(readOnly = true)
	public Address getAddressById(Integer addrId) {
		return addressMapper.selectByPrimaryKey(addrId);
	}
}
