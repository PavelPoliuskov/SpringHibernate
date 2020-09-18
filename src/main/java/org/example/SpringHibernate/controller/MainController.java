package org.example.SpringHibernate.controller;

import org.example.SpringHibernate.domain.Message;
import org.example.SpringHibernate.domain.User;
import org.example.SpringHibernate.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    Iterable<Message> messages;


    //setting upload path variable (application.properties)
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter, Model model
    ){
        //filter
        if(filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else{ //if filter is empty, return all messages
            messages = messageRepo.findAll();
        }

        //adding attributes
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    //@RequestParam means it is a parameter from the GET or POST request (Spring.io)

    //https://spring.io/guides/gs/uploading-files/
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Message message = new Message(text, tag, user);

        //Uploading a file (only if file is not null or empty)
        if(file != null && !file.getOriginalFilename().isEmpty()){
            //uploading to directory uploadPath
            File uploadDir = new File(uploadPath);

            //create the directory, if it doesn't exist
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            //universally unique identifier. Creating filename unifier
            String uuidFile = UUID.randomUUID().toString();
            //concatenating unifier and file name
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            //uploading the file
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            //attaching file to the message
            message.setFilename(resultFilename);
        }

        //saving messages
        messageRepo.save(message);

        //returning all messages
        messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }
}
