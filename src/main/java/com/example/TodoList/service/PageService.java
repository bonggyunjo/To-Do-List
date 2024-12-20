package com.example.TodoList.service;

import com.example.TodoList.entity.Board;
import com.example.TodoList.entity.User;
import com.example.TodoList.entity.record.Block;
import com.example.TodoList.entity.record.Page;
import com.example.TodoList.repository.PageRepository;
import com.example.TodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PageService {
    @Autowired
    PageRepository pageRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Page> findByUserId(String userId) {
        return pageRepository.findByUser_UserId(userId); // 수정한 메서드 사용
    }


    public Page createPage(String title, String content, String userId){
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Page page = Page.builder()
                .title(title)
                .content(content)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .user(user)
                .build();
        return pageRepository.save(page);
    }

    public Page updatePage(Long id, String title, String content) {
        Optional<Page> optionalPage = pageRepository.findById(id);
        if (optionalPage.isPresent()) {
            Page page = optionalPage.get();
            page.setTitle(title);
            page.setContent(content);
            return pageRepository.save(page);
        } else {
            throw new RuntimeException("페이지를 찾을 수 없습니다.");
        }
    }

    public Page moveToTrash(Long id) {
        Page page = pageRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("페이지를 찾을 수 없습니다."));
        page.setDeleted(true);
        pageRepository.save(page);
        return pageRepository.save(page);
    }

    public List<Page> getDeletedPage(){
        return pageRepository.findByDeleted(true);
    }

    public Page restorePage(Long id) {
        Optional<Page> optionalPage = pageRepository.findById(id);
        if (optionalPage.isPresent()) {
            Page page = optionalPage.get();
            if (page.isDeleted()) {
                page.setDeleted(false);
                return pageRepository.save(page);
            } else {
                throw new RuntimeException("페이지가 복원되었습니다.");
            }
        } else {
            throw new RuntimeException("페이지를 찾을 수 없습니다.");
        }
    }

    public void permanentDeletePage(Long id) {
        Optional<Page> optionalPage = pageRepository.findById(id);
        if (optionalPage.isPresent()) {
            pageRepository.delete(optionalPage.get());
        } else {
            throw new RuntimeException("블록 페이지를 찾을 수 없습니다.");
        }
    }
}
