package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.Impl;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.CommentResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Enums.AuthorType;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Exception.CoolDownException;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Exception.TooManyBotCommentsException;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Comment;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Post;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository.CommentRepository;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository.PostRepository;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.CommentService;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RedisService redisService;

    @Override
    public CommentResponseDTO addComment(CommentRequestDTO dto, Long postId) {

        if("BOT".equalsIgnoreCase(String.valueOf(dto.getAuthorType()))) {
            String horizontalKey = "post:" + postId + ":bot_count";
            Long incremented = redisService.increment(horizontalKey);
            if (incremented > 100) {
                redisService.decreamentKey(horizontalKey);
                throw new TooManyBotCommentsException("Too Many bot Comments on this Post!");
            }


            Optional<Post> postJi = postRepository.findById(postId);
            String cooldownKey = "cooldown:bot_"+dto.getAuthorId()+":human_"+postJi.get().getAuthorId();
            if(redisService.isExists(cooldownKey)){
                throw new CoolDownException("You(bot) Can't Comment again in 10 minutes.");
            }else{
                redisService.setWithTTL(cooldownKey,"1",900);
            }
        }

        if(dto.getDepthLevel()>20)
            throw new RuntimeException("No more comment Allowed on this Post");


        Comment comment = new Comment();
        comment.setAuthorId(dto.getAuthorId());
        comment.setContent(dto.getContent());
        comment.setAuthorType(dto.getAuthorType());
        comment.setPostId(postId);
        comment.setDepthLevel(dto.getDepthLevel());

        Comment saved = commentRepository.save(comment);

        String s = redisService.get("post:" + postId + ":virality_score");
        int virality_score ;
        int current= (s==null)?0:Integer.parseInt(s) ;
        if (saved.getAuthorType()== AuthorType.BOT) {
                virality_score = current + 1;
            } else {
                virality_score = current + 50;
            }

        redisService.set("post:"+postId+":virality_score",String.valueOf(virality_score));
        return CommentResponseDTO.builder()
                .id(saved.getId())
                .createdAt(saved.getCreatedAt())
                .content(saved.getContent())
                .depthLevel(saved.getDepthLevel())
                .build();
    }
}
