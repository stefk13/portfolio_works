package ca.gbc.post;

import ca.gbc.post.dto.PostRequest;
import ca.gbc.post.model.Post;
import ca.gbc.post.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PostApplicationTests extends AbstractContainerBaseTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    private PostRequest getPostRequest(){
        String postContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus luctus mauris ac dui feugiat ornare. Vestibulum dictum orci a dui consectetur finibus. Maecenas lacinia, quam vitae mattis interdum, velit eros suscipit erat, a gravida metus nisi et eros. Donec tincidunt a nulla id convallis. Vivamus sit amet blandit mi.";
        User user = null;
        return PostRequest.builder()
                .title("STUFF - TEST")
                .content(postContent)
                .userId("1")
                .build();
    }

    @Test
    void createProduct() throws Exception {

        PostRequest postRequest = getPostRequest();
        String postRequestJsonString = objectMapper.writeValueAsString(postRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postRequestJsonString))
                        .andExpect(MockMvcResultMatchers.status().isCreated());

        //Assertions
        Assertions.assertTrue(postRepository.findAll().size() > 0);

        Query query = new Query();
        query.addCriteria(Criteria.where("title").is("STUFF - TEST"));
        List<Post> post = mongoTemplate.find(query, Post.class);

        Assertions.assertTrue(post.size() > 0);

    }

}
