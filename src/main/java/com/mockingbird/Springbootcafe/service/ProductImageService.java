package com.mockingbird.Springbootcafe.service;

import com.mockingbird.Springbootcafe.dao.ProductImageDAO;
import com.mockingbird.Springbootcafe.pojo.OrderItem;
import com.mockingbird.Springbootcafe.pojo.Product;
import com.mockingbird.Springbootcafe.pojo.ProductImage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductImageService {
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Resource
    ProductImageDAO productImageDAO;


    public void add(ProductImage bean) {
        productImageDAO.save(bean);

    }

    public void delete(int id) {
        productImageDAO.deleteById(id);
    }

    public ProductImage get(int id) {
        return productImageDAO.findById(id).orElse(null);
    }

    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, "type_" + type_single);
    }

    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, "type_" + type_detail);
    }

    public void setFirstProductImage(Product product) {
        List<ProductImage> singleImages = listSingleProductImages(product);
        if (!singleImages.isEmpty())
            product.setFirstProductImage(singleImages.get(0));
        else
            product.setFirstProductImage(new ProductImage()); //这样做是考虑到产品还没有来得及设置图片，但是在订单后台管理里查看订单项的对应产品图片。

    }

    public void setFirstProductImages(List<Product> products) {
        for (Product product : products)
            setFirstProductImage(product);
    }

    public void setFirstProductImagesOnOrderItems(List<OrderItem> ois) {
        for (OrderItem oi : ois) {
            setFirstProductImage(oi.getProduct());
        }
    }
}
