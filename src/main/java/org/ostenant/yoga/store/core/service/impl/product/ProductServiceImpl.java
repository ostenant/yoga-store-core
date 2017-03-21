package org.ostenant.yoga.store.core.service.impl.product;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.ostenant.yoga.store.common.constant.CustomConstant;
import org.ostenant.yoga.store.common.utils.CharSequenceUtils;
import org.ostenant.yoga.store.core.bean.product.Image;
import org.ostenant.yoga.store.core.bean.product.Product;
import org.ostenant.yoga.store.core.bean.product.ProductWithBLOBs;
import org.ostenant.yoga.store.core.bean.product.SkuCell;
import org.ostenant.yoga.store.core.dao.product.ProductMapper;
import org.ostenant.yoga.store.core.dao.product.SkuCellMapper;
import org.ostenant.yoga.store.core.query.MultiProductQuery;
import org.ostenant.yoga.store.core.query.ProductQuery;
import org.ostenant.yoga.store.core.query.ProductSaveData;
import org.ostenant.yoga.store.core.response.ProductResponse;
import org.ostenant.yoga.store.core.response.SkuCellResponse;
import org.ostenant.yoga.store.core.service.FreeMarkerStaticService;
import org.ostenant.yoga.store.core.service.product.ImageService;
import org.ostenant.yoga.store.core.service.product.ProductService;
import org.ostenant.yoga.store.core.service.product.SkuCellService;
import org.ostenant.yoga.store.webservice.DirMakerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductMapper productMapper;

	@Resource
	private SkuCellMapper skuCellMapper;

	@Autowired
	private ImageService imageService;

	@Autowired
	private SkuCellService skuCellService;

	@Autowired
	private FreeMarkerStaticService freeMarkerStaticService;

	private static Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	@Transactional(readOnly = true)
	public List<ProductResponse> getProductListByQuery(ProductQuery productQuery) {
		List<ProductResponse> pageList = productMapper
				.getProductListByQuery(productQuery);
		return pageList;
	}

	@Transactional(readOnly = true)
	public int getTotalCountByQuery(ProductQuery productQuery) {
		int count = productMapper.getTotalCountByQuery(productQuery);
		return count;
	}

	public int addProduct(ProductSaveData productSaveData) {

		ProductWithBLOBs product = new ProductWithBLOBs();
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");

		// -- 1.一般属性
		product.setIsCommend(productSaveData.getIsCommend());
		product.setIsDel(productSaveData.getIsDel());
		product.setIsHot(productSaveData.getIsHot());
		product.setIsNew(productSaveData.getIsNew());
		product.setIsShow(productSaveData.getIsShow());

		String no = format.format(new Date()); // 序号
		product.setNo(no);
		product.setName(productSaveData.getName());
		product.setWeight(productSaveData.getWeight());
		product.setTypeId(productSaveData.getTypeId());
		product.setBrandId(productSaveData.getBrandId());

		// -- 2.创建者
		product.setCreateUserId(productSaveData.getCreateUserId());
		product.setCreateTime(new Date());

		// -- 3.checkbox属性
		String[] colors = productSaveData.getColor();
		String[] features = productSaveData.getFeature();
		String[] sizes = productSaveData.getSize();

		product.setColor(CharSequenceUtils.arrayToString(colors));
		product.setSize(CharSequenceUtils.arrayToString(sizes));
		product.setFeature(CharSequenceUtils.arrayToString(features));

		// -- 4.大文本字段
		product.setDescription(productSaveData.getDescription());
		product.setPackageList(productSaveData.getPackageList());

		// 1.保存商品信息 返回影响行数
		int row = productMapper.insertSelective(product);

		// 2.保存图片信息
		Integer productId = product.getId();
		Image image = new Image();
		image.setProductId(productId);
		image.setUrl(productSaveData.getUrl());
		image.setIsDef(true); // 默认图片

		// imageMapper.insertSelective(image);
		// TODO 为了配置AOP，加上分布式缓存，精确到细粒度 必须注入service
		imageService.addImage(image);

		// 3.保存商品最小最小单元信息
		SkuCell sku = new SkuCell();
		// 商品ID
		sku.setProductId(productId);
		// 运费
		sku.setDeliveFee(10.00);
		// 售价
		sku.setSkuPrice(88.00);
		// 市场价
		sku.setMarketPrice(99.00);
		// 库存
		sku.setStockInventory(100);
		// 购买限制
		sku.setSkuUpperLimit(3);
		// 添加时间
		sku.setCreateTime(new Date());
		// 是否最新
		sku.setLastStatus(1);// 历史或者最新
		// 商品
		sku.setSkuType(1);
		// 销量
		sku.setSales(12);

		// 尺寸大小，颜色分开保存
		for (String colorId : colors) {
			Integer cid = Integer.parseInt(colorId);
			sku.setColorId(cid);
			for (String size : sizes) {
				sku.setSize(size);
				// 批量保存
				if (image.getIsDef()) {
					// 商品图片默认是最小销售单元图片
					sku.setSkuImg(image.getUrl());
				}
				skuCellMapper.insertSelective(sku);
			}
		}

		productMapper.updateSalesFromSkuBy(productId);
		productMapper.updatePriceFromSkuBy(productId);

		return row;

	}

	public void deleteProductById(ProductQuery productQuery) {
		try {

			int productId = productQuery.getId();
			if (productId > 0) {

				List<Image> images = imageService.getByProductId(productId);

				// -- 调用webservice删除远程服务器图片
				URL wsdlDocumentLocation;

				wsdlDocumentLocation = new URL(CustomConstant.DIR_WSDL_URL);

				// wsdl命名空间
				String namespaceURI = CustomConstant.NAMESPACE_URI;
				// 服务视图名称
				String localPart = CustomConstant.LOCAL_PART;
				QName serviceName = new QName(namespaceURI, localPart);

				// 创建服务视图
				javax.xml.ws.Service service = javax.xml.ws.Service.create(
						wsdlDocumentLocation, serviceName);
				// 获取portType
				DirMakerImpl dirMaker = service.getPort(DirMakerImpl.class);
				// -- 1.删除图片
				for (Image image : images) {
					String url = image.getUrl();

					int row = imageService.deleteByProductId(productId);
					if (row > 0) {
						// 删除图片文件
						boolean isDel = dirMaker.deleteImage(url);
						if (!isDel) {
							logger.warn("图片删除失败，图片可能不存在！");
						} else {
							logger.warn("图片删除成功！");
						}
						// 删除图片信息
						imageService.deleteByProductId(productId);
						// TODO 为了配置AOP，加上分布式缓存，精确到细粒度 必须注入service
					}
				}

				// -- 2.删除最小销售单元
				skuCellMapper.deleteByProductId(productId);
				// -- 3.删除商品信息
				productMapper.deleteByPrimaryKey(productId);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void underCarriageByIds(Integer[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (Integer id : ids) {
			if (id != null && id > 0) {
				idList.add(id);
			}
		}

		HashMap<Object, Object> params = Maps.newHashMap();
		params.put("idList", idList);
		params.put("isShow", false);
		productMapper.changeIsShowByIds(params);

	}

	public void topCarriageByIds(Integer[] ids) {
		List<Integer> idList = new ArrayList<Integer>();
		for (Integer id : ids) {
			if (id != null && id > 0) {
				idList.add(id);
			}
		}

		HashMap<Object, Object> params = Maps.newHashMap();
		params.put("idList", idList);
		params.put("isShow", true);
		productMapper.changeIsShowByIds(params);

		// ---------------------------------- 准备模版数据
		// --------------------------------------
		Map<String, Object> rootMap = null;
		if (ids != null && ids.length > 0) {
			for (Integer productId : ids) {
				rootMap = new HashMap<String, Object>();
				// 指定商品详情
				ProductResponse productResponse = getProductById(productId);

				// 最小销售单元
				List<SkuCellResponse> skuCells = skuCellService
						.getSkuListByPidWithoutPage(productId);

				// freemarker遍历时key不能是Integer
				HashMap<String, String> colors = new HashMap<String, String>();
				for (SkuCellResponse skuCell : skuCells) {
					if (!colors.containsKey(skuCell.getColorId() + "")) {
						// 如果没有key
						colors.put(skuCell.getColorId() + "",
								skuCell.getColor());
					}
				}

				rootMap.put("product", productResponse);
				rootMap.put("skuCells", skuCells);
				rootMap.put("colors", colors);
				rootMap.put("basePath", CustomConstant.IMAGE_SERVER_URL);
				rootMap.put("path", CustomConstant.IMAGE_SERVER_URL
						+ productResponse.getUrl());

				// 分别生成模版输出
				freeMarkerStaticService.generateHtml(rootMap, productId);
			}
		}

	}

	@Transactional(readOnly = true)
	public List<ProductResponse> getProductListWithPage(
			MultiProductQuery multiProductQuery) {
		List<ProductResponse> productListWithPage = productMapper
				.getProductListWithPage(multiProductQuery);
		return productListWithPage;
	}

	@Transactional(readOnly = true)
	public int getTotalCountListWithPage(MultiProductQuery multiProductQuery) {
		return productMapper.getTotalCountListWithPage(multiProductQuery);
	}

	@Transactional(readOnly = true)
	public ProductResponse getProductById(Integer productId) {
		ProductResponse productResponse = productMapper
				.getProductById(productId);
		return productResponse;
	}

	@Transactional(readOnly = true)
	public List<ProductResponse> getProductByIds(List<String> list) {
		List<ProductResponse> productList = productMapper.getProductByIds(list);
		return productList;
	}

	@Transactional(readOnly = true)
	public Product getProductByIdOnly(Integer productId) {
		Product product = productMapper.getByProductId(productId);
		return product;
	}

}
