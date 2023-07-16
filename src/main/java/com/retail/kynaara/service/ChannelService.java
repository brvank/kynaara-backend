package com.retail.kynaara.service;

import com.retail.kynaara.model.Channel;
import com.retail.kynaara.model.User;
import com.retail.kynaara.model.UserPermissions;
import com.retail.kynaara.repository.ChannelCustomRepository;
import com.retail.kynaara.repository.ProductCustomRepository;
import com.retail.kynaara.response_model.CountResponse;
import com.retail.kynaara.utility.AppMessages;
import com.retail.kynaara.utility.AppResponse;
import com.retail.kynaara.utility.AppUtil;
import com.retail.kynaara.utility.UserPermissionsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ChannelService {
    @Autowired
    ChannelCustomRepository channelCustomRepository;

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
    public ResponseEntity<Object> addChannel(Map<String, Object> channelMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            UserPermissions userPermissions = userPermissionsManager.getUserPermissions(user.getUser_user_level());
            if(!userPermissions.isAlterChannel()){
                return appResponse.failureResponse(error.permissionDenied);
            }

            Channel channel = new Channel();

            channel.setChannel_logo_link((String) channelMap.get(appUtilConstants.CHANNEL_LOGO_LINK));
            channel.setChannel_link((String) channelMap.get(appUtilConstants.CHANNEL_LINK));
            channel.setChannel_name((String) channelMap.get(appUtilConstants.CHANNEL_NAME));
            channel.setChannel_creation_date(LocalDateTime.now());
            channel.setChannel_creator_id(user.getUser_id());

            channelCustomRepository.addChannel(channel);

            return appResponse.successResponse(success.channelAdded);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.channelNotAdded);
        }
    }

    //read operations

    public ResponseEntity<Object> getChannels(int start, int size, String q, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{

            return appResponse.successResponse(
                    new CountResponse(
                            channelCustomRepository.getCountChannels(q),
                            channelCustomRepository.getChannels(start, size, q)
                    ), null);

        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    public ResponseEntity<Object> getChannelByChannelId(int channelId, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            List<Channel> channelList = channelCustomRepository.getChannelByChannelId(channelId);
            if(channelList.isEmpty()){
                return appResponse.failureResponse(channelList, error.channelDoesNotExist);
            }else{
                return appResponse.successResponse(channelList.get(0), null);
            }

        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.unknownErrorOccurred);
        }
    }

    //update operations
    public ResponseEntity<Object> updateChannel(Map<String, Object> channelMap, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            UserPermissions userPermissions = userPermissionsManager.getUserPermissions(user.getUser_user_level());
            if(!userPermissions.isAlterChannel()){
                return appResponse.failureResponse(error.permissionDenied);
            }

            Channel channelToUpdate = new Channel();

            channelToUpdate.setChannel_id((int) channelMap.get(appUtilConstants.CHANNEL_ID));
            channelToUpdate.setChannel_logo_link((String) channelMap.get(appUtilConstants.CHANNEL_LOGO_LINK));
            channelToUpdate.setChannel_link((String) channelMap.get(appUtilConstants.CHANNEL_LINK));
            channelToUpdate.setChannel_name((String) channelMap.get(appUtilConstants.CHANNEL_NAME));

            List<Channel> channelList = channelCustomRepository.getChannelByChannelId(channelToUpdate.getChannel_id());
            if(channelList.isEmpty()){
                return appResponse.failureResponse(error.channelDoesNotExist);
            }

            channelCustomRepository.updateChannel(channelToUpdate);

            return appResponse.successResponse(success.channelUpdated);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.channelNotUpdated);
        }
    }

    //delete operations
    public ResponseEntity<Object> deleteChannel(int channelId, User user){
        if(user == null){
            return appResponse.failureResponse(error.permissionDenied);
        }
        try{
            UserPermissions userPermissions = userPermissionsManager.getUserPermissions(user.getUser_user_level());
            if(!userPermissions.isAlterChannel()){
                return appResponse.failureResponse(error.permissionDenied);
            }

            List<Channel> channelList = channelCustomRepository.getChannelByChannelId(channelId);
            if(channelList.isEmpty()){
                return appResponse.failureResponse(error.channelDoesNotExist);
            }

            channelCustomRepository.deleteChannel(channelId);
            productCustomRepository.deleteProducts(channelId);

            return appResponse.successResponse(success.channelDeleted);
        }catch (Exception e){
            e.printStackTrace();
            return appResponse.failureResponse(error.channelNotDeleted);
        }
    }
}
