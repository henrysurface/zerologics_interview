package com.interview.zerologics.demo.service;

import com.interview.zerologics.demo.entity.Text;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

public interface TextService {

    void postText(Text text);

    Optional<Text> findTextById(Long textId);

    void favoriteText(Long userId, Long textId) throws ChangeSetPersister.NotFoundException;

    void replyText(Text reply) throws ChangeSetPersister.NotFoundException;

    void starText(Long userId, Long textId) throws ChangeSetPersister.NotFoundException;

    void shareTextWithUser(Long fromUserId, Long toUserId, Long textId) throws ChangeSetPersister.NotFoundException;
}
