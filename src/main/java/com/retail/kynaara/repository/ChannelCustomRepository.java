package com.retail.kynaara.repository;

import com.retail.kynaara.model.Channel;
import com.retail.kynaara.response_model.CountResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChannelCustomRepository {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ChannelRepository channelRepository;

    //create operations
    public void addChannel(Channel channel){
        channelRepository.save(channel);
    }

    //read operations
    public List<Channel> getChannels(int start, int size, String q){
        if(start < 0){
            start = 0;
        }
        if(size < 0){
            size = 0;
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Channel> channelCriteriaQuery = criteriaBuilder.createQuery(Channel.class);

        Root<Channel> channelRoot = channelCriteriaQuery.from(Channel.class);

        Predicate predicate = criteriaBuilder.conjunction();

        if(q != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(channelRoot.get("channel_name"), "%" + q + "%"));
        }

        channelCriteriaQuery.select(channelRoot);

        channelCriteriaQuery.where(predicate);

        return entityManager.createQuery(channelCriteriaQuery).setFirstResult(start).setMaxResults(size).getResultList();
    }

    public List<Channel> getChannelByChannelId(int channelId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Channel> channelCriteriaQuery = criteriaBuilder.createQuery(Channel.class);

        Root<Channel> channelRoot = channelCriteriaQuery.from(Channel.class);

        Predicate predicateChannelId = criteriaBuilder.equal(channelRoot.get("channel_id"), channelId);

        channelCriteriaQuery.select(channelRoot);

        channelCriteriaQuery.where(predicateChannelId);

        return entityManager.createQuery(channelCriteriaQuery).getResultList();
    }

    //update operations
    @Transactional
    public void updateChannel(Channel channel){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Channel> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Channel.class);

        Root<Channel> channelRoot = criteriaUpdate.from(Channel.class);

        criteriaUpdate.set("channel_name", channel.getChannel_name());
        criteriaUpdate.set("channel_link", channel.getChannel_link());
        criteriaUpdate.set("channel_logo_link", channel.getChannel_logo_link());

        Predicate predicateChannelId = criteriaBuilder.equal(channelRoot.get("channel_id"), channel.getChannel_id());

        criteriaUpdate.where(predicateChannelId);

        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }

    //delete operations
    @Transactional
    public void deleteChannel(int channelId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Channel> criteriaDelete = criteriaBuilder.createCriteriaDelete(Channel.class);

        Root<Channel> channelRoot = criteriaDelete.from(Channel.class);

        Predicate predicateChannelId = criteriaBuilder.equal(channelRoot.get("channel_id"), channelId);

        criteriaDelete.where(predicateChannelId);

        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    //count operations
    public Long getCountChannels(String q){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> channelCriteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<Channel> channelRoot = channelCriteriaQuery.from(Channel.class);

        Predicate predicate = criteriaBuilder.conjunction();

        if(q != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(channelRoot.get("channel_name"), "%" + q + "%"));
        }

        channelCriteriaQuery.select(criteriaBuilder.count(channelRoot));

        channelCriteriaQuery.where(predicate);

        return entityManager.createQuery(channelCriteriaQuery).getSingleResult();
    }
}
