package com.interview.zerologics.demo.service;

import com.interview.zerologics.demo.entity.*;
import com.interview.zerologics.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextServiceImpl implements TextService{

    @Autowired
    UserService userService;

    @Autowired
    StarRepository starRepository;

    @Autowired
    TextRepository textRepository;

    @Autowired
    ShareRepository shareRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public void postText(Text text) {
        text.setReplyId(null);
        textRepository.save(text);
    }

    @Override
    public Optional<Text> findTextById(Long textId) {
        return textRepository.findById(textId);
    }

    @Override
    public void favoriteText(Long userId, Long textId) throws ChangeSetPersister.NotFoundException {
        Favorite favorite = Favorite.builder().user(userService.findUserById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new))
                .text(textRepository.findById(textId).orElseThrow(ChangeSetPersister.NotFoundException::new)).build();
        favoriteRepository.save(favorite);
    }

    @Override
    public void replyText(Text reply) throws ChangeSetPersister.NotFoundException {
        textRepository.findById(reply.getReplyId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
        textRepository.save(reply);
    }

    @Override
    public void starText(Long userId, Long textId) throws ChangeSetPersister.NotFoundException {
        Star star = Star.builder().user(userService.findUserById(userId).orElseThrow(ChangeSetPersister.NotFoundException::new))
                .text(textRepository.findById(textId).orElseThrow(ChangeSetPersister.NotFoundException::new)).build();
        starRepository.save(star);
    }

    @Override
    public void shareTextWithUser(Long fromUserId, Long toUserId, Long textId) throws ChangeSetPersister.NotFoundException {
        Share share = Share.builder().toUser(userService.findUserById(fromUserId).orElseThrow(ChangeSetPersister.NotFoundException::new))
                .fromUser(userService.findUserById(toUserId).orElseThrow(ChangeSetPersister.NotFoundException::new))
                .text(textRepository.findById(textId).orElseThrow(ChangeSetPersister.NotFoundException::new)).build();
        shareRepository.save(share);
    }
}
