package org.ostenant.yoga.store.core.service.buyer;

import java.util.List;

import org.ostenant.yoga.store.core.bean.user.Address;
import org.ostenant.yoga.store.core.bean.user.Buyer;

public interface AddressService {

	/**
	 * @Title: getOwnAddrList <br>
	 * @Description: 获取收货地址列表 <br>
	 * @Author: madison <br>
	 * @return List<Address>    返回类型   <br>
	 * @throws
	 */
	List<Address> getOwnAddrList(Buyer buyer);

	/**
	 * @Title: modifyDefaultAddress <br>
	 * @Description: 修改默认的收货地址 <br>
	 * @Author: madison <br>
	 * @param @param addrId    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void modifyDefaultAddress(Integer addrId);

	/**
	 * @Title: getOwnAddrCount <br>
	 * @Description: 查看当前用户的地址保存个数 <br>
	 * @Author: madison <br>
	 * @return Integer    返回类型   <br>
	 * @throws
	 */
	Integer getOwnAddrCount(String username);

	/**
	 * @Title: saveOwnAddr <br>
	 * @Description: 保存一条收货地址<br>
	 * @Author: madison <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void saveOwnAddr(Address address);

	/**
	 * @Title: deleteAddressById <br>
	 * @Description: 删除收货地址 <br>
	 * @Author: madison <br>
	 * @param @param addrId    设定文件 <br>
	 * @return void    返回类型   <br>
	 * @throws
	 */
	void deleteAddressById(Integer addrId);

	/**
	 * @Title: getDefaultAddress <br>
	 * @Description: 获取默认收货地址 <br>
	 * @Author: madison <br>
	 * @return Address    返回类型   <br>
	 * @throws
	 */
	Address getDefaultAddress(String username);

	/**
	 * 
	 * @Title: getAddressById <br>
	 * @Description: 获取地址 <br>
	 * @Author: madison <br>
	 * @return Address    返回类型   <br>
	 * @throws
	 */
	Address getAddressById(Integer addrId);

}
