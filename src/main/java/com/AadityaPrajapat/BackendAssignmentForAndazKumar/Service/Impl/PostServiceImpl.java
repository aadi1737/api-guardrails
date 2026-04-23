package com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.Impl;

import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.LikeRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.LikeResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostRequestDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.DTOs.PostResponseDTO;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Enums.AuthorType;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Exception.BotCanNotLikeException;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Model.Post;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Repository.PostRepository;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.PostService;
import com.AadityaPrajapat.BackendAssignmentForAndazKumar.Service.RedisService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RedisService redisService;

    public PostResponseDTO addPost(PostRequestDTO dto){
        Post post = new Post();
        post.setAuthorId(dto.getAuthorId());
        post.setAuthorType(dto.getAuthorType());
        post.setContent(dto.getContent());
        post.setLikeCount(0);
        //post.setCreatedAt not required cause i have already added @CreatedTimeSTamp annotation on attribute
        Post saved ;
        try {
          saved = postRepository.save(post);
        }catch (Exception e){
            throw  new RuntimeException("Error While Saving Post in DB",e);
        }

        PostResponseDTO responseDTO = new PostResponseDTO();

        responseDTO.setId(saved.getId());
        responseDTO.setCreatedAt(saved.getCreatedAt());
        responseDTO.setContent(saved.getContent());
        responseDTO.setAuthorId(saved.getAuthorId());
        responseDTO.setAuthorType(saved.getAuthorType());

        return responseDTO;
    }

    @Override
    public LikeResponseDTO likeAPost(LikeRequestDTO dto, Long postId){

         if(dto.getAuthorType()== AuthorType.HUMAN){

             // Duplicate like check
             String likeKey = "like:user_" + dto.getAuthorId() + ":post_" + postId;
             if(redisService.isExists(likeKey)) {
                 throw new RuntimeException("You have already liked this post!");
             }
             redisService.set(likeKey, "1");

             String key = "post:"+postId+":virality_score";
             String s = redisService.get(key);
             int current= (s==null)?0:Integer.parseInt(s);
             redisService.set(key,String.valueOf(current+20));
        }else{
            throw new BotCanNotLikeException("A bot can not like a,Human Post(But you can comment!)");
        }
        Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException(("Post Not Found")));
        post.setLikeCount(post.getLikeCount()+1);
        postRepository.save(post);


        return LikeResponseDTO.builder()
                .likeCount(post.getLikeCount())
                .postId(postId)
                .message("Successfully Liked a Post")
                .build();
    }


}
