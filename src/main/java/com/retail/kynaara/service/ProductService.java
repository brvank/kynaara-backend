package com.retail.kynaara.service;

import com.retail.kynaara.model.Product;
import com.retail.kynaara.model.User;
import com.retail.kynaara.model.UserPermissions;
import com.retail.kynaara.repository.ProductCustomRepository;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.AppUtil;
import com.retail.kynaara.utility.UserPermissionsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProductService {
    @Autowired
    ProductCustomRepository productCustomRepository;

    @Autowired
    AppUtil.Constants appUtilConstants;

    @Autowired
    AppMessages.Success success;

    @Autowired
    AppMessages.Error error;

    @Autowired
    private AppResponse appResponse;

    @Autowired
    private UserPermissionsManager userPermissionsManager;

    
    //create operations
    public ResponseEntity<Object> addProduct(Map<String, Object> productMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            UserPermissions userPermissions = userPermissionsManager.getUserPermissions(user.getUser_user_level());
            if(!userPermissions.isAlterProduct()){
                return appResponse.failureResponse(error.permissionDenied);
            }

            Product product = new Product();

            product.setProduct_link((String) productMap.get(appUtilConstants.PRODUCT_LINK));
            product.setProduct_image_link((String) productMap.get(appUtilConstants.PRODUCT_IMAGE_LINK));
            product.setProduct_date_created(LocalDateTime.now());
            product.setProduct_creator_id(user.getUser_id());

            productCustomRepository.addProduct(product);

            return appResponse.successResponse(success.productAdded);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.productNotAdded);
        }
    }
    
    //read operations
    public ResponseEntity<Object> getProducts(int start, int size, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            return appResponse.successResponse(productCustomRepository.getProducts(start, size), null);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    public ResponseEntity<Object> getProductByProductId(int productId, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            List<Product> productList = productCustomRepository.getProductByProductId(productId);
            if(productList.isEmpty()){
                return appResponse.failureResponse(productList, error.productDoesNotExist);
            }else{
                return appResponse.successResponse(productList.get(0), null);
            }

        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    //update operations
    public ResponseEntity<Object> updateProduct(Map<String, Object> productMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            UserPermissions userPermissions = userPermissionsManager.getUserPermissions(user.getUser_user_level());
            if(!userPermissions.isAlterProduct()){
                return appResponse.failureResponse(error.permissionDenied);
            }

            Product productToUpdate = new Product();

            productToUpdate.setProduct_id((int) productMap.get(appUtilConstants.PRODUCT_ID));
            productToUpdate.setProduct_image_link((String) productMap.get(appUtilConstants.PRODUCT_IMAGE_LINK));
            productToUpdate.setProduct_link((String) productMap.get(appUtilConstants.PRODUCT_LINK));

            List<Product> productList = productCustomRepository.getProductByProductId(productToUpdate.getProduct_id());
            if(productList.isEmpty()){
                return appResponse.failureResponse(error.productDoesNotExist);
            }

            productCustomRepository.updateProduct(productToUpdate);

            return appResponse.successResponse(success.productUpdated);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.productNotUpdated);
        }
    }

    //delete operations
    public ResponseEntity<Object> deleteProduct(int productId, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            UserPermissions userPermissions = userPermissionsManager.getUserPermissions(user.getUser_user_level());
            if(!userPermissions.isAlterProduct()){
                return appResponse.failureResponse(error.permissionDenied);
            }

            List<Product> productList = productCustomRepository.getProductByProductId(productId);
            if(productList.isEmpty()){
                return appResponse.failureResponse(error.productDoesNotExist);
            }

            productCustomRepository.deleteProduct(productId);

            return appResponse.successResponse(success.productDeleted);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.productNotDeleted);
        }
    }
}
