package org.ostenant.yoga.store.core.dao.user;

import java.util.List;

import org.ostenant.yoga.store.core.bean.user.Address;

public interface AddressMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    /**
     * @Title: getOwnAddrList <br>
     * @Description: 获取收货地址列表 <br>
     * @Author: madison <br>
     * @return List<Address>    返回类型   <br>
     * @throws
     */
	List<Address> getOwnAddrList(String username);

	/**
	 * @Title: modifyDefaultAddress <br>
	 * @Description: 修改默认的收货地址 <br>
	 * @Author: madison <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void modifyDefaultAddress(Integer addrId);

	/**
	 * @Title: getOwnAddrCount <br>
	 * @Description: 当前用户已经保存的地址条数 <br>
	 * @Author: madison <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	Integer getOwnAddrCount(String username);

	/**
	 * 获取默认收货地址
	 * @Title: getDefaultAddress <br>
	 * @Description: TODO(这里用一句话描述这个方法的作用) <br>
	 * @Author: madison <br>
	 * @return Address    返回类型   <br>
	 * @throws
	 */
	Address getDefaultAddress(String username);
}