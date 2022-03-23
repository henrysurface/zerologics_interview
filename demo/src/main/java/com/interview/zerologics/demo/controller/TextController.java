package com.interview.zerologics.demo.controller;

import com.interview.zerologics.demo.entity.Favorite;
import com.interview.zerologics.demo.entity.Text;
import com.interview.zerologics.demo.service.TextService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/text")
public class TextController {

    private final TextService textService;

    public TextController(TextService textService){
        this.textService = textService;
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Text> postText(@RequestBody Text text) {

        try{
            textService.postText(text);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/find/{textId}", method = RequestMethod.GET)
    public ResponseEntity<Text> findTextById(@PathVariable(value = "textId") Long textId){
        Text text;
        try{
            text = textService.findTextById(textId).orElseThrow(ChangeSetPersister.NotFoundException::new);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(text, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/favorite/{textId}", method = RequestMethod.POST)
    public ResponseEntity<Favorite> favoriteText(@PathVariable(value = "userId") Long userId,
                                                 @PathVariable(value = "textId") Long textId){
        try{
            textService.favoriteText(userId, textId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/reply/", method = RequestMethod.POST)
    public ResponseEntity<Text>  replyText(@RequestBody Text reply){

        try{
            textService.replyText(reply);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{userId}/star/{textId}", method = RequestMethod.POST)
    public ResponseEntity<Text> starText(@PathVariable(value = "userId") Long userId,
                                          @PathVariable(value = "textId") Long textId) {

        try{
            textService.starText(userId, textId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/fromUser/{fromUserId}/share/{textId}/to/{toUserId}", method = RequestMethod.POST)
    public ResponseEntity<Text>  shareTextWithUser(@PathVariable(value = "fromUserId") Long fromUserId
            , @PathVariable(value = "toUserId")  Long toUserId,  @PathVariable(value = "textId") Long textId){

        try{
            textService.shareTextWithUser(fromUserId, toUserId, textId);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
